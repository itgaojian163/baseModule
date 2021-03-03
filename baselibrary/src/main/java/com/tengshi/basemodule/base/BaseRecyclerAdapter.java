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
public abstract class BaseRecyclerAdapter<D> extends RecyclerView.Adapter<BaseViewHolder> {
    protected Context mContext;
    protected List<D> mData;
    protected OnItemClickListener<D> mListener;
    protected List<Boolean> mChoose = new ArrayList<>();

    public BaseRecyclerAdapter(Context ctx, List<D> list) {
        this.mData = list;
        this.mContext = ctx;
        for (int i = 0; i < mData.size(); i++) {
            mChoose.add(i, false);
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createHolder(parent, viewType);
    }

    /**
     * 创建Holder
     *
     * @return
     */
    public abstract BaseViewHolder createHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        bindHolder(holder, position);
        if (mListener != null) {
            holder.itemView.setOnClickListener(v -> mListener.onItemClickListen(mData.get(position), position));
        }
    }

    /**
     * view与数据进行绑定
     *
     * @param h ViewHolder
     * @param i 数据索引
     */
    public abstract void bindHolder(BaseViewHolder h, int i);

    /**
     * 更新数据
     *
     * @param list
     */
    public void setData(List<D> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    public List<D> getData() {
        return mData;
    }

    /**
     * 插入一条数据
     *
     * @param d 插入的Bean
     */
    public void insertItem(D d) {
        this.mData.add(d);
        notifyDataSetChanged();
    }

    /**
     * 删除一条数据
     * 需要重写Bean的equals方法
     *
     * @param d 要删除的Bean
     */
    public void removeItem(D d) {
        if (mData != null && mData.size() > 0) {
            if (mData.contains(d)) {
                this.mData.remove(d);
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
    public void addOnItemClickListener(OnItemClickListener<D> listener) {
        this.mListener = listener;
    }

    /**
     * 条目点击事件回调
     *
     * @param <D> 将点击条目的Bean返回
     */
    public interface OnItemClickListener<D> {
        void onItemClickListen(D d, int pos);
    }
}
