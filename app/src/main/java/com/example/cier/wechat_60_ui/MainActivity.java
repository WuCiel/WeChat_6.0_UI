package com.example.cier.wechat_60_ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.cier.wechat_60_ui.Adapter.MyViewPagerAdapter;
import com.example.cier.wechat_60_ui.Fragment.Chatlist;
import com.example.cier.wechat_60_ui.Fragment.Contact;
import com.example.cier.wechat_60_ui.Fragment.Discovery;
import com.example.cier.wechat_60_ui.Fragment.Me;
import com.example.cier.wechat_60_ui.Listener.BottomTabListener;
import com.example.cier.wechat_60_ui.Listener.ViewPagerChangeListener;
import com.example.cier.wechat_60_ui.MyView.BottomTabView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG="MainActivity";

    //toolbar
    private Toolbar toolbar;
    private String toolbarTitle;
    private SearchView searchView;

    //viewPager
    public static ViewPager viewPager;
    private List<Fragment> fragmentList;
    private MyViewPagerAdapter pagerAdapter;

    //BottomTab
    private BottomTabListener tabListener;
    private ViewPagerChangeListener pageChangeListener;
    private BottomTabView tab_1,tab_2,tab_3,tab_4;
    public static List<BottomTabView> tabList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initToolbar();
        setFragToPager();
    }

    private void init() {
        toolbarTitle = getResources().getString(R.string.app_name);
        viewPager= (ViewPager) findViewById(R.id.viewPager);

        fragmentList=new ArrayList<>();
        tabList=new ArrayList<>();
        tabListener=new BottomTabListener();
        pageChangeListener=new ViewPagerChangeListener();
        viewPager.setOnPageChangeListener(pageChangeListener);

        tab_1= (BottomTabView) findViewById(R.id.tab_one);
        tab_2= (BottomTabView) findViewById(R.id.tab_two);
        tab_3= (BottomTabView) findViewById(R.id.tab_three);
        tab_4= (BottomTabView) findViewById(R.id.tab_four);
        tabList.add(tab_1);
        tabList.add(tab_2);
        tabList.add(tab_3);
        tabList.add(tab_4);
        tab_1.setOnClickListener(tabListener);
        tab_2.setOnClickListener(tabListener);
        tab_3.setOnClickListener(tabListener);
        tab_4.setOnClickListener(tabListener);
        tab_1.setIconAlpha(1.0f);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main);
        toolbar.setTitle(toolbarTitle);
        setSupportActionBar(toolbar);//将toolbar设置为ActionBar
        toolbar.setOnMenuItemClickListener(onMenuItemClick);//设置子菜单监听
    }

    private void initSearchView(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.actionbar_search);
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setQueryHint("搜索");
//        searchView.onActionViewExpanded();
//        searchView.setIconified(true);

        searchView.setOnCloseListener(searchCloseListener);
        searchView.setOnQueryTextListener(searchQueryListener);
        searchView.setOnQueryTextFocusChangeListener(searchFocusChsngeListener);
//        searchView.setOnSystemUiVisibilityChangeListener(searchViewVisiblelistener);
    }

    private void setFragToPager(){
        Chatlist chatList=new Chatlist();
        Contact contact=new Contact();
        Discovery discovery=new Discovery();
        Me me=new Me();
        fragmentList.add(chatList);
        fragmentList.add(contact);
        fragmentList.add(discovery);
        fragmentList.add(me);

        pagerAdapter=new MyViewPagerAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        initSearchView(menu);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            toolbar.getMenu().findItem(R.id.actionbar_more).setVisible(false);
        }
        return super.onKeyDown(keyCode, event);
    }

    private SearchView.OnCloseListener searchCloseListener = new SearchView.OnCloseListener() {
        @Override
        public boolean onClose() {
            Toast.makeText(MainActivity.this, "search close", Toast.LENGTH_SHORT).show();
            toolbar.getMenu().findItem(R.id.actionbar_more).setVisible(true);
            return true;
        }
    };

    private SearchView.OnQueryTextListener searchQueryListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Toast.makeText(MainActivity.this, query + "", Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
//            Toast.makeText(MainActivity.this, newText+"", Toast.LENGTH_SHORT).show();
            return true;
        }
    };

    private SearchView.OnSystemUiVisibilityChangeListener searchViewVisiblelistener= new SearchView.OnSystemUiVisibilityChangeListener() {
        @Override
        public void onSystemUiVisibilityChange(int visibility) {
            Toast.makeText(MainActivity.this, visibility+"", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnFocusChangeListener searchFocusChsngeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            //搜索框获取焦点时隐藏overflowbutton，失去焦点时显示overflowbutton
            if (hasFocus) {
                toolbar.getMenu().findItem(R.id.actionbar_more).setVisible(false);
            } else {
                toolbar.getMenu().findItem(R.id.actionbar_more).setVisible(true);
            }
        }
    };

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.actionbar_search:
//                    toolbar.getMenu().findItem(R.id.actionbar_more).setVisible(false);
                    break;

                case R.id.actionbar_more:
                    break;

                case R.id.menu_start_group_chat:
                    Toast.makeText(MainActivity.this, item.getTitle() + "", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.menu_add_friend:
                    Toast.makeText(MainActivity.this, item.getTitle() + "", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.menu_scan:
                    Toast.makeText(MainActivity.this, item.getTitle() + "", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.menu_get_and_pay:
                    Toast.makeText(MainActivity.this, item.getTitle() + "", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.menu_help_and_suggest:
                    Toast.makeText(MainActivity.this, item.getTitle() + "", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    throw new RuntimeException("不存在此子菜单选项");
            }
            return true;
        }
    };
}
