package com.example.nice.geeknews.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nice.geeknews.R;
import com.example.nice.geeknews.adapter.TabZhiHuAdapter;
import com.example.nice.geeknews.base.BaseFragment;
import com.example.nice.geeknews.fragmentchild.DailyFragment;
import com.example.nice.geeknews.fragmentchild.HotFragment;
import com.example.nice.geeknews.fragmentchild.SpecialFragment;
import com.example.nice.geeknews.presenter.ZhihuDailyNewsP;
import com.example.nice.geeknews.view.ZhihuDailyNewsV;

import java.util.ArrayList;

/**
 * caused by ws 2019/4/17
 * A simple {@link Fragment} subclass.
 */
public class ZhiHuFragment extends BaseFragment<ZhihuDailyNewsV, ZhihuDailyNewsP> implements ZhihuDailyNewsV {


    private TabLayout tab;
    private ViewPager vp;
    private ArrayList<Fragment> fragments;
    private TabZhiHuAdapter zhiHuAdapter;


    @Override
    protected ZhihuDailyNewsP initPresenter() {
        return new ZhihuDailyNewsP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhi_hu;
    }

    @Override
    protected void initView(View view) {
        tab = (TabLayout) view.findViewById(R.id.tab);
        vp = (ViewPager) view.findViewById(R.id.vp);
        fragments = new ArrayList<>();
        fragments.add(new DailyFragment());
        fragments.add(new SpecialFragment());
        fragments.add(new HotFragment());

        tab.addTab(tab.newTab().setText("日报"));
        tab.addTab(tab.newTab().setText("专栏"));
        tab.addTab(tab.newTab().setText("热门"));

        zhiHuAdapter = new TabZhiHuAdapter(getChildFragmentManager(), fragments);
        vp.setAdapter(zhiHuAdapter);

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));


    }

}
