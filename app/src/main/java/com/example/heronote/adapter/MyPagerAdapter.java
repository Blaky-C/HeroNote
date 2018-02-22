package com.example.heronote.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Jack on 2018/2/14.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

    private String[] titles = new String[]{"时间轴", "标签", "社区"};
    private List<Fragment> fragList;

    public MyPagerAdapter(FragmentManager fm, List<Fragment> list){
        super(fm);
        fragList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    @Override
    public int getCount() {
        return fragList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
