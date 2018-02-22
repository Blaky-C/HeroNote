package com.example.heronote.util;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.example.heronote.base.BaseApplication;

/**
 * Created by SQS on 2018/2/21.
 */

public class Utils {

    public static void log(Object o) {
        LogUtil.d("shit", String.valueOf(o));
    }

    public static void toast(String s) {
        Toast.makeText(BaseApplication.getContext(), s, Toast.LENGTH_SHORT).show();
    }

    public static void snackbar(View view, String s) {
        Snackbar.make(view, s, Snackbar.LENGTH_SHORT).show();
    }

    public static void snackbar(View view, String s, String button_text, View.OnClickListener listener) {
        Snackbar.make(view, s, Snackbar.LENGTH_SHORT).setAction(button_text, listener).show();
    }

    public static void goToNewAct(java.lang.Class<?> cls) {
        Intent intent = new Intent(BaseApplication.getContext(), cls);
        BaseApplication.getContext().startActivity(intent);
    }

    public static void goToNewAct(java.lang.Class<?> cls, String name, Parcelable parcelable) {
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

}
