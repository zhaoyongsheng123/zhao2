package com.bwie.test.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bwie.test.dao.MyAdapter;
import com.bwie.test.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private ArrayList<String> list;
    private Button button;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);

        SharedPreferences sp = getSharedPreferences("a", 0);
        boolean flag = sp.getBoolean("flag", true);
        edit = sp.edit();
        if (!flag){
            Intent it=new Intent(this,YindaoActivity.class);
            startActivity(it);
            finish();
        }
        ViewPager viewPager= (ViewPager) findViewById(R.id.viewPager);
        button = (Button) findViewById(R.id.jinru);
        list = new ArrayList<>();
        String url="http://dr.renminkaiguan.com/images/460/timg02/uploaded/i2/18837021583589400/T1VpSaXpVXXXXXXXXX_!!0-item_pic.jpg";
        String url2="http://images.zhiwo.com/product/2014/0804/17687069085422253193.jpg";
        String url3="http://img.txooo.com/2010/07/03/195a288cae701b13dca6336cdf3e0226.jpg";
        list.add(url);
        list.add(url2);
        list.add(url3);
        viewPager.setAdapter(new MyAdapter(this, list));
        viewPager.setOnPageChangeListener(this);
        button.setOnClickListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (list.size()-1==position){
            button.setVisibility(View.VISIBLE);
        }else{
            button.setVisibility(View.GONE);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        edit.putBoolean("flag",false);
        edit.commit();
        Intent it=new Intent(this,ShouActivity.class);
        startActivity(it);
        finish();
    }
}
