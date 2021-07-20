package com.example.co3201weighttrainingapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.Month;

public class SaveWorkoutPopUpActivity extends AppCompatActivity {


    Button buttonSaveWorkout;
    RatingBar workoutRatingBar;
    String workoutRating;
    TextView textViewMovement1;
    TextView textViewMovement2;
    TextView textViewMovement3;
    EditText editTextMovement1;
    EditText editTextMovement2;
    EditText editTextMovement3;
    private String getWorkoutMovement1Weight;
    private String getWorkoutMovement2Weight;
    private String getWorkoutMovement3Weight;
    private String workoutMovement1;
    private String workoutMovement2;
    private String workoutMovement3;
    private int workoutReps;
    private int workoutSets;
    private int workoutRest;
    private String workoutMonth = "N/A";


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_workout_pop_up);

        textViewMovement1 = findViewById(R.id.textViewMovement1);
        textViewMovement2 = findViewById(R.id.textViewMovement2);
        textViewMovement3 = findViewById(R.id.textViewMovement3);
        editTextMovement1 = findViewById(R.id.editTextMovement1);
        editTextMovement2 = findViewById(R.id.editTextMovement2);
        editTextMovement3 = findViewById(R.id.editTextMovement3);


        sendData();



    }



    public void sendData(){
        Intent incomingIntent = getIntent();
        Bundle workoutData = incomingIntent.getExtras();

        workoutMovement1 = workoutData.getString("MOVEMENT1");
        workoutMovement2 = workoutData.getString("MOVEMENT2");
        workoutMovement3 = workoutData.getString("MOVEMENT3");
        workoutReps = workoutData.getInt("REPS", 0);
        workoutSets = workoutData.getInt("SETS", 0);
        workoutRest = workoutData.getInt("REST", 0);
        workoutRating = workoutData.getString("RATING");
        workoutMonth = workoutData.getString("MONTH");


        textViewMovement1.setText(workoutMovement1);
        textViewMovement2.setText(workoutMovement2);
        textViewMovement3.setText(workoutMovement3);




        buttonSaveWorkout = findViewById(R.id.saveWorkoutYes);
        workoutRatingBar = findViewById(R.id.woroutRatingBar);
        buttonSaveWorkout.setOnClickListener(v -> {
            getWorkoutMovement1Weight = editTextMovement1.getText().toString();
            getWorkoutMovement2Weight = editTextMovement2.getText().toString();
            getWorkoutMovement3Weight = editTextMovement3.getText().toString();

            workoutRating = String.valueOf(workoutRatingBar.getRating());
            Intent workoutRatingWindow = new Intent(SaveWorkoutPopUpActivity.this,DateClickedActivity.class);
            Bundle workoutDataToSend = new Bundle();
            workoutDataToSend.putString("MOVEMENT1", workoutMovement1);
            workoutDataToSend.putString("MOVEMENT2", workoutMovement2);
            workoutDataToSend.putString("MOVEMENT3", workoutMovement3);
            workoutDataToSend.putInt("REPS", workoutReps);
            workoutDataToSend.putInt("SETS", workoutSets);
            workoutDataToSend.putInt("REST", workoutRest);





            workoutRatingWindow.putExtras(workoutData);
            workoutRatingWindow.putExtra("MOVEMENT1WEIGHT", getWorkoutMovement1Weight);
            workoutRatingWindow.putExtra("MOVEMENT2WEIGHT", getWorkoutMovement2Weight);
            workoutRatingWindow.putExtra("MOVEMENT3WEIGHT", getWorkoutMovement3Weight);
            workoutRatingWindow.putExtra("RATING", workoutRating);




            startActivity(workoutRatingWindow);
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(height*.8));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 20;
        params.y = 20;

        getWindow().setAttributes(params);
    }



}