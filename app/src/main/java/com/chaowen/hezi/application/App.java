package com.chaowen.hezi.application;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;

/**
 * Created by tangqi on 7/20/15.
 */
public class App extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Bmob.initialize(this, "e168720434e61014d48cba0c210eaa10");
    }
}
