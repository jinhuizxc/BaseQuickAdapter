package com.jh.basequickadapter;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jh.basequickadapter.R;

import java.util.List;

/**
 * https://github.com/piscessu/BaseQuickAdapter
 */
public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout srl;
    RecyclerView rcv;
    List<MovieEntity.SubjectsBean> mBeanList;
    static int CURRENT_PAGE = 0;//起始页第一页
    static final int PAGE_COUNT = 50;//每页有50条数据

    MovieQuickAdapter mQuickAdapter;
    LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
