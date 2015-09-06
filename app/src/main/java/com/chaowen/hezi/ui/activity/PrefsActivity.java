package com.chaowen.hezi.ui.activity;

import android.os.Bundle;

import com.chaowen.hezi.R;
import com.chaowen.hezi.ui.activity.base.ToolbarActivity;
import com.chaowen.hezi.ui.fragment.PrefsFragment;

import butterknife.ButterKnife;



public class PrefsActivity extends ToolbarActivity {
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_prefs;
    }

    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitle(getString(R.string.settings_title));
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_frame, new PrefsFragment())
                .commit();
    }
}