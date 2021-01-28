package com.tengshi.basemodule.base;

import android.content.Context;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 作者 : Adam on 2018/6/15.
 * 邮箱 : itgaojian@163.com
 * 描述 : RecyclerView的适配器基类
 */
public abstract class BaseRecyclerAdapter<T, H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<H> {
    protected Context mContext;
    protected List<T> mData;
    protected OnItemClicklistener<T> mListener;
    protected List<Boolean> mChoose = new ArrayList<>();

    public BaseRecyclerAdapter(Context ctx, List<T> list) {
        this.mData = list;
        this.mContext = ctx;
        for (int i = 0; i < mData.size(); i++) {
            mChoose.add(i, false);
        }
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        return createHolder(parent, viewType);
    }

    /**
     * 创建Holder
     *
     * @return
     */
    public abstract H createHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(H holder, int position) {
        bindHolder(holder, position);
        if (mListener != null) {
            holder.itemView.setOnClickListener(v -> mListener.onItemClickListen(mData.get(position)));
        }
    }

    /**
     * view与数据进行绑定
     *
     * @param h ViewHolder
     * @param i 数据索引
     */
    public abstract void bindHolder(H h, int i);

    /**
     * 更新数据
     *
     * @param list
     */
    public void setData(List<T> list) {
        this.mData = list;
//        mChoose.clear();
//        for (int i = 0; i < mData.size(); i++) {
//            mChoose.add(i, false);
//        }
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return mData;
    }

    /**
     * 插入一条数据
     *
     * @param t 插入的Bean
     */
    public void insertItem(T t) {
        this.mData.add(t);
        notifyDataSetChanged();
    }

    /**
     * 删除一条数据
     * 需要重写Bean的equals方法
     *
     * @param t 要删除的Bean
     */
    public void removeItem(T t) {
        if (mData != null && mData.size() > 0) {
            if (mData.contains(t)) {
                this.mData.remove(t);
                notifyDataSetChanged();
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 设置条目点击事件
     *
     * @param listener
     */
    public void addOnItemClickListener(OnItemClicklistener<T> listener) {
        this.mListener = listener;
    }

    /**
     * 条目点击事件回调
     *
     * @param <T> 将点击条目的Bean返回
     */
    public interface OnItemClicklistener<T> {
        void onItemClickListen(T t);
    }
}
