package com.example.heronote.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by Jack on 2018/2/14.
 */

public class BaseApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //LitePalApplication.initialize(context);
    }

    public static Context getContext(){
        return context;
    }
}
