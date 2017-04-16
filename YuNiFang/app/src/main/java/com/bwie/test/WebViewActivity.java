package com.bwie.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bwie.test.bean.F1bean;
import com.bwie.test.dao.MyPagerAdapter;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

public class WebViewActivity extends AppCompatActivity {

    private String url;
    private int id;
    private String url1;
    private F1bean f1bean;
    private WebView webview;
    private Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webview = (WebView) findViewById(R.id.webView);
        it = getIntent();
        url = it.getStringExtra("url");

        getData();


    }
    public void getData() {

        //获取请求队列
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson=new Gson();
                f1bean =  gson.fromJson(s, F1bean.class);
               //ArrayList<F1bean.DataBean.Ad1Bean> ad1 = (ArrayList<F1bean.DataBean.Ad1Bean>) f1bean.getData().getAd1();
                for (int i = 0; i < f1bean.getData().getAd5().size(); i++) {
                    id = it.getIntExtra("s",0);

                    if (i==id){

                        url1 = f1bean.getData().getAd5().get(i).getAd_type_dynamic_data();
                        Log.i("ss",url1);
                        //设置WebView属性，能够执行Javascript脚本
                        webview.getSettings().setJavaScriptEnabled(true);
                        //加载需要显示的网页
                        webview.loadUrl(url1);
                        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
                        webview.setWebViewClient(new WebViewClient(){
                            @Override
                            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                // TODO Auto-generated method stub
                                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                                view.loadUrl(url);
                                return true;
                            }
                        });
                    }
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(request);

    }
}
