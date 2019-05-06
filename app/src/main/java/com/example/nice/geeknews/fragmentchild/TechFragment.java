package com.example.nice.geeknews.fragmentchild;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nice.geeknews.R;
import com.example.nice.geeknews.adapter.GanlItemAdapter;
//import com.example.nice.geeknews.adapter.TechAdapter;
import com.example.nice.geeknews.bean.GankItemBean;
import com.example.nice.geeknews.net.GankServer;
import com.example.nice.geeknews.util.SystemUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.BlurTransformation;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class TechFragment extends Fragment {


    private static final String TAG = "TechFragment";
    private ImageView iv_tech_blur;
    private ImageView iv_tech_origin;
    private TextView tv_tech_copyright;
    private AppBarLayout tech_appbar;
    private RecyclerView rv;
    List<GankItemBean.ResultsBean> mList;
//    TechAdapter mAdapter;
    private String Url = "https://ws1.sinaimg.cn/large/0065oQSqgy1fwgzx8n1syj30sg15h7ew.jpg";

    private GanlItemAdapter adapter;
    boolean isLoadingMore = false;
    String tech;
    int type;
    private int verticalOffset;


    public TechFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_tech, container, false);
        initView(inflate);
        return inflate;
    }


    private void initView(View inflate) {
        iv_tech_blur = (ImageView) inflate.findViewById(R.id.iv_tech_blur);
        iv_tech_origin = (ImageView) inflate.findViewById(R.id.iv_tech_origin);
        tv_tech_copyright = (TextView) inflate.findViewById(R.id.tv_tech_copyright);
        tech_appbar = (AppBarLayout) inflate.findViewById(R.id.tech_appbar);
        rv = (RecyclerView) inflate.findViewById(R.id.rv);

        mList = new ArrayList<>();
        adapter = new GanlItemAdapter(getActivity());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        initTool();
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GankServer.mUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        GankServer gankServer = retrofit.create(GankServer.class);

        Observable<GankItemBean> data = gankServer.getData();

        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankItemBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GankItemBean gankItemBean) {
                        mList.addAll(gankItemBean.getResults());
                        adapter.update(mList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initTool() {
        RequestOptions options = new RequestOptions()
                .transform(new BlurTransformation());
        Glide.with(getContext()).load(Url).apply(options).into(iv_tech_blur);
        Glide.with(getContext()).load(Url).into(iv_tech_origin);
        tech_appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                //图片随着滑动变模糊,
                //有两张图片,后面一张是高斯模糊过的,前面一张是原图,
                // 随着滑动原图改变透明度实现上面的效果
                //透明度 0-1,0完全透明,1,完全不透明
                //verticalOffset *1.0f / SystemUtil.dp2px(256) -1到0
                //以两倍的速度减小透明度,可以更快的看到模糊效果
                float rate = 1 + i * 2.0f / SystemUtil.dp2px(256);
                if (rate >= 0)
                    iv_tech_origin.setAlpha(rate);
            }
        });
    }
}
