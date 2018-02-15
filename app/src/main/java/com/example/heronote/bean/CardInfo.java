package com.example.heronote.bean;

/**
 * Created by Jack on 2018/2/14.
 */

public class CardInfo {

    private String date;
    private String day;
    private String time;
    private int img;
    private String quote;

    public CardInfo(String date, String day, String time, int img, String quote) {
        this.date = date;
        this.day = day;
        this.time = time;
        this.img = img;
        this.quote = quote;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
