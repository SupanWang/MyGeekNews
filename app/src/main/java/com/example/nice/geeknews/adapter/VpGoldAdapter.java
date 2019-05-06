package com.example.nice.geeknews.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.nice.geeknews.base.BaseFragment;
import com.example.nice.geeknews.bean.GoldTitleBean;

import java.util.ArrayList;

/**
 * @author ws
 * Created by asus on 2019/4/17.
 * 生命周期不一样
 * FragmentStatePagerAdapter:88
 * FragmentPagerAdapter:用不着的碎片生命周期,只会走到onDestoryView();
 */

public class VpGoldAdapter extends FragmentStatePagerAdapter {
    private ArrayList<BaseFragment> mFragments;
    private ArrayList<GoldTitleBean> mTitles;
    private ArrayList<String> mNewTitles = new ArrayList<>();

    public VpGoldAdapter(FragmentManager fm,ArrayList<BaseFragment> fragments, ArrayList<GoldTitleBean> titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;

        //设置新Tab栏标题
        for (int i = 0; i < mTitles.size(); i++) {
            GoldTitleBean bean = mTitles.get(i);
            if (bean.isChecked) {
                mNewTitles.add(bean.title);
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mNewTitles.get(position);//设置新Title
    }
}
