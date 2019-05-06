package com.example.nice.geeknews.net;

import com.example.nice.geeknews.bean.SearchBean;
import com.example.nice.geeknews.bean.SelectionBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeChatService {

    /*
    http://api.tianapi.com/
1)wxnew(http://api.tianapi.com/wxnew/?key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=1)
参数：key=52b7ec3471ac3bec6846577e79f20e4c
          num(每页的数量)
          page(页码)
     */

    public String WeChatUrl = "http://api.tianapi.com/";

    @GET("wxnew/?")
    Observable<SelectionBean> getSelect(@Query("key")String key , @Query("num") int num , @Query("page") int page);


    /*
    微信精选列表(搜索)
1)wxnew(http://api.tianapi.com/wxnew/?key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=1)
参数：key=52b7ec3471ac3bec6846577e79f20e4c
          num(每页的数量)
          page(页码)
          word(关键字检索)
     */
    @GET("wxnew/?")
    Observable<SelectionBean> getSearch(@Query("key")String key , @Query("num") int num , @Query("page") int page , @Query("word") String word);

}
