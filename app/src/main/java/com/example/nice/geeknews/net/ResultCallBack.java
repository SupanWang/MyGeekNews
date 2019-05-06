package com.example.nice.geeknews.net;

public interface ResultCallBack<T> {

    void onSuccess(T bean);
    void onFailed(String str);
}

