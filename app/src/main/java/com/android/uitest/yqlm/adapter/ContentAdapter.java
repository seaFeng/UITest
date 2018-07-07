package com.android.uitest.yqlm.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.uitest.R;

import java.util.List;

/**
 * Created by 张海洋 on 2018-07-03.
 */
public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder> {
    private List<String> list;

    public ContentAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ContentViewHolder(inflater.inflate(R.layout.view_recycler_content,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ContentViewHolder extends RecyclerView.ViewHolder{
        private TextView tvContent;

        public ContentViewHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.view_recyclerView_menuItem);
        }
    }
}
