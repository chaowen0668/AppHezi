package com.chaowen.hezi.ui.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.chaowen.hezi.R;
import com.chaowen.hezi.adapter.FeedListAdapter2;
import com.chaowen.hezi.model.FeedItem;
import com.chaowen.hezi.ui.activity.base.BaseActivity;
import com.chaowen.hezi.ui.fragment.NewsListFragment;
import com.chaowen.hezi.utils.Constants;
import com.chaowen.hezi.utils.ToastUtils;
import com.chaowen.hezi.widget.CardStackView;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import butterknife.Bind;
import butterknife.ButterKnife;



public class MainActivity extends BaseActivity {
    // save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;


    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.mCardStack)
    CardStackView mCardStack;
    /**数据队列*/
    private ConcurrentLinkedQueue<Integer> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutResID = R.layout.activity_main;
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        createProfile(savedInstanceState);
        doInitialize();
        initCard();
    }

    private void initCard() {
        mCardStack.setCardStackListener(new CardStackView.CardStackListener() {
            @Override
            public void onUpdateProgress(boolean choice, float percent, View view) {

            }

            @Override
            public void onCancelled(View beingDragged) {

            }

            @Override
            public void onChoiceMade(boolean choice, View beingDragged) {

            }
        });
    }

    private void doInitialize() {
      List<FeedItem> mItems = new ArrayList<FeedItem>();
        mItems.add(new FeedItem(1,"https://img.alicdn.com/imgextra/i3/738517069/T2o6eYXhFbXXXXXXXX_!!738517069.jpg"));
        mItems.add(new FeedItem(2,"https://img.alicdn.com/imgextra/i3/644216784/TB2AvPgeVXXXXXrXXXXXXXXXXXX_!!644216784.jpg"));
        mCardStack.setAdapter(new FeedListAdapter2(this,mItems));
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_about:
                return prepareIntent(com.chaowen.hezi.ui.activity.AboutActivity.class);
            case R.id.menu_action_pick_photo:
                return prepareIntent(com.chaowen.hezi.ui.activity.PickPhotoActivity.class);
            case R.id.menu_action_settings:
                return prepareIntent(com.chaowen.hezi.ui.activity.PrefsActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean prepareIntent(Class clazz) {
        startActivity(new Intent(MainActivity.this, clazz));
        return true;
    }

    public void createProfile(Bundle savedInstanceState) {
        // Create a few sample profile
        // NOTE you have to define the loader logic too. See the CustomApplication for more details
        final IProfile profile = new ProfileDrawerItem().withName("Qi Tang").withEmail("imtangqi@gmail.com").withIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_avatar_tangqi));
        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        profile,
                        //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
                        new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new GitHub Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).actionBarSize().paddingDp(5).colorRes(R.color.material_drawer_primary_text)).withIdentifier(Constants.PROFILE_SETTING),
                        new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        //sample usage of the onProfileChanged listener
                        //if the clicked item has the identifier 1 add a new profile ;)
                        if (profile instanceof IDrawerItem && ((IDrawerItem) profile).getIdentifier() == Constants.PROFILE_SETTING) {
                            IProfile newProfile = new ProfileDrawerItem().withNameShown(true).withName("Building Blocks").withEmail("buildingblocks@gmail.com").withIcon(getResources().getDrawable(R.mipmap.ic_launcher));
                            if (headerResult.getProfiles() != null) {
                                //we know that there are 2 setting elements. set the new profile above them ;)
                                headerResult.addProfile(newProfile, headerResult.getProfiles().size() - 2);
                            } else {
                                headerResult.addProfiles(newProfile);
                            }
                        }
                        //false if you have not consumed the event and it should close the drawer
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();
        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
               // .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIcon(FontAwesome.Icon.faw_home),
                        //here we use a customPrimaryDrawerItem we defined in our sample app
                        //this custom DrawerItem extends the PrimaryDrawerItem so it just overwrites some methods
                        new PrimaryDrawerItem().withName(R.string.drawer_item_free_play).withIcon(FontAwesome.Icon.faw_gamepad),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_custom).withDescription("This is a description").withIcon(FontAwesome.Icon.faw_eye),
                        new SectionDrawerItem().withName(R.string.drawer_item_section_header),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_cart_plus),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_help).withIcon(FontAwesome.Icon.faw_database).setEnabled(false),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_open_source).withIcon(FontAwesome.Icon.faw_github),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_contact).withSelectedIconColor(Color.RED).withIconTintingEnabled(true).withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).actionBarSize().paddingDp(5).colorRes(R.color.material_drawer_dark_primary_text)).withTag("Bullhorn"),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_help).withIcon(FontAwesome.Icon.faw_question).setEnabled(false)
                ) // add the items we want to use with our Drawer
                .withOnDrawerNavigationListener(new Drawer.OnDrawerNavigationListener() {
                    @Override
                    public boolean onNavigationClickListener(View clickedView) {
                        //this method is only called if the Arrow icon is shown. The hamburger is automatically managed by the MaterialDrawer
                        //if the back arrow is shown. close the activity
                        //return true if we have consumed the event
                        return true;
                    }
                })
                .addStickyDrawerItems(
                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_cog).withIdentifier(10),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_open_source).withIcon(FontAwesome.Icon.faw_github)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        // do something with the clicked item :D
                        ToastUtils.showShort(R.string.rest_over_to_you);
                        return false;
                    }
                })
                .withAnimateDrawerItems(true)
                .withSavedInstance(savedInstanceState)
                .build();

        //only set the active selection or active profile if we do not recreate the activity
        if (savedInstanceState == null) {
            // set the selection to the item with the identifier 11
            result.setSelectionByIdentifier(11, false);

            //set the active profile
            headerResult.setActiveProfile(profile);
        }
    }

    /**
     * 实现再按一次退出提醒
     */
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 3000) {
                ToastUtils.showShort(getString(R.string.exit_once_more));
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }





    public int getResourceByReflect(String imageName) {
        Class drawable = R.drawable.class;
        Field field ;
        int r_id;
        try {
            field = drawable.getField(imageName);
            r_id = field.getInt(field.getName());
        } catch (Exception e) {
            r_id = R.mipmap.img1;
            Log.e("ERROR", String.valueOf(e.getMessage()));
        }
        return r_id;
    }


    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Bundle bundle = new Bundle();
            Fragment newFragment = new NewsListFragment();

            Calendar dateToGetUrl = Calendar.getInstance();
            dateToGetUrl.add(Calendar.DAY_OF_YEAR, 1 - i);
            String date = Constants.simpleDateFormat.format(dateToGetUrl.getTime());

            bundle.putBoolean("first_page?", i == 0);
            bundle.putBoolean("single?", false);
            bundle.putString("date", date);

            newFragment.setArguments(bundle);
            return newFragment;
        }

        @Override
        public int getCount() {
            return Constants.PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Calendar displayDate = Calendar.getInstance();
            displayDate.add(Calendar.DAY_OF_YEAR, -position);

            return DateFormat.getDateInstance().format(displayDate.getTime());
        }
    }



}
