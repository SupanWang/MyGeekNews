package com.example.nice.geeknews.model;

import com.example.nice.geeknews.bean.SearchBean;
import com.example.nice.geeknews.bean.SelectionBean;
import com.example.nice.geeknews.callback.SelectCallBack;
import com.example.nice.geeknews.net.WeChatService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelectModelImpl implements SelectModel {
    @Override
    public void getData(String key , int num , int page  , final SelectCallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeChatService.WeChatUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        WeChatService weChatService = retrofit.create(WeChatService.class);

        Observable<SelectionBean> select = weChatService.getSelect(key, num, page);

        select.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SelectionBean selectionBean) {
                        if (selectionBean!=null && selectionBean.getNewslist()!=null) {
                            callBack.onSuccess(selectionBean);
                        }else {
                            callBack.onFailed("精选数据请求失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailed("精选数据请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getWordData(String key, int num, int page, String query, final SelectCallBack selectCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeChatService.WeChatUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeChatService weChatService = retrofit.create(WeChatService.class);

        final Observable<SelectionBean> search = weChatService.getSearch(key, num, page, query);

        search.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SelectionBean searchBean) {
                        if (searchBean!=null && searchBean.getNewslist()!=null){
                            selectCallBack.onSuccess(searchBean);
                        }else {
                            selectCallBack.onFailed("搜索数据请求失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        selectCallBack.onFailed("搜索数据请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
