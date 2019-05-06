package com.example.nice.geeknews.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V extends BaseMvpView, P extends BasePresenter> extends Fragment implements BaseMvpView {

    private Unbinder unbinder;
    private P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        unbinder = ButterKnife.bind(this, view);//绑定
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.bind((V) this);
        }

        initView(view);
        initListener();
        iitData();
        return view;

    }

    protected void iitData() {
    }

    protected void initListener() {
    }

    protected void initView(View view) {
    }

    protected abstract P initPresenter();

    protected abstract int getLayoutId();

    @Override
    public void onDestroyView() {//解绑
        super.onDestroyView();
        unbinder.unbind();
        mPresenter.onDestory();
        mPresenter = null;
    }
}
