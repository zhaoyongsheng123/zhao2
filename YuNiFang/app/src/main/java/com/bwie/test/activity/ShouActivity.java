package com.bwie.test.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.test.R;
import com.bwie.test.ZhuCeActivity;
import com.bwie.test.fragment1.fragment1;
import com.bwie.test.fragment1.fragment2;
import com.bwie.test.fragment1.fragment3;
import com.bwie.test.fragment1.fragment4;
import com.bwie.test.fragment1.fragment5;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ShouActivity extends AppCompatActivity implements View.OnClickListener {


    private ArrayList<ImageView> listImg;
    private ArrayList<TextView> listText;
    private FragmentManager fm;
    private SlidingMenu menu;
    private LinearLayout gouliner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou);
        initView();
    }

    private void initView() {
        fm = getSupportFragmentManager();
        LinearLayout shouliner= (LinearLayout) findViewById(R.id.shouliner);
        LinearLayout fenliner= (LinearLayout) findViewById(R.id.fenliner);
        LinearLayout meiliner= (LinearLayout) findViewById(R.id.meiliner);
        gouliner = (LinearLayout) findViewById(R.id.gouliner);
        LinearLayout meliner= (LinearLayout) findViewById(R.id.meliner);
        FrameLayout frameLayout= (FrameLayout) findViewById(R.id.framelayout);
        ImageView shouImg= (ImageView) findViewById(R.id.shouImg);
        ImageView fenImg= (ImageView) findViewById(R.id.fenImg);
        ImageView meiImg= (ImageView) findViewById(R.id.meiImg);
        ImageView gouImg= (ImageView) findViewById(R.id.gouImg);
        ImageView meImg= (ImageView) findViewById(R.id.meImg);
        TextView textView= (TextView) findViewById(R.id.shouText);
        TextView textView2= (TextView) findViewById(R.id.meiText);
        TextView textView3= (TextView) findViewById(R.id.fenText);
        TextView textView4= (TextView) findViewById(R.id.gouText);
        TextView textView5= (TextView) findViewById(R.id.meText);
        shouliner.setOnClickListener(this);
        fenliner.setOnClickListener(this);
        meiliner.setOnClickListener(this);
        gouliner.setOnClickListener(this);
        meliner.setOnClickListener(this);
        listImg = new ArrayList<>();
        listImg.add(shouImg);
        listImg.add(meiImg);
        listImg.add(fenImg);
        listImg.add(gouImg);
        listImg.add(meImg);
        listText = new ArrayList<>();
        listText.add(textView);
        listText.add(textView2);
        listText.add(textView3);
        listText.add(textView4);
        listText.add(textView5);
        add(new fragment1(),"f1");
        SetBackgroundColor(R.id.shouImg);
        getColores(R.id.shouText);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shouliner:
                add(new fragment1(),"f1");
                SetBackgroundColor(R.id.shouImg);
                getColores(R.id.shouText);
                break;
            case R.id.fenliner:
                add(new fragment2(),"f2");
                SetBackgroundColor(R.id.fenImg);
                getColores(R.id.fenText);
                break;
            case R.id.meiliner:
                add(new fragment3(),"f3");
                SetBackgroundColor(R.id.meiImg);
                getColores(R.id.meiText);
                break;
            case R.id.gouliner:
                add(new fragment4(),"f4");
                SetBackgroundColor(R.id.gouImg);
                getColores(R.id.gouText);
                cehHua();
                break;
            case R.id.meliner:
                add(new fragment5(),"f5");
                SetBackgroundColor(R.id.meImg);
                getColores(R.id.meText);
                break;
        }
    }
    private void SetBackgroundColor(int id) {
        for (int i = 0; i < listImg.size(); i++) {
            if (listImg.get(i).getId() == id) {
                listImg.get(i).setEnabled(false);
            } else {
                listImg.get(i).setEnabled(true);
            }
        }
    }

    private void add(Fragment fragment, String tag) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.framelayout, fragment, tag);
        ft.commit();
    }


    public void getColores(int id) {
        for (int i = 0; i < listText.size(); i++) {
            if (listText.get(i).getId() == id) {
                listText.get(i).setTextColor(Color.RED);
            } else {
                listText.get(i).setTextColor(Color.BLACK);
            }
        }
    }
    private void cehHua() {
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.RIGHT);
//        //设置侧滑方向
//        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置整个屏幕都能滑出
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//        //设置整个屏幕不让滑出
//        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
//        //设置侧滑宽度
//        menu.setBehindOffset(200);
        //让侧滑依附到activity上
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);


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

        menu.toggle();

    }
}