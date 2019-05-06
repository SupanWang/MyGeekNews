package com.example.nice.geeknews.model;

import android.net.Uri;

import com.example.nice.geeknews.bean.DailyListBean;
import com.example.nice.geeknews.bean.DailyNewsBean;
import com.example.nice.geeknews.callback.DailyCallBack;
import com.example.nice.geeknews.net.ZhihuService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailyModelImpl implements DailyModel {
    @Override
    public void getData(final DailyCallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ZhihuService.sBaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ZhihuService zhihuService = retrofit.create(ZhihuService.class);

        Observable<DailyNewsBean> lastDailyNews = zhihuService.getLastDailyNews();

        lastDailyNews.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DailyNewsBean dailyNewsBean) {
                        if (dailyNewsBean!=null && dailyNewsBean.getStories()!=null && dailyNewsBean.getTop_stories()!=null){
                            callBack.onSuccess(dailyNewsBean);
                        }else {
                            callBack.onFailed("失败");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailed("失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
