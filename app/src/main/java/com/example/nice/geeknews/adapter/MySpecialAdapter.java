package com.example.nice.geeknews.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.nice.geeknews.R;
import com.example.nice.geeknews.bean.HotBean;
import com.example.nice.geeknews.bean.SpecialBean;
import com.example.nice.geeknews.util.SystemUtil;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MySpecialAdapter extends RecyclerView.Adapter<MySpecialAdapter.ViewHolder> {
    private ArrayList<SpecialBean.DataBean> list = new ArrayList<>();
    private Context context;



    public MySpecialAdapter(Context context) {
        this.context = context;
    }

    public void update(List<SpecialBean.DataBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_section, null);
        return new ViewHolder(inflate);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        SpecialBean.DataBean dataBean = list.get(position);
        viewHolder.descr.setText(dataBean.getDescription());
        viewHolder.name.setText(dataBean.getName());

        //圆角图片
        RequestOptions options = RequestOptions.bitmapTransform(new RoundedCorners(30));
        Glide.with(context).load(dataBean.getThumbnail()).apply(options).into(viewHolder.img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView descr;
        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            descr = itemView.findViewById(R.id.descr);
            name = itemView.findViewById(R.id.name);

        }
    }
}
