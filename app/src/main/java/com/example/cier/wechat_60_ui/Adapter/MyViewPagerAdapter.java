package com.example.cier.wechat_60_ui.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Cier on 2017/10/7.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter{
    public static final String TAG="MyViewPagerAdapter";
    private List<Fragment> list;

    public MyViewPagerAdapter(FragmentManager manager,List<Fragment> list) {
        super(manager);
        this.list = list;
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
