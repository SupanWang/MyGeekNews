package com.example.nice.geeknews.model;

import com.example.nice.geeknews.callback.SelectCallBack;

public interface SelectModel {

    void getData(String key , int num , int page , SelectCallBack callBack);

    void getWordData(String key, int num, int page, String query,SelectCallBack selectCallBack);
}
