package com.chaowen.hezi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chaowen.hezi.R;
import com.chaowen.hezi.model.FeedItem;

import java.util.List;

/**
 * Created by diallo on 21/03/14.
 */
public class FeedListAdapter2 extends AppBaseAdapter<FeedItem> {

    List<FeedItem> mItems;

    Context context;

    public FeedListAdapter2(Context context, List<FeedItem> collection, boolean hasImageLoader) {
        super(context, collection, hasImageLoader);
        this.context = context;
    }

    public FeedListAdapter2(Context context, List<FeedItem> collection) {
        super(context, collection);
        this.context = context;

    }




    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(context, R.layout.feed_item, null);
        }
        FeedItem item = getAllItems().get(position);
        ImageView picture = ViewHolder.get(convertView,R.id.picture);
        Glide.with(this.context).load(item.getPhoto()).into(picture);
        return convertView;
    }

}
