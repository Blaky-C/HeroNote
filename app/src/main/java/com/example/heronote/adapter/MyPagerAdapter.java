package com.example.heronote.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.heronote.fragment.Fragment1;
import com.example.heronote.fragment.Fragment2;
import com.example.heronote.fragment.Fragment3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2018/2/14.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

    private String[] titles = new String[]{"时间轴", "标签分类", "社区"};
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
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
