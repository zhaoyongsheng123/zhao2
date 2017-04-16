package com.bwie.test.dao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.test.R;
import com.bwie.test.bean.F1bean;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * 类的用途：
 *
 * @author 赵永生
 * @time 2017/4/14 15:38
 */
public class ReMenRecyclerAdapter extends RecyclerView.Adapter<ReMenRecyclerAdapter.MyViewHolder> {
    private Context context;
    private List<F1bean.DataBean.SubjectsBean.GoodsListBean> list;

    public ReMenRecyclerAdapter(Context context,List<F1bean.DataBean.SubjectsBean.GoodsListBean> list) {
        this.context = context;
        this. list = list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item, null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BitmapUtils utils=new BitmapUtils(context);
        String goods_img = list.get(position).getGoods_img();
        utils.display(holder.imageView,list.get(position).getGoods_img());
        holder.textView.setText(list.get(position).getGoods_name());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
             imageView = (ImageView) itemView.findViewById(R.id.id_index_gallery_item_image);
            textView= (TextView) itemView.findViewById(R.id.id_index_gallery_item_text);
        }

    }
}
