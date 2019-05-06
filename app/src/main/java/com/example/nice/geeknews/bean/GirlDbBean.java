package com.example.nice.geeknews.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class GirlDbBean {

    @Id
    private Long lid;
    private String url;
    @Generated(hash = 3083159)
    public GirlDbBean(Long lid, String url) {
        this.lid = lid;
        this.url = url;
    }
    @Generated(hash = 955951171)
    public GirlDbBean() {
    }
    public Long getLid() {
        return this.lid;
    }
    public void setLid(Long lid) {
        this.lid = lid;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
