package com.example.henryzheng.qiushibaike.C.info.news;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.henryzheng.qiushibaike.C.info.base.BaseInfoActivity;
import com.example.henryzheng.qiushibaike.M.Bean.news.Data;
import com.example.henryzheng.qiushibaike.R;

import butterknife.BindView;

public class NewsInfoActivity extends BaseInfoActivity {
    @BindView(R.id.webView0)
    public WebView webview0;
    String uri="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            handlerData();
            webViewToShowInfo();
    }

    private void handlerData() {
        Intent intent=getIntent();
      Data data= (Data) intent.getSerializableExtra("data");
        uri= data.getWebLink();
    }

    private void webViewToShowInfo() {
        WebSettings webSettings = webview0.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        webview0.loadUrl(uri);
        //设置Web视图
        webview0.setWebViewClient(new WebViewClient());
    }

    @Override
    public int getContentViewById() {
        return R.layout.activity_news_info;
    }
}
