package com.naceyeve.liandroid.recently;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.naceyeve.liandroid.R;
import com.naceyeve.liandroid.base.BaseFragment;
import com.naceyeve.liandroid.bean.Ganhuo;
import com.naceyeve.liandroid.bean.RecentlyWrapper;
import com.naceyeve.liandroid.widget.StatusViewLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/5.
 */

public class RecentlyFragment extends BaseFragment implements RecentlyContract.View {

    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.status_view)
    StatusViewLayout mStatusView;

    private List<Ganhuo> mDatalist;
    private List<String> mDate;
    private RecentlyPresenter mPresenter;
    private RecentlyPageAdapter mAdapter;

    public static RecentlyFragment getInstence() {
        RecentlyFragment fragment = new RecentlyFragment();
        return fragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        mPresenter = new RecentlyPresenter(this);
        initData();
    }

    private void initData() {
        mPresenter.getRecentlyData();
        mStatusView.showLoading();
        mStatusView.setOnRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getRecentlyData();
            }
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recently;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showProgressDialog() {
        mStatusView.showLoading();
    }

    @Override
    public void hidProgressDialog() {
        mStatusView.showContent();
    }

    @Override
    public void showError(String error) {
        mStatusView.showError(error);
    }

    @Override
    public void setRecentlyData(RecentlyWrapper data) {
        mDate = data.titlelist;
        mDatalist = data.datalist;
        mAdapter = new RecentlyPageAdapter(getChildFragmentManager(), mDatalist, mDate);
        mViewpager.setAdapter(mAdapter);
        for(int i = 0 ; i < mDate.size(); i++){
            mTabLayout.addTab(mTabLayout.newTab());
        }
        mTabLayout.setupWithViewPager(mViewpager);
    }
}
