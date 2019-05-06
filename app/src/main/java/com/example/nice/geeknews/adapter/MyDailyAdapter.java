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
import com.example.nice.geeknews.bean.DailyNewsBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import butterknife.OnItemClick;

public class MyDailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<DailyNewsBean.StoriesBean> list = new ArrayList<>();
    private ArrayList<DailyNewsBean.TopStoriesBean> topList = new ArrayList<>();
    private Context context;



    public MyDailyAdapter(Context context) {
        this.context = context;
    }

    public void update(List<DailyNewsBean.StoriesBean> list, List<DailyNewsBean.TopStoriesBean> topList) {
        this.list.clear();
        this.list.addAll(list);
        this.topList.clear();
        this.topList.addAll(topList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        RecyclerView.ViewHolder viewHolder = null;
        if (position == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_banner, null);
            viewHolder = new BannerViewHolder(inflate);
        } else if (position == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_time, null);
            viewHolder = new DateViewHolder(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_list, null);
            viewHolder = new ListViewHolder(inflate);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof BannerViewHolder) {
            //Banner
            BannerViewHolder bannerViewHolder = (BannerViewHolder) viewHolder;
            DailyNewsBean.TopStoriesBean topStoriesBean = topList.get(position);
            bannerViewHolder.banner.setImages(topList)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            DailyNewsBean.TopStoriesBean bean = (DailyNewsBean.TopStoriesBean) path;
                            Glide.with(context).load(bean.getImage()).into(imageView);
                        }
                    }).start();
        } else if (viewHolder instanceof DateViewHolder) {
            //日期
            DateViewHolder dateViewHolder = (DateViewHolder) viewHolder;
            dateViewHolder.tv_time.setText("今日新闻");


        } else if (viewHolder instanceof ListViewHolder) {
            //新闻
            int newPosition = position-1;
            if (topList.size()>0){
                newPosition -= 1;
            }
            ListViewHolder listViewHolder = (ListViewHolder) viewHolder;
            final DailyNewsBean.StoriesBean storiesBean = list.get(newPosition);
            listViewHolder.tv_list.setText(storiesBean.getTitle());
            Glide.with(context).load(storiesBean.getImages().get(0)).into(listViewHolder.img);


            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener!=null){
                        onItemClickListener.onItemClcik(storiesBean);
                    }
                }
            });
        }


    }


    public int getItemViewType(int position) {
        if (topList.size() > 0) {
            if (position == 0) {
                return 0;
            } else if (position == 1) {
                return 1;
            } else {
                return 2;
            }
        } else {
            if (position == 0) {
                return 1;
            } else {
                return 2;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (topList.size() > 0) {
            return 1 + 1 + list.size();
        } else {
            return 1 + list.size();
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        private Banner banner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    class DateViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_time;


        public DateViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView tv_list;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            tv_list = itemView.findViewById(R.id.tv_list);
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClcik(DailyNewsBean.StoriesBean storiesBean);
    }


    private ItemTxetView itemTxetView;

    public void setItemTxetView(ItemTxetView itemTxetView) {
        this.itemTxetView = itemTxetView;
    }

    public interface ItemTxetView{
        void onitem(TextView tv_time);
    }
}
