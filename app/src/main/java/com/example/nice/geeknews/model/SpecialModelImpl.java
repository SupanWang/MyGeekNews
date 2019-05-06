package com.example.nice.geeknews.model;

import com.example.nice.geeknews.bean.SpecialBean;
import com.example.nice.geeknews.callback.SpecialCallBack;
import com.example.nice.geeknews.net.ZhihuService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpecialModelImpl implements SpecialModel {
    @Override
    public void getData(final SpecialCallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ZhihuService.sBaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ZhihuService zhihuService = retrofit.create(ZhihuService.class);

        final Observable<SpecialBean> special = zhihuService.getSpecial();

        special.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SpecialBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SpecialBean specialBean) {
                        if (specialBean!=null && specialBean.getData()!=null){
                            callBack.onSuccess(specialBean);
                        }else {
                            callBack.onFailed("专栏数据请求失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailed("专栏数据请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
