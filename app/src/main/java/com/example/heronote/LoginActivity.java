package com.example.heronote;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.heronote.base.BaseActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("登录 / 注册");
        setSupportActionBar(toolbar);

        findViewById(R.id.forget_pw).setOnClickListener(this);
        findViewById(R.id.button_register).setOnClickListener(this);
        findViewById(R.id.button_login).setOnClickListener(this);
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
                Snackbar.make(view, "Forger Password", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.button_register:
                Snackbar.make(view, "To Register", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.button_login:
                Snackbar.make(view, "To Login", Snackbar.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
