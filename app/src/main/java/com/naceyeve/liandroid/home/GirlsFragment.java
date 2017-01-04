package com.naceyeve.liandroid.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewStub;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.naceyeve.liandroid.R;
import com.naceyeve.liandroid.base.BaseFragment;
import com.naceyeve.liandroid.bean.Meizi;
import com.naceyeve.liandroid.girl.GirlActivity;
import com.naceyeve.liandroid.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/12/29.
 */

public class GirlsFragment extends BaseFragment implements GirlsContract.View, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "GirlsFragment";
    @Bind(R.id.network_error_layout)
    ViewStub mNetworkErrorLayout;
    @Bind(R.id.girls_recycler_view)
    EasyRecyclerView mGirlsRecyclerView;

    private View errorView;

    private ArrayList<Meizi> data;
    private GirlsAdapter mAdapter;
    private int page = 1; //页数
    private int size = 20;//数目
    private GirlsPresenter mPresenter;

    public static GirlsFragment getInstance() {
        GirlsFragment fragment = new GirlsFragment();
        return fragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        mPresenter = new GirlsPresenter(this);
        initRecyclerView();
        mPresenter.getGirls(page,size,true);
    }

    private void initRecyclerView() {
        data = new ArrayList<>();
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mGirlsRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new GirlsAdapter(getContext());
        mGirlsRecyclerView.setAdapterWithProgress(mAdapter);

        mAdapter.setMore(R.layout.load_more_layout, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if(data.size() % 20 == 0){
                    LogUtil.d(TAG,"loadMore---");
                    page++;
                    // TODO: 2016/12/29 访问网络请求
                    mPresenter.getGirls(page,size,false);
                }
            }
        });
        mAdapter.setNoMore(R.layout.no_more_layout);
        mAdapter.setError(R.layout.error_layout);

        mAdapter.setOnMyItemClickListener(new GirlsAdapter.OnMyItemClickListener() {
            @Override
            public void onItemClick(BaseViewHolder holder, int position) {
                // TODO: 2016/12/29 跳转GirlsActivity
                Intent intent = new Intent(mActivity, GirlActivity.class);
                intent.putParcelableArrayListExtra("girls",data);
                intent.putExtra("current",position);
                ActivityOptionsCompat optionsCompat;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),holder.itemView,holder.itemView.getTransitionName());
                }else {

                     optionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(holder.itemView, holder.itemView.getWidth() / 2, holder.itemView.getHeight() / 2, 0, 0);
                }
                startActivity(intent,optionsCompat.toBundle());

            }
        });

        mGirlsRecyclerView.setRefreshListener(this);

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
    public void onRefresh() {
        page = 1;
        mPresenter.getGirls(page,size,true);
    }

    @Override
    public void refresh(List<Meizi> datas) {
        data.clear();
        data.addAll(datas);
        mAdapter.clear();
        mAdapter.addAll(datas);
    }

    @Override
    public void load(List<Meizi> datas) {
        data.addAll(datas);
        mAdapter.addAll(datas);

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
        mGirlsRecyclerView.showError();
            if(errorView != null){
                errorView.setVisibility(View.GONE);
                return;
            }
        errorView = mNetworkErrorLayout.inflate();
    }
}
