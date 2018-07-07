package com.android.uitest.net;

import com.android.uitest.net.bean.HistoryBean;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by 张海洋 on 2018-07-06.
 */
public interface GitHubService {

    @GET("/japi/toh")
    Call<HistoryBean> getHoistoryData(@QueryMap Map<String,String> params);
}
