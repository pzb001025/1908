package com.example.lenovo.text1.fragment;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.lenovo.text1.BaseApp;
import com.example.lenovo.text1.R;
import com.example.lenovo.text1.WebViewActivity;
import com.example.lenovo.text1.adapter.RvAdapter;
import com.example.lenovo.text1.bean.Bean;
import com.example.lenovo.text1.bean.RecentBean;
import com.example.lenovo.text1.db.RecentBeanDao;
import com.example.lenovo.text1.presenter.HomePresenter;
import com.example.lenovo.text1.view.HomeView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeView, View.OnClickListener {


    private View view;
    private RecyclerView rlv_home;
    private RvAdapter adapter;
    private HomePresenter presenter;
    private int mPosition;
    private Button btn_que;
    private Button btn_qu;
    private PopupWindow pop;
    private RecentBeanDao mDao;
    private int sPosition;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        presenter = new HomePresenter(this);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        presenter.setData();
    }

    private void initView(View view) {
        rlv_home = (RecyclerView) view.findViewById(R.id.rlv_home);
        rlv_home.setLayoutManager(new LinearLayoutManager(getActivity()));
        rlv_home.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        final ArrayList<RecentBean> list = new ArrayList<>();
        adapter = new RvAdapter(list, getActivity());
        rlv_home.setAdapter(adapter);

        adapter.setOnItemClickListener(new RvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                sPosition = position;
                final RecentBean bean = adapter.list.get(sPosition);
                final Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url", bean.getUrl());
                startActivity(intent);
            }
        });

        adapter.setOnItemLongClickListener(new RvAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                mPosition = position;
                pop();
            }
        });

    }

    private void pop() {
        final View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.view_pop, null);
        pop = new PopupWindow(view1, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new ColorDrawable());
        pop.setOutsideTouchable(true);

        pop.showAtLocation(rlv_home, Gravity.CENTER, 0, 0);
        btn_que = (Button) view1.findViewById(R.id.btn_que);
        btn_que.setOnClickListener(this);
        btn_qu = (Button) view1.findViewById(R.id.btn_qu);
        btn_qu.setOnClickListener(this);
    }

    @Override
    public void setData(Bean bean) {
        adapter.addData(bean.getRecent());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_que:
                final RecentBean bean = adapter.list.get(mPosition);
                mDao = BaseApp.getInstance().getDaoSession().getRecentBeanDao();
                mDao.insertOrReplace(bean);
                adapter.notifyDataSetChanged();
                pop.dismiss();
                break;
            case R.id.btn_qu:
                pop.dismiss();
                break;
        }
    }
}
