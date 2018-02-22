package com.example.heronote;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.heronote.adapter.MyPagerAdapter;
import com.example.heronote.base.BaseActivity;
import com.example.heronote.fragment.Fragment1;
import com.example.heronote.fragment.Fragment2;
import com.example.heronote.fragment.Fragment3;
import com.example.heronote.utility.Utils;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);

        transparentStatusBar();

        initActionBar(R.id.toolbar);

        navView.setCheckedItem(R.id.nav_home);
        navView.setNavigationItemSelectedListener(this);

        initListenerToThis(navView.inflateHeaderView(R.layout.nav_header), R.id.icon_image);
        initListenerToThis(R.id.fab);

        setupTabWithViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.search:
                Utils.toast("You click search");
            break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_image:
                goToANew(LoginActivity.class);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.fab:
                Utils.snackbar(view, "Note Sth", "Undo", Utils.listenerToToast("Canceled"));
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            default:
                Utils.toast(item.getTitle().toString());
                break;
        }
        drawerLayout.closeDrawers();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPostResume() {
        navView.setCheckedItem(R.id.nav_home);
        super.onPostResume();
    }

    private void setupTabWithViewPager() {
        //初始化
        List<Fragment> fragmentList = Arrays.asList(new Fragment1(), new Fragment2(), new Fragment3());
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        //绑定适配器
        FragmentManager manager = getSupportFragmentManager();
        PagerAdapter pagerAdapter = new MyPagerAdapter(manager, fragmentList);
        viewPager.setAdapter(pagerAdapter);
        //绑定TabLayout和ViewPager
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.mipmap.date);
        tabLayout.getTabAt(1).setIcon(R.mipmap.tag);
        tabLayout.getTabAt(2).setIcon(R.mipmap.explore);
//        tabLayout.setViewPager(viewPager);
    }

}
