package com.example.nice.geeknews.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;

import com.example.nice.geeknews.R;
import com.example.nice.geeknews.activity.MainActivity;
import com.example.nice.geeknews.base.BaseFragment;
import com.example.nice.geeknews.base.Constants;
import com.example.nice.geeknews.presenter.EmptyP;
import com.example.nice.geeknews.util.SpUtil;
import com.example.nice.geeknews.util.UIModeUtil;
import com.example.nice.geeknews.view.EmptyV;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetFragment extends BaseFragment<EmptyV, EmptyP> implements EmptyV {

    private SwitchCompat sc2;

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_set;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        sc2 = (SwitchCompat) view.findViewById(R.id.sc2);
    }

    @Override
    protected void initListener() {
        super.initListener();
        sc2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //切换模式,
                //切换夜间模式的时候,Activiyt会重新创建,新建的switchCompat默认是false,会
                //调用这个回调,去掉非人为的回调
                if (buttonView.isPressed()) {
                    UIModeUtil.changeModeUI((MainActivity) getActivity());

                    //保存设置碎片的位置，再次进入之后直接显示碎片
                    SpUtil.setParam(Constants.NIGHT_CURRENT_FRAG_POS, MainActivity.TYPE_SETTING);
                }
            }
        });
    }
}
