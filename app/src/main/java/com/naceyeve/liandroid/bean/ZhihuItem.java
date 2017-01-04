package com.naceyeve.liandroid.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/3.
 */

public class ZhihuItem {

    /**
     * images : ["http://pic3.zhimg.com/88dfefc31c598621126947d6aaed416a.jpg"]
     * type : 0
     * id : 9115115
     * ga_prefix : 010311
     * title : 孩子自尊心最脆弱的部分，别让最爱他们的家人伤害
     */

    private int type;
    private int id;
    private String ga_prefix;
    private String title;
    private List<String> images;

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

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
