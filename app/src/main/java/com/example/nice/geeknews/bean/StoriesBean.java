package com.example.nice.geeknews.bean;

import java.util.List;

public class StoriesBean {
    /**
     * title : 用游戏「实地」图解：巴黎圣母院为什么会着火？
     * ga_prefix : 041709
     * images : ["https://pic2.zhimg.com/v2-9cdc4b2a57325d83f171a5675485e655.jpg"]
     * multipic : true
     * type : 0
     * id : 9710280
     */

    private String title;
    private String ga_prefix;
    private boolean multipic;
    private int type;
    private int id;
    private List<String> images;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public boolean isMultipic() {
        return multipic;
    }

    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
