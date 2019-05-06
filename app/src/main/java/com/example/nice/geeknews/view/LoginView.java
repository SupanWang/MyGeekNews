package com.example.nice.geeknews.view;


import com.example.nice.geeknews.base.BaseMvpView;



public interface LoginView extends BaseMvpView {
    void setData(String data);

    String getUserName();
    String getPsd();

    void showToast(String msg);
}
