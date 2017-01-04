package com.naceyeve.liandroid.zhihu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewStub;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.naceyeve.liandroid.R;
import com.naceyeve.liandroid.base.BaseFragment;
import com.naceyeve.liandroid.bean.Zhihu;
import com.naceyeve.liandroid.bean.ZhihuItem;
import com.naceyeve.liandroid.zhihu.zhihuDescriber.ZhihuDescriberActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/3.
 */

public class ZhihuFragment extends BaseFragment implements ZhihuContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.network_error_layout)
    ViewStub mNetworkErrorLayout;
    @Bind(R.id.girls_recycler_view)
    EasyRecyclerView mZhihuRecyclerView;

    private ArrayList<ZhihuItem> data;
    private ZhihuAdapter mAdapter;
    private ZhihuPresenter mZhihuPresenter;
    private String mCurrentLoadData;
    private View errorView;

    public static ZhihuFragment getInstance() {
        ZhihuFragment fragment = new ZhihuFragment();
        return fragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        mZhihuPresenter = new ZhihuPresenter(this);
        initRecyclerView();
        mZhihuPresenter.getLastNews();
    }

    private void initRecyclerView() {
        data = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mZhihuRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new ZhihuAdapter(getContext());
        mZhihuRecyclerView.setAdapterWithProgress(mAdapter);

        mAdapter.setMore(R.layout.load_more_layout, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
//                if(data.size() % 20 == 0){
                    mZhihuPresenter.getTheDaily(mCurrentLoadData,false);
//                }
            }
        });
        mAdapter.setNoMore(R.layout.no_more_layout);
        mAdapter.setError(R.layout.error_layout);

        mAdapter.setOnMyItemClickListener(new ZhihuAdapter.OnMyItemClickListener() {
            @Override
            public void onItemClick(BaseViewHolder holder, int position) {
                ZhihuItem item = data.get(position);
                toDescribeActivity(holder,item);
            }
        });
        mZhihuRecyclerView.setRefreshListener(this);
    }

    private void toDescribeActivity(BaseViewHolder holder, ZhihuItem item) {
        Intent intent = new Intent(getContext(),ZhihuDescriberActivity.class);
        intent.putExtra("id",item.getId());
        intent.putExtra("title",item.getTitle());
        intent.putExtra("image",item.getImages().get(0));
        getContext().startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
    

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void refresh(Zhihu datas) {
        List<ZhihuItem> stories = datas.getStories();
        mCurrentLoadData = datas.getDate();
        data.clear();
        data.addAll(stories);
        mAdapter.clear();
        mAdapter.addAll(stories);
    }

    @Override
    public void load(Zhihu datas) {
        List<ZhihuItem> stories = datas.getStories();
        mCurrentLoadData = datas.getDate();
        data.addAll(stories);
        mAdapter.addAll(stories);
    }

    @Override
    public void showNormal() {
        if(errorView != null){
            errorView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hidProgressDialog() {

    }

    @Override
    public void showError(String error) {
        mZhihuRecyclerView.showError();
        if(errorView != null){
            errorView.setVisibility(View.GONE);
            return;
        }
        errorView = mNetworkErrorLayout.inflate();

    }

    @Override
    public void onRefresh() {
        mZhihuPresenter.getTheDaily(mCurrentLoadData,true);
    }
}
