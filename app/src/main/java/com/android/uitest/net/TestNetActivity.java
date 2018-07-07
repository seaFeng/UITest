package com.android.uitest.net;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.uitest.R;
import com.android.uitest.net.bean.HistoryBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestNetActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "TestNetActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_net);
        initView();
    }

    private void initView() {
        findViewById(R.id.net_history).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.net_history:
                GitHubService gitHubService = RetrofitClient.retrofit.create(GitHubService.class);
                Call<HistoryBean> call = gitHubService.getHoistoryData(getHoistoryRequestData());
                call.enqueue(new Callback<HistoryBean>() {
                    @Override
                    public void onResponse(Call<HistoryBean> call, Response<HistoryBean> response) {
                        HistoryBean body = response.body();
                        Log.e(TAG,body.result.get(0).des);
                    }

                    @Override
                    public void onFailure(Call<HistoryBean> call, Throwable t) {
                        Log.e(TAG,t.toString());
                    }
                });
                break;
        }
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
