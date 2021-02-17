package com.next.androidtest.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;

import com.google.android.material.appbar.AppBarLayout;
import com.next.androidtest.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;

//    public MainActivity() {
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding();
    }

    private void binding(){
        drawerLayout = findViewById(R.id.drawer_layout);
        appBarLayout = findViewById(R.id.appBarNormalMode);
        toolbar = findViewById(R.id.toolbar_normalMode);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.normal_mode_menu, menu);
        return true;
    }

    public void openDrawerLayout(View view) {
        drawerLayout.openDrawer(Gravity.LEFT);
    }
}