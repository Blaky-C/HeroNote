package com.example.heronote.util;

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

}
