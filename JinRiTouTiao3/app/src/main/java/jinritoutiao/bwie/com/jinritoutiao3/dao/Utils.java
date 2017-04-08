package jinritoutiao.bwie.com.jinritoutiao3.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhao on 2017/2/16.
 */
public class Utils {
    public static String pressare(InputStream inputStream){
        byte[] by=new byte[1024];
        int len=0;
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        try {
            while ((len=inputStream.read(by))!=-1){
                bos.write(by,0,len);
            }
            bos.close();
            inputStream.close();
            return bos.toString("utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
