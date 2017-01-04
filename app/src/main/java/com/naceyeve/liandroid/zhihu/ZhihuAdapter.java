package com.naceyeve.liandroid.zhihu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.naceyeve.liandroid.bean.ZhihuItem;

/**
 * Created by Administrator on 2017/1/3.
 */

public class ZhihuAdapter extends RecyclerArrayAdapter<ZhihuItem> {

    public ZhihuAdapter(Context context) {
        super(context);
    }
    private ZhihuAdapter.OnMyItemClickListener mOnItemClickListener;

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ZhihuHolder(parent);
    }

    @Override
    public void OnBindViewHolder(final BaseViewHolder holder, final int position) {
        super.OnBindViewHolder(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(holder,position);
            }
        });


    }

    interface OnMyItemClickListener {
        void onItemClick(BaseViewHolder holder,int position);
    }

    public void setOnMyItemClickListener(ZhihuAdapter.OnMyItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
