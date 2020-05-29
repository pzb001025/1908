package com.example.lenovo.text1.net;

import com.example.lenovo.text1.bean.Bean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by Lenovo on 2020/5/27.
 */

public interface ApiService {
    String url = "http://news-at.zhihu.com/";

    @GET("api/4/news/hot")
    Flowable<Bean> getData();
}
