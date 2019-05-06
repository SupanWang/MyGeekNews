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
import com.bumptech.glide.request.RequestOptions;
import com.example.nice.geeknews.R;
import com.example.nice.geeknews.bean.V2exListBean;

import java.util.ArrayList;
import java.util.List;

public class V2exListAdapter extends RecyclerView.Adapter<V2exListAdapter.ViewHolder> {

    private ArrayList<V2exListBean> list = new ArrayList<>();
    private Context context;


    public V2exListAdapter(Context context) {
        this.context = context;
    }

    public void update(List<V2exListBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_v2ex, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        V2exListBean v2exListBean = list.get(position);
        viewHolder.reply.setText(v2exListBean.reply);
        viewHolder.title.setText(v2exListBean.title);
        viewHolder.cont.setText(v2exListBean.topicText);
        viewHolder.text.setText(v2exListBean.secTab);
        //圆形图片，需要单独写一个实体类，继承extends AppGlideModule，加注解@GlideModule
        RequestOptions options = RequestOptions.circleCropTransform();
        Glide.with(context).load("http:"+v2exListBean.img).apply(options).into(viewHolder.img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView cont;
        private TextView reply;
        private TextView text;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            reply = itemView.findViewById(R.id.reply);
            text = itemView.findViewById(R.id.text);
            cont = itemView.findViewById(R.id.cont);
        }
    }
}
