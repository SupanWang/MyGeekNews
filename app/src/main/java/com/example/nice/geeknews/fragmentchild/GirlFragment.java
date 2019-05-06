package com.example.nice.geeknews.fragmentchild;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nice.geeknews.R;
import com.example.nice.geeknews.activity.MainActivity;
import com.example.nice.geeknews.adapter.GirlAdapter;
import com.example.nice.geeknews.bean.GirlBean;
import com.example.nice.geeknews.bean.GirlDbBean;
import com.example.nice.geeknews.model.GirlModelImpl;
import com.example.nice.geeknews.presenter.GirlPresenter;
import com.example.nice.geeknews.presenter.GirlPresenterImpl;
import com.example.nice.geeknews.util.DbUtils;
import com.example.nice.geeknews.view.GirlView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GirlFragment extends Fragment implements GirlView, GirlAdapter.OnItemClickListener {


    private static final String TAG = "GirlFragment";
    private RecyclerView rv;
    private SmartRefreshLayout sRl;
    private ArrayList<GirlBean.ResultsBean> list;
    private GirlAdapter adapter;
    private int page = 1;


    public GirlFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_girl, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        GirlPresenter girlPresenter = new GirlPresenterImpl(new GirlModelImpl(), this);
        girlPresenter.getData(page);
    }

    private void initView(View inflate) {
        rv = (RecyclerView) inflate.findViewById(R.id.rv);
        sRl = (SmartRefreshLayout) inflate.findViewById(R.id.sRl);

        list = new ArrayList<>();
        adapter = new GirlAdapter(getActivity());
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //防止图片上下滑动
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(this);

        sRl.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                //加载更多
                page++;
                initData();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //下拉刷新
                list.clear();
                page = 1;
                initData();
            }
        });
    }

    @Override
    public void onSuccess(GirlBean girlBean) {
        List<GirlBean.ResultsBean> results = girlBean.getResults();
        if (girlBean != null && girlBean.getResults() != null && girlBean.getResults().size() > 0) {
            list.addAll(results);
            adapter.update(list);
        }
        sRl.finishLoadmore();
        sRl.finishRefresh();

    }

    @Override
    public void onFailed(String str) {
        Log.d(TAG, "onFailed: " + str);

        sRl.finishRefresh();
        sRl.finishLoadmore();
    }

    @Override
    public void onItemClick(final GirlBean.ResultsBean resultsBean) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("温馨提示")
                .setMessage("是否收藏？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GirlDbBean girlDbBean = new GirlDbBean();
                        girlDbBean.setLid(null);
                        girlDbBean.setUrl(resultsBean.getUrl());
                        DbUtils.getDbUtils().insertAll(girlDbBean);
                        Toast.makeText(getActivity(),"成功收藏！",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("取消", null)
                .show();
    }
}
