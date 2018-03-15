package com.example.heronote.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.example.heronote.util.Utils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

/**
 * Created by SQS on 2018/3/15.
 */

public class MyTabLayout extends CommonTabLayout implements OnTabSelectListener, ViewPager.OnPageChangeListener {

    private ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();

    private ArrayList<Fragment> fragments = new ArrayList<>();

    private ViewPager viewPager;

    public MyTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTabLayout add(final String title, final int selectedIcon, final int unselectedIcon, Fragment fragment) {
        tabEntities.add(new CustomTabEntity() {
            @Override
            public String getTabTitle() {
                return title;
            }

            @Override
            public int getTabSelectedIcon() {
                return selectedIcon;
            }

            @Override
            public int getTabUnselectedIcon() {
                return unselectedIcon;
            }
        });
        fragments.add(fragment);
        return this;
    }

    public void setViewPager(FragmentActivity fa, int viewPagerId) {
        this.setTabData(tabEntities);
        viewPager = fa.findViewById(viewPagerId);
        viewPager.setAdapter(new FragmentPagerAdapter(fa.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        this.setOnTabSelectListener(this);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onTabSelect(int position) {
        if (viewPager != null) {
            viewPager.setCurrentItem(position);
        }
    }

    @Override
    public void onTabReselect(int position) {
        Utils.toast(MyTabLayout.this.getTabEntity(position).getTabTitle());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        this.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public CustomTabEntity getTabEntity(int position) {
        return tabEntities.get(position);
    }

}
