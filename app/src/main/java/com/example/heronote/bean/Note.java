package com.example.heronote.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jack on 2018/2/22.
 */

public class Note implements Parcelable {

    //    private boolean hasTitleOrNot;
    private long time;
    private String title;
    private String quote;
    private String quoteFrom;
    private String coverPicPath;
    private String content;

//    public Note() {
//        hasTitleOrNot = false;
//        title = null;
//    }

//    public Note(boolean hasTitleOrNot, String title, long time, String quote, String quoteFrom, String content, String coverPicPath) {
//        this.hasTitleOrNot = hasTitleOrNot;
//        this.title = title;
//        this.time = time;
//        this.quote = quote;
//        this.quoteFrom = quoteFrom;
//        this.content = content;
//        this.coverPicPath = coverPicPath;
//    }

    public Note() {}

    public Note(String title, String quote, String quoteFrom, String coverPicPath, String content) {
        this(new Date(), title, quote, quoteFrom, coverPicPath, content);
    }

    public Note(Date date, String title, String quote, String quoteFrom, String coverPicPath, String content) {
        this(date.getTime(), title, quote, quoteFrom, coverPicPath, content);
    }

    public Note(long time, String title, String quote, String quoteFrom, String coverPicPath, String content) {
            this.time = time;
            this.title = title;
            this.quote = quote;
            this.quoteFrom = quoteFrom;
            this.coverPicPath = coverPicPath;
            this.content = content;
        }

//    public boolean isHasTitleOrNot() {
//        return hasTitleOrNot;
//    }

//    public void setHasTitleOrNot(boolean hasTitleOrNot) {
//        this.hasTitleOrNot = hasTitleOrNot;
//    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setTime(Date date) {
        this.time = date.getTime();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getQuoteFrom() {
        return quoteFrom;
    }

    public void setQuoteFrom(String quoteFrom) {
        this.quoteFrom = quoteFrom;
    }

    public String getCoverPicPath() {
        return coverPicPath;
    }

    public void setCoverPicPath(String coverPicPath) {
        this.coverPicPath = coverPicPath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(time);
        parcel.writeString(title);
        parcel.writeString(quote);
        parcel.writeString(quoteFrom);
        parcel.writeString(coverPicPath);
        parcel.writeString(content);
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel parcel) {
            return new Note(parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        @Override
        public Note[] newArray(int i) {
            return new Note[i];
        }
    };

    public String formatDate(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date(time));
    }
}