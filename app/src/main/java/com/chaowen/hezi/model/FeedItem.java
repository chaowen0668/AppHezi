package com.chaowen.hezi.model;

/**
 * Created by diallo on 21/03/14.
 */
public class FeedItem {
    private int mIndex;
    private String photo;
    public FeedItem(int index,String photo) {
        this.mIndex = index;
        this.photo = photo;
    }

    public int getmIndex() {
        return mIndex;
    }

    public void setmIndex(int mIndex) {
        this.mIndex = mIndex;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Card #"+mIndex;
    }
}
