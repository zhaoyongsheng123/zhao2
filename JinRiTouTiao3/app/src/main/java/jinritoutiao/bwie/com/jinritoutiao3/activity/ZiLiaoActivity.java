package jinritoutiao.bwie.com.jinritoutiao3.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import jinritoutiao.bwie.com.jinritoutiao3.R;


public class ZiLiaoActivity extends AppCompatActivity implements View.OnClickListener {

    private String iconurl;
    private String name;
    private TextView ziLiaoJianjie;
    private TextView ziLiaoName;
    private ImageView ziLiaoGengduo;
    private ImageView ziLiaoTouXiang;
    private String yongHuMing;
    private String jieShao;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_liao);
        Intent it = getIntent();
        iconurl = it.getStringExtra("iconurl");
        name = it.getStringExtra("name");

        initView();
    }

    private void initView() {
        ImageView ziLiaoFanhui= (ImageView) findViewById(R.id.ziLiaoFanhui);
        ziLiaoTouXiang = (ImageView) findViewById(R.id.ziLiaoTouXiang);
        ziLiaoGengduo = (ImageView) findViewById(R.id.ziLiaoGengduo);
        ziLiaoName = (TextView) findViewById(R.id.ziLiaoName);
        ziLiaoJianjie = (TextView) findViewById(R.id.ziLiaoJianjie);
        TextView ziLiaoBianji= (TextView) findViewById(R.id.ziLiaoBianji);
        ziLiaoName.setText(name);
        ImageOptions options = new ImageOptions.Builder().setCircular(true).setCrop(true).setSize(100, 100).setLoadingDrawableId(R.mipmap.ic_launcher).build();
        x.image().bind(ziLiaoTouXiang, iconurl,options);
        ziLiaoTouXiang.setOnClickListener(this);
        ziLiaoFanhui.setOnClickListener(this);
        ziLiaoGengduo.setOnClickListener(this);
        ziLiaoBianji.setOnClickListener(this);
        ziLiaoJianjie.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ziLiaoTouXiang:
                Intent it=new Intent(ZiLiaoActivity.this, TouXiangActivity.class);
                it.putExtra("iconurl",iconurl);
                startActivity(it);
                break;
            case R.id.ziLiaoFanhui:
                Intent its=new Intent(ZiLiaoActivity.this, ShouActivity.class);
                startActivity(its);

                break;
            case R.id.ziLiaoGengduo:

                break;
            case R.id.ziLiaoBianji:
                String jianjie = ziLiaoJianjie.getText().toString().trim();
                Intent its3=new Intent(ZiLiaoActivity.this, JianJieActivity.class);
                its3.putExtra("jianjie",jianjie);
                its3.putExtra("iconurl",iconurl);
                its3.putExtra("name",name);
               startActivityForResult(its3,3);
                break;

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==3){
            Intent in = data;
            yongHuMing = in.getStringExtra("流失，时光");
            jieShao = in.getStringExtra("jieShao");
            imagePath = in.getStringExtra("imagePath");
            ziLiaoJianjie.setText(jieShao);
            ziLiaoName.setText(yongHuMing);
            Log.e("ss","ssssssssss"+ imagePath);
            ImageOptions options = new ImageOptions.Builder().setCircular(true).setCrop(true).setSize(100, 100).setLoadingDrawableId(R.mipmap.ic_launcher).build();
            x.image().bind(ziLiaoTouXiang, imagePath,options);
        }
    }

}
