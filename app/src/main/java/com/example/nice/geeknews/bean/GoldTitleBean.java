package com.example.nice.geeknews.bean;

import java.io.Serializable;

/**
 * @author ws
 *         Created by asus on 2019/4/18.
 */

public class GoldTitleBean implements Serializable{
    public String title;
    public boolean isChecked;

    public GoldTitleBean(String title, boolean isChecked) {
        this.title = title;
        this.isChecked = isChecked;
    }
}
