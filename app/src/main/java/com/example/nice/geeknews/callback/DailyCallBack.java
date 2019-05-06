package com.example.nice.geeknews.callback;

import com.example.nice.geeknews.bean.DailyNewsBean;
import com.example.nice.geeknews.bean.StoriesBean;

public interface DailyCallBack {

    void onSuccess(DailyNewsBean dailyNewsBean);
    void onFailed(String str);
}
