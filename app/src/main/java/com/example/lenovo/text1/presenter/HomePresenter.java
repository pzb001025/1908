package com.example.lenovo.text1.presenter;

import android.util.Log;

import com.example.lenovo.text1.bean.Bean;
import com.example.lenovo.text1.model.HomeModel;
import com.example.lenovo.text1.net.HomeCallBack;
import com.example.lenovo.text1.view.HomeView;

/**
 * Created by Lenovo on 2020/5/27.
 */

public class HomePresenter {
    private HomeView homeView;
    private final HomeModel model;

    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
        model = new HomeModel();
    }

    public void setData() {
        model.setData(new HomeCallBack<Bean>() {
            @Override
            public void onSuccess(Bean bean) {
                homeView.setData(bean);
            }

            @Override
            public void onFail(String erro) {
                Log.d("TAG", "onFail: "+erro);
            }
        });
    }
}
