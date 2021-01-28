package com.tengshi.basemodule.base;


import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class BaseStateFragmentAdapter extends FragmentStatePagerAdapter {
    private String[] mTitle;
    private List<Fragment> mFragments;

    public BaseStateFragmentAdapter(FragmentManager fm, List<Fragment> fragments, String[] title) {
        super(fm);
        this.mTitle = title;
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
