package com.example.nice.geeknews.base;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseMvpView> {

    protected V mMvpView;
    protected ArrayList<BaseModel> models = new ArrayList<>();

    public BasePresenter(){
        initModel();//定义一个M层方法
    }

    protected abstract void initModel();//交给子类实现

    public void bind(V view){//P与V绑定
        this.mMvpView = view;
    }

    public void onDestory(){
        //打断P与V的联系
        mMvpView = null;
        //掐断网络权限
        if (models.size() > 0 ){
            for (BaseModel model : models){
                model.onDestory();
            }
            models.clear();
        }
    }
}
