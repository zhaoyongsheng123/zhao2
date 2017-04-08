package jinritoutiao.bwie.com.jinritoutiao3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import jinritoutiao.bwie.com.jinritoutiao3.R;
import jinritoutiao.bwie.com.jinritoutiao3.utils.BitmapUtils;

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
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        ImageView imageView = (ImageView) findViewById(R.id.yindaoimg);
        String url="http://img.hb.aicdn.com/406657622b6cf04d076c1e033640f69c4e4ab73831787-oulpDm_fw658";
        //ImageLoader.getInstance().displayImage(url,imageView);
        BitmapUtils utils=new BitmapUtils(this);
        utils.display(imageView,url);
        handler.sendEmptyMessageDelayed(0,3000);
    }
}
