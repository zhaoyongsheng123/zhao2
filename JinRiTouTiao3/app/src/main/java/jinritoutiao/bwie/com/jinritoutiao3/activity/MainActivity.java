package jinritoutiao.bwie.com.jinritoutiao3.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;
import jinritoutiao.bwie.com.jinritoutiao3.R;
import jinritoutiao.bwie.com.jinritoutiao3.activity.ShouActivity;
import jinritoutiao.bwie.com.jinritoutiao3.activity.YindaoActivity;
import jinritoutiao.bwie.com.jinritoutiao3.dao.MyAdapter;

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
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
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
        String url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489204799170&di=85602ba23057ba1177189e26d80a9fb9&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019e0d55b0609432f875495e135fd0.png";
        String url2="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489204799175&di=d95e8c01f11cf07b1d2c052616c41b61&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01cbb85638469e6ac7259e0f160c58.jpg";
        String url3="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489204839060&di=233542af392eb34749549eaecb836ddf&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F011edf5661602e6ac725b2c85b6f0b.jpg";
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
