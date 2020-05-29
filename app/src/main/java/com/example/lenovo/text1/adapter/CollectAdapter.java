package com.example.lenovo.text1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.text1.R;
import com.example.lenovo.text1.bean.RecentBean;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2020/5/27.
 */

public class CollectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public ArrayList<RecentBean> list;
    private Context context;
    private int TYPE_ONE = 1;
    private int TYPE_TWO = 2;

    public CollectAdapter(ArrayList<RecentBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ONE) {
            return new ViewHolderOne(LayoutInflater.from(context).inflate(R.layout.item_rv, parent, false));
        } else {
            return new ViewHolderTwo(LayoutInflater.from(context).inflate(R.layout.item_rv2, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        RecentBean bean = list.get(position);
        if (itemViewType == TYPE_ONE) {
            ViewHolderOne viewHolderOne = (ViewHolderOne) holder;
            viewHolderOne.tv_title.setText(bean.getTitle());
            Glide.with(context).load(bean.getThumbnail()).into(viewHolderOne.iv_pic1);
        } else {
            ViewHolderTwo viewHolderTwo = (ViewHolderTwo) holder;
            viewHolderTwo.tv_title2.setText(bean.getTitle());
            Glide.with(context).load(bean.getThumbnail()).into(viewHolderTwo.iv_pic2);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return TYPE_ONE;
        } else {
            return TYPE_TWO;
        }
    }

    public static class ViewHolderOne extends RecyclerView.ViewHolder {
        public ImageView iv_pic1;
        public TextView tv_title;

        public ViewHolderOne(View rootView) {
            super(rootView);
            this.iv_pic1 = (ImageView) rootView.findViewById(R.id.iv_pic1);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        }

    }

    public static class ViewHolderTwo extends RecyclerView.ViewHolder {
        public ImageView iv_pic2;
        public TextView tv_title2;

        public ViewHolderTwo(View rootView) {
            super(rootView);
            this.iv_pic2 = (ImageView) rootView.findViewById(R.id.iv_pic2);
            this.tv_title2 = (TextView) rootView.findViewById(R.id.tv_title2);
        }

    }
}
