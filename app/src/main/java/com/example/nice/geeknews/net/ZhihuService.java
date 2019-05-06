package com.example.nice.geeknews.net;

import com.example.nice.geeknews.bean.DailyListBean;
import com.example.nice.geeknews.bean.DailyNewsBean;
import com.example.nice.geeknews.bean.HotBean;
import com.example.nice.geeknews.bean.SpecialBean;
import com.example.nice.geeknews.bean.StoriesBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author ws
 *         Created by asus on 2019/4/17.
 */

public interface ZhihuService {
    String sBaseUrl = "http://news-at.zhihu.com/api/4/";

    @GET("news/latest")
    Observable<DailyNewsBean> getLastDailyNews();


    @GET("news/hot")
    Observable<HotBean> gethot();

    @GET("sections")
    Observable<SpecialBean> getSpecial();

//    @GET("news/before/{date}")
//    Observable<DailyListBean> getDateDailyNews(@Path("date") String date);
}
