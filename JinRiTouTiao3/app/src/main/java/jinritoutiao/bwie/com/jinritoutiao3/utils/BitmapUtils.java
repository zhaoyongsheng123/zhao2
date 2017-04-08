package jinritoutiao.bwie.com.jinritoutiao3.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

/**
 * 1. 类的用途
 * 2. @author forever
 * 3. @date 2017/3/22 15:23
 */

public class BitmapUtils {
    Context context;
    //图片本地缓存路径
    private final static String SDCARD_CACHE = Environment.getExternalStorageDirectory() + "/imagecache";
    //图片存放文件夹
    File fileDir = new File(SDCARD_CACHE);

    private Map<String, SoftReference<Bitmap>> map = new HashMap<String, SoftReference<Bitmap>>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    ImageViewToBitmap imageViewToBitmap = (ImageViewToBitmap) msg.obj;
                    imageViewToBitmap.iv.setImageBitmap(imageViewToBitmap.bitmap);
                    break;
            }
        }
    };

    //构造方法
    public BitmapUtils(Context context) {
        this.context = context;
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
    }

    //加载图片的方法
    public void display(ImageView iv, String url) {
        //内存缓存

        Bitmap bitmap = loadMemory(url);
        if (bitmap != null) {
            iv.setImageBitmap(bitmap);
        } else {
            //sdcard缓存
            bitmap = loadSD(url);
            if (bitmap != null) {
                iv.setImageBitmap(bitmap);
            } else {
                //网络缓存
                loadInternetImage(iv, url);
            }
        }


    }

    private Bitmap loadMemory(String url) {
        SoftReference<Bitmap> value = map.get(url);
        if (value != null) {
            Bitmap bitmap = value.get();
            return bitmap;
        }
        return null;

    }

    //获取本地图片
    private Bitmap loadSD(String url) {
        String name = getFileName(url);
        File file = new File(fileDir, name);
        if (file.exists()) {
            //BitmapFactory选项
            BitmapFactory.Options options = new BitmapFactory.Options();
            //加载图片宽高
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(name, options);
            //获取图片和手机屏幕宽高
            int outWidth = options.outWidth;
            int outHeight = options.outHeight;
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            int widthPixels = displayMetrics.widthPixels;
            int heightPixels = displayMetrics.heightPixels;
            //图片跟手机屏幕进行比对
            int scale = 0;
            int scaleX = outWidth / widthPixels;
            int scaleY = outHeight / heightPixels;
            scale = scaleX > scaleY ? scaleX : scaleY;
            if (scale == 0) {
                scale = 1;
            }
            options.inJustDecodeBounds = false;
            options.inSampleSize = scale;
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            //内存缓存
            SoftReference<Bitmap> value = new SoftReference<Bitmap>(bitmap);
            map.put(name, value);
            return bitmap;
        }


        return null;

    }

    //获取网络图片
    private void loadInternetImage(ImageView iv, String url) {
        //开子线程做耗时操作
        new Thread(new DownloadImage(iv, url)).start();

    }

    private class DownloadImage implements Runnable {
        ImageView iv;
        String url;
        private InputStream inputStream;
        private FileOutputStream fos;

        public DownloadImage(ImageView iv, String url) {
            this.iv = iv;
            this.url = url;
        }

        @Override
        public void run() {
            try {
            HttpClient clent = new DefaultHttpClient();
            HttpGet get = new HttpGet(url);

                HttpResponse execute = clent.execute(get);
                if (execute.getStatusLine().getStatusCode() == 200) {
                    inputStream = execute.getEntity().getContent();
                    String name = getFileName(url);
                    File file = new File(fileDir, name);
                    fos = new FileOutputStream(file);
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                    }

                    //sdcard缓存
                    Bitmap bitmap = loadSD(name);
                    //ImageView转换成Bitmap转换成Bitmap
                    ImageViewToBitmap ivtb = new ImageViewToBitmap(iv, bitmap);
                    Message message = Message.obtain(handler, 0, ivtb);
                    message.sendToTarget();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }


    }

    //获取图片的名称
    private String getFileName(String url) {
        return Md5Utils.encode(url) + ".jpg";

    }

    //ImageView转换成Bitmap转换成Bitmap
    private class ImageViewToBitmap {
        ImageView iv;
        Bitmap bitmap;

        public ImageViewToBitmap(ImageView iv, Bitmap bitmap) {
            this.iv = iv;
            this.bitmap = bitmap;
        }
    }
}
