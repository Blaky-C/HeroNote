package com.example.heronote.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SQS on 2018/2/23.
 */

public class Note implements Parcelable {

    private Long timeMillis;
    private String slogan;
    private String hero;
    private int cover;

    public Note(String slogan, String hero, int cover) {
        this(new Date().getTime(), slogan, hero, cover);
    }

    public Note(Long timeMillis, String slogan, String hero, int cover) {
        this.timeMillis = timeMillis;
        this.slogan = slogan;
        this.hero = hero;
        this.cover = cover;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(timeMillis);
        parcel.writeString(slogan);
        parcel.writeString(hero);
        parcel.writeInt(cover);
    }

    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel parcel) {
            return new Note(parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readInt());
        }

        @Override
        public Note[] newArray(int i) {
            return new Note[i];
        }
    };

    public Long getTimeMillis() {
        return timeMillis;
    }

    public void setTimeMillis(Long timeMillis) {
        this.timeMillis = timeMillis;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }

    public String getHero() {
        return hero;
    }

    public void setHero(String hero) {
        this.hero = hero;
    }

    public String formatDate(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date(timeMillis));
    }
}
