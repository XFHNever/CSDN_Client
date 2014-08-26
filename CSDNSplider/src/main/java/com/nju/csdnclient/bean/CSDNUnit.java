package com.nju.csdnclient.bean;

/**
 * Created by never on 2014/8/25.
 */
public class CSDNUnit {
    private String title;
    private String link;
    private String date;
    private String imgPath;
    private String content;
    private int type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CSDNUnit{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", date='" + date + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                '}';
    }
}
