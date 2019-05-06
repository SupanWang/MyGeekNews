package com.example.nice.geeknews.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.nice.geeknews.R;
import com.example.nice.geeknews.activity.V2NavigationActivity;
import com.example.nice.geeknews.adapter.TabZhiHuAdapter;
import com.example.nice.geeknews.adapter.VtexVpAdapter;
import com.example.nice.geeknews.base.BaseFragment;
import com.example.nice.geeknews.bean.V2exListBean;
import com.example.nice.geeknews.bean.V2exTabsBean;
import com.example.nice.geeknews.fragmentchild.V2exListFragment;
import com.example.nice.geeknews.presenter.V2exP;
import com.example.nice.geeknews.view.V2exV;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 王帅 H1808A
 * A simple {@link Fragment} subclass.
 */
public class VTEXFragment extends BaseFragment<V2exV, V2exP> implements V2exV {

    private static final String TAG = "VTEXFragment";
    private String Url = "https://www.v2ex.com/";
    private TabLayout tabLayout;
    private ImageView iv;
    private ViewPager vp;
    private String tab;
    private ArrayList<Fragment> fragments;
    private VtexVpAdapter v2adapter;
    private ArrayList<V2exTabsBean> tabsList;


    @Override
    protected V2exP initPresenter() {
        return new V2exP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_vtex;
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        iv = (ImageView) view.findViewById(R.id.iv);
        vp = (ViewPager) view.findViewById(R.id.vp);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity() , V2NavigationActivity.class));
            }
        });
        initData();
    }

    private void initTab() {
        fragments = new ArrayList<>();
        for (int i = 0; i < tabsList.size(); i++) {
            fragments.add(new V2exListFragment(tabsList.get(i).getTab()));
        }
        v2adapter = new VtexVpAdapter(getChildFragmentManager(), fragments,tabsList);
        vp.setAdapter(v2adapter);
        tabLayout.setupWithViewPager(vp);
    }

    private void initData() {
        tabsList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(Url).get();//得到网页链接
                    Element tabs = doc.select("div#Tabs").first();//寻找所需文本，根据id获取
                    Elements allTabs = tabs.select("a[href]");//获取带有href属性的a元素

                    for (Element element : allTabs) {
                        //获取href属性
                        String linlHref = element.attr("href");
                        //获取标签里的文本
                        String linkText = element.text();
                        Log.d(TAG, "linkHref: " + linlHref + ",tab:" + linkText);

                        V2exTabsBean v2exTabsBean = new V2exTabsBean(linlHref, linkText);
                        tabsList.add(v2exTabsBean);


                    }
                    //切回主线程
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initTab();
                        }
                    });
                } catch (IOException e) {
                }
            }
        }).start();

    }

}
