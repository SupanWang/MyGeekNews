package com.example.nice.geeknews.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.nice.geeknews.R;
import com.example.nice.geeknews.base.BaseApp;
import com.example.nice.geeknews.bean.GirlBean;
import com.example.nice.geeknews.bean.GirlDbBean;
import com.example.nice.geeknews.util.SystemUtil;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class GirlDbAdapter extends RecyclerView.Adapter<GirlDbAdapter.ViewHolder> {

    private ArrayList<GirlDbBean> list = new ArrayList<>();
    private Context context;

    public GirlDbAdapter(Context context) {
        this.context = context;
    }

    public void update(List<GirlDbBean> list) {
        this.list.clear();
        this.list.addAll(list);
//        setImageScale();
        notifyDataSetChanged();
    }

    //计算图片宽高比
//    private void setImageScale() {
//        for (final GirlBean.ResultsBean resultsBean: list) {
//            if (resultsBean.getScale() == 0){
//                Glide.with(context).load(resultsBean.getUrl()).into(new SimpleTarget<Drawable>() {
//                    @Override
//                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                        //宽高比
//                        float scale = resource.getIntrinsicWidth() * 1.0f / resource.getIntrinsicHeight();
//
//                        resultsBean.setScale(scale);
//                        notifyDataSetChanged();
//                    }
//                });
//            }else {
//                notifyDataSetChanged();
//            }
//        }
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_dbgirl, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final GirlDbBean girlDbBean = list.get(position);
//        final GirlBean.ResultsBean resultsBean = list.get(position);
//        //设置图片前需要重新设置图片的宽高
//        int imageWidth = BaseApp.mWidthPixels / 2 - SystemUtil.dp2px(6);
//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewHolder.img.getLayoutParams();
//        layoutParams.width = imageWidth;
//        if (resultsBean.getScale() != 0){
//            layoutParams.height = (int) (imageWidth/resultsBean.getScale());
//        }
//        layoutParams.leftMargin = layoutParams.topMargin = SystemUtil.dp2px(4);
//        viewHolder.img.setLayoutParams(layoutParams);

        RequestOptions options = RequestOptions.circleCropTransform();
        RequestOptions transform = options.transform(new RoundedCornersTransformation(SystemUtil.dp2px(3), 0));
        Glide.with(context).load(girlDbBean.getUrl()).apply(transform).into(viewHolder.img);

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (onItemDbClickListener!=null){
                    onItemDbClickListener.onItemClick(girlDbBean);
                }
                return false;

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
        }
    }

    private OnItemDbClickListener onItemDbClickListener;

    public void setOnItemDbClickListener(OnItemDbClickListener onItemDbClickListener) {
        this.onItemDbClickListener = onItemDbClickListener;
    }

    public interface OnItemDbClickListener{
        void onItemClick(GirlDbBean girlDbBean);
    }
}
