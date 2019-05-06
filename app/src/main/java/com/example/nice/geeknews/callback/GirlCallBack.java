package com.example.nice.geeknews.callback;

import com.example.nice.geeknews.bean.GirlBean;

public interface GirlCallBack {

    void onSuccess(GirlBean girlBean);
    void onFailed(String str);
}
