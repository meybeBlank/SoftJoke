package com.fengz.softjoke.business1.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fengz.softjoke.business1.ui.fragment.FunnyStoryFragment;
import com.fengz.softjoke.business1.ui.fragment.FunnyVideoFragment;
import com.fengz.softjoke.business1.ui.fragment.HomeFragment;
import com.fengz.softjoke.business1.ui.fragment.MineFragment;

import java.util.HashMap;

public class MainPageAdapter extends FragmentPagerAdapter {

    public static final int MAIN_SIZE = 4;
    private HashMap<Integer, Fragment> mFragmentHashMap = new HashMap<>();

    public MainPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (mFragmentHashMap.containsKey(position)) {
            fragment = mFragmentHashMap.get(position);
        } else {
            switch (position) {
                case 0:
                    fragment = HomeFragment.newInstance();
                    break;
                case 1:
                    fragment = FunnyStoryFragment.newInstance();
                    break;
                case 2:
                    fragment = FunnyVideoFragment.newInstance();
                    break;
                case 3:
                    fragment = MineFragment.newInstance();
                    break;
                default:
                    break;
            }
            mFragmentHashMap.put(position, fragment);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return MAIN_SIZE;
    }
}
