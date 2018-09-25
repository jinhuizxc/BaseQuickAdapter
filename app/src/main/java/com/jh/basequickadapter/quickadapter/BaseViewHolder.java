package com.jh.basequickadapter.quickadapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jinhui on 2018/9/25.
 * email: 1004260403@qq.com
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;
    private View convertView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.views = new SparseArray<>();
        convertView = itemView;
    }

    public View getView(int viewId) {
        return retrieveView(viewId);
    }

    //根据id检索获得该View对象
    private <T extends View> T retrieveView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public BaseViewHolder setText(int viewId, CharSequence charSequence) {
        TextView textView = retrieveView(viewId);
        textView.setText(charSequence);
        return this;
    }
}
