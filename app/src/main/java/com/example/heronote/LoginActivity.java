package com.example.heronote;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.heronote.base.BaseActivity;
import com.example.heronote.util.Utils;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initActionBar(R.id.toolbar, "登录 / 注册");
        initListenerToThis(R.id.forget_pw, R.id.button_register, R.id.button_login);
        findViewById(R.id.forget_pw).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forget_pw:
                Utils.snackbar(view, "Forger Password");
                break;
            case R.id.button_register:
                Utils.snackbar(view, "To Register");
                break;
            case R.id.button_login:
                Utils.snackbar(view, "To Login");
                break;
            default:
                break;
        }
    }
}
