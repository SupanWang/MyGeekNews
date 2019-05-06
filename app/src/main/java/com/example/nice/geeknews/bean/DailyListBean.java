package com.example.nice.geeknews.bean;

import java.util.List;
import java.util.ListResourceBundle;

public class DailyListBean {

    private String date;
    private List<StoriesBean> stories;

    @Override
    public String toString() {
        return "DailyListBean{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public DailyListBean(String date, List<StoriesBean> stories) {

        this.date = date;
        this.stories = stories;
    }
}
