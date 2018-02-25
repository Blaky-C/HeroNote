package com.example.heronote.util;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.example.heronote.base.BaseApplication;

/**
 * Created by Jack on 2018/2/25.
 */

public class CommonUtils {

    //显示toast
    public static void showToast(String text){
        LogUtils.i("BaseActivity: ", text);
        Toast.makeText(BaseApplication.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    //显示snackbar
    public static void showSnackbar(View view, String s) {
        Snackbar.make(view, s, Snackbar.LENGTH_SHORT).show();
    }

    public static void showSnackbar(View view, String text, String button_text, final String toast_text, View.OnClickListener listener) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
                .setAction(button_text, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showToast(toast_text);
                    }
                }).show();
    }

    //返回一个生成Toast的监听器
    public static View.OnClickListener listenerToShowToast(final String s) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast(s);
            }
        };
        return listener;
    }

    //返回一个生成SnackBar的监听器
    public static View.OnClickListener listenerToShowSnackbar(final String s) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSnackbar(view, s);
            }
        };
        return listener;
    }
}
