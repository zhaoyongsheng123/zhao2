package jinritoutiao.bwie.com.jinritoutiao3.application;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by zhao on 2017/3/11.
 */
public class ImageLoaderApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
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
}
