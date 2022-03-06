package com.hegazy.myshop.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.hegazy.myshop.R;
import com.hegazy.myshop.ui.activity.search.SearchActivity;
import com.hegazy.myshop.ui.fragment.home.favorite.FavoriteFragment;
import com.hegazy.myshop.ui.fragment.home.home.HomeFragment;
import com.hegazy.myshop.ui.fragment.home.notification.NotificationFragment;
import com.hegazy.myshop.ui.fragment.home.profile.ProfileFragment;
import com.hegazy.myshop.ui.fragment.home.setting.SettingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hegazy.myshop.helpar.HelperMethod.replaceFragment;

public class HomeActivity extends BaseActivity {

    private final static int ID_Favorite = 1;
    private final static int ID_NOTIFICATION = 2;
    private final static int ID_HOME = 3;
    private final static int ID_ACCOUNT = 4;
    private final static int ID_SETTING = 5;


    @BindView(R.id.activity_home_iv_search)
    ImageView activityHomeIvSearch;
    @BindView(R.id.activity_home_tv_title)
    TextView activityHomeTvTitle;
    @BindView(R.id.activity_home_iv_cart)
    ImageView activityHomeIvCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.backrund));


        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);


        MeowBottomNavigation bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_Favorite, R.drawable.ic_favorite_border));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_NOTIFICATION, R.drawable.ic_notification));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.ic_account));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_SETTING, R.drawable.ic_baseline_more));


        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
            }
        });


        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                String Title;

                switch (item.getId()) {
                    case ID_HOME:
                        replaceFragment(HomeActivity.this.getSupportFragmentManager(), R.id.home_activity_fr_home, new HomeFragment());
                        Title = getString(R.string.home_page);
                        break;

                    case ID_NOTIFICATION:
                        replaceFragment(HomeActivity.this.getSupportFragmentManager(), R.id.home_activity_fr_home, new NotificationFragment());
                        Title = getString(R.string.notification_page);

                        break;

                    case ID_Favorite:
                        replaceFragment(HomeActivity.this.getSupportFragmentManager(), R.id.home_activity_fr_home, new FavoriteFragment());
                        Title = getString(R.string.favorite_page);

                        break;


                    case ID_ACCOUNT:
                        replaceFragment(HomeActivity.this.getSupportFragmentManager(), R.id.home_activity_fr_home, new ProfileFragment());
                        Title = getString(R.string.profile_page);

                        break;

                    case ID_SETTING:
                        replaceFragment(HomeActivity.this.getSupportFragmentManager(), R.id.home_activity_fr_home, new SettingFragment());
                        Title = getString(R.string.setting_page);

                        break;

                    default:
                        Title = "";
                }
                activityHomeTvTitle.setText(Title);
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
            }
        });


        bottomNavigation.setCount(ID_NOTIFICATION, "0");
        bottomNavigation.show(ID_HOME, true);
}


    @Override
    public void onBackPressed() {
        finish();
    }

    @OnClick({R.id.activity_home_iv_search, R.id.activity_home_iv_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_home_iv_search:
                startActivity(new Intent(HomeActivity.this, SearchActivity.class));

                break;
            case R.id.activity_home_iv_cart:
                startActivity(new Intent(HomeActivity.this, OrderActivity.class));

                break;
        }
    }
}
