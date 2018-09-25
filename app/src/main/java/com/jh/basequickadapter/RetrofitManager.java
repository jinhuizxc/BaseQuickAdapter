package com.jh.basequickadapter;

import com.jh.basequickadapter.example.MovieEntity;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jinhui on 2018/9/25.
 * email: 1004260403@qq.com
 */

public class RetrofitManager {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.douban.com/v2/movie/";

    public static RetrofitService getService() {
        if (retrofit == null){
            synchronized (RetrofitManager.class){
                if (retrofit == null){
                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit.create(RetrofitService.class);
    }

    interface RetrofitService{
        /**
         * @param start 起始index
         * @param count 每页多少条数据
         * @return 返回查询结果列表
         */
        @GET("top250")
        Call<MovieEntity> getTopMovie(@Query("start") int start,
                                      @Query("count") int count);

    }
}
