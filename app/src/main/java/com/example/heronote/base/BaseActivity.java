package com.example.heronote.base;

/**
 * Created by Jack on 2018/2/14.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.heronote.util.LogUtil;

/**
 * Created by Jack on 2017/11/6.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //通过BaseActivity获取当前类的信息
        Log.d("BaseActivity", getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    //显示toast
    public void showToast(String text){
        LogUtil.d("BaseActivity:", text);
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}

