package com.example.nice.geeknews.view;

import com.example.nice.geeknews.bean.SelectionBean;

public interface SelectView {
    void onSuccess(SelectionBean selectionBean);
    void onFailed(String str);
}
