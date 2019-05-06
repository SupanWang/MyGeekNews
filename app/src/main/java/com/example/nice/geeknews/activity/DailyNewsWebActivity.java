package com.example.nice.geeknews.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.nice.geeknews.R;

public class DailyNewsWebActivity extends AppCompatActivity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_news_web);
        initView();
    }

    private void initView() {
        web = (WebView) findViewById(R.id.web);

        //        web = (WebView) findViewById(R.id.web);
//        web.getSettings().setJavaScriptEnabled(true);
//        web.setWebChromeClient(new WebChromeClient());
//        web.loadUrl(url);
    }
}
