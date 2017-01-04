package com.naceyeve.liandroid.zhihu;

import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.naceyeve.liandroid.R;
import com.naceyeve.liandroid.bean.ZhihuItem;
import com.naceyeve.liandroid.widget.CircleImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/1/3.
 */

public class ZhihuHolder extends BaseViewHolder<ZhihuItem> {

    private final CircleImageView mImage;
    private final TextView mText;

    public ZhihuHolder(ViewGroup parent) {
        super(parent, R.layout.item_zhihu);
        mImage = $(R.id.item_image_id);
        mText = $(R.id.item_text_id);
    }

    @Override
    public void setData(ZhihuItem data) {
        super.setData(data);
        List<String> images = data.getImages();
        Glide.with(getContext())
                .load(images.get(0))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImage);
        mText.setText(data.getTitle());
    }
}
