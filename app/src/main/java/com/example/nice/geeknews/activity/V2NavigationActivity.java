package com.example.nice.geeknews.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.nice.geeknews.R;
import com.example.nice.geeknews.adapter.NavigationAdapter;
import com.example.nice.geeknews.bean.NavigationBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import qdx.stickyheaderdecoration.NormalDecoration;

public class V2NavigationActivity extends AppCompatActivity {
    /*
    王帅   H1808A
     */
    private static final String TAG = "V2NavigationActivity";
    NavigationBean navigationBean = new NavigationBean();
    private Toolbar toolbar;
    private String Url = "https://www.v2ex.com/?tab=creative";
    private RecyclerView rv;
    private String text;
    private String title;
    private ArrayList<NavigationBean> list;
    private NavigationAdapter adapter;
    private ArrayList<ArrayList<String>> arrayLists;
    private ArrayList<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v2_navigation);
        initView();
        initData();
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(Url).get();
                    Elements elements = doc.select("div#Main div.box");
                    Element element1 = elements.get(1);

                    titles = new ArrayList<>();

                    arrayLists = new ArrayList<>();
                    Elements cells = element1.select("div.cell");

                    for (int i = 1; i < cells.size(); i++) {
                        // 分享与探索、V2EX、iOS、Geek、游戏、Apple、生活、Internet、城市
                        title = cells.get(i).select("table tr td span").text();
                        titles.add(title);
                        navigationBean.setTitle(titles);


                        Log.d(TAG, "title: " + title);
                        ArrayList<String> arrayList = new ArrayList<>();
                        Elements articles = cells.get(i).select("table tr td a");
                        for (int j = 0; j < articles.size(); j++) {
                            text = articles.get(j).text();
                            arrayList.add(text);

                            Log.d(TAG, "text: " + text);

                        }
                        arrayLists.add(arrayList);
                        navigationBean.setText(arrayLists);
                    }

                    // 品牌
                    title = element1.select("div.inner table tr td span").text();
                    titles.add(title);

                    final ArrayList<String> arrayList = new ArrayList<>();
                    Elements select = element1.select("div.inner table tr td a");
                    for (int i = 0; i < select.size(); i++) {
                        text = select.get(i).text();
                        arrayList.add(text);

                    }
                    arrayLists.add(arrayList);
                    navigationBean.setText(arrayLists);
                    list.add(navigationBean);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initList();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initList() {
        adapter = new NavigationAdapter(this);

        rv.setLayoutManager(new LinearLayoutManager(this));
        //返回头布局的内容
        NormalDecoration decoration = new NormalDecoration() {
            @Override
            public String getHeaderName(int i) {
                return titles.get(i);
            }
        };
        rv.addItemDecoration(decoration);
        rv.setAdapter(adapter);
        adapter.update(arrayLists);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rv = (RecyclerView) findViewById(R.id.rv);

        toolbar.setTitle("节点导航");
        setSupportActionBar(toolbar);

        //1.返回按钮的监听
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        list = new ArrayList<>();

    }

    //2.重写onOptionsItemSelected方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}
