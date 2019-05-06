package com.example.nice.geeknews.fragmentchild;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nice.geeknews.R;
import com.example.nice.geeknews.adapter.V2exListAdapter;
import com.example.nice.geeknews.bean.V2exListBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 王帅  H1808A
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class V2exListFragment extends Fragment {

    private static final String TAG = "V2exListFragment";
    private String Url = "https://www.v2ex.com/?tab=";
    private ArrayList<V2exListBean> listBeans;
    private V2exListAdapter adapter;
    private RecyclerView rv;
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    initList();
                    break;
            }
        }
    };
    private String tab;


    @SuppressLint("ValidFragment")
    public V2exListFragment(String string) {
        // Required empty public constructor
        this.tab = string;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_v2ex_list, container, false);
        initView(inflate);
        initData();
//        initList();
        return inflate;
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(Url + tab).get();//得到网页链接
                    //新闻item数据
                    Elements items = doc.select("div.cell.item");
                    for (Element item : items) {
                        //图片
                        Element image = item.select("table tr td a > img.avatar").first();
                        String src = image.attr("src");
                        Log.d(TAG, "src: " + src);

                        //评论数量和评论链接地址
                        Element comment = item.select("table tbody tr td a.count_livid").first();
                        if (comment != null) {
                            String href = comment.attr("href");
                            String text = comment.text();
                            Log.d(TAG, "评论: " + ",链接:" + href + ",数量:" + text);
                        }
                        //标题
                        Element title = item.select("table tbody tr td span.item_title > a").first();
                        String text = title.text();
                        Log.d(TAG, "标题: " + text);

                        //topic_info
                        Element topic = item.select("table tbody tr td span.topic_info").first();
                        Element secondaryTab = topic.select("a.node").first();
                        String secTab = secondaryTab.text();
                        Log.d(TAG, "secTab: " + secTab);

                        String topicText = topic.text();
                        Log.d(TAG, "topicText: " + topicText);

                        Elements people = topic.select("strong > a");
                        if (people.size() > 0) {
                            //作者
                            Element element = people.get(0);
                            Log.d(TAG, "作者: " + element.text());
                        }

                        if (people.size() > 1) {
                            //最后的评论者
                            Element element = people.get(1);
                            Log.d(TAG, "最后的评论者: " + element.text());
                        }

                        V2exListBean v2exListBean = new V2exListBean(src, text, text, secTab, topicText);
                        listBeans.add(v2exListBean);
                    }
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            initList();
//                        }
//                    });
                    mHandler.sendEmptyMessage(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void initList() {
        adapter = new V2exListAdapter(getContext());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.update(listBeans);
    }

    private void initView(View inflate) {
        rv = (RecyclerView) inflate.findViewById(R.id.rv);
        listBeans = new ArrayList<>();
    }
}
