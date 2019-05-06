package com.example.nice.geeknews.net;

import android.util.Log;

import com.example.nice.geeknews.util.ToastUtil;

import io.reactivex.Observer;


public abstract class BaseObserver<T> implements Observer<T> {
    private final String TAG = getClass().getName();


    public void onError(Throwable e) {
        Log.d(TAG, "onError: "+e.toString());
        ToastUtil.showShort("数据加载失败");
    }

    public void onComplete() {
        Log.d(TAG, "onComplete: ");
    }
}
