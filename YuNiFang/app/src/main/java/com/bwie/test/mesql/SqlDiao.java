package com.bwie.test.mesql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bwie.test.bean.MeSqlBean;

import java.util.ArrayList;

/**
 * 类的用途：
 *
 * @author 赵永生
 * @time 2017/4/12 18:41
 */
public class SqlDiao {

    private MySqlKu mySqlKu;

    public SqlDiao(Context context){
        mySqlKu = new MySqlKu(context);
    }
    public void insent(String zhanghao,String mima){
        SQLiteDatabase db = mySqlKu.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("zhanghao",zhanghao);
        values.put("mima",mima);
        db.insert("name",null,values);
    }
    public ArrayList<MeSqlBean> list=new ArrayList<>();
    public ArrayList<MeSqlBean> select (){
        list.clear();
        SQLiteDatabase db = mySqlKu.getReadableDatabase();
        String sql="select * from name";
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            String zhanghao1 = cursor.getString(cursor.getColumnIndex("zhanghao"));
            String mima1 = cursor.getString(cursor.getColumnIndex("mima"));
            list.add(new MeSqlBean(zhanghao1,mima1));

        }
        return list;
    }
}
