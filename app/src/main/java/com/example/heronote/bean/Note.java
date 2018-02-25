package com.example.heronote.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jack on 2018/2/22.
 */

public class Note implements Parcelable {

    private boolean hasTitleOrNot;
    private String title;
    private long time;
    private String quote;
    private String quoteFrom;
    private String content;
    private String coverPicPath;

    public Note() {
        hasTitleOrNot = false;
        title = null;
    }

    public Note(boolean hasTitleOrNot, String title, long time, String quote, String quoteFrom, String content, String coverPicPath) {
        this.hasTitleOrNot = hasTitleOrNot;
        this.title = title;
        this.time = time;
        this.quote = quote;
        this.quoteFrom = quoteFrom;
        this.content = content;
        this.coverPicPath = coverPicPath;
    }

    public boolean isHasTitleOrNot() {
        return hasTitleOrNot;
    }

    public void setHasTitleOrNot(boolean hasTitleOrNot) {
        this.hasTitleOrNot = hasTitleOrNot;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCoverPicPath() {
        return coverPicPath;
    }

    public void setCoverPicPath(String coverPicPath) {
        this.coverPicPath = coverPicPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(hasTitleOrNot ? 1 : 0);
        dest.writeString(title);
        dest.writeLong(time);
        dest.writeString(quote);
        dest.writeString(quoteFrom);
        dest.writeString(content);
        dest.writeString(coverPicPath);
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            Note note = new Note();
            note.hasTitleOrNot = (source.readInt() == 1 ? true : false);
            note.title = source.readString();
            note.time = source.readLong();
            note.quote = source.readString();
            note.quoteFrom = source.readString();
            note.content = source.readString();
            note.coverPicPath = source.readString();

            return note;
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}