package com.jh.basequickadapter.quickadapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by jinhui on 2018/9/25.
 * email: 1004260403@qq.com
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> viewSparseArray;
    private View convertView;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

}
