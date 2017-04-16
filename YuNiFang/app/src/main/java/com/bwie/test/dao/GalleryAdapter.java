package com.bwie.test.dao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.test.R;
import com.bwie.test.bean.F1bean;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的用途：
 *
 * @author 赵永生
 * @time 2017/4/14 13:45
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {


    private Context context;
    private List<F1bean.DataBean.ActivityInfoBean.ActivityInfoListBean> list;

    public GalleryAdapter(Context context,List<F1bean.DataBean.ActivityInfoBean.ActivityInfoListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item2, null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//       holder.imageView.setImageResource(list.get(position).getGoods_img());
        BitmapUtils  utils=new BitmapUtils(context);
        String goods_img = list.get(position%list.size()).getActivityImg();
        utils.display(holder.imageView,goods_img);
     }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.youhuiImg);
        }

    }

}
