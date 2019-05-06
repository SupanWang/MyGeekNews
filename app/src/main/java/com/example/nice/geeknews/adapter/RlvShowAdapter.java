package com.example.nice.geeknews.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.nice.geeknews.R;
import com.example.nice.geeknews.bean.GoldTitleBean;
import com.example.nice.geeknews.widget.TouchCallBack;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author ws
 * Created by asus on 2019/4/18.
 */

public class RlvShowAdapter extends RecyclerView.Adapter implements TouchCallBack{
    private ArrayList<GoldTitleBean> mTitles;


    public RlvShowAdapter(ArrayList<GoldTitleBean> titles) {
        mTitles = titles;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show, null);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;
        final GoldTitleBean bean = mTitles.get(position);
        vh.tv.setText(bean.title);
        vh.sc.setChecked(bean.isChecked);
        vh.sc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bean.isChecked = isChecked;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    //左滑删除
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //交换集合中两个数据的位置
        Collections.swap(mTitles , fromPosition , toPosition);
        //刷新界面，局部刷新，导致索引混乱
        notifyItemMoved(fromPosition , toPosition);
    }

    //上拉拖拽
    @Override
    public void onItemDelete(int position) {
        mTitles.remove(position);
        //局部刷新,索引会混乱，集合越界
        notifyItemRemoved(position);
    }

    class VH extends RecyclerView.ViewHolder {
        private TextView tv;
        private SwitchCompat sc;

        public VH(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.tv);
            sc = (SwitchCompat)itemView.findViewById(R.id.sc);
        }
    }
}
