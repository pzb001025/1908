package com.example.lenovo.text1.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class RecentBean {
    /**
     * news_id : 9724100
     * url : http://news-at.zhihu.com/api/2/news/9724100
     * thumbnail : https://pic2.zhimg.com/v2-f6ce92ee9fc1c9e6e69459cd2493958d.jpg
     * title : 瞎扯 · 如何正确地吐槽
     */

    private int news_id;
    private String url;
    private String thumbnail;
    @Id
    private String title;

    @Generated(hash = 985766309)
    public RecentBean(int news_id, String url, String thumbnail, String title) {
        this.news_id = news_id;
        this.url = url;
        this.thumbnail = thumbnail;
        this.title = title;
    }

    @Generated(hash = 1697461393)
    public RecentBean() {
    }

    public int getNews_id() {
        return this.news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}