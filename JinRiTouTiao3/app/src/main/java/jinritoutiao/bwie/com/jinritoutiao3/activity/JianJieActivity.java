package jinritoutiao.bwie.com.jinritoutiao3.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import jinritoutiao.bwie.com.jinritoutiao3.R;
import jinritoutiao.bwie.com.jinritoutiao3.activity.ShouActivity;
import jinritoutiao.bwie.com.jinritoutiao3.utils.Utilser;

public class JianJieActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView yongHuMingtext;
    private TextView jieShaotext;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    protected static Uri tempUri;
    private static final int CROP_SMALL_PICTURE = 2;
    private String imagePath;
    private ImageView touXiangImage;
    private Bitmap photo;
    private EditText jianjeXuiGai;
    private AlertDialog builder;
    private String edit;
    private String edit2;
    private String edit1;

    //private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jian_jie);
        Intent it = getIntent();
        String iconurl = it.getStringExtra("iconurl");
        String name = it.getStringExtra("name");
        String jianjie = it.getStringExtra("jianjie");
        RelativeLayout relative1= (RelativeLayout) findViewById(R.id.relative1);
        RelativeLayout relative2= (RelativeLayout) findViewById(R.id.relative2);
        RelativeLayout relative3= (RelativeLayout) findViewById(R.id.relative3);
        relative1.setOnClickListener(this);
        relative2.setOnClickListener(this);
        relative3.setOnClickListener(this);
//        ArrayList<RelativeLayout> list=new ArrayList<>();
//        list.add(relative1);
//        list.add(relative2);
//        list.add(relative3);
        ImageView jianJieFanhui= (ImageView) findViewById(R.id.jianJieFanhui);
        touXiangImage = (ImageView) findViewById(R.id.touXiangImage);
        yongHuMingtext = (TextView) findViewById(R.id.yongHuMingtext);
        jieShaotext = (TextView) findViewById(R.id.jieShaotext);
        ImageOptions options = new ImageOptions.Builder().setCircular(true).setCrop(true).setSize(100, 100).setLoadingDrawableId(R.mipmap.ic_launcher).build();
        x.image().bind(touXiangImage, iconurl,options);
        yongHuMingtext.setText(name);
        jieShaotext.setText(jianjie);
        jianJieFanhui.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jianJieFanhui:
                String yongHuMing = yongHuMingtext.getText().toString().trim();
                String jieShao = jieShaotext.getText().toString().trim();
                SharedPreferences sp = getSharedPreferences("d", 0);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("imagePath",imagePath);
                edit.putString("yongHuMing",edit1);
                edit.putString("jieShao",edit2);
                edit.commit();
                Intent its=new Intent();
                its.putExtra("imagePath",imagePath);
                its.putExtra("yongHuMing", this.edit);
                its.putExtra("jieShao",edit2);
                //startActivity(its);
                 setResult(3,its);
                finish();
                break;
            case R.id.relative1:
                    getTuPianShow();
                break;
            case R.id.relative2:

                edit1 = getEdit();
                yongHuMingtext.setText(this.edit);
                break;
            case R.id.relative3:
                edit2 = getEdit();
                jieShaotext.setText(edit2);
                break;
        }
    }


    public String getEdit() {

        builder = new AlertDialog.Builder(this).create();
        //builder.setIcon(R.mipmap.why1);
        //builder.setView(R.layout.alertdialogitem);
        builder.show();
        final Window window= builder.getWindow();
        window.setContentView(R.layout.alertdialogitem2);
        jianjeXuiGai = (EditText) window.findViewById(R.id.jianjeXuiGai);
        TextView jianjeWanCheng= (TextView) window.findViewById(R.id.jianjeWanCheng);
        TextView jianjeQuXiao= (TextView) window.findViewById(R.id.jianjeQuXiao);
        jianjeQuXiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });
        jianjeWanCheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDiao();
                builder.dismiss();
            }
        });
        return null;
    }

    private String getDiao() {
        String s = jianjeXuiGai.getText().toString().trim();
        return s;
    }

    public void getTuPianShow(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items={"选择本地照片","拍照"};
        builder.setNegativeButton("取消",null);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case CHOOSE_PICTURE:
                        Intent openAlbumIntent =new Intent(Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE:
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "image.jpg"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });

        builder.create().show();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode ==RESULT_OK ) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            //Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }
    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     *
     * @param
     */
    public void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            photo = extras.getParcelable("data");
            photo = Utilser.toRoundBitmap(photo, tempUri); // 这个时候的图片已经被处理成圆形的了
            touXiangImage.setImageBitmap(photo);
            uploadPic(photo);
        }
    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了

        imagePath = Utilser.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));
        //Log.e("imagePath", imagePath+"");
        if(imagePath != null){
            // 拿着imagePath上传了
            // ...
        }
    }


}
