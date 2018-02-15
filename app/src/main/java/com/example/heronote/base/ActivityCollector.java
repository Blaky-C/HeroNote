package com.example.heronote.base;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2018/2/14.
 */

public class ActivityCollector {

    //活动管理器
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity a: activities){
            if (!a.isFinishing()){
                a.finish();
            }
        }
    }
}
