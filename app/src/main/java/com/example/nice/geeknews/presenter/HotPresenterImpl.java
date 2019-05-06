package com.example.nice.geeknews.presenter;

import com.example.nice.geeknews.bean.HotBean;
import com.example.nice.geeknews.callback.HotCallBack;
import com.example.nice.geeknews.model.HotModel;
import com.example.nice.geeknews.view.HotView;

public class HotPresenterImpl implements HotPresenter, HotCallBack {

    private HotModel hotModel;
    private HotView hotView;

    public HotPresenterImpl(HotModel hotModel, HotView hotView) {
        this.hotModel = hotModel;
        this.hotView = hotView;
    }

    @Override
    public void getData() {
        if (hotModel!=null){
            hotModel.getData(this);
        }
    }

    @Override
    public void onSuccess(HotBean hotBean) {
        if (hotView!=null){
            hotView.onSuccess(hotBean);
        }
    }

    @Override
    public void onFailed(String str){
        if (hotView!=null){
            hotView.onFailed(str);
        }
    }
}
