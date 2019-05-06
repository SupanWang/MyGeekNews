package com.example.nice.geeknews.view;

import com.example.nice.geeknews.bean.HotBean;

public interface HotView {

    void onSuccess(HotBean hotBean);
    void onFailed(String str);
}
