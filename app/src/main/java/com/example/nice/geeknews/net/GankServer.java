package com.example.nice.geeknews.net;

import com.example.nice.geeknews.bean.GankItemBean;
import com.example.nice.geeknews.bean.GirlBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GankServer {

    public String mUrl = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/";

    @GET("10/1")
    Observable<GankItemBean> getData();


    @GET("10/{page}")
    Observable<GirlBean> getGirl(@Path("page")int page);
}
