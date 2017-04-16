package com.bwie.test.mesql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 类的用途：
 *
 * @author 赵永生
 * @time 2017/4/12 16:29
 */
public class MySqlKu extends SQLiteOpenHelper {
    public MySqlKu(Context context) {
        super(context, "mysql1502L", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table name(_id integer primary key autoincrement ,zhanghao varchar(100),mima varchar(20))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
