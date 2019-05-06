package com.example.nice.geeknews.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.nice.geeknews.R;
import com.example.nice.geeknews.adapter.SearchAdapter;
import com.example.nice.geeknews.adapter.SelectionAdapter;
import com.example.nice.geeknews.base.BaseFragment;
import com.example.nice.geeknews.bean.SearchBean;
import com.example.nice.geeknews.bean.SelectionBean;
import com.example.nice.geeknews.model.SelectModelImpl;
import com.example.nice.geeknews.presenter.SelectPresenter;
import com.example.nice.geeknews.presenter.SelectPresenterImpl;
import com.example.nice.geeknews.presenter.WeChatP;
import com.example.nice.geeknews.view.SelectView;
import com.example.nice.geeknews.view.WeChatV;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeChatFragment extends BaseFragment<WeChatV, WeChatP> implements WeChatV, SelectView {


    private static final String TAG = "ChatSelectFragment";
    private RecyclerView rv;
    private ArrayList<SelectionBean.NewslistBean> selctList;
    private SelectionAdapter selectionAdapter;
    private String key = "52b7ec3471ac3bec6846577e79f20e4c";
    private int num = 10;
    private int page = 1;
    private ArrayList<SearchBean.NewslistBean> searchList;
    private SearchAdapter searchAdapter;
    private SmartRefreshLayout refreshLayout;
    private SelectPresenter selectPresenter;


    @Override
    protected WeChatP initPresenter() {
        return new WeChatP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_we_chat;
    }

    private void initData() {
        selectPresenter = new SelectPresenterImpl(new SelectModelImpl(), this);
        selectPresenter.getData(key, num, page);
    }

    protected void initView(View view) {
        rv = (RecyclerView) view.findViewById(R.id.rv);
        refreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);

        selctList = new ArrayList<>();
        selectionAdapter = new SelectionAdapter(getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(selectionAdapter);
        initData();

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                selctList.clear();
                initData();
                refreshlayout.finishRefresh();
            }
        });
    }

    @Override
    public void onSuccess(SelectionBean selectionBean) {
        List<SelectionBean.NewslistBean> newslist = selectionBean.getNewslist();
        selctList.clear();
        selctList.addAll(newslist);
        selectionAdapter.update(selctList);
    }

    @Override
    public void onFailed(String str) {
        Log.d(TAG, "onFailed: " + str);
    }

    public void inserch(String query) {
        if (query != null) {
            selectPresenter.getWordData(key, num, page, query);
        } else {
            Toast.makeText(getActivity(), "没有改字段", Toast.LENGTH_SHORT).show();
        }
    }
}
