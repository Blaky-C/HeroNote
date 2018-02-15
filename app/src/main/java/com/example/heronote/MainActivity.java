package com.example.heronote;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.heronote.adapter.CardAdapter;
import com.example.heronote.adapter.MyPagerAdapter;
import com.example.heronote.base.BaseActivity;
import com.example.heronote.bean.CardInfo;
import com.example.heronote.fragment.Fragment1;
import com.example.heronote.fragment.Fragment2;
import com.example.heronote.fragment.Fragment3;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                Toast.makeText(this, "You click search", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.END);
                break;
        }
        return true;
    }

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private FloatingActionButton fab;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyPagerAdapter pagerAdapter;
    private List<Fragment> fragList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar和DrawerHome按钮
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.bars);
        }

        //NavigationView
        navView = (NavigationView)findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.nav_home);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(MainActivity.this, "You click an undefined button.", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawers();
                return true;
            }
        });

        //FloatingActionButton
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Write some data", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "取消编辑", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        //初始化ViewPager和TabLayout
        Fragment fragment1 = new Fragment1();
        Fragment fragment2 = new Fragment2();
        Fragment fragment3 = new Fragment3();
        fragList.add(fragment1);
        fragList.add(fragment2);
        fragList.add(fragment3);

        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        //绑定适配器
        FragmentManager manager = getSupportFragmentManager();
        pagerAdapter = new MyPagerAdapter(manager, fragList);
        viewPager.setAdapter(pagerAdapter);
        //绑定TabLayout和ViewPager
        tabLayout.setupWithViewPager(viewPager);

    }
}
