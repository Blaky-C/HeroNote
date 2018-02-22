package com.example.heronote;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heronote.base.BaseActivity;

public class EditActivity extends BaseActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.cancel:
                AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                builder.setTitle("提示");
                builder.setMessage("取消编辑？");
                builder.setCancelable(false);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /*showToast("已取消！");*/
                        Toast.makeText(EditActivity.this, "已取消", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.create().show();
                return true;
            case R.id.save:
                Toast.makeText(this, "You click save", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Toolbar toolbar;
    private ActionBar actionBar;
    private EditText editTitle;
    private SwitchCompat switchCompat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        actionBar.setTitle("新建记录");

        editTitle = (EditText)findViewById(R.id.et_new_title);
        switchCompat = (SwitchCompat)findViewById(R.id.switch_compat);

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    editTitle.setVisibility(EditText.VISIBLE);
                    editTitle.setFocusable(true);
                    editTitle.setFocusableInTouchMode(true);
                    editTitle.requestFocus();
                }else {
                    editTitle.setVisibility(EditText.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
