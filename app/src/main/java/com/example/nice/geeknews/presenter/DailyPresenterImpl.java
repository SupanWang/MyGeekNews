package com.example.nice.geeknews.presenter;

import com.example.nice.geeknews.bean.DailyNewsBean;
import com.example.nice.geeknews.callback.DailyCallBack;
import com.example.nice.geeknews.model.DailyModel;
import com.example.nice.geeknews.view.DailyView;

public class DailyPresenterImpl implements DailyPresenter, DailyCallBack {

    private DailyModel dailyModel;
    private DailyView dailyView;

    public DailyPresenterImpl(DailyModel dailyModel, DailyView dailyView) {
        this.dailyModel = dailyModel;
        this.dailyView = dailyView;
    }

    @Override
    public void getData() {
        if (dailyModel!=null){
            dailyModel.getData(this);
        }
    }

    @Override
    public void onSuccess(DailyNewsBean dailyNewsBean) {
        if (dailyView!=null){
            dailyView.onSuccess(dailyNewsBean);
        }
    }

    @Override
    public void onFailed(String str) {
        if (dailyView!=null){
            dailyView.onFailed(str);
        }
    }
}
