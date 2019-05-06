package com.example.nice.geeknews.presenter;

import com.example.nice.geeknews.bean.SelectionBean;
import com.example.nice.geeknews.callback.SelectCallBack;
import com.example.nice.geeknews.model.SelectModel;
import com.example.nice.geeknews.view.SelectView;

public class SelectPresenterImpl implements SelectPresenter, SelectCallBack {

    private SelectModel selectModel;
    private SelectView selectView;

    public SelectPresenterImpl(SelectModel selectModel, SelectView selectView) {
        this.selectModel = selectModel;
        this.selectView = selectView;
    }

    @Override
    public void getData(String key, int num, int page) {
        if (selectModel!=null){
            selectModel.getData(key , num,page,this);
        }
    }

    @Override
    public void getWordData(String key, int num, int page, String query) {
        if (selectModel!=null){
            selectModel.getWordData(key , num , page , query , this);
        }
    }

    @Override
    public void onSuccess(SelectionBean selectionBean) {
        if (selectView!=null){
            selectView.onSuccess(selectionBean);
        }
    }

    @Override
    public void onFailed(String str) {
        if (selectView!=null){
            selectView.onFailed(str);
        }
    }
}
