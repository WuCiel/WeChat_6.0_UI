package com.example.cier.wechat_60_ui.Listener;

import android.view.View;

import com.example.cier.wechat_60_ui.MainActivity;
import com.example.cier.wechat_60_ui.R;

/**
 * Created by Cier on 2017/10/8.
 */

public class BottomTabListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tab_one:
                resetTabView();
                MainActivity.viewPager.setCurrentItem(0,false);
                MainActivity.tabList.get(0).setIconAlpha(1.0f);
                break;

            case R.id.tab_two:
                resetTabView();
                MainActivity.viewPager.setCurrentItem(1,false);
                MainActivity.tabList.get(1).setIconAlpha(1.0f);
                break;

            case R.id.tab_three:
                resetTabView();
                MainActivity.viewPager.setCurrentItem(2,false);
                MainActivity.tabList.get(2).setIconAlpha(1.0f);
                break;

            case R.id.tab_four:
                resetTabView();
                MainActivity.viewPager.setCurrentItem(3,false);
                MainActivity.tabList.get(3).setIconAlpha(1.0f);
                break;
        }

    }
        private void resetTabView(){
            int len=MainActivity.tabList.size();
            for(int i=0;i<len;i++)
                MainActivity.tabList.get(i).setIconAlpha(0.0f);
        }
}
