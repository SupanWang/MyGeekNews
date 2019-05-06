package com.example.nice.geeknews.base;

import io.reactivex.disposables.CompositeDisposable;

public class BaseModel {
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void onDestory(){
        //切断所有的Disposable对象
        compositeDisposable.clear();
    }
}
