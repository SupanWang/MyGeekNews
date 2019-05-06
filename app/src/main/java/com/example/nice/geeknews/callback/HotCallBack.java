package com.example.nice.geeknews.callback;

import com.example.nice.geeknews.bean.HotBean;

public interface HotCallBack {

    void onSuccess(HotBean hotBean);
    void onFailed(String str);
}
