package com.fengz.softjoke.business1.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.fengz.softjoke.R;
import com.fengz.softjoke.base.mvp.BaseActivity;
import com.fengz.softjoke.business1.contract.MainContract;
import com.fengz.softjoke.business1.ui.adapter.MainPageAdapter;
import com.fengz.softjoke.utils.ScreenUtils;
import com.fengz.softjoke.utils.ToastUtils;

import butterknife.BindView;
import cn.jzvd.Jzvd;

/**
 * 创建时间：2019/1/22
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：主界面 包含四个子界面
 */
public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.viewpager_main_act)
    AHBottomNavigationViewPager mViewpagerMainAct;
    @BindView(R.id.navigator_main_act)
    AHBottomNavigation mNavigatorMainAct;
    @BindView(R.id.tv_title_main_act)
    TextView mTvTitle;

    private MainPageAdapter mAdapter;
    private long lastClick = 0;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        mTvTitle.setVisibility(View.GONE);
        setFull(false);
    }

    public void setFull(boolean full) {
        ScreenUtils.setFullScreen(this, full);
    }

    private void initUI() {

        mAdapter = new MainPageAdapter(getSupportFragmentManager());
        mViewpagerMainAct.setAdapter(mAdapter);
        mViewpagerMainAct.setOffscreenPageLimit(4);
        mViewpagerMainAct.setPagingEnabled(false);
        mViewpagerMainAct.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
//                if (i == 0 || (i == 2 && v > 0) || (i == 3 && v == 0)) {
//                    mTvTitle.setVisibility(View.INVISIBLE);
//                    setFull(false);
//                } else {
//                    setFull(true);
//                    mTvTitle.setVisibility(View.VISIBLE);
//                }
            }

            @Override
            public void onPageSelected(int i) {
                mNavigatorMainAct.setCurrentItem(i);
                Jzvd.releaseAllVideos();
                switch (i) {
                    case 0:
                        mTvTitle.setVisibility(View.GONE);
                        setFull(false);
                        break;
                    case 1:
                        mTvTitle.setText("段子");
                        mTvTitle.setVisibility(View.VISIBLE);
                        setFull(true);
                        break;
                    case 2:
                        mTvTitle.setText("视频");
                        mTvTitle.setVisibility(View.VISIBLE);
                        setFull(true);
                        break;
                    case 3:
                        mTvTitle.setText("我的");
                        mTvTitle.setVisibility(View.VISIBLE);
                        setFull(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        int[] tabColors = getApplicationContext().getResources().getIntArray(R.array.tab_colors);
        AHBottomNavigationAdapter navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu_4);
        navigationAdapter.setupWithBottomNavigation(mNavigatorMainAct, tabColors);
        mNavigatorMainAct.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
//        mNavigatorMainAct.setCurrentItem(1);
        // 按钮颜色 和 背景同化 true tabColors才生效
//        mNavigatorMainAct.setColored(true);
        mNavigatorMainAct.setAccentColor(ContextCompat.getColor(this, R.color.colorPrimary));
        mNavigatorMainAct.setInactiveColor(ContextCompat.getColor(this, R.color.c_333333_text));
        // 背景色
        mNavigatorMainAct.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.white_darker));
        // 圆角图标
//        AHNotification notification = new AHNotification.Builder()
//                .setText("233")
//                .setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.c_2d2d2d_text_gray))
//                .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.grey_dark))
//                .build();
//        mNavigatorMainAct.setNotification(notification, 1);
        mNavigatorMainAct.setOnTabSelectedListener((position, wasSelected) -> {
            mViewpagerMainAct.setCurrentItem(position, false);
            return true;
        });

//        // Display color under navigation bar (API 21+)
//        // Don't forget these lines in your style-v21
//        // <item name="android:windowTranslucentNavigation">true</item>
//        // <item name="android:fitsSystemWindows">true</item>
//        mNavigatorMainAct.setTranslucentNavigationEnabled(true);
//        // Force to tint the drawable (useful for font with icon for example)
//        mNavigatorMainAct.setForceTint(true);
//        // Use colored navigation with circle reveal effect
//        mNavigatorMainAct.setColored(true);
//
//        // Customize notification (title, background, typeface)
////        mNavigatorMainAct.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));
////        // Add or remove notification for each item
////        mNavigatorMainAct.setNotification("1", 3);
////        // OR
//
//        // Enable / disable item & set disable color
////        mNavigatorMainAct.enableItemAtPosition(2);
////        mNavigatorMainAct.disableItemAtPosition(2);
////        mNavigatorMainAct.setItemDisableColor(Color.parseColor("#3A000000"));
//
//        mNavigatorMainAct.setOnNavigationPositionListener(y -> {
//            // Manage the new y position
//        });
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        if (mViewpagerMainAct.getCurrentItem() == 0) {
            long second = System.currentTimeMillis() / 1000;
            if (second - lastClick <= 2) {
                super.onBackPressed();
            } else {
                lastClick = second;
                ToastUtils.show("再次点击退出程序");
            }
        } else {
            switchFragment(0);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public void switchFragment(int position) {
        mNavigatorMainAct.setCurrentItem(position);
    }
}
