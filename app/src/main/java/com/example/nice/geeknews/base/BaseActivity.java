package com.example.nice.geeknews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseActivity<V extends BaseMvpView, P extends BasePresenter> extends AppCompatActivity implements BaseMvpView {

//    MVP
//    V:视图展示+用户交互
//    P:V层和M层的桥梁+业务逻辑
//    M:负责数据处理:网络/文件/数据库...耗时操作
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());//设置ID寻找
        ButterKnife.bind(this);//ButterKnife绑定
        initView();
        mPresenter = initPresenter();//创建P层对象
        if (mPresenter != null) {
            mPresenter.bind((V) this);
        }

        initListener();
        initData();
    }

    protected abstract P initPresenter();//交个子类实现

    protected void initData() {
    }

    protected void initListener() {

    }

    protected void initView() {

    }

    protected abstract int getLayoutId();//交给子类


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestory();
        mPresenter = null;
    }
}
