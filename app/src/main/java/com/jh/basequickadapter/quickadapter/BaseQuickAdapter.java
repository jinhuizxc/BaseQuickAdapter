package com.jh.basequickadapter.quickadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by jinhui on 2018/9/25.
 * email: 1004260403@qq.com
 */

public class BaseQuickAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private int mLayoutResID;
    private List<T> data;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
