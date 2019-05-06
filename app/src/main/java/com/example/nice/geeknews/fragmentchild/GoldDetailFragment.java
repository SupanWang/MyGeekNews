package com.example.nice.geeknews.fragmentchild;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nice.geeknews.R;
import com.example.nice.geeknews.activity.DailyNewsWebActivity;
import com.example.nice.geeknews.base.BaseFragment;
import com.example.nice.geeknews.base.Constants;
import com.example.nice.geeknews.presenter.EmptyP;
import com.example.nice.geeknews.view.EmptyV;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoldDetailFragment extends BaseFragment<EmptyV , EmptyP> implements EmptyV{

    /*
    王帅  H1808A
     */
    private TextView tv;


    public static GoldDetailFragment newInstance(String text) {
        GoldDetailFragment goldDetailFragment = new GoldDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.DATA , text);
        goldDetailFragment.setArguments(bundle);
        return goldDetailFragment;
    }

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold_detail;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tv = (TextView)view.findViewById(R.id.tv);
        Bundle arguments = getArguments();
        String date = arguments.getString(Constants.DATA);
        tv.setText(date);
    }
}
