package com.dev.foodmovers;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.dev.foodmovers.Data.PrefManager;
import com.dev.foodmovers.Kogi.Badge.BadgeView;
import com.dev.foodmovers.Views.Fragments.FragmentCompleted;
import com.dev.foodmovers.Views.Fragments.FragmentProducts;
import com.dev.foodmovers.Views.Fragments.FragmentSaved;
import com.dev.foodmovers.Views.Login.About;
import com.dev.foodmovers.Views.Login.LoginActivity;
import com.dev.foodmovers.Views.MyProfile;
import com.dev.lishabora.Views.Trader.Activities.DrawerClass;
import com.dev.lishabora.Views.Trader.Activities.DrawerItemListener;

public class MainActivity extends AppCompatActivity {
    public static Fragment fragment = null;
    AHBottomNavigationItem item2;
    AHBottomNavigation bottomNavigation;
    String desc = "";
    String title = "";
    private ImageView imagePay;
    private BadgeView qBadgeView;
    private LinearLayout linearPay;
    private PrefManager prefManager;
    SearchView mSearchView;


    private void setUpDrawer(Toolbar toolbar) {

        ImageView imageView = findViewById(R.id.resideShow);
        imageView.setVisibility(View.GONE);
        DrawerClass.Companion.getDrawer("That taste", "Food Movers", MainActivity.this, toolbar, new DrawerItemListener() {
            @Override
            public void logOutClicked() {


                if (prefManager.isLoggedIn()) {
                    prefManager.setIsLoggedIn(false);
                    prefManager.clearData();


                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                } else {

                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void helpClicked() {
                startActivity(new Intent(MainActivity.this, About.class));
                //finish();

            }

            @Override
            public void homeClicked() {

            }


            @Override
            public void profileClicked() {
                startActivity(new Intent(MainActivity.this, MyProfile.class));

            }


            @Override
            public void shareClicked() {
                // startActivity(new Intent(MainActivity.this, MainStories.class));
                //finish();
                try {
                    Intent in = new Intent();
                    in.setAction(Intent.ACTION_SEND);
                    in.putExtra(Intent.EXTRA_TEXT, " Photozuri is on a mission to help you turn your favourite memories of family, vacations, parties or a baby's first years into mementos");
                    in.setType("text/plain");
                    startActivity(in);
                } catch (Exception nm) {

                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem mSearch = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setVisibility(View.GONE);
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setQueryHint("Search here");
        mSearchView.setSearchableInfo(searchManager != null ? searchManager.getSearchableInfo(getComponentName()) : null);

        SearchView.SearchAutoComplete searchAutoComplete = mSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(Color.BLACK);
        searchAutoComplete.setTextColor(Color.BLACK);
        mSearchView.setBackgroundColor(this.getResources().getColor(R.color.transparent));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prefManager = new PrefManager(MainActivity.this);

        setUpDrawer(toolbar);
        bottomNav();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingAB_layout);

        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.colorPrimary));
        //linearPay = findViewById(R.id.linear_pay);


        fragment = new FragmentProducts();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment, "fragmentMain").commit();
//
//        imagePay = findViewById(R.id.image);
//        YoYo.with(Techniques.BounceInDown)
//                .duration(100).repeatMode(YoYo.INFINITE)
//                .interpolate(new AccelerateDecelerateInterpolator())
//
//                .playOn(imagePay);

        Intent i = getIntent();
        try {
            if (i != null && i.getStringExtra("type").equals("notification_cart")) {
                bottomNavigation.setCurrentItem(0);
                Bundle bundle = new Bundle();

                popOutFragments();
                fragment = new FragmentSaved();
                bundle.putInt("type", 1);
                fragment.setArguments(bundle);
                setFragment();
            }
        } catch (Exception nm) {
            nm.printStackTrace();
        }
    }


    public void setSearchVisibility(int visibility) {
//        imagePay.setVisibility(visibility);
//        linearPay.setVisibility(visibility);

//
//        YoYo.with(Techniques.BounceInDown)
//                .duration(100).repeatMode(YoYo.INFINITE)
//                .interpolate(new AccelerateDecelerateInterpolator())
//                .playOn(imagePay);
//        YoYo.with(Techniques.BounceInUp)
//                .duration(700)
//                .repeat(2)
//                .playOn(imagePay);


//        YoYo.with(Techniques.BounceInDown)
//                .duration(700).repeat(2)
//                .interpolate(new AccelerateDecelerateInterpolator())
//
//                .playOn(imagePay);
//        YoYo.with(Techniques.Tada)
//                .duration(700)
//                .repeat(5)
//                .playOn(findViewById(R.id.txt_pay));

        if (mSearchView != null) {
            mSearchView.setVisibility(visibility);
        }

    }

    public void setImagePayVisibility(int visibility, int resource) {
//        imagePay.setVisibility(visibility);
//
//        linearPay.setVisibility(visibility);
//
//
////        imagePay.setImageResource(resource);
////        RotateAnimation anim = new RotateAnimation(0f, 350f, 15f, 15f);
////        anim.setInterpolator(new LinearInterpolator());
////        anim.setRepeatCount(Animation.INFINITE);
////        anim.setDuration(700);
//        if (visibility == View.VISIBLE) {
//
//
//// Start animating the image
//            // final ImageView splash = (ImageView) findViewById(R.id.splash);
//            // imagePay.startAnimation(anim);
//
//
//// Later.. stop the animation
//            //imagePay.setAnimation(null);
//
//        } else {
//            // imagePay.startAnimation(anim);
//
//        }

    }

    public void setImagePayBadge(String badge) {
//        if (qBadgeView != null) {
//
//
//            YoYo.with(Techniques.BounceInDown)
//                    .duration(700).repeatMode(YoYo.INFINITE)
//                    .interpolate(new AccelerateDecelerateInterpolator())
//
//                    .playOn(imagePay);
////            YoYo.with(Techniques.Tada)
////                    .duration(700)
////                    .repeat(5)
////                    .playOn(imagePay);
//            qBadgeView.bindTarget(imagePay).setBadgeText(badge);
//        }
    }


    private void bottomNav() {
        bottomNavigation = findViewById(R.id.bottom_navigation);

// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Home", R.drawable.ic_home_black_24dp, R.color.white);
        item2 = new AHBottomNavigationItem("Cart", R.drawable.ic_shopping_cart_black_24dp, R.color.white);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Orders", R.drawable.ic_history_black_24dp, R.color.white);
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#209fdf"));
        bottomNavigation.setBehaviorTranslationEnabled(true);
        bottomNavigation.setInactiveColor(Color.parseColor("#FFFFFF"));
        bottomNavigation.setForceTint(true);
        bottomNavigation.setCurrentItem(0);
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));

        bottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {
            Bundle bundle = new Bundle();
            switch (position) {
                case 0:
                    fragment = new FragmentProducts();
                    popOutFragments();
                    bundle.putInt("type", 0);
                    fragment.setArguments(bundle);
                    setFragment();
                    break;
                case 1:
                    popOutFragments();
                    fragment = new FragmentSaved();
                    bundle.putInt("type", 1);
                    fragment.setArguments(bundle);
                    setFragment();
                    break;
                case 2:
                    fragment = new FragmentCompleted();
                    popOutFragments();
                    bundle.putInt("type", 2);
                    fragment.setArguments(bundle);
                    setFragment();
                    break;

                default:
            }
            return true;
        });
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {
                // Manage the new y position
            }
        });


    }

    void setFragment() {
        // fragment = new FragmentSearch();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment, "fragmentMain").commit();
    }

    void popOutFragments() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
    }


}
