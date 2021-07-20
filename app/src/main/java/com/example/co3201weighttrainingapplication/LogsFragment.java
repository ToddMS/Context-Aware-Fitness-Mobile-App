package com.example.co3201weighttrainingapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

public class LogsFragment extends Fragment {

    String TAG = "LogsFragment";
    private CalendarView logCalendar;


    @Override
    public void onCreate(Bundle savedInstanceState) {        
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_logs, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getCurrentDate(view);

    }

    public void getCurrentDate(View view) { //Used to get the current date, and show the user their logs on that page
        logCalendar = view.findViewById(R.id.logCalendar);
        logCalendar.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            Intent intent = new Intent(getActivity(), DateClickedActivity.class);
            Bundle dayMonthYear = new Bundle();
            dayMonthYear.putInt("DAY", dayOfMonth);
            dayMonthYear.putInt("MONTH", month + 1);
            dayMonthYear.putInt("YEAR", year);
            intent.putExtras(dayMonthYear);
            startActivity(intent);
        });
    }


}




