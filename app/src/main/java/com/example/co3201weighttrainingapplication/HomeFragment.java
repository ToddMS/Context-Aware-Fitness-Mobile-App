package com.example.co3201weighttrainingapplication;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class HomeFragment extends Fragment {
    public static int workoutsJan = 0;
    public static int workoutsFeb = 0;
    public static int workoutsMar = 0;
    public static int workoutsApr = 0;
    public static int workoutsMay = 0;
    public static int workoutsJun = 0;
    public static int workoutsJul = 0;
    public static int workoutsAug = 0;
    public static int workoutsSep = 0;
    public static int workoutsOct = 0;
    public static int workoutsNov = 0;
    public static int workoutsDec = 0;

    public static int workoutBack = 0;
    public static int workoutArms = 0;
    public static int workoutLegs = 0;
    public static int workoutChest = 0;

    public static float bmiScore;








    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("workout april: " + workoutsApr);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setTextDate(view);



        super.onViewCreated(view, savedInstanceState);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setTextDate(View view){
        TextView currentDate = view.findViewById(R.id.currentDate);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = (dtf.format(now));

        currentDate.setText("CURRENT DATE: " + date);
    }
}