package com.example.nice.geeknews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;

import com.example.nice.geeknews.R;
import com.example.nice.geeknews.adapter.RlvShowAdapter;
import com.example.nice.geeknews.base.Constants;
import com.example.nice.geeknews.bean.GoldTitleBean;
import com.example.nice.geeknews.widget.SimpleItemTouchCallBack;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowGoldActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private RecyclerView rv;
    private ArrayList<GoldTitleBean> mTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_gold);
        initView();
    }

    private void initView() {
        toolBar = (Toolbar) findViewById(R.id.toolBar);
        rv = (RecyclerView) findViewById(R.id.rv);

        toolBar.setTitle("首页特别展示");
        setSupportActionBar(toolBar);

        //返回按钮的监听
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        mTitles = (ArrayList<GoldTitleBean>) getIntent().getSerializableExtra(Constants.DATA);
        rv.setLayoutManager(new LinearLayoutManager(this));
        RlvShowAdapter adapter = new RlvShowAdapter(mTitles);
        rv.setAdapter(adapter);

        //拖拽移动，左滑删除
        SimpleItemTouchCallBack simpleItemTouchCallBack = new SimpleItemTouchCallBack(adapter);
        simpleItemTouchCallBack.setSwipeEnable(false);
        ItemTouchHelper helper = new ItemTouchHelper(simpleItemTouchCallBack);
        helper.attachToRecyclerView(rv);

    }

//    2.重写onOptionsItemSelected方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            Intent intent = new Intent();
            intent.putExtra(Constants.DATA , mTitles);
            setResult(RESULT_OK , intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
