package com.example.co3201weighttrainingapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class WorkoutsFragment extends Fragment implements View.OnClickListener{

    SeekBar intensitySlider;
    int progressNumber;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workouts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        intensitySliderMoved(view);
        switchesFlipped(view);
        findAvailableWorkouts(view);
    }

        public void intensitySliderMoved(View view){
            SeekBar intensitySlider;
            intensitySlider = requireView().findViewById(R.id.intensitySlider);
            intensitySlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    progressNumber = (progress);
                    setText(Integer.toString(progressNumber));

                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}
            });
        }
        public void switchesFlipped(View view){
            @SuppressLint("UseSwitchCompatOrMaterialCode") Switch weightAccess = requireView().findViewById(R.id.weightAccess);
            @SuppressLint("UseSwitchCompatOrMaterialCode") Switch upperBodyInjured = requireView().findViewById(R.id.upperBodyInjured);
            @SuppressLint("UseSwitchCompatOrMaterialCode") Switch lowerBodyInjured = requireView().findViewById(R.id.lowerBodyInjured);
            @SuppressLint("UseSwitchCompatOrMaterialCode") Switch thirtyMinutes = requireView().findViewById(R.id.thirtyMinutes);
            @SuppressLint("UseSwitchCompatOrMaterialCode") Switch sixtyMinutes = requireView().findViewById(R.id.sixtyMinutes);

            thirtyMinutes.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (thirtyMinutes.isChecked() == true){
                    sixtyMinutes.setChecked(false);
                }
            });

            sixtyMinutes.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (sixtyMinutes.isChecked() == true){
                    thirtyMinutes.setChecked(false);
                }
            });
    }
    public void sendingData(View view){
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch weightAccess = requireView().findViewById(R.id.weightAccess);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch upperBodyInjured = requireView().findViewById(R.id.upperBodyInjured);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch lowerBodyInjured = requireView().findViewById(R.id.lowerBodyInjured);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch thirtyMinutes = requireView().findViewById(R.id.thirtyMinutes);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch sixtyMinutes = requireView().findViewById(R.id.sixtyMinutes);

        Intent intent = new Intent(getActivity(), CurrentWorkoutActivity.class);
        Bundle dataToSend = new Bundle();

        dataToSend.putInt("WORKOUT_INTENSITY", progressNumber);

        boolean weightAccessChecked = weightAccess.isChecked();
        if (weightAccessChecked) {
            dataToSend.putBoolean("WEIGHT_ACCESS", true);
        } else if (!(weightAccessChecked)) {
            dataToSend.putBoolean("WEIGHT_ACCESS", false);
        } else {
            dataToSend.putBoolean("WEIGHT_ACCESS", false);
        }


        boolean upperBodyInjuredChecked = upperBodyInjured.isChecked();
        if (upperBodyInjuredChecked) {
            dataToSend.putBoolean("UPPER_BODY_INJURED", true);
        } else if (!(upperBodyInjuredChecked)) {
            dataToSend.putBoolean("UPPER_BODY_INJURED", false);
        } else {
            dataToSend.putBoolean("UPPER_BODY_INJURED", false);
        }

        boolean lowerBodyInjuredChecked = lowerBodyInjured.isChecked();
        if (lowerBodyInjuredChecked) {
            dataToSend.putBoolean("LOWER_BODY_INJURED", true);
        } else if (!(lowerBodyInjuredChecked)) {
            dataToSend.putBoolean("LOWER_BODY_INJURED", false);
        } else {
            dataToSend.putBoolean("LOWER_BODY_INJURED", false);
        }

        boolean thirtyMinutesChecked = thirtyMinutes.isChecked();
        if (thirtyMinutesChecked) {
            dataToSend.putBoolean("THIRTY_MINUTES", true);
        } else if (!(thirtyMinutesChecked)) {
            dataToSend.putBoolean("THIRTY_MINUTES", false);
        } else {
            dataToSend.putBoolean("THIRTY_MINUTES", false);
        }

        boolean sixtyMinutesChecked = sixtyMinutes.isChecked();
        if (sixtyMinutesChecked) {
            dataToSend.putBoolean("SIXTY_MINUTES", true);
        } else if (!(sixtyMinutesChecked)) {
            dataToSend.putBoolean("SIXTY_MINUTES", false);
        } else {
            dataToSend.putBoolean("SIXTY_MINUTES", false);
        }

        intent.putExtras(dataToSend);

        if (!thirtyMinutesChecked && !sixtyMinutesChecked) {
            Intent popupWindowTime = new Intent(getActivity(),PopupActivity.class);
            Bundle timeBoxesTicked = new Bundle();
            timeBoxesTicked.putString("WARNING_MESSAGE", "CHOOSE A WORKOUT LENGTH");
            popupWindowTime.putExtras(timeBoxesTicked);
            startActivity(popupWindowTime);
        }else if(upperBodyInjuredChecked && lowerBodyInjuredChecked){

            Intent popupWindowInjures = new Intent(getActivity(),PopupActivity.class);
            Bundle InjuresBoxesTicked = new Bundle();
            InjuresBoxesTicked.putString("WARNING_MESSAGE", "YOU'RE INJURED, TAKE A DAY REST");
            popupWindowInjures.putExtras(InjuresBoxesTicked);
            startActivity(popupWindowInjures);
        }else{
            startActivity(intent);
        }
    }

    public void setText(String text){
        TextView intensityNumber = requireView().findViewById(R.id.intensityNumber);
        intensityNumber.setText(text);
    }

    public void findAvailableWorkouts(View view){
        Button findWorkoutsButton;

        findWorkoutsButton = view.findViewById(R.id.findWorkouts);
        findWorkoutsButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            sendingData(view);

        }
        });
    }

    @Override
    public void onClick(View v) {}
}

