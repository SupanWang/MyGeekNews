package com.example.nice.geeknews.presenter;

public interface SelectPresenter {
    void getData(String key , int num , int page );

    void getWordData(String key, int num, int page, String query);
}
