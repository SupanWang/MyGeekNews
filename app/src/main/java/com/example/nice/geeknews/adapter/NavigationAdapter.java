package com.example.nice.geeknews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nice.geeknews.R;
import com.example.nice.geeknews.bean.NavigationBean;
import com.example.nice.geeknews.util.ToastUtil;
import com.example.nice.geeknews.widget.FlowLayout;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {

    private ArrayList<ArrayList<String>> list = new ArrayList<>();

    private Context context;
    private FlowLayout fl;


    public NavigationAdapter(Context context) {
        this.context = context;
    }

    public void update(List<ArrayList<String>> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_navigation, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        initText(position);
    }


    private void initText(int position) {
        for (int i = 0; i < list.get(position).size(); i++) {
            //获取视图,视图可以自定义,可以添加自己想要的效果
            TextView label = (TextView) View.inflate(context ,R.layout.item_label, null);
            //获取数据
            final String navigationBean = list.get(position).get(i);
            //绑定数据
            label.setText(navigationBean);

            label.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showToast(navigationBean);
                }
            });

            //加到容器中,parent是FlowLayout
            fl.addView(label);
        }
    }

    private void showToast(String data) {
        ToastUtil.showShort(data);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fl = itemView.findViewById(R.id.fl);
        }
    }
}
