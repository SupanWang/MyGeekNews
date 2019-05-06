package com.example.nice.geeknews.fragment;



import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.example.nice.geeknews.R;
import com.example.nice.geeknews.adapter.GankMainAdapter;
import com.example.nice.geeknews.base.BaseFragment;
import com.example.nice.geeknews.fragmentchild.GirlFragment;
import com.example.nice.geeknews.fragmentchild.TechFragment;
import com.example.nice.geeknews.presenter.GankP;
import com.example.nice.geeknews.view.GankV;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GankFragment extends BaseFragment<GankV, GankP> implements GankV {


    private TabLayout tabLayout;
    private ViewPager vp;
    private ArrayList<Fragment> list;
    public static String[] tabTitle = new String[]{"Android","iOS","前端","福利"};
    private GankMainAdapter adapter;

    @Override
    protected GankP initPresenter() {
        return new GankP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);
        vp = (ViewPager)view.findViewById(R.id.vp);
        initTab();
    }

    private void initTab() {

        list = new ArrayList<>();
        list.add(new TechFragment());
        list.add(new TechFragment());
        list.add(new TechFragment());
        list.add(new GirlFragment());

        tabLayout.addTab(tabLayout.newTab().setText(tabTitle[0].toString()));
        tabLayout.addTab(tabLayout.newTab().setText(tabTitle[1].toString()));
        tabLayout.addTab(tabLayout.newTab().setText(tabTitle[2].toString()));
        tabLayout.addTab(tabLayout.newTab().setText(tabTitle[3].toString()));

        adapter = new GankMainAdapter(getChildFragmentManager() , list);
        vp.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}
