package com.naceyeve.liandroid.recently;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.naceyeve.liandroid.bean.Ganhuo;

import java.util.List;

/**
 * Created by Administrator on 2017/1/5.
 */

public class RecentlyPageAdapter extends FragmentStatePagerAdapter {
    private List<Ganhuo> mData;
    private List<String> mDate;

    public RecentlyPageAdapter(FragmentManager fm, List<Ganhuo> data, List<String> date) {
        super(fm);
        this.mData = data;
        this.mDate = date;
    }

    @Override
    public Fragment getItem(int position) {
        RecentlyListFragment fragment = new RecentlyListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(RecentlyListFragment.DATE_STRING, mDate.get(position));
        bundle.putString(RecentlyListFragment.TITLE, mData.get(position).getTitle());
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        super.restoreState(state, loader);
    }

}
