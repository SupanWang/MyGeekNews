package com.example.nice.geeknews.presenter;

import com.example.nice.geeknews.bean.SpecialBean;
import com.example.nice.geeknews.callback.SpecialCallBack;
import com.example.nice.geeknews.model.SpecialModel;
import com.example.nice.geeknews.view.SpecialView;

public class SpecialPresenterImpl implements SpecialPresenter, SpecialCallBack {

    private SpecialModel specialModel;
    private SpecialView specialView;

    public SpecialPresenterImpl(SpecialModel specialModel, SpecialView specialView) {
        this.specialModel = specialModel;
        this.specialView = specialView;
    }

    @Override
    public void getData() {
        if (specialModel!=null){
            specialModel.getData(this);
        }
    }

    @Override
    public void onSuccess(SpecialBean specialBean) {
        if (specialView!=null){
            specialView.onSuccess(specialBean);
        }
    }

    @Override
    public void onFailed(String str) {
        if (specialView!=null){
            specialView.onFailed(str);
        }
    }
}
