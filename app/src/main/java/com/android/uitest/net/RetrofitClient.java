package com.android.uitest.net;

import com.android.uitest.net.bean.HistoryBean;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 张海洋 on 2018-07-06.
 */
public class RetrofitClient {

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.juheapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static GitHubService service = retrofit.create(GitHubService.class);


 /*   public static GitHubService getGitHubService(){
        return retrofit.create(GitHubService.class);
    }*/

    public static void requestHistoryData(final NetCallback<HistoryBean> callback,int month,int day) {

        Map<String,String> map = new HashMap<>();
        map.put("v","1.0");
        map.put("month",String.valueOf(month));
        map.put("key",Constant.KEY);
        map.put("day",String.valueOf(day));

        Call<HistoryBean> call = service.getHoistoryData(map);
        call.enqueue(new Callback<HistoryBean>() {
            @Override
            public void onResponse(Call<HistoryBean> call, Response<HistoryBean> response) {
                callback.onResponse(call,response);
            }

            @Override
            public void onFailure(Call<HistoryBean> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    public interface NetCallback<T>{
        void onResponse(Call<T> call, Response<T> response);
        void onFailure(Call<T> call, Throwable t);
    }

    // v=1.0&month=10&day=6&key=e5d427da62e36f2b5751bb23de1b66eb
    private Map<String,String> getHoistoryRequestData(){
        Map<String,String> map = new HashMap<>();
        map.put("v","1.0");
        map.put("month","10");
        map.put("key",Constant.KEY);
        map.put("day","1");

        return map;
    }
}
