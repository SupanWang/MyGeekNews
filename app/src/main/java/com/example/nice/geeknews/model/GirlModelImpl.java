package com.example.nice.geeknews.model;


import android.util.Log;

import com.example.nice.geeknews.bean.GirlBean;
import com.example.nice.geeknews.callback.GirlCallBack;
import com.example.nice.geeknews.net.GankServer;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GirlModelImpl implements GirlModel {

    private static final String TAG = "GirlModelImpl";
    @Override
    public void getData(int page, final GirlCallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GankServer.mUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GankServer gankServer = retrofit.create(GankServer.class);

        Observable<GirlBean> girl = gankServer.getGirl(page);

        girl.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GirlBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GirlBean girlBean) {
                        callBack.onSuccess(girlBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailed("妹子请求失败"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
