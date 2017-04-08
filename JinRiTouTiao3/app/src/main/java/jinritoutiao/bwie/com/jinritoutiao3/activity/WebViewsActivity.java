package jinritoutiao.bwie.com.jinritoutiao3.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import jinritoutiao.bwie.com.jinritoutiao3.R;

public class WebViewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_views);
        Intent it = getIntent();
        String url = it.getStringExtra("url");
        WebView webview = (WebView) findViewById(R.id.webView);
        //设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setJavaScriptEnabled(true);
        //加载需要显示的网页
        webview.loadUrl(url);

        //设置Web视图
        //webview.setWebViewClient();

//        Uri uri = Uri.parse(url);
//       it=new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(it);
//        //实例化WebView对象
//        WebView webview = new WebView(this);
//        //设置WebView属性，能够执行Javascript脚本
//        webview.getSettings().setJavaScriptEnabled(true);
//        //加载需要显示的网页
//        webview.loadUrl("url");
//        //设置Web视图
//        setContentView(webview);
    }
}
