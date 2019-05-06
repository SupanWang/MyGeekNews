package com.example.nice.geeknews.fragment;


import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.nice.geeknews.R;
import com.example.nice.geeknews.activity.ShowGoldActivity;
import com.example.nice.geeknews.adapter.VpGoldAdapter;
import com.example.nice.geeknews.base.BaseFragment;
import com.example.nice.geeknews.base.Constants;
import com.example.nice.geeknews.bean.GoldTitleBean;
import com.example.nice.geeknews.fragmentchild.GoldDetailFragment;
import com.example.nice.geeknews.presenter.GoldP;
import com.example.nice.geeknews.view.GoldV;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoldFragment extends BaseFragment<GoldV, GoldP> implements GoldV {


    private TabLayout MtabLayout;
    private ImageView iv;
    private ViewPager mVp;
    private ArrayList<GoldTitleBean> mTitles;
    private ArrayList<BaseFragment> mFragments;
    private ArrayList<GoldTitleBean> mList;

    @Override
    protected GoldP initPresenter() {
        return new GoldP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        MtabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        iv = (ImageView) view.findViewById(R.id.iv);
        mVp = (ViewPager) view.findViewById(R.id.vp);

        initTitles();
        setFragments();


        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go2ShowActivity();
            }
        });

    }

    private void setFragments() {
        initFragments();
        VpGoldAdapter adapter = new VpGoldAdapter(getChildFragmentManager(), mFragments, mTitles);
        mVp.setAdapter(adapter);
        MtabLayout.setupWithViewPager(mVp);
    }

    private void go2ShowActivity() {
        Intent intent = new Intent(getContext(), ShowGoldActivity.class);
        intent.putExtra(Constants.DATA,mTitles);
        startActivityForResult(intent,100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null){
            if (requestCode == 100 && resultCode == Activity.RESULT_OK){
                mTitles = (ArrayList<GoldTitleBean>) data.getSerializableExtra(Constants.DATA);
                setFragments();
            }
        }
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.size(); i++) {
            GoldTitleBean goldTitleBean = mTitles.get(i);
            if (goldTitleBean.isChecked) {
                mFragments.add(GoldDetailFragment.newInstance(goldTitleBean.title));
            }
        }
    }

    private void initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add(new GoldTitleBean("Android",true));
        mTitles.add(new GoldTitleBean("iOS",true));
        mTitles.add(new GoldTitleBean("设计",true));
        mTitles.add(new GoldTitleBean("工具资源",true));
        mTitles.add(new GoldTitleBean("产品",true));
        mTitles.add(new GoldTitleBean("阅读",true));
        mTitles.add(new GoldTitleBean("前端",true));
        mTitles.add(new GoldTitleBean("后端",true));
    }

}
