package com.example.nice.geeknews.callback;

import com.example.nice.geeknews.bean.SpecialBean;

public interface SpecialCallBack {
    void onSuccess(SpecialBean specialBean);
    void onFailed(String str);
}
