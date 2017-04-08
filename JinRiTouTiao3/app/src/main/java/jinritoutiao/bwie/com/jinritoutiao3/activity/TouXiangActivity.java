package jinritoutiao.bwie.com.jinritoutiao3.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.handmark.view.photoview.PhotoView;
import com.handmark.view.photoview.PhotoViewAttacher;
import com.squareup.picasso.Picasso;

import jinritoutiao.bwie.com.jinritoutiao3.R;

public class TouXiangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tou_xiang);
        Intent it = getIntent();
        String iconurl = it.getStringExtra("iconurl");
        PhotoView photoView= (PhotoView) findViewById(R.id.photoView);
        Picasso.with(this).load(iconurl).placeholder(R.mipmap.ic_launcher).into(photoView);
        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                finish();
            }
        });
    }
}
