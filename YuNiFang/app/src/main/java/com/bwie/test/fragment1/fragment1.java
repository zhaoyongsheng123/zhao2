package com.bwie.test.fragment1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bwie.test.DengLuActivity;
import com.bwie.test.R;
import com.bwie.test.SaoMaActivity;
import com.bwie.test.WebViewActivity;
import com.bwie.test.ZhuCeActivity;
import com.bwie.test.activity.ShouActivity;
import com.bwie.test.bean.F1bean;
import com.bwie.test.bean.MeSqlBean;
import com.bwie.test.dao.GalleryAdapter;
import com.bwie.test.dao.MyPagerAdapter;
import com.bwie.test.dao.ReMenAdapter;
import com.bwie.test.dao.RecyclerAdapter;
import com.bwie.test.mesql.SqlDiao;
import com.bwie.test.view.MyListview;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.BitmapUtils;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 类的用途：
 *
 * @author 赵永生
 * @time 2017/4/11 19:32
 */
public class fragment1 extends Fragment  {

    private String url="http://m.yunifang.com/yunifang/mobile/home?random=84831&encode=9dd34239798e8cb22bf99a75d1882447";
    private ImageView denglu;
    public static SlidingMenu menu;
    private EditText editTextZH;
    private EditText editTextMIMA;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {

                    int currentItem = viewPager.getCurrentItem();
                    currentItem++;
                    viewPager.setCurrentItem(currentItem% ad1.size());
                    handler.sendEmptyMessageDelayed(0,3000);


            }
        }
    };
    private ViewPager viewPager;
    private ArrayList<F1bean.DataBean.Ad1Bean> ad1;
    private ImageView qiandaoimg;
    private ImageView jifenImg;
    private ImageView yuehuanImg;
    private ImageView zhenweiImg;
    private ArrayList<LinearLayout> listImg;
    private List<F1bean.DataBean.Ad5Bean> ad5;
    private int id;
    private int [] s=new int[]{0,1,2,3};
    private RecyclerView mRecyclerView;
    private F1bean f1bean;
    private ArrayList<F1bean.DataBean.BestSellersBean> bestSellers;
    private List<F1bean.DataBean.BestSellersBean.GoodsListBeanX> goodsList;
    private RecyclerView mRecyclerView2;
    private List<F1bean.DataBean.ActivityInfoBean.ActivityInfoListBean> activityInfoList;
    private MyListview listView;
    private ImageView saoMa;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment1,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        denglu = (ImageView) view.findViewById(R.id.denglu);
        saoMa = (ImageView) view.findViewById(R.id.saoMa);
        viewPager = (ViewPager) view.findViewById(R.id.shouViewPager);
        listView = (MyListview) view.findViewById(R.id.listViewReMem);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview_horizontal);
        mRecyclerView2 = (RecyclerView) view.findViewById(R.id.youHuiHuoDong);
        LinearLayout linearLayout= (LinearLayout) view.findViewById(R.id.qiandao);
        LinearLayout linearLayout2= (LinearLayout) view.findViewById(R.id.jifen);
        LinearLayout linearLayout3= (LinearLayout) view.findViewById(R.id.yuehuan);
        LinearLayout linearLayout4= (LinearLayout) view.findViewById(R.id.zhenwei);
        qiandaoimg = (ImageView) view.findViewById(R.id.qiandaoimg);
        jifenImg = (ImageView) view.findViewById(R.id.jifenImg);
        yuehuanImg = (ImageView) view.findViewById(R.id.yuehuanImg);
        zhenweiImg = (ImageView) view.findViewById(R.id.zhenweiImg);
        listImg = new ArrayList<>();
        listImg.add(linearLayout);
        listImg.add(linearLayout2);
        listImg.add(linearLayout3);
        listImg.add(linearLayout4);
        for (int i = 0; i < listImg.size(); i++) {
            id =i;
            listImg.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent it=new Intent(getActivity(), WebViewActivity.class);

                    it.getIntExtra("s", s[id]);
                    it.putExtra("url",url);
                   // it.putExtra("s",id);
                    startActivity(it);
                }
            });
        }

        getData();
        cehHua();
        getsaoMa();
    }
    public void getData() {

        //获取请求队列
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest request = new StringRequest(url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson=new Gson();
                f1bean = gson.fromJson(s, F1bean.class);
                bestSellers = (ArrayList<F1bean.DataBean.BestSellersBean>) f1bean.getData().getBestSellers();
                for (int i = 0; i < bestSellers.size(); i++) {
                    goodsList = bestSellers.get(i).getGoodsList();

                }
                activityInfoList = f1bean.getData().getActivityInfo().getActivityInfoList();

                ad5 = f1bean.getData().getAd5();
                for (int i = 0; i < ad5.size() ; i++) {
                    BitmapUtils utils=new BitmapUtils(getActivity());
                    utils.display(qiandaoimg, ad5.get(0).getImage());
                    utils.display(jifenImg, ad5.get(1).getImage());
                    utils.display(yuehuanImg, ad5.get(2).getImage());
                    utils.display(zhenweiImg, ad5.get(3).getImage());

                }
                getRecycler();
                getYouhui();
                ad1 = (ArrayList<F1bean.DataBean.Ad1Bean>) f1bean.getData().getAd1();
                List<F1bean.DataBean.SubjectsBean> subjects = f1bean.getData().getSubjects();

                listView.setAdapter(new ReMenAdapter(getActivity(),subjects));
                viewPager.setAdapter(new MyPagerAdapter(getActivity(),ad1));

                handler.sendEmptyMessageDelayed(0,3000);

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(request);

    }


    private void cehHua() {
        menu = new SlidingMenu(getActivity());
        menu.setMode(SlidingMenu.RIGHT);
//        //设置侧滑方向
//        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置整个屏幕都能滑出
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//        //设置整个屏幕不让滑出
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
//        //设置侧滑宽度
//        menu.setBehindOffset(200);
        //让侧滑依附到activity上
        menu.attachToActivity(getActivity(), SlidingMenu.SLIDING_CONTENT);


        //设置侧滑布局
        //直接使用Activity布局
        menu.setMenu(R.layout.activity_deng_lu);
        menu.getMenu().findViewById(R.id.dengluFanHui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fragment1.menu!=null&&fragment1.menu.isMenuShowing()) {
                    fragment1.menu.toggle(false);
                }
            }
        });
        //点击按钮侧滑
        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "ddddddddddddddddddd", Toast.LENGTH_SHORT).show();
                menu.toggle();
            }
        });
        TextView zhuCe= (TextView) menu.findViewById(R.id.zhuCe);
        zhuCe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(),ZhuCeActivity.class);
                startActivity(it);
            }
        });
        getViews();
    }

    public void getViews() {
        editTextZH = (EditText) menu.findViewById(R.id.editTextZH);
        editTextMIMA = (EditText) menu.findViewById(R.id.editTextMIMA);
        Button denglubut= (Button) menu.findViewById(R.id.denglubut);
        denglubut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SqlDiao diao=new SqlDiao(getActivity());
                ArrayList<MeSqlBean> select = diao.select();
                for (int i = 0; i < select.size(); i++) {
                    String zhanghao = select.get(i).getZhanghao();
                    String mima = select.get(i).getMima();
                    String zhang = editTextZH.getText().toString().trim();
                    String mi = editTextMIMA.getText().toString().trim();
                    if (!zhang.equals(zhanghao)||!mi.equals(mima)){

                        Toast.makeText(getActivity(), "账号、密码有误，请从新输入", Toast.LENGTH_SHORT).show();
                    }else {
                        if(fragment1.menu!=null&&fragment1.menu.isMenuShowing()) {
                            fragment1.menu.toggle(false);
                        }

                    }
                }
            }
        });
    }


    public void getRecycler() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器

        RecyclerAdapter galleryAdapter=new RecyclerAdapter(getActivity(),goodsList);

        mRecyclerView.setAdapter(galleryAdapter);
    }
    public void getYouhui(){
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView2.setLayoutManager(linearLayoutManager);
        //设置适配器
         GalleryAdapter galleryAdapter = new GalleryAdapter(getActivity(),activityInfoList);
        mRecyclerView2.setAdapter(galleryAdapter);
    }

    public void getsaoMa() {
        saoMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getActivity(), SaoMaActivity.class);
                startActivity(it);
            }
        });
    }
}
