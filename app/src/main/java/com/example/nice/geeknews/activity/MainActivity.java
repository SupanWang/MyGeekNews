package com.example.nice.geeknews.activity;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.nice.geeknews.R;
import com.example.nice.geeknews.base.BaseActivity;
import com.example.nice.geeknews.base.Constants;
import com.example.nice.geeknews.fragment.AboutFragment;
import com.example.nice.geeknews.fragment.CollectFragment;
import com.example.nice.geeknews.fragment.GankFragment;
import com.example.nice.geeknews.fragment.GoldFragment;
import com.example.nice.geeknews.fragment.SetFragment;
import com.example.nice.geeknews.fragment.VTEXFragment;
import com.example.nice.geeknews.fragment.WeChatFragment;
import com.example.nice.geeknews.fragment.ZhiHuFragment;
import com.example.nice.geeknews.presenter.MainP;
import com.example.nice.geeknews.util.SpUtil;
import com.example.nice.geeknews.view.MainV;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.greenrobot.greendao.DbUtils;

import java.util.ArrayList;

/*
王帅 H1808A
 */
public class MainActivity extends BaseActivity<MainV, MainP> implements MainV {

    private final int TYPE_ZHIHU = 0;
    private final int TYPE_WECHAT = 1;
    private final int TYPE_GANK = 2;
    private final int TYPE_GOLD = 3;
    private final int TYPE_V2EX = 4;
    private final int TYPE_COLLECT = 5;
    public static final int TYPE_SETTING = 6;
    private final int TYPE_ABOUT = 7;


    private ArrayList<Fragment> mFragments;
    private FragmentManager mManager;
    private ArrayList<Integer> mTitles;
    //上一次显示的fragmnet的索引
    private int mLastFragmentPosition = 0;
    private Toolbar toolbar;
    private FrameLayout Fragm;
    private NavigationView mNv;
    private DrawerLayout dl;
    private MaterialSearchView search_view;
    private FrameLayout toolbar_container;
    private MenuItem mSearchItem;
    private WeChatFragment fragment;


    @Override
    protected MainP initPresenter() {
        return new MainP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        Fragm = (FrameLayout) findViewById(R.id.Fragm);
        mNv = (NavigationView) findViewById(R.id.mNv);
        dl = (DrawerLayout) findViewById(R.id.dl);
        search_view = (MaterialSearchView) findViewById(R.id.search_view);
//        toolbar.setTitle("知乎日报");
//        toolbar.setTitle("");

        mManager = getSupportFragmentManager();

        toolbar.setTitleTextColor(getResources().getColor(R.color.white));//给ToolBar标题设置颜色
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.about, R.string.about);
        //设置旋转开关颜色
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        dl.addDrawerListener(toggle);
        toggle.syncState();

        initTitles();
        initFragments();
        addZhihuDailyNewsFragment();

    }

    private void initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add(R.id.zhihu);
        mTitles.add(R.id.wechat);
        mTitles.add(R.id.Gank);
        mTitles.add(R.id.Gold);
        mTitles.add(R.id.VTEX);
        mTitles.add(R.id.Collect);
        mTitles.add(R.id.Setting);
        mTitles.add(R.id.About);
    }

    private void addZhihuDailyNewsFragment() {

        //根据保存的碎片位置显示对应碎片
//        int type = (int) SpUtil.getParam(Constants.NIGHT_CURRENT_FRAG_POS ,TYPE_ZHIHU );
        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.add(R.id.Fragm, mFragments.get(0));
        transaction.commit();

        toolbar.setTitle(mTitles.get(0));

    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(new ZhiHuFragment());
        mFragments.add(new WeChatFragment());
        mFragments.add(new GankFragment());
        mFragments.add(new GoldFragment());
        mFragments.add(new VTEXFragment());
        mFragments.add(new CollectFragment());
        mFragments.add(new SetFragment());
        mFragments.add(new AboutFragment());

        //碎片管理器获取碎片，实现搜索框
        fragment = (WeChatFragment) mFragments.get(TYPE_WECHAT);

    }

    @Override
    protected void initListener() {
        mNv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId != R.id.info_title && itemId != R.id.option_title) {
                    item.setChecked(true);
                    switch (itemId) {
                        case R.id.zhihu:
                            switchFragments(TYPE_ZHIHU);
                            toolbar.setTitle("知乎日报");
                            break;
                        case R.id.wechat:
                            switchFragments(TYPE_WECHAT);
                            toolbar.setTitle("微信精选");
                            break;
                        case R.id.Gank:
                            switchFragments(TYPE_GANK);
                            toolbar.setTitle("干货集中营");
                            break;
                        case R.id.Gold:
                            switchFragments(TYPE_GOLD);
                            toolbar.setTitle("稀土掘金");
                            break;
                        case R.id.VTEX:
                            switchFragments(TYPE_V2EX);
                            toolbar.setTitle("V2EX");
                            break;
                        case R.id.Collect:
                            switchFragments(TYPE_COLLECT);
                            toolbar.setTitle("收藏");
                            break;
                        case R.id.Setting:
                            switchFragments(TYPE_SETTING);
                            toolbar.setTitle("设置");
                            break;
                        case R.id.About:
                            switchFragments(TYPE_ABOUT);
                            toolbar.setTitle("关于");
                            break;
                    }
                    dl.closeDrawer(Gravity.LEFT);
                } else {
                    item.setChecked(false);
                }
                return false;
            }
        });

        //搜索框监听
        search_view.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //提交搜索内容时的监听
                fragment.inserch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //文本发生改变的监听
                //ToastUtil.showShort(newText);
                return false;
            }
        });

        search_view.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //搜索框展开
//                ToastUtil.showShort("展开");
            }

            @Override
            public void onSearchViewClosed() {
                //搜索框关闭
//                ToastUtil.showShort("关闭");
            }
        });

        //显示提示信息
        search_view.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
    }


    private void switchFragments(int type) {
        //本质显示一个framgnet,隐藏一个
        //要显示的fragment
        Fragment fragment = mFragments.get(type);
        //要隐藏的碎片
        Fragment hideFragment = mFragments.get(mLastFragmentPosition);
        FragmentTransaction transaction = mManager.beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(R.id.Fragm, fragment);
        }

        transaction.hide(hideFragment);
        transaction.show(fragment);
        transaction.commit();

        mLastFragmentPosition = type;

        //显示隐藏搜索框
        if (type == TYPE_WECHAT || type == TYPE_GANK) {
            mSearchItem.setVisible(true);
        } else {
            mSearchItem.setVisible(false);
        }
        //切换主题
//        toolbar.setTitle(mTitles.get(type));
    }


    //创建选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        mSearchItem = menu.findItem(R.id.action_search);
        mSearchItem.setVisible(false);
        search_view.setMenuItem(mSearchItem);

        return true;
    }

    /**
     * 按回退键会调用这个方法
     */
    @Override
    public void onBackPressed() {
        if (search_view.isSearchOpen()) {
            search_view.closeSearch();
        } else {
            //记录碎片位置
//            SpUtil.setParam(Constants.NIGHT_CURRENT_FRAG_POS , TYPE_ZHIHU);

//            super.onBackPressed();
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("温馨提示")
                    .setMessage("是否要退出GeekNews？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNeutralButton("取消", null)
                    .show();
        }
    }

}
