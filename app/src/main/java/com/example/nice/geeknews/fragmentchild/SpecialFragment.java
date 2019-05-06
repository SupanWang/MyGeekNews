package com.example.nice.geeknews.fragmentchild;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nice.geeknews.R;
import com.example.nice.geeknews.adapter.MySpecialAdapter;
import com.example.nice.geeknews.bean.SpecialBean;
import com.example.nice.geeknews.model.SpecialModelImpl;
import com.example.nice.geeknews.presenter.SpecialPresenter;
import com.example.nice.geeknews.presenter.SpecialPresenterImpl;
import com.example.nice.geeknews.view.SpecialView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialFragment extends Fragment implements SpecialView {


    private RecyclerView rv;
    private ArrayList<SpecialBean.DataBean> list;
    private MySpecialAdapter specialAdapter;
    private static final String TAG = "SpecialFragment";

    public SpecialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_special, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (getUserVisibleHint()){
//            initData();
//        }
//    }

    private void initData() {
        SpecialPresenter specialPresenter = new SpecialPresenterImpl(new SpecialModelImpl() , this);
        specialPresenter.getData();
    }

    private void initView(View inflate) {
        rv = (RecyclerView) inflate.findViewById(R.id.rv);
        list = new ArrayList<>();
        specialAdapter = new MySpecialAdapter(getActivity());
        rv.setAdapter(specialAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rv.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onSuccess(SpecialBean specialBean) {
        List<SpecialBean.DataBean> data = specialBean.getData();
        list.addAll(data);
        specialAdapter.update(list);
    }

    @Override
    public void onFailed(String str) {
        Log.d(TAG, "onFailed: "+str);
    }
}
