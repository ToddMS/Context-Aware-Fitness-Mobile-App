package com.example.co3201weighttrainingapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.http.SslCertificate;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Objects;


public class ExerciseStartActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SensorEventListener {
    private DrawerLayout iDrawerLayout;
    private ActionBarDrawerToggle iToggle;

    private CountDownTimer restTimer;
    private boolean restTimerOn;
    private long restTimeLeftInMillis;

    private SensorManager sensorManager;
    Sensor accelerometer;
    private boolean startReadingAccelerometre;
    private long userMovedDown;

    private int workoutButtonClicked;
    Vibrator vibrator;



    private String movement1;
    private String movement2;
    private String movement3;
    private String workoutTitle;
    private int reps;
    private int sets;
    private int rest;
    private int repsDone;
    private int setsDone;
    private int movementIncrement = 1;
    TextView movementText;
    TextView repsText;
    TextView setsText;
    TextView timeLeft;
    TextView setsDoneTextView;
    TextView repsDoneTextView;
    ImageButton imageButtonI;
    Button startWorkoutButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_start);
        movementText = findViewById(R.id.movementTextView);
        repsText = findViewById(R.id.repsTextView);
        setsText = findViewById(R.id.setsTextView);
        timeLeft = findViewById(R.id.timeLeft);
        startWorkoutButton = findViewById(R.id.startWorkout);
        iDrawerLayout = findViewById(R.id.drawer_layout);
        repsDoneTextView = findViewById(R.id.repsDone);
        setsDoneTextView = findViewById(R.id.setsDone);
        imageButtonI = findViewById(R.id.imageButtonI);

        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        workoutChosen();
        navigationMenu();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float yCoord = sensorEvent.values[1]*10;
        if (!startReadingAccelerometre){
            repsDoneTextView.setText("REPS: " + reps +"/"+reps);

        }else{
            repsDoneTextView.setText("REPS: " + repsDone +"/"+reps);
        }


        if (startReadingAccelerometre){
            if (repsDone >= reps){
                startReadingAccelerometre = false;
                repsDone = 0;
                setsDone += 1;
                startWorkoutButton.setClickable(true);
                startWorkoutButton.setBackgroundColor(getResources().getColor(R.color.foregroundColour));
                startWorkoutButton.setText("START MOVEMENT");
                setsDoneTextView.setText("SETS: " + setsDone +"/"+sets);
                startRestTimer();
            }

            if (setsDone >= sets){
                startReadingAccelerometre = false;

                if (movementIncrement == 1){
                    movementText.setText("MOVEMENT: " + movement2);
                    movementIncrement += 1;
                    setsDone = 0;
                    setsDoneTextView.setText("SETS: " + setsDone +"/"+sets);
                }else if (movementIncrement == 2){
                    movementText.setText("MOVEMENT: " + movement3);
                    movementIncrement += 1;
                    setsDone = 0;
                    setsDoneTextView.setText("SETS: " + setsDone +"/"+sets);
                }else{
                    workoutFinished();

                }
            }

            if (yCoord >= 20){//phone/user is moving down
                userMovedDown = System.currentTimeMillis();
            }
            if (yCoord <= -20){//phone/user is moving up
                if (userMovedDown > System.currentTimeMillis() - 2000){
                    repsDone += 1;
                    restTimeLeftInMillis = rest*1000;
                    updateRestTimer();
                    repsDoneTextView.setText("REPS: " + repsDone +"/"+reps);
                    vibrator.vibrate(100);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }



    public void startWorkout() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); //starting the accelerometre
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sensorManager.registerListener(ExerciseStartActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        System.out.println("REST LENGTH: " + rest);
        System.out.println("WORKOUT SETS: " + sets);
        System.out.println("MOVEMENT REPS: " + reps);
        startReadingAccelerometre = true;
    }

    public void startRestTimer(){
        restTimer = new CountDownTimer(restTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                restTimeLeftInMillis = millisUntilFinished;
                updateRestTimer();
            }
            @Override
            public void onFinish() {
            }
        }.start();
        restTimerOn = true;
    }
    public void updateRestTimer(){
        startWorkoutButton.setClickable(false);
        startWorkoutButton.setBackgroundColor(getResources().getColor(R.color.backgroundColour2));
        int minutes = (int)restTimeLeftInMillis/60/1000;
        int seconds = (int)restTimeLeftInMillis%60000/1000;
        String restTimeLeftText;
        restTimeLeftText = "" + minutes;
        restTimeLeftText +=":";

        if (seconds < 10){
            restTimeLeftText += "0";
        }
        restTimeLeftText += seconds;
        System.out.println(restTimeLeftText);
        timeLeft.setText("REST\n" + restTimeLeftText);
        if (timeLeft.getText().equals("REST\n0:00")){
            startWorkoutButton.setClickable(true);
            startWorkoutButton.setBackgroundColor(getResources().getColor(R.color.foregroundColour));

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void workoutFinished(){

        incrementMonth();
        Intent popupWindow = new Intent(ExerciseStartActivity.this,SaveWorkoutPopUpActivity.class);
        Bundle workoutData = new Bundle();




        if (workoutButtonClicked == 1){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutBack++;
        }else if (workoutButtonClicked == 2){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutBack++;
        }else if (workoutButtonClicked == 3){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutArms++;
        }else if (workoutButtonClicked == 4){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutArms++;
        }else if (workoutButtonClicked == 5){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutBack++;
        }else if (workoutButtonClicked == 6){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutBack++;
        }else if (workoutButtonClicked == 7){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutArms++;
        }else if (workoutButtonClicked == 8){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutArms++;
        }else if (workoutButtonClicked == 9){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutBack++;
        }else if (workoutButtonClicked == 10){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutBack++;
        }else if (workoutButtonClicked == 11){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutLegs++;
        }else if (workoutButtonClicked == 12){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutLegs++;
        }else if (workoutButtonClicked == 13){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutBack++;
        }else if (workoutButtonClicked == 14){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutBack++;
        }else if (workoutButtonClicked == 15){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutArms++;
        }else if (workoutButtonClicked == 16){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutArms++;
        }else if (workoutButtonClicked == 17){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutBack++;
        }else if (workoutButtonClicked == 18){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutBack++;
        }else if (workoutButtonClicked == 19){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutArms++;
        }else if (workoutButtonClicked == 20){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutArms++;
        }else if (workoutButtonClicked == 21){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutBack++;
        }else if (workoutButtonClicked == 22){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutBack++;
        }else if (workoutButtonClicked == 23){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutLegs++;
        }else if (workoutButtonClicked == 24){
            HomeFragment.workoutLegs++;
            HomeFragment.workoutChest++;
            HomeFragment.workoutLegs++;
        }


        workoutData.putString("MOVEMENT_TITLE", workoutTitle);
        workoutData.putString("MOVEMENT1", movement1);
        workoutData.putString("MOVEMENT2", movement2);
        workoutData.putString("MOVEMENT3", movement3);
        workoutData.putInt("REPS", reps);
        workoutData.putInt("SETS", sets);
        workoutData.putInt("REST", rest);
        vibrator.vibrate(1000);

        popupWindow.putExtras(workoutData);
        startActivity(popupWindow);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void incrementMonth(){
        LocalDate currentDate = LocalDate.now();
        Month month = currentDate.getMonth();
        String currMonth = month.toString();

        System.out.println("Testing123");

        switch (currMonth){
            case "JANUARY":
                HomeFragment.workoutsJan++;
                break;
            case "FEBRUARY":
                HomeFragment.workoutsFeb++;
                break;
            case "MARCH":
                HomeFragment.workoutsMar++;
                break;
            case "APRIL":
                HomeFragment.workoutsApr++;
                break;
            case "MAY":
                HomeFragment.workoutsMay++;
                break;
            case "JUNE":
                HomeFragment.workoutsJun++;
                break;
            case "JULY":
                HomeFragment.workoutsJul++;
                break;
            case "AUGUST":
                HomeFragment.workoutsAug++;
                break;
            case "SEPTEMBER":
                HomeFragment.workoutsSep++;
                break;
            case "OCTOBER":
                HomeFragment.workoutsOct++;
                break;
            case "NOVEMBER":
                HomeFragment.workoutsNov++;
                break;
            case "DECEMBER":
                HomeFragment.workoutsDec++;
                break;
        }
    }

    public void workoutChosen(){ //getting data from previous activity
        Intent incomingIntent = getIntent();
        workoutButtonClicked = incomingIntent.getIntExtra("WORKOUT", 0);
        if (workoutButtonClicked == 1){
            workoutTitle = "1";
            movement1 = "SQUAT";
            movement2 = "BENCH PRESS";
            movement3 = "DEADLIFT";
            reps = 2; //6
            sets = 2; //2
            rest = 1; //90
        }else if (workoutButtonClicked == 2){
            workoutTitle = "2";
            movement1 = "SQUAT";
            movement2 = "BENCH PRESS";
            movement3 = "DEADLIFT";

            reps = 6;
            sets = 2;
            rest = 60;
        }else if (workoutButtonClicked == 3){
            workoutTitle = "3";
            movement1 = "BODYWEIGHT SQUAT";
            movement2 = "PUSH UPS";
            movement3 = "TRICEP DIPS";
            reps = 10;
            sets = 4;
            rest = 60;
        }else if (workoutButtonClicked == 4){
            workoutTitle = "4";
            movement1 = "BODYWEIGHT SQUAT";
            movement2 = "PUSH UPS";
            movement3 = "TRICEP DIPS";
            reps = 10;
            sets = 4;
            rest = 45;
        }else if (workoutButtonClicked == 5){
            workoutTitle = "5";
            movement1 = "GOBLET SQUAT";
            movement2 = "BENCH PRESS";
            movement3 = "BARBELL ROW";
            reps = 6;
            sets = 2;
            rest = 90;
        }else if (workoutButtonClicked == 6){
            workoutTitle = "6";
            movement1 = "GOBLET SQUAT";
            movement2 = "BENCH PRESS";
            movement3 = "BARBELL ROW";
            reps = 6;
            sets = 2;
            rest = 60;
        }else if (workoutButtonClicked == 7){
            workoutTitle = "7";
            movement1 = "BODYWEIGHT SQUAT";
            movement2 = "PUSH UPS";
            movement3 = "TRICEP DIPS";
            reps = 10;
            sets = 4;
            rest = 60;

        }else if (workoutButtonClicked == 8){
            workoutTitle = "8";
            movement1 = "BODYWEIGHT SQUAT";
            movement2 = "PUSH UPS";
            movement3 = "TRICEP DIPS";
            reps = 10;
            sets = 4;
            rest = 45;
        }else if (workoutButtonClicked == 9){
            workoutTitle = "9";
            movement1 = "SQUAT";
            movement2 = "PUSH UPS";
            movement3 = "DEADLIFT";
            reps = 10;
            sets = 4;
            rest = 90;
        }else if (workoutButtonClicked == 10){
            workoutTitle = "19";
            movement1 = "SQUAT";
            movement2 = "PUSH UP";
            movement3 = "DEADLIFT";
            reps = 6;
            sets = 2;
            rest = 60;
        }else if (workoutButtonClicked == 11){
            workoutTitle = "11";
            movement1 = "BODYWEIGHT SQUAT";
            movement2 = "KNEE PUSH UPS";
            movement3 = "BODYWEIGHT LUNGES";
            reps = 10;
            sets = 4;
            rest = 60;
        }else if (workoutButtonClicked == 12){
            workoutTitle = "12";
            movement1 = "BODYWEIGHT SQUAT";
            movement2 = "KNEE PUSH UPS";
            movement3 = "BODYWEIGHT LUNGES";
            reps = 10;
            sets = 4;
            rest = 45;
        }else if (workoutButtonClicked == 13){
            workoutTitle = "13";
            movement1 = "SQUAT";
            movement2 = "BENCH PRESS";
            movement3 = "DEADLIFT";
            reps = 6;
            sets = 4;
            rest = 90;
        }else if (workoutButtonClicked == 14){
            workoutTitle = "14";
            movement1 = "SQUAT";
            movement2 = "BENCH PRESS";
            movement3 = "DEADLIFT";
            reps = 6;
            sets = 4;
            rest = 60;
        }else if (workoutButtonClicked == 15){
            workoutTitle = "15";
            movement1 = "BODYWEIGHT SQUAT";
            movement2 = "PUSH UPS";
            movement3 = "TRICEP DIPS";
            reps = 12;
            sets = 5;
            rest = 60;
        }else if (workoutButtonClicked == 16){
            workoutTitle = "16";
            movement1 = "BODYWEIGHT SQUAT";
            movement2 = "PUSH UPS";
            movement3 = "TRICEP DIPS";
            reps = 12;
            sets = 5;
            rest = 45;
        }else if (workoutButtonClicked == 17){
            workoutTitle = "17";
            movement1 = "GOBLET SQUAT";
            movement2 = "BENCH PRESS";
            movement3 = "BARBELL ROW";
            reps = 6;
            sets = 4;
            rest = 90;
        }else if (workoutButtonClicked == 18){
            workoutTitle = "18";
            movement1 = "GOBLET SQUAT";
            movement2 = "BENCH PRESS";
            movement3 = "BARBELL ROW";
            reps = 6;
            sets = 4;
            rest = 60;
        }else if (workoutButtonClicked == 19){
            workoutTitle = "19";
            movement1 = "BODYWEIGHT SQUAT";
            movement2 = "PUSH UPS";
            movement3 = "TRICEP DIPS";
            reps = 12;
            sets = 5;
            rest = 60;
        }else if (workoutButtonClicked == 20){
            workoutTitle = "20";
            movement1 = "BODYWEIGHT SQUAT";
            movement2 = "PUSH UPS";
            movement3 = "TRICEP DIPS";
            reps = 12;
            sets = 5;
            rest = 45;
        }else if (workoutButtonClicked == 21){
            workoutTitle = "21";
            movement1 = "SQUAT";
            movement2 = "PUSH UPS";
            movement3 = "DEADLIFT";
            reps = 6;
            sets = 4;
            rest = 90;
        }else if (workoutButtonClicked == 22){
            workoutTitle = "22";
            movement1 = "SQUAT";
            movement2 = "PUSH UPS";
            movement3 = "DEADLIFT";
            reps = 6;
            sets = 4;
            rest = 60;
        }else if (workoutButtonClicked == 23){
            workoutTitle = "23";
            movement1 = "BODYWEIGHT SQUAT";
            movement2 = "KNEE PUSH UPS";
            movement3 = "BODYWEIGHT LUNGES";
            reps = 12;
            sets = 5;
            rest = 60;
        }else if (workoutButtonClicked == 24){
            workoutTitle = "24";
            movement1 = "BODYWEIGHT SQUAT";
            movement2 = "KNEE PUSH UPS";
            movement3 = "BODYWEIGHT LUNGES";
            reps = 12;
            sets = 5;
            rest = 45;
        }


        System.out.println(rest);
        restTimeLeftInMillis = rest*1000;
        updateRestTimer();
        movementText.setText("MOVEMENT: " +  movement1);

        repsText.setText("REPS: "+ reps);
        setsText.setText("SETS: " + sets);
        repsDoneTextView.setText("REPS: 0/"+ reps);
        setsDoneTextView.setText("SETS: 0/" + sets);


        startWorkoutButton.setOnClickListener(v -> {
            startWorkout();
            startWorkoutButton.setText("MOVEMENT STARTED");
            startWorkoutButton.setBackgroundColor(getResources().getColor(R.color.backgroundColour2));
            startReadingAccelerometre = true;
            startWorkoutButton.setClickable(false);
            vibrator.vibrate(100);
        });

        imageButtonI.setOnClickListener(v -> {




            Intent popupHelp = new Intent(ExerciseStartActivity.this,helpButtonActivity.class);

            Bundle workoutData = new Bundle();
            workoutData.putString("MOVEMENT_TITLE", workoutTitle);
            workoutData.putString("MOVEMENT1", movement1);
            workoutData.putString("MOVEMENT2", movement2);
            workoutData.putString("MOVEMENT3", movement3);


            popupHelp.putExtras(workoutData);
            startActivity(popupHelp);
        });

    }
    //###################################
    //######### Naivgation Menu #########
    //###################################
    public void navigationMenu(){
        iToggle = new ActionBarDrawerToggle(this, iDrawerLayout, R.string.openNavDrawer, R.string.closeNavDrawer);
        iDrawerLayout.addDrawerListener(iToggle);
        iToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.navHome:
                Intent intentHome = new Intent(ExerciseStartActivity.this, MainActivity.class);
                intentHome.putExtra("FRAGMENT_PAGE", "home");
                startActivity(intentHome);
                break;
            case R.id.navLogs:

                Intent intentLogs = new Intent(ExerciseStartActivity.this, MainActivity.class);
                intentLogs.putExtra("FRAGMENT_PAGE", "logs");
                startActivity(intentLogs);
                break;
            case R.id.navWorkouts:
                Intent intentWorkouts = new Intent(ExerciseStartActivity.this, MainActivity.class);
                intentWorkouts.putExtra("FRAGMENT_PAGE", "workouts");
                startActivity(intentWorkouts);
                break;
            case R.id.navStats:
                Intent intentStats = new Intent(ExerciseStartActivity.this, MainActivity.class);
                intentStats.putExtra("FRAGMENT_PAGE", "statistics");
                startActivity(intentStats);
                break;
            case R.id.navFAQ:
                Intent intentFAQ = new Intent(ExerciseStartActivity.this, MainActivity.class);
                intentFAQ.putExtra("FRAGMENT_PAGE", "faq");
                startActivity(intentFAQ);
                break;
        }
        iDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(iToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
