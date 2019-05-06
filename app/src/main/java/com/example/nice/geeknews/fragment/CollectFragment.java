package com.example.nice.geeknews.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nice.geeknews.R;
import com.example.nice.geeknews.adapter.GirlDbAdapter;
import com.example.nice.geeknews.base.BaseFragment;
import com.example.nice.geeknews.bean.GirlDbBean;
import com.example.nice.geeknews.presenter.EmptyP;
import com.example.nice.geeknews.util.DbUtils;
import com.example.nice.geeknews.view.EmptyV;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectFragment extends BaseFragment<EmptyV, EmptyP> implements EmptyV {

    private RecyclerView rv;
    private ArrayList<GirlDbBean> list;
    private GirlDbAdapter adapter;
    private static final String TAG = "CollectFragment";

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        rv = (RecyclerView)view.findViewById(R.id.rv);
        list = new ArrayList<>();
        adapter = new GirlDbAdapter(getActivity());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        initData();
    }

    private void initData() {
        List<GirlDbBean> query = DbUtils.getDbUtils().query();
        list.addAll(query);
        adapter.update(list);
    }

}
