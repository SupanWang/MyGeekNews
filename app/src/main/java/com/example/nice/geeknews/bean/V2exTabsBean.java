package com.example.nice.geeknews.bean;

public class V2exTabsBean {

    private  String link;
    private  String tab;

    public V2exTabsBean(String link, String tab) {
        this.link = link;
        this.tab = tab;
    }

    public String getLink() {

        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }
}
