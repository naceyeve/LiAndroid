package com.naceyeve.liandroid.recently;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.naceyeve.liandroid.R;
import com.naceyeve.liandroid.base.BaseFragment;
import com.naceyeve.liandroid.util.LogUtil;

/**
 * Created by Administrator on 2017/1/5.
 */

public class RecentlyListFragment extends BaseFragment {

    public static final String DATE_STRING = "dateString";
    public static final String TITLE = "fragment_index";
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        try {

            String date = getArguments().get(DATE_STRING).toString().replace("-", "/");
            String title = getArguments().get(TITLE).toString();
            TextView text = (TextView) view.findViewById(R.id.showText);
            text.setText(title);
        }catch (Exception e){
            LogUtil.e(e.getMessage());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recentlylist;
    }
}
