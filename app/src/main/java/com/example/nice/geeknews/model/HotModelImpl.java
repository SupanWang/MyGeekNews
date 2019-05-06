package com.example.nice.geeknews.model;

import com.example.nice.geeknews.bean.HotBean;
import com.example.nice.geeknews.callback.HotCallBack;
import com.example.nice.geeknews.net.ZhihuService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HotModelImpl implements HotModel {
    @Override
    public void getData(final HotCallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ZhihuService.sBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ZhihuService zhihuService = retrofit.create(ZhihuService.class);

        Observable<HotBean> gethot = zhihuService.gethot();

        gethot.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotBean hotBean) {
                        if (hotBean!=null && hotBean.getRecent()!=null){
                            callBack.onSuccess(hotBean);
                        }else {
                            callBack.onFailed("热门数据请求失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailed("热门数据请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
