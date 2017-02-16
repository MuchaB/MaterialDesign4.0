package com.ben.muchab.materialdesign40;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolBar = (Toolbar) findViewById(R.id.app_bar);

        DrawerLayout drawerL = (DrawerLayout) findViewById(R.id.drawer_layout);

        setSupportActionBar(toolBar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_nav);

        drawerFragment.setUp(R.id.fragment_nav,drawerL, toolBar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuu = getMenuInflater();
        menuu.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.navigateid) {
            Intent intent = new Intent(MainActivity.this, Navigation.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);

    }
}
