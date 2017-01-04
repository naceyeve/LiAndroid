package com.naceyeve.liandroid.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.naceyeve.liandroid.bean.Meizi;

/**
 * Created by Administrator on 2016/12/29.
 */

public class GirlsAdapter extends RecyclerArrayAdapter<Meizi> {
    public GirlsAdapter(Context context) {
        super(context);
    }

    private OnMyItemClickListener mOnItemClickListener;

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new GirlsHolder(parent);
    }

    @Override
    public void OnBindViewHolder(final BaseViewHolder holder, final int position) {
        super.OnBindViewHolder(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(holder,position);
                }
            }
        });
    }



    interface OnMyItemClickListener {
        void onItemClick(BaseViewHolder holder,int position);
    }

    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
