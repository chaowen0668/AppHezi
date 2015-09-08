package com.chaowen.hezi.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chaowen.hezi.R;
import com.chaowen.hezi.model.FeedItem;


/**
 * Created by diallo on 21/03/14.
 */
public class FeedItemView extends RelativeLayout implements CardStackView.CardStackListener {

    ImageView picture;

    TextView id;

    TextView ok;

    TextView no;

    private FeedItem mFeedItem;
    Context context;
    public FeedItemView(Context context) {
        super(context);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.feed_item, this, true);
        picture = (ImageView) findViewById(R.id.picture);
        id = (TextView) findViewById(R.id.id_textView);
        ok = (TextView) findViewById(R.id.ok);
        no = (TextView) findViewById(R.id.no);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

    public void bind(FeedItem item) {
        mFeedItem = item;

        return;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (mFeedItem != null) {
            loadPicture(mFeedItem.getPhoto());

            id.setText(mFeedItem.toString());
        }
    }

    public FeedItem getFeedItem() {
        return mFeedItem;
    }

    void loadPicture(String photo) {
        Glide.with(this.context).load(photo).into(picture);
    }


    @Override
    public void onUpdateProgress(boolean positif, float percent, View view) {
        if (positif) {
            ok.setAlpha(percent);
        } else {
            no.setAlpha(percent);
        }
    }

    @Override
    public void onCancelled(View beingDragged) {
        ok.setAlpha(0);
        no.setAlpha(0);
    }

    @Override
    public void onChoiceMade(boolean choice, View beingDragged) {
        ok.setAlpha(0);
        no.setAlpha(0);
    }
}
