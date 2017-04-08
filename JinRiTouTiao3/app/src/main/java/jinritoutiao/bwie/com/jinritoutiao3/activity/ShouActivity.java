package jinritoutiao.bwie.com.jinritoutiao3.activity;

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

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.SnsPlatform;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import jinritoutiao.bwie.com.jinritoutiao3.R;
import jinritoutiao.bwie.com.jinritoutiao3.fragment.Fragment1;
import jinritoutiao.bwie.com.jinritoutiao3.fragment.Fragment2;
import jinritoutiao.bwie.com.jinritoutiao3.fragment.Fragment3;
import jinritoutiao.bwie.com.jinritoutiao3.utils.NetWorkUtils;

public class ShouActivity extends AppCompatActivity implements View.OnClickListener {

    private int theme=R.style.AppTheme;
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private SHARE_MEDIA[] displaylist = new SHARE_MEDIA[] {SHARE_MEDIA.WEIXIN,
            SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA, SHARE_MEDIA.QQ,
            SHARE_MEDIA.QZONE, SHARE_MEDIA.DOUBAN};
    //private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ};
    private ImageView shouImg;
    private ImageView yangImg;
    private ImageView guanImg;
    private TextView shouText;
    private TextView yangText;
    private TextView guanText;
    private ArrayList<ImageView> listImg = new ArrayList<>();
    private ArrayList<TextView> listText;
    private FrameLayout frameLayout;
    private FragmentManager fm;
    private ImageView cehuaimg;
    private ImageView souSuo;
    private SlidingMenu menu;
    private TextView biaoti;
    private ImageView shoujidenglu;
    private ImageView yejianImg;
    private TextView textYejian;
    private ImageView qqdenglu;
    private ImageView shoujidenglu1;
    private ImageView touxiangYin;
    private TextView textName;
    private ImageView weibodenglu;
    private String iconurl;
    private String name;
    private boolean flag;
    private SharedPreferences.Editor edit;
    private SharedPreferences sp;
    private String iconurles;
    private String namees;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            theme=savedInstanceState.getInt("theme");
            setTheme(theme);
        }
        setContentView(R.layout.activity_shou);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //手势关闭
        shouShiGuanbi();
        sp = getSharedPreferences("s", 0);
        edit = sp.edit();
        flag = sp.getBoolean("flag", true);
        ConnectivityManager mConnectivityManager = (ConnectivityManager) this
                         .getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            initView();
        }else {
            Toast.makeText(ShouActivity.this, "网络未连接，请检查网络", Toast.LENGTH_SHORT).show();
            initView();
        }

        if (!flag) {
            iconurles = sp.getString("iconurl", "iconurl");
            namees = sp.getString("name", "name");
            ImageOptions options = new ImageOptions.Builder().setCircular(true).setCrop(true).setSize(100, 100).setLoadingDrawableId(R.mipmap.ic_launcher).build();
            ImageOptions options1 = new ImageOptions.Builder().setCircular(true).setCrop(true).setSize(40, 40).setLoadingDrawableId(R.mipmap.ic_launcher).build();
            x.image().bind(touxiangYin, iconurles,options);
            x.image().bind(cehuaimg, iconurles,options1);

            textName.setText(name);
            qqdenglu.setVisibility(View.GONE);
            shoujidenglu.setVisibility(View.GONE);
            weibodenglu.setVisibility(View.GONE);
            touxiangYin.setVisibility(View.VISIBLE);

        }
        SharedPreferences spd = getSharedPreferences("d", 0);
        String imagePath = spd.getString("imagePath", "imagePath");
        String yongHuMing = spd.getString("yongHuMing", "yongHuMing");
        ImageOptions options = new ImageOptions.Builder().setCircular(true).setCrop(true).setSize(100, 100).setLoadingDrawableId(R.mipmap.ic_launcher).build();
        ImageOptions options1 = new ImageOptions.Builder().setCircular(true).setCrop(true).setSize(40, 40).setLoadingDrawableId(R.mipmap.ic_launcher).build();
        x.image().bind(touxiangYin, imagePath,options);
        x.image().bind(cehuaimg, imagePath,options1);

        textName.setText(yongHuMing);
        if(theme== R.style.NightAppTheme){
            yejianImg.setImageResource(R.mipmap.nighticon_profile);
            textYejian.setText("夜间");
        }else
        {
            yejianImg.setImageResource(R.mipmap.dayicon_profile);
            textYejian.setText("日间");
        }

    }
    //手势关闭
    private void shouShiGuanbi() {
        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                // if (Math.abs(e1.getRawX() - e2.getRawX()) > 250) {
                // // System.out.println("水平方向移动距离过大");
                // return true;
                // }
                if (Math.abs(velocityY) < 100) {
                    // System.out.println("手指移动的太慢了");
                    return true;
                }

                // 手势向下 down
                if ((e2.getRawY() - e1.getRawY()) > 200) {
                    finish();//在此处控制关闭
                    return true;
                }
                // 手势向上 up
                if ((e1.getRawY() - e2.getRawY()) > 200) {
//                    Intent it=new Intent(MainActivity.this,Main2Activity.class);
//                    startActivity(it);
                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }
    //2.让手势识别器 工作起来
    //当activity被触摸的时候调用的方法.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    private void initView() {
        fm = getSupportFragmentManager();
        LinearLayout shouliner = (LinearLayout) findViewById(R.id.shouliner);
        LinearLayout yiangliner = (LinearLayout) findViewById(R.id.yiangliner);
        LinearLayout guanliner = (LinearLayout) findViewById(R.id.guanliner);
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
        listImg.add(shouImg);
        listImg.add(yangImg);
        listImg.add(guanImg);

        listText = new ArrayList<>();
        listText.add(shouText);
        listText.add(yangText);
        listText.add(guanText);
        //侧滑界面
        cehHua();

        add(new Fragment1(), "f1");
        SetBackgroundColor(R.id.shouImg);
        getColores(R.id.shouText);
        shouliner.setOnClickListener(this);
        yiangliner.setOnClickListener(this);
        guanliner.setOnClickListener(this);

        getCeHuaActivityss();
        //手机登录
        shouJiDenglu();
        //日夜模式转换
        getRiYeZhuan();
        //listView展示
        getlistVIew();
        souSuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(ShouActivity.this, SouSuoActivity.class);
                startActivity(it);
            }
        });
        touxiangYin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(ShouActivity.this, ZiLiaoActivity.class);
                it.putExtra("iconurl",iconurles);
                it.putExtra("name",namees);
                startActivityForResult(it,2);
            }
        });
    }

    private void shouJiDenglu() {
        shoujidenglu = (ImageView) findViewById(R.id.shoujidenglu);
        shoujidenglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开注册页面
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
            // 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            @SuppressWarnings("unchecked")
                            HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            String phone = (String) phoneMap.get("phone");

            // 提交用户信息（此方法可以不调用）
                            //registerUser(country, phone);
                        }
                    }
                });
                registerPage.show(ShouActivity.this);
            }
        });
    }

    private void cehHua() {
        menu = new SlidingMenu(ShouActivity.this);
        menu.setMode(SlidingMenu.LEFT);
//        //设置侧滑方向
//        slidingMenu.setMode(SlidingMenu.LEFT);
//        //设置整个屏幕都能滑出
//        //设置整个屏幕不让滑出
//        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        //设置侧滑宽度
        menu.setBehindOffset(200);
        //让侧滑依附到activity上
        menu.attachToActivity(ShouActivity.this, SlidingMenu.SLIDING_CONTENT);


        //设置侧滑布局
        //直接使用Activity布局
        menu.setMenu(R.layout.activity_ce_huas);


//        //fragment管理器、使用FrameLayout布局
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        //开启事务
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        //替换
//        transaction.replace(R.id.cehuaFragment, new CeHua());
//        //提交
//        transaction.commit();
//                getSupportFragmentManager().beginTransaction().replace(R.id.frag1, new Fragment1()).commit();
        //点击按钮侧滑
        cehuaimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShouActivity.this, "eeeeeeeee", Toast.LENGTH_SHORT).show();
                menu.toggle();
            }
        });

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
                add(new Fragment1(), "f1");
                SetBackgroundColor(R.id.shouImg);
                getColores(R.id.shouText);
                biaoti.setText("今日头条");
                break;
            case R.id.yiangliner:
                add(new Fragment2(), "f2");
                SetBackgroundColor(R.id.yangImg);
                getColores(R.id.yangText);
                biaoti.setText("阳光宽频");
                break;
            case R.id.guanliner:
                add(new Fragment3(), "f3");
                SetBackgroundColor(R.id.guanImg);
                getColores(R.id.guanText);
                biaoti.setText("我的关注");
                break;
        }
    }


    public void getCeHuaActivityss() {
        qqdenglu = (ImageView) findViewById(R.id.qqdenglu);
        touxiangYin = (ImageView) findViewById(R.id.touxiangYin);
        weibodenglu = (ImageView) findViewById(R.id.weibodenglu);
        textName = (TextView) findViewById(R.id.textName);
        initPlatforms();


    }


    private void initPlatforms() {
        for (SHARE_MEDIA e : displaylist) {
            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
                platforms.add(e.toSnsPlatform());
            }
        }
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);

            Toast.makeText(ShouActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ShouActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ShouActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
    //登录的监听
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "Authorize succeed"+data.toString(), Toast.LENGTH_SHORT).show();
            iconurl = data.get("iconurl");
            name = data.get("name");
            ImageOptions options = new ImageOptions.Builder().setCircular(true).setCrop(true).setSize(100, 100).setLoadingDrawableId(R.mipmap.ic_launcher).build();
            ImageOptions options1 = new ImageOptions.Builder().setCircular(true).setCrop(true).setSize(40, 40).setLoadingDrawableId(R.mipmap.ic_launcher).build();
            x.image().bind(touxiangYin, iconurl,options);
            x.image().bind(cehuaimg, iconurl,options1);
            textName.setText(name);
            if (flag){
                edit.putString("iconurl", iconurl);
                edit.putString("name", name);
                edit.putBoolean("flag",false);
                edit.commit();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    public void Qhare(View v) {
     /*  new ShareAction(MainActivity.this).withText("hello")
               .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
               .setCallback(umShareListener).open();*/
     /*  new ShareAction(MainActivity.this).setPlatform(SHARE_MEDIA.QQ)
               .withText("hello")
               .setCallback(umShareListener)
               .share();*/
     /*  UMImage image = new UMImage(MainActivity.this,
               BitmapFactory.decodeResource(getResources(),
                       R.mipmap.ic_launcher));*/
        UMImage umImage = new UMImage(ShouActivity.this, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489994233&di=c5b3c72b34e883b2826c390bb7ad6c81&imgtype=jpg&er=1&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F63d9f2d3572c11df28e42e30602762d0f703c2e8.jpg");
        new ShareAction(ShouActivity.this)
                //  .setPlatform(SHARE_MEDIA.QQ)
                .setPlatform(SHARE_MEDIA.QZONE)
                .withText("hello")
                .withMedia(umImage)
                .setCallback(umShareListener)
                .share();


    /*   new ShareAction(this).setDisplayList(displaylist).withText("呵呵")
               .withTitle("title").withTargetUrl("http://www.baidu.com")
               .withMedia(image).setListenerList(umShareListener).open();*/
    }

    public void Login(View v){
        UMShareAPI mShareAPI=UMShareAPI.get(ShouActivity.this);
        //   mShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, umAuthListener);
        mShareAPI.getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener);
        qqdenglu.setVisibility(View.GONE);
        shoujidenglu.setVisibility(View.GONE);
        weibodenglu.setVisibility(View.GONE);
        touxiangYin.setVisibility(View.VISIBLE);
    }



//    private void initPlatforms() {
//        platforms.clear();
//        for (SHARE_MEDIA e : displaylist) {
//            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
//                platforms.add(e.toSnsPlatform());
//            }
//        }
//    }
//
//    UMAuthListener authListener = new UMAuthListener() {
//        @Override
//        public void onStart(SHARE_MEDIA platform) {
//            // SocializeUtils.safeShowDialog(dialog);
//        }
//
//        @Override
//        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
//            //  SocializeUtils.safeCloseDialog(dialog);
//            Toast.makeText(ShouActivity.this, "成功了", Toast.LENGTH_LONG).show();
//            //  notifyDataSetChanged();
//        }
//
//        @Override
//        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
//            //  SocializeUtils.safeCloseDialog(dialog);
//            Toast.makeText(ShouActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
//        }
//
//        @Override
//        public void onCancel(SHARE_MEDIA platform, int action) {
//            //   SocializeUtils.safeCloseDialog(dialog);
//            Toast.makeText(ShouActivity.this, "取消了", Toast.LENGTH_LONG).show();
//        }
//    };

    //日夜模式转换
    public void getRiYeZhuan() {
        yejianImg = (ImageView) findViewById(R.id.yejianImg);
        textYejian = (TextView) findViewById(R.id.textYejian);
        yejianImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theme=(theme==R.style.AppTheme) ? R.style.NightAppTheme : R.style.AppTheme;
                 recreate();
            }
        });
    }
    //保存数据
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);
    }

    public void getlistVIew() {
        ListView listView= (ListView) findViewById(R.id.listviewCeHua);
        ArrayList<String> list=new ArrayList<>();
        list.add("消息通知");
        list.add("我的收藏");
        list.add("版本更新");
        list.add("头条上城");
        list.add("京东特供");
        list.add("我要爆料");
        list.add("用户反馈");
        list.add("浏览历史");
        listView.setAdapter(new ArrayAdapter<String>(ShouActivity.this,android.R.layout.simple_expandable_list_item_1,list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:

                        break;
                    case 1:
                        Intent it=new Intent(ShouActivity.this, ShouCangImageActivity.class);
                        startActivity(it);
                        break;
                    case 2:
                        boolean workIsAvailable = NetWorkUtils.isNetWorkIsAvailable(ShouActivity.this);

                        if (!workIsAvailable) {

                            Toast.makeText(ShouActivity.this, "网络未连接，请设置网络", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);

                            startActivity(intent);

                        } else {

                            //连接成功下载

                            Toast.makeText(ShouActivity.this, "网络连接成功", Toast.LENGTH_SHORT).show();



                            download();

                        }
                        break;

                }
            }
        });
    }
    //下载

    private void download() {

        final String[] items = {"wifi", "手机流量"};

        new AlertDialog.Builder(this).setTitle("网络选择").setIcon(R.mipmap.ic_launcher).setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case 0:
                        //wifi下 下载apk
                        downloadApk();
                        break;
                    case 1:
                        //手机流量下提醒用户
                        boolean mobile = NetWorkUtils.isMobile(ShouActivity.this);
                        if (mobile) {
                            Toast.makeText(ShouActivity.this, "现在未使用wifi,将耗用手机流量", Toast.LENGTH_SHORT).show();
                            Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
                            startActivity(wifiSettingsIntent);
                        }
                        break;
                }
                dialog.dismiss();
            }

        }).show();

    }



    //下载apk

    private void downloadApk() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("版本更新");

        builder.setMessage("现在检测到新版本，是否更新？");

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {

                updateApk();

            }

        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }

        });

        builder.create();

        builder.show();





    }



    //版本更新

    private void updateApk() {

        String url = "http://imtt.dd.qq.com/16891/433949400FC6E29FDE9E209099BFE5BC.apk?fsname=com.tencent.mobileqq_6.7.1_500.apk&csr=97c2";

        RequestParams params = new RequestParams(url);

        //保存到sdcard
        String path = Environment.getExternalStorageDirectory() + "/bawei/";
        params.setSaveFilePath(path);
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();

        }

        //自动文件命令

        params.setAutoRename(true);

        //下载

        x.http().post(params, new Callback.ProgressCallback<File>() {

            @Override

            public void onWaiting() {
            }

            @Override
            public void onStarted() {
            }
            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
            }
            @Override
            public void onSuccess(File result) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(result), "application/vnd.android.package-archive");
                startActivity(intent);
            }
            @Override

            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }
            @Override
            public void onFinished() {
            }
        });
    }
}
