package com.example.lenovo.text1.net;

/**
 * Created by Lenovo on 2020/5/27.
 */

public interface HomeCallBack<T> {
    void onSuccess(T t);

    void onFail(String erro);
}
