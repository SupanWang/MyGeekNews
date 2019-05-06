package com.example.nice.geeknews.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class SimpleItemTouchCallBack extends ItemTouchHelper.Callback {

    private final TouchCallBack mCallBack;
    private boolean mSwipeEnable = true;

    public SimpleItemTouchCallBack(TouchCallBack callBack) {
        this.mCallBack = callBack;
    }

    /*
        返回可以滑动的方向,一般使用makeMovementFlags(int,int)
        或makeFlag(int, int)来构造我们的返回值
         */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

        //允许上下拖拽
        int drag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //允许向左滑动
        int swipe = ItemTouchHelper.LEFT;
        //设置
        return makeMovementFlags(drag , swipe);
    }

    /*
     * 上下拖动item时回调,可以调用Adapter的notifyItemMoved方法来交换两个ViewHolder的位置，
     * 最后返回true，
     * 表示被拖动的ViewHolder已经移动到了目的位置
     */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder viewHolder1) {
        //通知适配器，两个字条目位置发生改变
        mCallBack.onItemMove(viewHolder.getAdapterPosition() , viewHolder1.getAdapterPosition());
        return true;
    }

    /**
     * 当用户左右滑动item时达到删除条件就会调用,一般为一半,条目继续滑动删除,否则弹回
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        mCallBack.onItemDelete(viewHolder.getAdapterPosition());
    }

    /*
    支持长按拖动，默认为true
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    /*
    支持滑动,即可以调用到onSwiped()方法,默认是true
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return mSwipeEnable;
    }

    /**
     * 设置是否支持左滑删除
     * @param enable
     */
    public void setSwipeEnable(boolean enable){
        mSwipeEnable = enable;
    }
}
