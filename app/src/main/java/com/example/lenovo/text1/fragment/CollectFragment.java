package com.example.lenovo.text1.fragment;


import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.text1.BaseApp;
import com.example.lenovo.text1.R;
import com.example.lenovo.text1.adapter.CollectAdapter;
import com.example.lenovo.text1.adapter.RvAdapter;
import com.example.lenovo.text1.bean.RecentBean;
import com.example.lenovo.text1.db.RecentBeanDao;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectFragment extends Fragment {


    private View view;
    private RecyclerView rlv_collect;
    private CollectAdapter adapter;
    private RecentBeanDao mDao;
    private boolean isView;
    private boolean isData = false;

    public CollectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_collect, container, false);
        isView = true;
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser == true && isView == true && isData == false) {
            initView(view);
        }
    }

    private void initData() {
        mDao = BaseApp.getInstance().getDaoSession().getRecentBeanDao();
        final List<RecentBean> all = mDao.loadAll();
        adapter.list.addAll(all);
        adapter.notifyDataSetChanged();
    }

    private void initView(View view) {
        rlv_collect = (RecyclerView) view.findViewById(R.id.rlv_collect);
        rlv_collect.setLayoutManager(new LinearLayoutManager(getActivity()));
        rlv_collect.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        final ArrayList<RecentBean> list = new ArrayList<>();
        adapter = new CollectAdapter(list, getActivity());
        rlv_collect.setAdapter(adapter);
        initData();
    }
}
