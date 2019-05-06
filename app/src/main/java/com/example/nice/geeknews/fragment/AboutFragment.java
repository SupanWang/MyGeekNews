package com.example.nice.geeknews.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nice.geeknews.R;
import com.example.nice.geeknews.base.BaseFragment;
import com.example.nice.geeknews.presenter.EmptyP;
import com.example.nice.geeknews.view.EmptyV;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends BaseFragment<EmptyV , EmptyP> implements EmptyV {


    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }
}