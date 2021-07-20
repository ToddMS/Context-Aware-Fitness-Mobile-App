package com.example.co3201weighttrainingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;

public class CurrentWorkoutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout iDrawerLayout;
    private ActionBarDrawerToggle iToggle;
    Button startWorkoutButton;
    Button goBackButton;
    TextView workoutText;
    InputStream inputStream;
    private int workoutNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_workout);

        showAvailableWorkouts();
        navigationMenu();

        goBackButtonPressed();
    }

    public void showAvailableWorkouts() {

        workoutText = findViewById(R.id.workoutText);

        Intent incomingIntent = getIntent();
        Bundle switchesFlipped = incomingIntent.getExtras();

        int workoutIntensity = incomingIntent.getIntExtra("WORKOUT_INTENSITY", 0);
        boolean weightAccess = switchesFlipped.getBoolean("WEIGHT_ACCESS");
        boolean upperBodyInjured = switchesFlipped.getBoolean("UPPER_BODY_INJURED");
        boolean lowerBodyInjured = switchesFlipped.getBoolean("LOWER_BODY_INJURED");
        boolean thirtyMinutes = switchesFlipped.getBoolean("THIRTY_MINUTES");
        boolean sixtyMinutes = switchesFlipped.getBoolean("SIXTY_MINUTES");

        if (workoutIntensity <= 5){
            if (weightAccess){
                if (!(upperBodyInjured && lowerBodyInjured)){ // no injures
                    if (thirtyMinutes){
                        inputStream = getResources().openRawResource(R.raw.workout1);
                        workoutNumber = 1;
                    }else if (sixtyMinutes) {
                        inputStream = getResources().openRawResource(R.raw.workout13);
                        workoutNumber = 13;
                    }
                }else if((!upperBodyInjured && lowerBodyInjured)){ //upper body injured
                    if (thirtyMinutes){
                        inputStream = getResources().openRawResource(R.raw.workout9);
                        workoutNumber = 9;
                    }else if (sixtyMinutes) {
                        inputStream = getResources().openRawResource(R.raw.workout21);
                        workoutNumber = 21;
                    }
                }else if((upperBodyInjured && !lowerBodyInjured)){ //lower body injured
                    if (thirtyMinutes){
                        inputStream = getResources().openRawResource(R.raw.workout5);
                        workoutNumber = 5;
                    }else if (sixtyMinutes){
                        inputStream = getResources().openRawResource(R.raw.workout17);
                        workoutNumber = 17;
                    }
                }else if(!(upperBodyInjured && lowerBodyInjured)){ //both body injured
                }
            }else { // no weight access
                if (!(upperBodyInjured && lowerBodyInjured)){// no injures
                    if (thirtyMinutes){
                        inputStream = getResources().openRawResource(R.raw.workout3);
                        workoutNumber = 3;
                    }else if (sixtyMinutes){
                        inputStream = getResources().openRawResource(R.raw.workout15);
                        workoutNumber = 15;
                    }
                }else if((!upperBodyInjured && lowerBodyInjured)){ //upper body injured
                    if (thirtyMinutes){
                        inputStream = getResources().openRawResource(R.raw.workout11);
                        workoutNumber = 11;
                    }else if (sixtyMinutes) {
                        inputStream = getResources().openRawResource(R.raw.workout23);
                        workoutNumber = 23;
                    }
                }else if((upperBodyInjured && !lowerBodyInjured)){ //lower body injured
                    if (thirtyMinutes){
                        inputStream = getResources().openRawResource(R.raw.workout7);
                        workoutNumber = 7;
                    }else if (sixtyMinutes){
                        inputStream = getResources().openRawResource(R.raw.workout19);
                        workoutNumber = 19;
                    }
                }else if(!(upperBodyInjured && lowerBodyInjured)){ //both body injured
                }
            }
        }else { //intensity 6-10
            if (weightAccess){
                if (!(upperBodyInjured && lowerBodyInjured)){// no injures
                    if (thirtyMinutes){
                        inputStream = getResources().openRawResource(R.raw.workout2);
                        workoutNumber = 2;
                    }else if (sixtyMinutes){
                        inputStream = getResources().openRawResource(R.raw.workout14);
                        workoutNumber = 14;
                    }
                }else if((!upperBodyInjured && lowerBodyInjured)){ //upper body injured
                    if (thirtyMinutes){
                        inputStream = getResources().openRawResource(R.raw.workout10);
                        workoutNumber = 10;
                    }else if (sixtyMinutes) {
                        inputStream = getResources().openRawResource(R.raw.workout22);
                        workoutNumber = 22;
                    }
                }else if((upperBodyInjured && !lowerBodyInjured)){ //lower body injured
                    if (thirtyMinutes){
                        inputStream = getResources().openRawResource(R.raw.workout6);
                        workoutNumber = 6;
                    }else if (sixtyMinutes){
                        inputStream = getResources().openRawResource(R.raw.workout18);
                        workoutNumber = 18;
                    }
                }else if(!(upperBodyInjured && lowerBodyInjured)){ //both body injured
                }

            }else { // no weight access
                if (!(upperBodyInjured && lowerBodyInjured)){ // no injures
                    if (thirtyMinutes){
                        inputStream = getResources().openRawResource(R.raw.workout4);
                        workoutNumber = 4;
                    }else if (sixtyMinutes){
                        inputStream = getResources().openRawResource(R.raw.workout16);
                        workoutNumber = 16;
                    }
                }else if((!upperBodyInjured && lowerBodyInjured)){ //upper body injured
                    if (thirtyMinutes){
                        inputStream = getResources().openRawResource(R.raw.workout12);
                        workoutNumber = 12;
                    }else if (sixtyMinutes) {
                        inputStream = getResources().openRawResource(R.raw.workout24);
                        workoutNumber = 24;
                    }
                }else if((upperBodyInjured && !lowerBodyInjured)){ //lower body injured
                    if (thirtyMinutes){
                        inputStream = getResources().openRawResource(R.raw.workout8);
                        workoutNumber = 8;
                    }else if (sixtyMinutes){
                        inputStream = getResources().openRawResource(R.raw.workout20);
                        workoutNumber = 20;
                    }
                }else if(!(upperBodyInjured && lowerBodyInjured)){ //both body injured
                }
            }
        }

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        StringBuilder text = new StringBuilder();

        try {
            while ((line = bufferedReader.readLine())!= null){
                text.append(line);
                text.append("\n");
            }
        }catch (IOException e){
            System.out.println(e);
        }
        workoutText.setText(text);

        startWorkout();
    }


    public void navigationMenu(){
        iDrawerLayout = findViewById(R.id.drawer_layout);
        iToggle = new ActionBarDrawerToggle(this, iDrawerLayout, R.string.openNavDrawer, R.string.closeNavDrawer);

        iDrawerLayout.addDrawerListener(iToggle);
        iToggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    public void startWorkout(){
        Intent intentWorkoutChosen = new Intent(CurrentWorkoutActivity.this, ExerciseStartActivity.class);

        startWorkoutButton = findViewById(R.id.startWorkoutButton);
        startWorkoutButton.setOnClickListener(v -> { //when the go back button is pressed



            intentWorkoutChosen.putExtra("WORKOUT", workoutNumber);
            startActivity(intentWorkoutChosen);

        });
    }

    public void goBackButtonPressed(){
        goBackButton = (Button) findViewById(R.id.goBackButton);
        goBackButton.setOnClickListener(v -> { //when the go back button is pressed
            Intent intentLogs = new Intent(CurrentWorkoutActivity.this, MainActivity.class);
            intentLogs.putExtra("FRAGMENT_PAGE", "workouts");
            startActivity(intentLogs);
        });
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
                Intent intentHome = new Intent(CurrentWorkoutActivity.this, MainActivity.class);
                intentHome.putExtra("FRAGMENT_PAGE", "home");
                startActivity(intentHome);
                break;
            case R.id.navLogs:
                Intent intentLogs = new Intent(CurrentWorkoutActivity.this, MainActivity.class);
                intentLogs.putExtra("FRAGMENT_PAGE", "logs");
                startActivity(intentLogs);
                break;
            case R.id.navWorkouts:
                Intent intentWorkouts = new Intent(CurrentWorkoutActivity.this, MainActivity.class);
                intentWorkouts.putExtra("FRAGMENT_PAGE", "workouts");
                startActivity(intentWorkouts);
                break;
            case R.id.navStats:
                Intent intentStats = new Intent(CurrentWorkoutActivity.this, MainActivity.class);
                intentStats.putExtra("FRAGMENT_PAGE", "statistics");
                startActivity(intentStats);
                break;
            case R.id.navFAQ:
                Intent intentFAQ = new Intent(CurrentWorkoutActivity.this, MainActivity.class);
                intentFAQ.putExtra("FRAGMENT_PAGE", "faq");
                startActivity(intentFAQ);
                break;
        }
        iDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}

