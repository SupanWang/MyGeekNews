package com.example.nice.geeknews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nice.geeknews.R;
import com.example.nice.geeknews.bean.GankItemBean;

import java.util.ArrayList;
import java.util.List;

public class GanlItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<GankItemBean.ResultsBean> list = new ArrayList<>();
    private Context context;



    public GanlItemAdapter(Context context) {
        this.context = context;
    }

    public void update(List<GankItemBean.ResultsBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        RecyclerView.ViewHolder viewHolder = null;
        if (position == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_tool, null);
            viewHolder = new LeftViewHolder(inflate);
        } else if (position == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_tech, null);
            viewHolder = new RightViewHolder(inflate);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof LeftViewHolder) {

        } else if (viewHolder instanceof RightViewHolder) {
            RightViewHolder rightViewHolder = (RightViewHolder) viewHolder;
            GankItemBean.ResultsBean resultsBean = list.get(position);
            rightViewHolder.tv_tech_author.setText(resultsBean.getDesc());
            rightViewHolder.tv_tech_time.setText(resultsBean.getType());
            rightViewHolder.tv_tech_title.setText(resultsBean.getPublishedAt());
            //圆形图片，需要单独写一个实体类，继承extends AppGlideModule，加注解@GlideModule
            RequestOptions options = RequestOptions.circleCropTransform();
            Glide.with(context).load(resultsBean.getUrl()).apply(options).into(rightViewHolder.iv_tech_icon);

        }
    }


    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class LeftViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_tech_blur;
        private ImageView iv_tech_origin;
        private TextView tv_tech_copyright;
        private AppBarLayout tech_appbar;

        public LeftViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class RightViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_tech_icon;
        private TextView tv_tech_title;
        private TextView tv_tech_author;
        private TextView tv_tech_time;

        public RightViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_tech_icon = itemView.findViewById(R.id.iv_tech_icon);
            tv_tech_title = itemView.findViewById(R.id.tv_tech_title);
            tv_tech_author = itemView.findViewById(R.id.tv_tech_author);
            tv_tech_time = itemView.findViewById(R.id.tv_tech_time);
        }
    }
}
