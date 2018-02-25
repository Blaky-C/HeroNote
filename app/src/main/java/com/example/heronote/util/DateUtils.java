package com.example.heronote.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理
 */

public class DateUtils {
    public static final String ENG_DATE_FROMAT = "EEE, d MMM yyyy HH:mm:ss z";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY = "yyyy";
    public static final String MM = "MM";
    public static final String DD = "dd";
    public static final String EE = "EEEE";
    public static final String YYYY_MM_HH_MM = "yyyy-MM HH:mm";

    /* 返回hh:mm:ss或mm:ss的格式 */
    public static String generateTime(long time) {
        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;
        return hours > 0 ? String.format("%02d:%02d:%02d", hours, minutes, seconds) : String.format("%02d:%02d", minutes, seconds);
    }

    /* 根据long毫秒数，获得时分秒 */
    public static String getDateFormatByLong(long time) {
        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /*格式化日期对象*/
    public static Date date2date(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String str = sdf.format(date);
        try {
            date = sdf.parse(str);
        } catch (Exception e) {
            return null;
        }
        return date;
    }

    /*时间对象转换成字符串*/
    public static String date2string(Date date, String form) {
        SimpleDateFormat sdf = new SimpleDateFormat(form);
        String strDate = sdf.format(date);
        return strDate;
    }

    /*通过时间获得文件名*/
    public static String getFileNameByDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String strDate = sdf.format(date);
        return strDate;
    }

    /*sql时间对象转换成字符串*/
    public static String timestamp2string(Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        String strDate = sdf.format(timestamp);
        return strDate;
    }

    /* 字符串转换成时间对象 */
    public static Date string2date(String dateString) {
        Date formateDate = null;
        DateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        try {
            formateDate = format.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
        return formateDate;
    }

    /* Date类型转换为Timestamp类型 */
    public static Timestamp date2timestamp(Date date) {
        if (date == null)
            return null;
        return new Timestamp(date.getTime());
    }

    /* 获得当前年份 */
    public static String getNowYear() {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY);
        return sdf.format(new Date());
    }

    /* 获得当前月份 */
    public static String getNowMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat(MM);
        return sdf.format(new Date());
    }

    /* 获得当前日期中的日 */
    public static String getNowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(DD);
        return sdf.format(new Date());
    }

    /* 获得当前日期中的星期 */
    public static String getNowDay(){
        SimpleDateFormat sdf = new SimpleDateFormat(EE);
        return sdf.format(new Date());
    }

    /* 获得当前日期的格式字符串 */
    public static String getNowFormatTime(String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    /* 指定时间距离当前时间的中文信息 */
    public static String getFriendlyTime(long time) {
        Calendar cal = Calendar.getInstance();
        long timel = cal.getTimeInMillis() - time;
        if (timel / 1000 < 60) {
            return "1分钟以内";
        } else if (timel / 1000 / 60 < 60) {
            return timel / 1000 / 60 + "分钟前";
        } else if (timel / 1000 / 60 / 60 < 24) {
            return timel / 1000 / 60 / 60 + "小时前";
        } else {
            return timel / 1000 / 60 / 60 / 24 + "天前";
        }
    }

    /* 以友好的方式显示时间 */
    public static String getFriendlyTime(Date time) {
        //获取time距离当前的秒数
        int ct = (int)((System.currentTimeMillis() - time.getTime())/1000);
        if(ct == 0) {
            return "刚刚";
        }
        if(ct > 0 && ct < 60) {
            return ct + "秒前";
        }
        if(ct >= 60 && ct < 3600) {
            return Math.max(ct / 60,1) + "分钟前";
        }
        if(ct >= 3600 && ct < 86400)
            return ct / 3600 + "小时前";
        if(ct >= 86400 && ct < 2592000){ //86400 * 30
            int day = ct / 86400 ;
            return day + "天前";
        }
        if(ct >= 2592000 && ct < 31104000) { //86400 * 30
            return ct / 2592000 + "月前";
        }
        return ct / 31104000 + "年前";
    }

    /* 格式化日期字符串 */
    public static String formatString(String currentTime) {
        DateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        return format.format(currentTime);
    }
}