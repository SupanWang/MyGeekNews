package com.example.nice.geeknews.fragmentchild;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nice.geeknews.R;
import com.example.nice.geeknews.adapter.MyHotAdapter;
import com.example.nice.geeknews.bean.HotBean;
import com.example.nice.geeknews.model.HotModelImpl;
import com.example.nice.geeknews.presenter.HotPresenter;
import com.example.nice.geeknews.presenter.HotPresenterImpl;
import com.example.nice.geeknews.view.HotView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment implements HotView {


    private RecyclerView rv;
    private ArrayList<HotBean.RecentBean> list;
    private MyHotAdapter hotAdapter;
    private static final String TAG = "HotFragment";

    public HotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_hot, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()){
            initData();
        }
    }

    private void initData() {
        HotPresenter hotPresenter = new HotPresenterImpl(new HotModelImpl()  ,this);
        hotPresenter.getData();
    }

    private void initView(View inflate) {
        rv = (RecyclerView) inflate.findViewById(R.id.rv);
        list = new ArrayList<>();
        hotAdapter = new MyHotAdapter(getActivity());
        rv.setAdapter(hotAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onSuccess(HotBean hotBean) {
        List<HotBean.RecentBean> recent = hotBean.getRecent();
        list.addAll(recent);
        hotAdapter.update(list);
    }

    @Override
    public void onFailed(String str) {
        Log.d(TAG, "onFailed: "+str);
    }
}
