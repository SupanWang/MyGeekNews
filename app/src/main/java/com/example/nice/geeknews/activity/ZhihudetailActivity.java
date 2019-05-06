package com.example.nice.geeknews.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nice.geeknews.R;
import com.tencent.smtt.sdk.WebView;

public class ZhihudetailActivity extends AppCompatActivity {

    private ImageView detail_bar_image;
    private TextView detail_bar_copyright;
    private Toolbar view_toolbar;
    private CollapsingToolbarLayout clp_toolbar;
    private AppBarLayout app_bar;
    private WebView view_main;
    private NestedScrollView nsv_scroller;
    private TextView tv_detail_bottom_like;
    private TextView tv_detail_bottom_comment;
    private TextView tv_detail_bottom_share;
    private FrameLayout ll_detail_bottom;
    private FloatingActionButton fab_like;

    int id = 0;
    int allNum = 0;
    int shortNum = 0;
    int longNum = 0;
    String shareUrl;
    String imgUrl;
    boolean isBottomShow = true;
    boolean isImageShow = false;
    boolean isTransitionEnd = false;
    boolean isNotTransition = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihudetail);
        initView();
    }

    private void initView() {
//        detail_bar_image = (ImageView) findViewById(R.id.detail_bar_image);
//        detail_bar_copyright = (TextView) findViewById(R.id.detail_bar_copyright);
//        view_toolbar = (Toolbar) findViewById(R.id.view_toolbar);
//        clp_toolbar = (CollapsingToolbarLayout) findViewById(R.id.clp_toolbar);
//        app_bar = (AppBarLayout) findViewById(R.id.app_bar);
//        view_main = (WebView) findViewById(R.id.view_main);
//        nsv_scroller = (NestedScrollView) findViewById(R.id.nsv_scroller);
//        tv_detail_bottom_like = (TextView) findViewById(R.id.tv_detail_bottom_like);
//        tv_detail_bottom_comment = (TextView) findViewById(R.id.tv_detail_bottom_comment);
//        tv_detail_bottom_share = (TextView) findViewById(R.id.tv_detail_bottom_share);
//        ll_detail_bottom = (FrameLayout) findViewById(R.id.ll_detail_bottom);
//        fab_like = (FloatingActionButton) findViewById(R.id.fab_like);
//
//        fab_like.setOnClickListener(this);
    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.fab_like:
//
//                break;
//        }
//    }
}
