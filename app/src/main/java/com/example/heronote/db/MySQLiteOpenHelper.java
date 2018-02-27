package com.example.heronote.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.heronote.base.BaseApplication;

/**
 * Created by Jack on 2018/2/24.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

//    private String CREATE_NOTE = "create table note (" +
//            "id integer primary key autoincrement, " +
//            "has_title_or_not integer, " +
//            "title text, " +
//            "time text, " +
//            "quote text, " +
//            "quote_from text, " +
//            "cover_pic_path text, " +
//            "content text)";
    private String CREATE_NOTE = "create table note (" +
            "time integer primary key, " +
            "has_title_or_not integer, " +
            "title text, " +
            "quote text, " +
            "quote_from text, " +
            "cover_pic_path text, " +
            "content text)";

    private final static String DB_NAME = "note.db";// 数据库文件名
    private final static int DB_VERSION = 1;// 数据库版本

    public MySQLiteOpenHelper() {
        super(BaseApplication.getContext(), DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_NOTE);
        Toast.makeText(BaseApplication.getContext(), "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //sqLiteDatabase.execSQL(CREATE_CATEGORY);
    }

}
