package com.android.uitest;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.uitest.yqlm.BrandsFragment;
import com.android.uitest.yqlm.MenuSelectActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        findViewById(R.id.recyclerView_select).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recyclerView_select:
                MenuSelectActivity.startActivity(this,new Intent(this,MenuSelectActivity.class));
                break;
        }
    }


}
