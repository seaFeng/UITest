package com.android.uitest.yqlm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.uitest.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class BrandsFragment extends Fragment {
    private static final String TAG = "BrandsFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private View rootView;
    private RecyclerView recyclerViewMenu;
    private MenuAdapter menuAdapter;
    private List<String> menuList = new ArrayList<>();
    private RecyclerView recyclerViewContent;

    public static BrandsFragment newInstance(String param1, String param2) {
        BrandsFragment fragment = new BrandsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_brands, container, false);
        initView();
        return rootView;
    }

    private void initView(){
        recyclerViewMenu = rootView.findViewById(R.id.recyclerView_menu);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMenu.setLayoutManager(linearLayoutManager);
        recyclerViewContent = rootView.findViewById(R.id.recyclerView_content);

        menuAdapter = new MenuAdapter(getMenuData());
        menuAdapter.setCallBack(new MenuAdapter.MenuCallBack(){

            @Override
            public void click(int position) {
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                int lastVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                Log.e(TAG,"menuRecyclerView的可见的个数 == " + firstVisibleItemPosition + "     " + lastVisibleItemPosition);

                int scrollCount = position + lastVisibleItemPosition;
                if (scrollCount >= menuList.size()) {
                    scrollCount = menuList.size() - 1;
                }
                recyclerViewMenu.scrollToPosition(scrollCount);
            }
        });
        recyclerViewMenu.setAdapter(menuAdapter);

        //recyclerViewContent.setAdapter();
    }

    private List<String> getMenuData(){
        for (int i = 0;i < 20;i++) {
            menuList.add(String.valueOf(i));
        }
        return menuList;
    }
}
