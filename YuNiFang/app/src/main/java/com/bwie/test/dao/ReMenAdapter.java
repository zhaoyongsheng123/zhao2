package com.bwie.test.dao;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bwie.test.R;
import com.bwie.test.bean.F1bean;


import java.util.List;

/**
 * 类的用途：
 *
 * @author 赵永生
 * @time 2017/4/14 20:43
 */
public class ReMenAdapter extends BaseAdapter {
    private Context context;
    private List<F1bean.DataBean.SubjectsBean> list;

    public ReMenAdapter(Context context, List<F1bean.DataBean.SubjectsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=View.inflate(context, R.layout.itemremen,null);
        ImageView imageView= (ImageView) view.findViewById(R.id.remenImage);
        RecyclerView mRecyclerView= (RecyclerView) view.findViewById(R.id.remenRecycler);
        String image = list.get(position).getImage();
        Glide.with(context).load(image).into(imageView);

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        List<F1bean.DataBean.SubjectsBean.GoodsListBean> goodsList = list.get(position).getGoodsList();
        ReMenRecyclerAdapter adapter=new ReMenRecyclerAdapter(context,goodsList);
        mRecyclerView.setAdapter(adapter);
        return view;
    }
}
