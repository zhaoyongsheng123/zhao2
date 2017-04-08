package jinritoutiao.bwie.com.jinritoutiao3.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

import jinritoutiao.bwie.com.jinritoutiao3.R;
import jinritoutiao.bwie.com.jinritoutiao3.fragment.Fragment1;
import jinritoutiao.bwie.com.jinritoutiao3.fragment.Fragment2;
import jinritoutiao.bwie.com.jinritoutiao3.fragment.Fragment3;

public class ShouActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView shouImg;
    private ImageView yangImg;
    private ImageView guanImg;
    private TextView shouText;
    private TextView yangText;
    private TextView guanText;
    private ArrayList<ImageView> listImg;
    private ArrayList<TextView> listText;
    private FrameLayout frameLayout;
    private FragmentManager fm;
    private ImageView cehuaimg;
    private ImageView souSuo;
    private SlidingMenu menu;
    private TextView biaoti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou);
        initView();
    }

    private void initView() {
        fm = getSupportFragmentManager();
        LinearLayout shouliner = (LinearLayout) findViewById(R.id.shouliner);
        LinearLayout yiangliner = (LinearLayout) findViewById(R.id.yiangliner);
        LinearLayout guanliner = (LinearLayout) findViewById(R.id.guanliner);
        shouliner.setOnClickListener(this);
        yiangliner.setOnClickListener(this);
        guanliner.setOnClickListener(this);
        frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        shouImg = (ImageView) findViewById(R.id.shouImg);
        yangImg = (ImageView) findViewById(R.id.yangImg);
        guanImg = (ImageView) findViewById(R.id.guanImg);
        cehuaimg = (ImageView) findViewById(R.id.cehuaImg);
        souSuo = (ImageView) findViewById(R.id.souSuoImg);
        shouText = (TextView) findViewById(R.id.shouText);
        yangText = (TextView) findViewById(R.id.yangText);
        guanText = (TextView) findViewById(R.id.guanText);
        biaoti = (TextView) findViewById(R.id.biaoti);
        listImg = new ArrayList<>();
        listImg.add(shouImg);
        listImg.add(yangImg);
        listImg.add(guanImg);
        listText = new ArrayList<>();
        listText.add(shouText);
        listText.add(yangText);
        listText.add(guanText);
        cehHua();

        add(new Fragment1(), "f1");
        SetBackgroundColor(R.id.shouImg);
        getColores(R.id.shouText);

    }

    private void cehHua() {
        menu = new SlidingMenu(ShouActivity.this);
        menu.setMode(SlidingMenu.LEFT);
        //设置侧滑方向
        //slidingMenu.setMode(SlidingMenu.LEFT);
        //设置整个屏幕都能滑出
        //  slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置整个屏幕不让滑出
        //  slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        //设置侧滑宽度
        menu.setBehindOffset(200);
        //让侧滑依附到activity上
        menu.attachToActivity(ShouActivity.this, SlidingMenu.SLIDING_CONTENT);
        //设置侧滑布局

        menu.setMenu(R.layout.activity_ce_huas);
        //menu.findViewById(R.layout.activity_ce_huas);
//        //fragment管理器
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        //开启事务
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        //替换
//        transaction.replace(R.id.cehuaFragment, new CeHua());
//        //提交
//        transaction.commit();
//                getSupportFragmentManager().beginTransaction().replace(R.id.frag1, new Fragment1()).commit();

        cehuaimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShouActivity.this, "eeeeeeeee", Toast.LENGTH_SHORT).show();
                menu.toggle();
            }
        });
        getCeHuaActivityss();
    }

    private void SetBackgroundColor(int id) {
        for (int i = 0; i < listImg.size(); i++) {
            if (listImg.get(i).getId() == id) {
                listImg.get(i).setEnabled(true);
            } else {
                listImg.get(i).setEnabled(false);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shouliner:
                Toast.makeText(this, "sssssssss", Toast.LENGTH_SHORT).show();
                add(new Fragment1(), "f1");
                SetBackgroundColor(R.id.shouImg);
                getColores(R.id.shouText);
                biaoti.setText("今日头条");
                break;
            case R.id.yiangliner:
                Toast.makeText(this, "aaaaaaaaaaa", Toast.LENGTH_SHORT).show();
                add(new Fragment2(), "f2");
                SetBackgroundColor(R.id.yangImg);
                getColores(R.id.yangText);
                biaoti.setText("阳光宽频");
                break;
            case R.id.guanliner:
                Toast.makeText(this, "ffffffffffff", Toast.LENGTH_SHORT).show();
                add(new Fragment3(), "f3");
                SetBackgroundColor(R.id.guanImg);
                getColores(R.id.guanText);
                biaoti.setText("我的关注");
                break;
        }
    }


    public void getCeHuaActivityss() {

    }
}
