package com.example.nice.geeknews.view;

import com.example.nice.geeknews.bean.DailyNewsBean;
import com.example.nice.geeknews.bean.StoriesBean;

public interface DailyView {

    void onSuccess(DailyNewsBean dailyNewsBean);
    void onFailed(String str);

}
