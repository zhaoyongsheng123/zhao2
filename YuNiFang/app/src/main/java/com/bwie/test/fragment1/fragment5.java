package com.bwie.test.fragment1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bwie.test.R;
import com.bwie.test.bean.MeListBean;
import com.bwie.test.dao.FAdapter;
import com.bwie.test.dao.ListF5Adapter;

import java.util.ArrayList;

/**
 * 类的用途：
 *
 * @author 赵永生
 * @time 2017/4/11 19:32
 */
public class fragment5 extends Fragment {

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment5,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.meListView);
        getlistView();
    }

    public void getlistView() {
        ArrayList<MeListBean> list=new ArrayList<>();
        list.add(new MeListBean(R.mipmap.my_order_icon,"我的订单"));
        list.add(new MeListBean(R.mipmap.my_invite_gift_icon,"我的订单"));
        list.add(new MeListBean(R.mipmap.my_face_test_icon,"我的订单"));
        list.add(new MeListBean(R.mipmap.exchange_area_icon,"我的订单"));
        list.add(new MeListBean(R.mipmap.my_coupon_icon,"我的订单"));
        list.add(new MeListBean(R.mipmap.my_lottery_icon,"我的订单"));
        list.add(new MeListBean(R.mipmap.my_collection_icon,"我的订单"));
        list.add(new MeListBean(R.mipmap.my_order_icon,"我的订单"));
        listView.setAdapter(new FAdapter(getActivity(),list));

    }
}
