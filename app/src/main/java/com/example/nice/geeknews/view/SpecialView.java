package com.example.nice.geeknews.view;

import com.example.nice.geeknews.bean.SpecialBean;

public interface SpecialView {
    void onSuccess(SpecialBean specialBean);
    void onFailed(String str);
}
