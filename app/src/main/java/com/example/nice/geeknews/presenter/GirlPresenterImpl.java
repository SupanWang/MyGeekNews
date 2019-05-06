package com.example.nice.geeknews.presenter;

import com.example.nice.geeknews.bean.GirlBean;
import com.example.nice.geeknews.callback.GirlCallBack;
import com.example.nice.geeknews.model.GirlModel;
import com.example.nice.geeknews.view.GirlView;

public class GirlPresenterImpl implements GirlPresenter, GirlCallBack {

    private GirlModel girlModel;
    private GirlView girlView;

    public GirlPresenterImpl(GirlModel girlModel, GirlView girlView) {
        this.girlModel = girlModel;
        this.girlView = girlView;
    }

    @Override
    public void getData(int page) {
        if (girlModel!=null){
            girlModel.getData(page,this);
        }
    }

    @Override
    public void onSuccess(GirlBean girlBean) {
        if (girlView!=null){
            girlView.onSuccess(girlBean);
        }
    }

    @Override
    public void onFailed(String str) {
        if (girlView!=null){
            girlView.onFailed(str);
        }
    }
}
