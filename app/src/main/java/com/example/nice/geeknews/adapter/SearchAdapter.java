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
import com.example.nice.geeknews.bean.SearchBean;
import com.example.nice.geeknews.bean.SelectionBean;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private ArrayList<SearchBean.NewslistBean> list = new ArrayList<>();
    private Context context;



    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void update(List<SearchBean.NewslistBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_select, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        SearchBean.NewslistBean newslistBean = list.get(position);
        viewHolder.tv_list.setText(newslistBean.getTitle());
        viewHolder.tv_name.setText(newslistBean.getDescription());
        viewHolder.tv_time.setText(newslistBean.getCtime());
        Glide.with(context).load(newslistBean.getPicUrl()).into(viewHolder.img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView tv_list;
        private TextView tv_name;
        private TextView tv_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tv_list = itemView.findViewById(R.id.tv_list);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_time);

        }
    }
}
