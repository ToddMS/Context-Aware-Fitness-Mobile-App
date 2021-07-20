package com.example.co3201weighttrainingapplication;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Objects;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private DrawerLayout iDrawerLayout;
    private ActionBarDrawerToggle iToggle;
    private TextView intensityNumber;
    private SeekBar intensitySlider;
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//opening on the home fragment

        Intent incomingIntent = getIntent();
        String incomingFragmentPage = incomingIntent.getStringExtra("FRAGMENT_PAGE");
        Log.d(TAG, "onCreate: " + incomingFragmentPage);

        if (incomingFragmentPage != null && incomingFragmentPage.equals("home")){
            getSupportFragmentManager().beginTransaction().replace(R.id.homePage, new HomeFragment()).commit();
        }else if (incomingFragmentPage != null &&incomingFragmentPage.equals("logs")){
            getSupportFragmentManager().beginTransaction().replace(R.id.homePage, new LogsFragment()).commit();
        }else if (incomingFragmentPage != null &&incomingFragmentPage.equals("workouts")){
            getSupportFragmentManager().beginTransaction().replace(R.id.homePage, new WorkoutsFragment()).commit();
        }else if (incomingFragmentPage != null &&incomingFragmentPage.equals("statistics")){
            getSupportFragmentManager().beginTransaction().replace(R.id.homePage, new StatsFragment()).commit();
        }else if (incomingFragmentPage != null &&incomingFragmentPage.equals("faq")){
            getSupportFragmentManager().beginTransaction().replace(R.id.homePage, new faqFragment()).commit();
        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.homePage, new HomeFragment()).commit();
        }




        navigationMenu();

    }

    public void navigationMenu(){

        iDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        iToggle = new ActionBarDrawerToggle(this, iDrawerLayout, R.string.openNavDrawer, R.string.closeNavDrawer);

        iDrawerLayout.addDrawerListener(iToggle);
        iToggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(iToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.navHome:
                getSupportFragmentManager().beginTransaction().replace(R.id.homePage,
                        new HomeFragment()).commit();
                break;
            case R.id.navLogs:
                getSupportFragmentManager().beginTransaction().replace(R.id.homePage,
                        new LogsFragment()).commit();
                break;
            case R.id.navWorkouts:
                getSupportFragmentManager().beginTransaction().replace(R.id.homePage,
                        new WorkoutsFragment()).commit();
                break;
            case R.id.navStats:
                getSupportFragmentManager().beginTransaction().replace(R.id.homePage,
                        new StatsFragment()).commit();
                break;
            case R.id.navFAQ:
                getSupportFragmentManager().beginTransaction().replace(R.id.homePage,
                        new faqFragment()).commit();
                break;
        }
        iDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}



