package com.example.nice.geeknews.bean;

import java.util.ArrayList;

public class NavigationBean {

    public ArrayList<String> title;
    public ArrayList <ArrayList<String>>text;
    public NavigationBean(){}

    public NavigationBean(ArrayList<String> title, ArrayList<ArrayList<String>> text) {
        this.title = title;
        this.text = text;
    }

    public ArrayList<String> getTitle() {
        return title;
    }

    public void setTitle(ArrayList<String> title) {
        this.title = title;
    }

    public ArrayList<ArrayList<String>> getText() {
        return text;
    }

    public void setText(ArrayList<ArrayList<String>> text) {
        this.text = text;
    }
}
