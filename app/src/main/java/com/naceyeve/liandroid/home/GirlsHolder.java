package com.naceyeve.liandroid.home;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.naceyeve.liandroid.R;
import com.naceyeve.liandroid.bean.Meizi;

/**
 * Created by Administrator on 2016/12/29.
 */
public class GirlsHolder extends BaseViewHolder<Meizi> {
    private ImageView mImageView;
    public GirlsHolder(ViewGroup parent) {
        super(parent, R.layout.item_girl);
        mImageView = (ImageView) $(R.id.girl_img);
    }

    @Override
    public void setData(Meizi data) {
        super.setData(data);
        Glide.with(getContext())
                .load(data.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImageView);
    }
}
