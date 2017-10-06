package com.example.cier.wechat_60_ui;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initToolbar();
    }

    private void init(){
        toolbarTitle=getResources().getString(R.string.app_name);
    }

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main);
        toolbar.setTitle(toolbarTitle);
        setSupportActionBar(toolbar);//将toolbar设置为ActionBar
        toolbar.setOnMenuItemClickListener(onMenuItemClick);//设置监听
    }

    private void initSearchView(Menu menu){
        MenuItem menuItem=menu.findItem(R.id.actionbar_search);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setQueryHint("搜索");
        searchView.onActionViewExpanded();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main,menu);

        initSearchView(menu);
        return true;
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick=new Toolbar.OnMenuItemClickListener(){

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.actionbar_search:
                    break;

                case R.id.actionbar_more:
                    break;

                case R.id.menu_start_group_chat:
                    Toast.makeText(MainActivity.this, item.getTitle()+"", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.menu_add_friend:
                    Toast.makeText(MainActivity.this, item.getTitle()+"", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.menu_scan:
                    Toast.makeText(MainActivity.this, item.getTitle()+"", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.menu_get_and_pay:
                    Toast.makeText(MainActivity.this, item.getTitle()+"", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.menu_help_and_suggest:
                    Toast.makeText(MainActivity.this, item.getTitle()+"", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    throw new RuntimeException("不存在此子菜单选项");
            }
            return true;
        }
    };
}
