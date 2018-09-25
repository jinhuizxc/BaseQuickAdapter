package com.jh.basequickadapter;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jh.basequickadapter.R;
import com.jh.basequickadapter.example.MovieEntity;
import com.jh.basequickadapter.example.MovieQuickAdapter;
import com.jh.basequickadapter.listener.OnItemClickListener;
import com.jh.basequickadapter.listener.OnPageLoadListener;
import com.jh.basequickadapter.quickadapter.EndlessScrollListener;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * https://github.com/piscessu/BaseQuickAdapter
 */
public class MainActivity extends AppCompatActivity implements Callback<MovieEntity> {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    List<MovieEntity.SubjectsBean> mBeanList;
    static int CURRENT_PAGE = 0;//起始页第一页
    static final int PAGE_COUNT = 50;//每页有50条数据

    MovieQuickAdapter mQuickAdapter;
    LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initAdapter();
        // 获取电影列表
        getMovie(CURRENT_PAGE);
    }

    private void initAdapter() {
        mBeanList = new ArrayList<>();
        mQuickAdapter = new MovieQuickAdapter(this, mBeanList);
        //set EmptyView
        mQuickAdapter.setEmptyView(R.layout.item_empty);
        mQuickAdapter.setOnPageLoadListener(new OnPageLoadListener() {
            @Override
            public void onPageLoad() {
                CURRENT_PAGE++;
                getMovie(CURRENT_PAGE);
            }
        }, PAGE_COUNT);
        //set OnItemClickListener
        mQuickAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toasty.info(MainActivity.this, mBeanList.get(position).getTitle()).show();
            }
        });
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rcv);
        mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        // addOnScrollListener
        recyclerView.addOnScrollListener(new EndlessScrollListener());

        swipeRefreshLayout = findViewById(R.id.srl);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.YELLOW);
        // setOnRefreshListener
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mBeanList.clear();
                        getMovie(CURRENT_PAGE);
                    }
                }, 2000);
            }
        });
    }

    private void getMovie(int page) {
        swipeRefreshLayout.setRefreshing(true);
        RetrofitManager.getService().getTopMovie(page * PAGE_COUNT, PAGE_COUNT).enqueue(this);
    }

    @Override
    public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
        if (recyclerView.getAdapter() == null){
            mBeanList.addAll(response.body().getSubjects());
            recyclerView.setAdapter(mQuickAdapter);
        }else {
            mQuickAdapter.appendList(response.body().getSubjects());
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFailure(Call<MovieEntity> call, Throwable t) {
        swipeRefreshLayout.setRefreshing(false);
    }
}
