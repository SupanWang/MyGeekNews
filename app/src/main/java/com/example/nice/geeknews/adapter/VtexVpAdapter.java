package com.example.nice.geeknews.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.nice.geeknews.bean.V2exTabsBean;

import java.util.ArrayList;

public class VtexVpAdapter extends FragmentStatePagerAdapter {
    private final ArrayList<V2exTabsBean> tabList;
    private ArrayList<Fragment> list;

    public VtexVpAdapter(FragmentManager fm, ArrayList<Fragment> list, ArrayList<V2exTabsBean> tabsList) {
        super(fm);
        this.list = list;
        this.tabList = tabsList;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabList.get(position).getTab();
    }
}
