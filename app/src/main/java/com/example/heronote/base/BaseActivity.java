package com.example.heronote.base;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.heronote.util.CommonUtils;
import android.view.View;
import android.widget.TextView;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Jack on 2017/11/6.
 */

public class BaseActivity extends SwipeBackActivity implements View.OnClickListener, View.OnLongClickListener {

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

    @Override
    public void onClick(View view) {}

    @Override
    public boolean onLongClick(View view) {
        return false;
    }

    protected void transparentStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    protected void setSwipeBack(boolean mode) {
        if (!mode) {
            getSwipeBackLayout().setEnableGesture(false);
        }
    }

    protected void setSwipeBack(int mode) {
        getSwipeBackLayout().setEdgeTrackingEnabled(mode);
    }

    /*初始化动作条*/
    protected void initActionBar(int id) {
        Toolbar toolbar = (Toolbar) findViewById(id);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    protected void initActionBar(int id, String title) {
        Toolbar toolbar = (Toolbar) findViewById(id);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    protected void initActionBar(int id, String title, int icon) {
        Toolbar toolbar = (Toolbar) findViewById(id);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(icon);
        }
    }

    protected void initActionBarLabelCenter(int toolbarId, int textViewId, String title) {
        Toolbar toolbar = (Toolbar) findViewById(toolbarId);
        toolbar.setTitle("");
        setTextInView(textViewId, title);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    protected void initActionBarLabelCenter(int toolbarId, int textViewId, String title, int icon) {
        Toolbar toolbar = (Toolbar) findViewById(toolbarId);
        toolbar.setTitle("");
        setTextInView(textViewId, title);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(icon);
        }
    }

    /*添加动作监听*/
    protected void initListenerToThis(int... ints) {
        for (int i: ints) {
            findViewById(i).setOnClickListener(this);
        }
    }

    protected void initListenerToThis(View view, int... ints) {
        for (int i: ints) {
            view.findViewById(i).setOnClickListener(this);
        }
    }

    protected void initLongClickListenerToThis(int... ints) {
        for (int i : ints) {
            findViewById(i).setOnLongClickListener(this);
        }
    }

    protected void initLongClickListenerToThis(View view, int... ints) {
        for (int i : ints) {
            view.findViewById(i).setOnLongClickListener(this);
        }
    }

    protected void setTextInView(int id, String text) {
        ((TextView) findViewById(id)).setText(text);
    }

    protected void setTextInView(View view, int id, String text) {
        ((TextView) view.findViewById(id)).setText(text);
    }

    protected void checkPermission(String permission, int requestCode){
        if (ContextCompat.checkSelfPermission(BaseApplication.getContext(), permission)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }else{
            actionAfterPermiss(requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            actionAfterPermiss(requestCode);
        }else {
            actionAfterDeny(requestCode);
        }
    }

    protected void actionAfterPermiss(int requestCode){  }

    protected void actionAfterDeny(int requestCode){
        CommonUtils.showToast("You denied the permission.");
    }

}

