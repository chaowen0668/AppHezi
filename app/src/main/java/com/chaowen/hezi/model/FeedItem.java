package com.chaowen.hezi.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by diallo on 21/03/14.
 */
public class FeedItem extends BmobObject {
    private String title;
    private String photo;
    private String likenum;
    private String url;
    public FeedItem() {
    }



    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public String getLikenum() {
        return likenum;
    }

    public void setLikenum(String likenum) {
        this.likenum = likenum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
