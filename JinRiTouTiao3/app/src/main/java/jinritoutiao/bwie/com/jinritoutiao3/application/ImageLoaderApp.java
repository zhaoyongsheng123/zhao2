package jinritoutiao.bwie.com.jinritoutiao3.application;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

import cn.jpush.android.api.JPushInterface;
import cn.smssdk.SMSSDK;

/**
 * Created by zhao on 2017/3/11.
 */
public class ImageLoaderApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化JPush
        JPushInterface.init(this);
        //设置debug模式
        JPushInterface.setDebugMode(true);
        x.Ext.init(this);
        x.Ext.setDebug(true);
        Config.DEBUG = true;
        UMShareAPI.get(this);
        SMSSDK.initSDK(this, "1c1a724c5b3de", "2024ca1e523629074bd14a5d315c54ae");
//        String path = Environment.getExternalStorageDirectory().getPath();
//        File file = new File(path+"/iamge");
//        File file = StorageUtils.getCacheDirectory(this);
//        if (file.exists()==false){
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(getApplicationContext()).memoryCacheExtraOptions(480,800)
//                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)) //可以通过自己的内存缓存实现
//                .memoryCacheSize(2 * 1024 * 1024)  // 内存缓存的最大值
//                .memoryCacheSizePercentage(13) // default
//                .diskCache(new UnlimitedDiscCache(file)) // default 可以自定义缓存路径
//                .diskCacheSize(50 * 1024 * 1024) // 50 Mb sd卡(本地)缓存的最大值
//                .diskCacheFileCount(100) // 可以缓存的文件数量 .build();
                .build();
        ImageLoader.getInstance().init(configuration);
    }
    {
        PlatformConfig.setQQZone("1106036236", "mjFCi0oxXZKZEWJs");
    }
    public static DbManager getDb(){
        DbManager.DaoConfig daoConfig=new DbManager.DaoConfig().setDbName("bw.db").setDbDir(new File("/mnt/sdcard")).setDbVersion(1);
        DbManager db=x.getDb(daoConfig);
        return db;
    }
}
