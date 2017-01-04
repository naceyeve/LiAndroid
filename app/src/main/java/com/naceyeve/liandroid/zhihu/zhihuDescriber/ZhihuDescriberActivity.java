package com.naceyeve.liandroid.zhihu.zhihuDescriber;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.naceyeve.liandroid.R;
import com.naceyeve.liandroid.base.AppActivity;
import com.naceyeve.liandroid.base.BaseFragment;
import com.naceyeve.liandroid.bean.ZhihuDescriber;
import com.naceyeve.liandroid.util.WebUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/3.
 */

public class ZhihuDescriberActivity extends AppActivity implements ZhihuDescriberContract.View{

    @Bind(R.id.backdrop)
    ImageView mBackdrop;
    @Bind(R.id.describer_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.webview)
    WebView mWebview;
    private int mId;
    private ZhihuDescriberPresenter mPresenter;

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_zhihudescriber;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter = new ZhihuDescriberPresenter(this);
        initData();
        mPresenter.getHtml(mId);
    }

    private void initData() {
        Intent intent = getIntent();
        mId = intent.getIntExtra("id",9118136);
        String title = intent.getStringExtra("title");
        String image = intent.getStringExtra("image");
        mToolbar.setTitle(title);
        mToolbar.setNavigationIcon(R.mipmap.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishWithAnim();
            }
        });
        Glide.with(this)
                .load(image)
                .into(mBackdrop);
        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webSettings.setBuiltInZoomControls(true);//设置使支持缩放
//        webSettings.setUseWideViewPort(true);  //将图片调整到适合webview的大小
//        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
//        webSettings.setLoadsImagesAutomatically(true);  //支持自动加载图片
//        webSettings.setAllowFileAccess(true);//设置可以访问文件
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(true);
        //webSettings.setUseWideViewPort(true);造成文字太小
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAppCachePath(getCacheDir().getAbsolutePath() + "/webViewCache");
        webSettings.setAppCacheEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebview.setWebViewClient(new WebViewClient());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void showHtml(ZhihuDescriber zhihu) {
        String html = zhihu.getBody();
        List<String> css = zhihu.getCss();
        boolean isEmpty = TextUtils.isEmpty(html);
        if(isEmpty){
            mWebview.loadUrl(zhihu.getShare_url());
        }else {
            String data = WebUtil.buildHtmlWithCss(html, css, false);
            mWebview.loadDataWithBaseURL(WebUtil.BASE_URL, data, WebUtil.MIME_TYPE, WebUtil.ENCODING, WebUtil.FAIL_URL);
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

    }
    private void finishWithAnim() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();//配合完成共享元素过渡动画
        }else {
            finish();
        }
        overridePendingTransition(R.anim.slide_left_in,R.anim.slide_right_out);
    }
}
