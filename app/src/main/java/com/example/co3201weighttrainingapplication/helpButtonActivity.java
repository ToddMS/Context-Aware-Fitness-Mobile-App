package com.example.co3201weighttrainingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class helpButtonActivity extends AppCompatActivity {

    TextView workoutTitleTextView;
    TextView movement1TitleTextView;
    TextView movement1DescriptionTextView;
    TextView movement2TitleTextView;
    TextView movement2DescriptionTextView;
    TextView movement3TitleTextView;
    TextView movement3DescriptionTextView;
    TextView workoutMovement2TextView;
    TextView workoutMovement3TextView;
    ImageView movementBox11;
    ImageView movementBox12;
    ImageView movementBox21;
    ImageView movementBox22;
    ImageView movementBox31;
    ImageView movementBox32;
    Button buttonPopupClose;
    InputStream inputStream;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_button);

        workoutTitleTextView = findViewById(R.id.workoutTitle);
        movement1TitleTextView = findViewById(R.id.movement1Title);
        movement2TitleTextView = findViewById(R.id.movement2Title);
        movement3TitleTextView = findViewById(R.id.movement3Title);
        movementBox11 = findViewById(R.id.tempworkout11);
        movementBox12 = findViewById(R.id.tempworkout12);
        movementBox21 = findViewById(R.id.tempworkout21);
        movementBox22 = findViewById(R.id.tempworkout22);
        movementBox31 = findViewById(R.id.tempworkout31);
        movementBox32 = findViewById(R.id.tempworkout32);










        fillMovementTitles();
        fillMovementBoxes();





        buttonPopupClose = (Button) findViewById(R.id.buttonPopupClose);
        buttonPopupClose.setOnClickListener(v -> finish());

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(height*.99));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 20;
        params.y = 20;

        getWindow().setAttributes(params);

    }

    @SuppressLint("SetTextI18n")
    public void fillMovementTitles() {
        Intent incomingIntent = getIntent();
        Bundle workoutData = incomingIntent.getExtras();

        String workoutTitle = workoutData.getString("MOVEMENT_TITLE");
        String workoutMovement1 = workoutData.getString("MOVEMENT1");
        String workoutMovement2 = workoutData.getString("MOVEMENT2");
        String workoutMovement3 = workoutData.getString("MOVEMENT3");

        System.out.println(workoutTitle);
        System.out.println(workoutMovement1);
        System.out.println(workoutMovement2);
        System.out.println(workoutMovement3);



        workoutTitleTextView.setText("WORKOUT " + workoutTitle);

        movement1TitleTextView.setText(workoutMovement1);
        movement2TitleTextView.setText(workoutMovement2);
        movement3TitleTextView.setText(workoutMovement3);
    }

    public void fillMovementBoxes(){
        Intent incomingIntent = getIntent();
        Bundle workoutData = incomingIntent.getExtras();

        String workoutMovement1 = workoutData.getString("MOVEMENT1");
        String workoutMovement2 = workoutData.getString("MOVEMENT2");
        String workoutMovement3 = workoutData.getString("MOVEMENT3");

        if (workoutMovement1.equals("SQUAT")){
            movementBox11.setBackgroundResource(R.mipmap.ic_squat_movement_1_foreground);
            movementBox12.setBackgroundResource(R.mipmap.ic_squat_movement_2_foreground);
        }else if(workoutMovement1.equals("BODYWEIGHT SQUAT")){
            movementBox11.setBackgroundResource(R.mipmap.ic_bodyweightsquat_movement_1_foreground);
            movementBox12.setBackgroundResource(R.mipmap.ic_bodyweightsquat_movement_2_foreground);
        }else if(workoutMovement1.equals("GOBLET SQUAT")){
            movementBox11.setBackgroundResource(R.mipmap.ic_gobletsquat_movement_1_foreground);
            movementBox12.setBackgroundResource(R.mipmap.ic_gobletsquat_movement_2_foreground);
        }else if(workoutMovement1.equals("BODYWEIGHT LUNGES")){
            movementBox11.setBackgroundResource(R.mipmap.ic_bodyweight_lunge_movement_1_foreground);
            movementBox12.setBackgroundResource(R.mipmap.ic_bodyweight_lunge_movement_2_foreground);
        }


        if (workoutMovement2.equals("BENCH PRESS")){
            movementBox21.setBackgroundResource(R.mipmap.ic_bench_press_movement_1_foreground);
            movementBox22.setBackgroundResource(R.mipmap.ic_bench_press_movement_2_foreground);
        }else if(workoutMovement2.equals("PUSH UPS")){
            movementBox21.setBackgroundResource(R.mipmap.ic_push_ups_movement_1_foreground);
            movementBox22.setBackgroundResource(R.mipmap.ic_push_ups_movement_2_foreground);
        }else if(workoutMovement2.equals("KNEE PUSH UPS")){
            movementBox21.setBackgroundResource(R.mipmap.ic_knee_push_ups_movement_1_foreground);
            movementBox22.setBackgroundResource(R.mipmap.ic_knee_push_ups_movement_2_foreground);
        }

        System.out.println(workoutMovement3);

        if (workoutMovement3.equals("DEADLIFT")){
            movementBox31.setBackgroundResource(R.mipmap.ic_deadlift_movement_1_foreground);
            movementBox32.setBackgroundResource(R.mipmap.ic_deadlift_movement_2_foreground);
        }else if(workoutMovement3.equals("TRICEP DIPS")){
            movementBox31.setBackgroundResource(R.mipmap.ic_tricep_dips_movement_1_foreground);
            movementBox32.setBackgroundResource(R.mipmap.ic_tricep_dips_movement_2_foreground);
        }else if(workoutMovement3.equals("BARBELL ROW")){
            movementBox31.setBackgroundResource(R.mipmap.ic_barbell_rows_movement_1_foreground);
            movementBox32.setBackgroundResource(R.mipmap.ic_barbell_rows_movement_2_foreground);
        }else if(workoutMovement1.equals("BODYWEIGHT LUNGES")){
            movementBox11.setBackgroundResource(R.mipmap.ic_bodyweight_lunge_movement_1_foreground);
            movementBox12.setBackgroundResource(R.mipmap.ic_bodyweight_lunge_movement_2_foreground);
        }



    }




}