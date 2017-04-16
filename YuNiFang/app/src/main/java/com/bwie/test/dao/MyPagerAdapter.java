package com.bwie.test.dao;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwie.test.R;
import com.bwie.test.bean.F1bean;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

/**
 * 类的用途：
 *
 * @author 赵永生
 * @time 2017/4/13 18:53
 */
public class MyPagerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<F1bean.DataBean.Ad1Bean> list;

    public MyPagerAdapter(Context context, ArrayList<F1bean.DataBean.Ad1Bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=View.inflate(context, R.layout.itemf1,null);
        ImageView imageView= (ImageView) view.findViewById(R.id.viewPagerImg1);
        BitmapUtils utils=new BitmapUtils(context);
        utils.display(imageView,list.get(position).getImage());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
