package com.example.nice.geeknews.callback;

import com.example.nice.geeknews.bean.SelectionBean;

public interface SelectCallBack {

    void onSuccess(SelectionBean selectionBean);
    void onFailed(String str);
}
