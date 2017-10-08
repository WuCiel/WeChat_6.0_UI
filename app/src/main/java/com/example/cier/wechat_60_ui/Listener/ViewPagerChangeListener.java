package com.example.cier.wechat_60_ui.Listener;

import android.support.v4.view.ViewPager;

import com.example.cier.wechat_60_ui.MainActivity;

/**
 * Created by Cier on 2017/10/8.
 */

public class ViewPagerChangeListener implements ViewPager.OnPageChangeListener {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(positionOffset>0){
            MainActivity.tabList.get(position).setIconAlpha(1-positionOffset);
            MainActivity.tabList.get(position+1).setIconAlpha(positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
