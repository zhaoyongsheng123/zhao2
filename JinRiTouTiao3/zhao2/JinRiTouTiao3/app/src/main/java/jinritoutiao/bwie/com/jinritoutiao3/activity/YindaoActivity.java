package jinritoutiao.bwie.com.jinritoutiao3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import jinritoutiao.bwie.com.jinritoutiao3.R;
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
        ImageView imageView = (ImageView) findViewById(R.id.yindaoimg);
        String url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489204799170&di=85602ba23057ba1177189e26d80a9fb9&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019e0d55b0609432f875495e135fd0.png";
        ImageLoader.getInstance().displayImage(url,imageView);
        handler.sendEmptyMessageDelayed(0,3000);
    }
}
