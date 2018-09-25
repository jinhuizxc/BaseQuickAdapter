package com.jh.basequickadapter.quickadapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by jinhui on 2018/9/25.
 * email: 1004260403@qq.com
 */

public class EndlessScrollListener extends RecyclerView.OnScrollListener {

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        BaseQuickAdapter adapter = (BaseQuickAdapter) recyclerView.getAdapter();

        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == layoutManager.getItemCount() && adapter.isHasMore()) {
            //当已经滑到最后一条的时候
            adapter.isLoadingMore();
        }
    }
}
