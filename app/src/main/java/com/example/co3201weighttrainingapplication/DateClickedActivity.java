package com.example.co3201weighttrainingapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

public class DateClickedActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView currentDate;
    TextView movement1TextView;
    TextView movement2TextView;
    TextView movement3TextView;
    TextView repsTextView;
    TextView setsTextView;
    TextView restTextView;
    TextView ratingTextView;
    Button goBackButton;
    DrawerLayout iDrawerLayout;
    ActionBarDrawerToggle iToggle;

    private int dateDay;
    private int dateMonth;
    private int dateYear;
    private String day;
    private String month;

    private String workoutMovement1Weight;
    private String workoutMovement2Weight;
    private String workoutMovement3Weight;

    private String dateRecieved;
    private String workoutMovement1;
    private String workoutMovement2;
    private String workoutMovement3;
    private int workoutReps;
    private int workoutSets;
    private int workoutRest;
    private String workoutRating;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_clicked);
        movement1TextView = findViewById(R.id.workoutMovement1);
        movement2TextView = findViewById(R.id.workoutMovement2);
        movement3TextView = findViewById(R.id.workoutMovement3);
        repsTextView = findViewById(R.id.workoutReps);
        setsTextView = findViewById(R.id.workoutSets);
        restTextView = findViewById(R.id.workoutRest);
        ratingTextView = findViewById(R.id.workoutRating);


        setCurrentDate();
        isButtonPressed();
        navigationMenu();


    }




    public void saveDataToStorage(){
        String text = ("MOVEMENT 1: "+ workoutMovement1 +  "\nMOVEMENT 2: "+ workoutMovement2 + "\nMOVEMENT 3: "+ workoutMovement3 + "\nREPS: " + workoutReps + "\nSETS: " + workoutSets + "\nREST: " + workoutRest + "\nRATING: " + workoutRating+ "\n" + workoutMovement1Weight + "\n" + workoutMovement2Weight + "\n" + workoutMovement3Weight);

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = openFileOutput(dateRecieved,MODE_PRIVATE);
            fileOutputStream.write(text.getBytes());
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + dateRecieved, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadDataFromStorage(){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput(dateRecieved);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String text;

            while((text = bufferedReader.readLine()) != null){
                stringBuilder.append(text).append("\n");
            }

            currentDate.setText(dateRecieved);

            movement1TextView.setText(stringBuilder.toString());

            System.out.println(stringBuilder);

            String[] stringArray = (stringBuilder.toString()).split("\\r?\\n");





            movement1TextView.setText(stringArray[0] + " " + stringArray[7] + "KG");
            movement2TextView.setText(stringArray[1] + " " + stringArray[8] + "KG");
            movement3TextView.setText(stringArray[2] + " " + stringArray[9] + "KG");
            repsTextView.setText(stringArray[3]);
            setsTextView.setText(stringArray[4]);
            restTextView.setText(stringArray[5]);
            ratingTextView.setText(stringArray[6]);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setCurrentDate(){// setting the date given from the calendar in the logsFragment

        Intent incomingIntent = getIntent();
        Bundle workoutDataAndDateChosenFromCalendar = incomingIntent.getExtras();

        currentDate = findViewById(R.id.dateChosen);
        workoutMovement1 = workoutDataAndDateChosenFromCalendar.getString("MOVEMENT1");
        workoutMovement2 = workoutDataAndDateChosenFromCalendar.getString("MOVEMENT2");
        workoutMovement3 = workoutDataAndDateChosenFromCalendar.getString("MOVEMENT3");
        workoutReps = workoutDataAndDateChosenFromCalendar.getInt("REPS", 0);
        workoutSets = workoutDataAndDateChosenFromCalendar.getInt("SETS", 0);
        workoutRest = workoutDataAndDateChosenFromCalendar.getInt("REST", 0);
        workoutRating = workoutDataAndDateChosenFromCalendar.getString("RATING");
        workoutMovement1Weight = workoutDataAndDateChosenFromCalendar.getString("MOVEMENT1WEIGHT");
        workoutMovement2Weight = workoutDataAndDateChosenFromCalendar.getString("MOVEMENT2WEIGHT");
        workoutMovement3Weight = workoutDataAndDateChosenFromCalendar.getString("MOVEMENT3WEIGHT");



        if ((workoutMovement1 == null) || (workoutMovement1.equals(""))){
            dateDay = workoutDataAndDateChosenFromCalendar.getInt("DAY");
            dateMonth = workoutDataAndDateChosenFromCalendar.getInt("MONTH");
            dateYear = workoutDataAndDateChosenFromCalendar.getInt("YEAR");
            dateRecieved = (dateDay + " " + dateMonth + " " + dateYear);

            loadDataFromStorage();


        }else{


            System.out.println("set weight 1: " + workoutMovement1Weight);
            System.out.println("set weight 2: " + workoutMovement2Weight);
            System.out.println("set weight 3: " + workoutMovement3Weight);

            movement1TextView.setText("MOVEMENT 1: "+ workoutMovement1 + " " + workoutMovement1Weight + "KG");
            movement2TextView.setText("MOVEMENT 2: "+ workoutMovement2 + " " + workoutMovement2Weight + "KG");
            movement3TextView.setText("MOVEMENT 3: "+ workoutMovement3 + " " + workoutMovement3Weight + "KG");
            repsTextView.setText("REPS: " + workoutReps);
            setsTextView.setText("SETS: " + workoutSets);
            restTextView.setText("REST: " + workoutRest);
            ratingTextView.setText("RATING: " + workoutRating);

            DateTimeFormatter dateDayDtf = DateTimeFormatter.ofPattern("dd");
            DateTimeFormatter dateMonthDtf = DateTimeFormatter.ofPattern("MM");
            DateTimeFormatter dateYearDtf = DateTimeFormatter.ofPattern("yyyy");
            LocalDateTime now = LocalDateTime.now();

            String dateDayString = dateDayDtf.format(now);
            String dateMonthString = dateMonthDtf.format(now);
            String dateYearString = dateYearDtf.format(now);
            dateDay = Integer.parseInt(dateDayString);
            dateMonth = Integer.parseInt(dateMonthString);
            dateYear = Integer.parseInt(dateYearString);






            dateRecieved = (dateDay + " " + dateMonth + " " + dateYear);
            currentDate.setText(dateRecieved);


            saveDataToStorage();

        }





        if (dateDay % 10 == 1){ //making sure the ending of the date is correct
            day = (dateDay + "ST");
        } else if (dateDay % 10 == 2){
            day = (dateDay + "ND");
        }else if (dateDay % 10 == 3){
            day = (dateDay + "RD");
        } else{
            day = (dateDay + "TH");
        }

        if (dateDay >= 11 && dateDay <= 13) {
            day = (dateDay + "TH");
        }

        if (dateMonth == 1){ //assigning the correct month
            month = ("JANUARY");
        } else if (dateMonth == 2){
            month = ("FEBRUARY");
        } else if (dateMonth == 3) {
            month = ("MARCH");
        }else if (dateMonth == 4) {
            month = ("APRIL");
        }else if (dateMonth == 5) {
            month = ("MAY");
        }else if (dateMonth == 6) {
            month = ("JUNE");
        }else if (dateMonth == 7) {
            month = ("JULY");
        }else if (dateMonth == 8) {
            month = ("AUGUST");
        }else if (dateMonth == 9) {
            month = ("SEPTEMBER");
        }else if (dateMonth == 10) {
            month = ("OCTOBER");
        }else if (dateMonth == 11) {
            month = ("NOVEMBER");
        }else if (dateMonth == 12) {
            month = ("DECEMBER");
        }

        dateRecieved = (day + " " + month + " " + dateYear);
        currentDate.setText(dateRecieved);
    }

    public void isButtonPressed(){
        goBackButton = findViewById(R.id.goBackButton);
        goBackButton.setOnClickListener(v -> { //when the go back button is pressed
            Intent intentWorkouts = new Intent(DateClickedActivity.this, MainActivity.class);
            intentWorkouts.putExtra("FRAGMENT_PAGE", "logs");
            startActivity(intentWorkouts);
        });
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
                Intent intentHome = new Intent(DateClickedActivity.this, MainActivity.class);
                intentHome.putExtra("FRAGMENT_PAGE", "home");
                startActivity(intentHome);
                break;
            case R.id.navLogs:
                Intent intentLogs = new Intent(DateClickedActivity.this, MainActivity.class);
                intentLogs.putExtra("FRAGMENT_PAGE", "logs");
                startActivity(intentLogs);
                break;
            case R.id.navWorkouts:
                Intent intentWorkouts = new Intent(DateClickedActivity.this, MainActivity.class);
                intentWorkouts.putExtra("FRAGMENT_PAGE", "workouts");
                startActivity(intentWorkouts);
                break;
            case R.id.navStats:
                Intent intentStats = new Intent(DateClickedActivity.this, MainActivity.class);
                intentStats.putExtra("FRAGMENT_PAGE", "statistics");
                startActivity(intentStats);
                break;
            case R.id.navFAQ:
                Intent intentFAQ = new Intent(DateClickedActivity.this, MainActivity.class);
                intentFAQ.putExtra("FRAGMENT_PAGE", "faq");
                startActivity(intentFAQ);
                break;
        }
        iDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }





}
