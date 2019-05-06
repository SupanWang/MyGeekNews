package com.example.nice.geeknews.net;

import com.example.nice.geeknews.bean.V2exTabsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface V2exService {

    public String Url = "https://www.v2ex.com/";

    @GET("/?")
    Observable<V2exTabsBean> getV2exTab(@Query("tab") String tab);
}
