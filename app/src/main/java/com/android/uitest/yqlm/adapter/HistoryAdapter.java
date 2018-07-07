package com.android.uitest.yqlm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.uitest.R;
import com.android.uitest.net.bean.HistoryBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 张海洋 on 2018-07-07.
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<HistoryBean.ItemData> list;
    private Context context;

    public HistoryAdapter(List<HistoryBean.ItemData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recycler_history, parent, false);
        return new HistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        HistoryBean.ItemData data = list.get(position);

        Glide.with(context)
                .load(data.pic)
                .into(holder.iv_content);
        holder.tv_title.setText(data.title);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{
        public ImageView iv_content;
        public TextView tv_title;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            iv_content = itemView.findViewById(R.id.history_iv_content);
            tv_title = itemView.findViewById(R.id.history_tv_title);
        }
    }
}
