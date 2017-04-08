package jinritoutiao.bwie.com.jinritoutiao3.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 1. 类的用途
 * 2. @author forever
 * 3. @date 2017/3/16 15:17
 */

public class NetWorkUtils {
    //判断网络连接状态
    public static boolean isNetWorkIsAvailable(Context context) {
        //网络连接管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取网络信息
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null) {
            return true;
        }
       /* if (info.isConnected()) {
            return true;
        }*/


        return false;
    }

    //判断是否是wifi
    public static boolean isWifi(Context context) {
        //网络连接管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取网络信息
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.getType() == connectivityManager.TYPE_WIFI) {
            return true;
        }

        return false;
    }

    //判断是否是手机流量
    public static boolean isMobile(Context context) {
        //网络连接管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取网络信息
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.getType() == connectivityManager.TYPE_MOBILE) {
            return true;
        }

        return false;
    }

}
