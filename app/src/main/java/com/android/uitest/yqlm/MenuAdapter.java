package com.android.uitest.yqlm;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.uitest.R;

import java.util.List;

/**
 * Created by 张海洋 on 2018-07-01.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuHolder> {
    private List<String> list;
    private MenuCallBack callBack;

    public MenuAdapter(List<String> list){
        this.list = list;
    }

    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MenuHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_menu,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MenuHolder holder, final int position) {
        holder.tvContent.setText(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.click(position);
            }
        });
    }

    public void setCallBack(MenuCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MenuHolder extends RecyclerView.ViewHolder{
        private TextView tvContent;

        public MenuHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.view_recyclerView_menuItem);
        }
    }

    interface MenuCallBack {
        void click(int position);
    }
}
