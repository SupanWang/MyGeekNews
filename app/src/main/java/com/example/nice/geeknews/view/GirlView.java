package com.example.nice.geeknews.view;

import com.example.nice.geeknews.bean.GirlBean;

public interface GirlView {
    void onSuccess(GirlBean girlBean);
    void onFailed(String str);
}
