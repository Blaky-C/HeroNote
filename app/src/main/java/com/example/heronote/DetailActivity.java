package com.example.heronote;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heronote.base.BaseActivity;
import com.example.heronote.bean.Note;

public class DetailActivity extends BaseActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private ActionBar actionBar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.search:
                Toast.makeText(this, "You click search", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Note note = getIntent().getParcelableExtra("note");

        ((TextView) findViewById(R.id.date)).setText(note.formatDate("yyyy-MM-dd · HH:mm:ss · EEEE"));
        ((ImageView) findViewById(R.id.cover)).setImageResource(note.getCover());
        ((TextView) findViewById(R.id.slogan)).setText(note.getSlogan());
        ((TextView) findViewById(R.id.hero)).setText("——  " + note.getHero());

        initActionBar(R.id.toolbar);

        collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        //collapsingToolbar.setTitle("NNN");

    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail);
//
//        if (Build.VERSION.SDK_INT >= 21) {
//            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
//
//        toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        actionBar = getSupportActionBar();
//        if (actionBar!=null){
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
//        collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
//        //collapsingToolbar.setTitle("NNN");
//
//    }
}
