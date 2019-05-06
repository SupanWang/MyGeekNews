package com.example.nice.geeknews.fragmentchild;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nice.geeknews.R;
import com.example.nice.geeknews.activity.CalendarActivity;
import com.example.nice.geeknews.activity.ZhihudetailActivity;
import com.example.nice.geeknews.adapter.MyDailyAdapter;
import com.example.nice.geeknews.bean.DailyNewsBean;
import com.example.nice.geeknews.model.DailyModelImpl;
import com.example.nice.geeknews.presenter.DailyPresenter;
import com.example.nice.geeknews.presenter.DailyPresenterImpl;
import com.example.nice.geeknews.view.DailyView;

import java.util.ArrayList;
import java.util.List;


/**
 * 王帅  H1808A
 * A simple {@link Fragment} subclass.
 */
public class DailyFragment extends Fragment implements DailyView, MyDailyAdapter.OnItemClickListener, View.OnClickListener {


    public static final long PERFECT_MILLS = 618;
    public static final int MINI_RADIUS = 0;
    private static final String TAG = "DailyFragment";
    private RecyclerView rv;
    private MyDailyAdapter dailyAdapter;
    private ArrayList<DailyNewsBean.StoriesBean> list;
    private ArrayList<DailyNewsBean.TopStoriesBean> topList;
    private FloatingActionButton floatbutton;

    public DailyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_daily, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        DailyPresenter dailyPresenter = new DailyPresenterImpl(new DailyModelImpl(), this);
        dailyPresenter.getData();
    }

    private void initView(View inflate) {
        rv = (RecyclerView) inflate.findViewById(R.id.rv);
        floatbutton = (FloatingActionButton) inflate.findViewById(R.id.floatbutton);
        list = new ArrayList<>();
        topList = new ArrayList<>();
        dailyAdapter = new MyDailyAdapter(getActivity());
        rv.setAdapter(dailyAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        dailyAdapter.setOnItemClickListener(this);
        floatbutton.setOnClickListener(this);//悬浮按钮

    }

    //请求数据成功方法
    @Override
    public void onSuccess(DailyNewsBean dailyNewsBean) {
        List<DailyNewsBean.StoriesBean> stories = dailyNewsBean.getStories();
        List<DailyNewsBean.TopStoriesBean> top_stories = dailyNewsBean.getTop_stories();
        list.addAll(stories);
        topList.addAll(top_stories);
        dailyAdapter.update(list, topList);
    }

    @Override
    public void onFailed(String str) {
        Log.d(TAG, "onFailed: " + str);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floatbutton:
                Intent it = new Intent();
                it.setClass(getActivity(), CalendarActivity.class);
                CircularAnim(PERFECT_MILLS);
                startActivityForResult(it, 22);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void CircularAnim(long perfectMills) {
        int[] location = new int[2];
        floatbutton.getLocationInWindow(location);
        final int cx = location[0] + floatbutton.getWidth() / 2;
        final int cy = location[1] + floatbutton.getHeight() / 2;
        final ImageView view = new ImageView(getActivity());
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setImageResource(R.color.colorAccent);
        final ViewGroup decorView = (ViewGroup) getActivity().getWindow().getDecorView();
        int w = decorView.getWidth();
        int h = decorView.getHeight();
        decorView.addView(view, w, h);

        // 计算中心点至view边界的最大距离
        int maxW = Math.max(cx, w - cx);
        int maxH = Math.max(cy, h - cy);
        final int finalRadius = (int) Math.sqrt(maxW * maxW + maxH * maxH) + 1;
        Animator
                anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
        int maxRadius = (int) Math.sqrt(w * w + h * h) + 1;
        // 若使用默认时长，则需要根据水波扩散的距离来计算实际时间
        if (perfectMills == PERFECT_MILLS) {
            // 算出实际边距与最大边距的比率
            double rate = 1d * finalRadius / maxRadius;
            // 水波扩散的距离与扩散时间成正比
            perfectMills = (long) (PERFECT_MILLS * rate);
        }
        final long finalDuration = perfectMills;
        anim.setDuration(finalDuration);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                // 默认渐隐过渡动画.
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                // 默认显示返回至当前Activity的动画.
                floatbutton.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, finalRadius, 0);
                        anim.setDuration(finalDuration);
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                try {
                                    decorView.removeView(view);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        anim.start();
                    }
                }, 1000);

            }
        });
        anim.start();
    }

    //接口回调
    @Override
    public void onItemClcik(DailyNewsBean.StoriesBean storiesBean) {
        startActivity(new Intent(getActivity() , ZhihudetailActivity.class));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 22 && resultCode == 23) {
            String date = data.getStringExtra("date");

        }


    }
}
