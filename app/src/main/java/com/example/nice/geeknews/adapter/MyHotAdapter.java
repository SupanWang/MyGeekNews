package com.example.nice.geeknews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nice.geeknews.R;
import com.example.nice.geeknews.bean.HotBean;

import java.util.ArrayList;
import java.util.List;

public class MyHotAdapter extends RecyclerView.Adapter<MyHotAdapter.ViewHolder> {
    private ArrayList<HotBean.RecentBean> list = new ArrayList<>();
    private Context context;


    public MyHotAdapter(Context context) {
        this.context = context;
    }

    public void update(List<HotBean.RecentBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_list, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        HotBean.RecentBean recentBean = list.get(position);
        viewHolder.tv_list.setText(recentBean.getTitle());
        Glide.with(context).load(recentBean.getThumbnail()).into(viewHolder.img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView tv_list;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tv_list = itemView.findViewById(R.id.tv_list);

        }
    }
}
