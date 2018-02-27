package com.example.heronote.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.heronote.base.BaseApplication;
import com.example.heronote.bean.Note;
import com.example.heronote.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2018/2/24.
 */

public class NoteDbOperate {

    private MySQLiteOpenHelper helper;

    public NoteDbOperate() {
        helper = new MySQLiteOpenHelper();
    }

    /**
     * 查询所有笔记
     */
    public List<Note> queryNotesAll() {
        SQLiteDatabase db = helper.getWritableDatabase();

        List<Note> noteList = new ArrayList<>();
        Note note;
        String sql;
        Cursor cursor = null;
        try {
            sql = "select * from note order by time desc";
            cursor = db.rawQuery(sql, null);
            //cursor = db.query("note", null, null, null, null, null, "n_id desc");
            while (cursor.moveToNext()) {
                //循环获得展品信息
                note = new Note();
                note.setHasTitleOrNot(cursor.getInt(cursor.getColumnIndex("has_title_or_not"))==1?true:false);
                note.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                note.setTime(Long.valueOf(cursor.getString(cursor.getColumnIndex("time"))));
                note.setQuote(cursor.getString(cursor.getColumnIndex("quote")));
                note.setQuoteFrom(cursor.getString(cursor.getColumnIndex("quote_from")));
                note.setCoverPicPath(cursor.getString(cursor.getColumnIndex("cover_pic_path")));
                note.setContent(cursor.getString(cursor.getColumnIndex("content")));
                noteList.add(note);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return noteList;
    }

    /**
     * 插入笔记
     */
    public void insertNote(Note note) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("time", note.getTime());
        values.put("has_title_or_not", note.isHasTitleOrNot()?1:0);
        values.put("title", note.getTitle());
        values.put("quote", note.getQuote());
        values.put("quote_from", note.getQuoteFrom());
        values.put("cover_pic_path", note.getCoverPicPath());
        values.put("content", note.getContent());

        db.insert("note", null, values);
        Toast.makeText(BaseApplication.getContext(), "新增数据成功", Toast.LENGTH_SHORT).show();
        db.close();
    }

    /**
     * 更新笔记
     * @param note
     */
    public void updateNote(Note note) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("has_title_or_not", note.isHasTitleOrNot()?1:0);
        values.put("title", note.getTitle());
        values.put("date", note.getTime());
        values.put("quote", note.getQuote());
        values.put("quote_from", note.getQuoteFrom());
        values.put("cover_pic_path", note.getCoverPicPath());
        values.put("content", note.getContent());


        //db.update("db_note", values, "n_id=?", new String[]{note.getId()+""});
        db.close();
    }

    /**
     * 删除笔记
     */
    public long deleteNote(long noteTime) {
        SQLiteDatabase db = helper.getWritableDatabase();
        long ret = 0;
        try {
            ret = db.delete("note", "time = ?", new String[]{noteTime + ""});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return ret;
    }

    /**
     * 批量删除笔记
     *
     * @param mNotes
     */
    public int deleteNote(List<Note> mNotes) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int ret = 0;
        try {
            if (mNotes != null && mNotes.size() > 0) {
                db.beginTransaction();//开始事务
                try {
                    for (Note note : mNotes) {
                        //ret += db.delete("db_note", "n_id=?", new String[]{note.getId() + ""});
                    }
                    db.setTransactionSuccessful();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    db.endTransaction();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return ret;
    }
}
