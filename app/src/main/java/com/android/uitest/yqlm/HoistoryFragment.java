package com.android.uitest.yqlm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.uitest.R;
import com.android.uitest.net.RetrofitClient;
import com.android.uitest.net.bean.HistoryBean;
import com.android.uitest.yqlm.adapter.HistoryAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class HoistoryFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "HoistoryFragment";

    private View rootView;
    @BindView(R.id.history_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.history_swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<HistoryBean.ItemData> list = new ArrayList<>();
    private HistoryAdapter adapter;
    private int month = 10;
    private int day = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_hoistory,container,false);
        ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        initView();
        initData(month,day);
    }

    private void initView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new HistoryAdapter(list,getActivity());
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                day++;
                if (day > getMaxMonthDay(month)) {
                    month++;
                }
                initData(month,day);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initData(int month,int day) {
        RetrofitClient.requestHistoryData(new RetrofitClient.NetCallback<HistoryBean>() {
            @Override
            public void onResponse(Call<HistoryBean> call, Response<HistoryBean> response) {
                HistoryBean bean = response.body();
                if (bean != null) {
                    list.addAll(bean.result);
                    adapter.notifyDataSetChanged();
                } else {
                    // todo 空页面
                }

            }

            @Override
            public void onFailure(Call<HistoryBean> call, Throwable t) {

            }
        },month,day);
    }

    @Override
    public void onClick(View v) {

    }

    private int getMaxMonthDay(int month)  {
        if (month > 12 || month <= 0) {
            //throw new Exception("月份输入错误");
            return 0;
        }

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int days = 30;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            days = 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            days = 30;
        } else if (month == 2 && (year%4 == 0 && year%100 != 0) && year%400 == 0) {
            days = 29;
        } else {
            days = 28;
        }
        return days;
    }
}
