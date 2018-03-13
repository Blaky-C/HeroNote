package com.example.heronote.util;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.example.heronote.base.BaseApplication;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by SQS on 2018/2/21.
 */

public class Utils {

    public static void log(Object o) {
        LogUtils.d("shit", String.valueOf(o));
    }

    public static void toast(Object o) {
        Toast.makeText(BaseApplication.getContext(), String.valueOf(o), Toast.LENGTH_SHORT).show();
    }

    public static void snackbar(View view, String s) {
        Snackbar.make(view, s, Snackbar.LENGTH_SHORT).show();
    }

    public static void snackbar(View view, String s, String button_text, View.OnClickListener listener) {
        Snackbar.make(view, s, Snackbar.LENGTH_SHORT).setAction(button_text, listener).show();
    }

    public static void goToActivity(java.lang.Class<?> cls) {
        Intent intent = new Intent(BaseApplication.getContext(), cls);
        BaseApplication.getContext().startActivity(intent);
    }

    public static void goToActivity(java.lang.Class<?> cls, String name, Parcelable parcelable) {
        Intent intent = new Intent(BaseApplication.getContext(), cls);
        intent.putExtra(name, parcelable);
        BaseApplication.getContext().startActivity(intent);
    }

    public static View.OnClickListener listenerToToast(final String s) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast(s);
            }
        };
        return listener;
    }

    public static View.OnClickListener listenerToSnackbar(final String s) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar(view, s);
            }
        };
        return listener;
    }

    public static View.OnLongClickListener longClickListenerToToast(final String s) {
        View.OnLongClickListener listener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                toast(s);
                return true;
            }
        };
        return listener;
    }

    public static View.OnLongClickListener longClicklistenerToSnackbar(final String s) {
        View.OnLongClickListener listener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                snackbar(view, s);
                return true;
            }
        };
        return listener;
    }

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        Request request = new Request.Builder().url(address).build();
        new OkHttpClient().newCall(request).enqueue(callback);
    }

    public static void glide(String src, ImageView imageView) {
        RequestManager manager = Glide.with(BaseApplication.getContext());
        RequestBuilder builder;
        switch (src.split("://")[0]) {
            case "http":
            case "https":
                builder = manager.load(src);
                break;
            case "content":
                builder = manager.load(Uri.parse(src));
                break;
            default:
                builder = manager.load(Integer.parseInt(src));
                break;
        }
        builder.into(imageView);
    }

}
