package com.bwie.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bwie.test.R;
import com.lidroid.xutils.BitmapUtils;


public class YindaoActivity extends AppCompatActivity {

    private int i=0;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                if (i == 0) {
                    i++;
                    Intent it=new Intent(YindaoActivity.this,ShouActivity.class);
                    startActivity(it);
                    finish();
                }

                handler.sendEmptyMessageDelayed(0,3000);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yindao);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        ImageView imageView = (ImageView) findViewById(R.id.yindaoimg);
        String url="http://c11.eoemarket.com/upload/apps/2013/0318/123685/screenshots/f5a6fdef-ef6d-4e09-cff2-beb2a2462ad7.jpg";
        //ImageLoader.getInstance().displayImage(url,imageView);
        BitmapUtils utils=new BitmapUtils(this);
        utils.display(imageView,url);
        handler.sendEmptyMessageDelayed(0,3000);
    }
}
