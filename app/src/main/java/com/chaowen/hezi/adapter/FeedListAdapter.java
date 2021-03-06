package com.chaowen.hezi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.chaowen.hezi.model.FeedItem;
import com.chaowen.hezi.widget.FeedItemView;

import java.util.List;

/**
 * Created by diallo on 21/03/14.
 */
public class FeedListAdapter extends BaseAdapter {

    List<FeedItem> mItems;

    Context context;

    public FeedListAdapter(Context context) {
        this.context = context;
        //initAdapter();
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
