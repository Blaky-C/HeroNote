package com.example.heronote.activity;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.heronote.R;
import com.example.heronote.base.BaseActivity;
import com.example.heronote.bean.Note;
import com.example.heronote.db.NoteDbOperate;
import com.example.heronote.fragment.Fragment1;
import com.example.heronote.fragment.Fragment2;
import com.example.heronote.fragment.Fragment3;
import com.example.heronote.util.Utils;
import com.example.heronote.view.MyTabLayout;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    final private static int EDIT_ACTIVITY = 0;

    private long timeMark;
    private int drawerGravity = GravityCompat.START;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private Fragment1 fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);

        setSwipeBack(false);
        transparentStatusBar();
        initActionBar(R.id.toolbar, "");

        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 0);

        navView.setNavigationItemSelectedListener(this);
        View navHeader = navView.inflateHeaderView(R.layout.nav_header);
        Glide.with(this).load(R.drawable.nav_photo).into((ImageView) navHeader.findViewById(R.id.nav_photo));
        initListenerToThis(navHeader, R.id.icon_image);
        initListenerToThis(R.id.fab);

        fragment1 = new Fragment1();
        ((MyTabLayout) findViewById(R.id.tab_layout))
                .add("时间轴", R.mipmap.date, R.mipmap.date_un, fragment1)
                .add("标签", R.mipmap.tag, R.mipmap.tag_un, new Fragment2())
                .add("社区", R.mipmap.explore, R.mipmap.explore_un, new Fragment3())
                .setViewPager(this, R.id.view_pager);

//        setupTabLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(drawerGravity);
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
                Utils.goToActivity(LoginActivity.class);
                drawerLayout.closeDrawer(drawerGravity);
                break;
            case R.id.fab:
//                Intent intent = new Intent(MainActivity.this, EditActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                BaseApplication.getContext().startActivity(intent);
//                Utils.goToActivity(EditActivity.class);
                startActivityForResult(new Intent(this, EditActivity.class), EDIT_ACTIVITY);
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
        if (drawerLayout.isDrawerOpen(drawerGravity)) {
            drawerLayout.closeDrawer(drawerGravity);
        } else {
            if (System.currentTimeMillis() - timeMark < 1000) {
                NoteDbOperate operate = new NoteDbOperate();
                for (Note note : operate.queryNotesAll()) {
                    operate.deleteNote(note.getTimeMillis());
                }
                operate.deleteNote(operate.queryNotesAll());
                super.onBackPressed();
            } else {
                Utils.toast("Press Back Again to Quit");
                timeMark = System.currentTimeMillis();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case EDIT_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    fragment1.setFlag(false);
                    fragment1.getSwipeRefreshLayout().setRefreshing(true);
                    fragment1.onRefresh();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPostResume() {
        navView.setCheckedItem(R.id.nav_home);
        super.onPostResume();
    }

    @Override
    protected void actionAfterDeny(int requestCode) {
        super.actionAfterDeny(requestCode);
        finish();
    }

//    @Override
//    protected void actionAfterPermiss(int requestCode) {
//        super.actionAfterPermiss(requestCode);
//    }

//    private void setupTabLayout() {
//        //初始化
//        List<Fragment> fragmentList = Arrays.asList(new Fragment1(), new Fragment2(), new Fragment3());
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
//        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
//        //绑定适配器
//        FragmentManager manager = getSupportFragmentManager();
//        PagerAdapter pagerAdapter = new MyPagerAdapter(manager, fragmentList);
//        viewPager.setAdapter(pagerAdapter);
//        //绑定TabLayout和ViewPager
//        tabLayout.setupWithViewPager(viewPager);
//        //设置图标及长按事件
//        String[] titles = {"时间轴", "标签", "社区"};
//        int[] icons = {R.mipmap.date, R.mipmap.tag, R.mipmap.explore};
//        for (int i = 0; i < tabLayout.getTabCount(); i++) {
//            TabLayout.Tab tab = tabLayout.getTabAt(i);
//            tab.setIcon(icons[i]);
//            Class c = tab.getClass();
//            try {
//                Field field = c.getDeclaredField("mView");
//                field.setAccessible(true);
//                View view = (View) field.get(tab);
//                view.setOnLongClickListener(Utils.longClickListenerToToast(titles[i]));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
