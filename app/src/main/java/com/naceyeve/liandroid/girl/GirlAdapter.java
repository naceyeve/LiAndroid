package com.naceyeve.liandroid.girl;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.naceyeve.liandroid.R;
import com.naceyeve.liandroid.bean.Meizi;
import com.naceyeve.liandroid.widget.PinchImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/30.
 */

public class GirlAdapter extends PagerAdapter {
    private ArrayList<Meizi> mData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private View mCurrent;

    public GirlAdapter(Context context, ArrayList<Meizi> datas) {
        this.mContext = context;
        this.mData = datas;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String url = mData.get(position).getUrl();
        View view = mLayoutInflater.inflate(R.layout.item_girl_detail, container,false);
        PinchImageView imageView = (PinchImageView) view.findViewById(R.id.img);
        Glide.with(mContext)
                .load(url)
                .thumbnail(0.2f)
                .into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        mCurrent = (View) object;
    }

    public View getPremaryItem(){
        return mCurrent;
    }
}
