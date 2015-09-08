package com.chaowen.hezi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.chaowen.hezi.model.FeedItem;
import com.chaowen.hezi.widget.FeedItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diallo on 21/03/14.
 */
public class FeedListAdapter extends BaseAdapter {

    List<FeedItem> mItems;

    Context context;

    public FeedListAdapter(Context context) {
        this.context = context;
        initAdapter();
    }

    void initAdapter() {
        mItems = new ArrayList<FeedItem>();
        mItems.add(new FeedItem(1,"https://img.alicdn.com/imgextra/i3/738517069/T2o6eYXhFbXXXXXXXX_!!738517069.jpg"));
        mItems.add(new FeedItem(2,"https://img.alicdn.com/imgextra/i3/644216784/TB2AvPgeVXXXXXrXXXXXXXXXXXX_!!644216784.jpg"));
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public FeedItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        FeedItemView personItemView;
        if (convertView == null) {
            personItemView = new FeedItemView(context);
        } else {
            personItemView = (FeedItemView) convertView;
        }

        personItemView.bind(getItem(position));

        return personItemView;
    }
}
