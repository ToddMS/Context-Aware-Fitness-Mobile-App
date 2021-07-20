package com.example.co3201weighttrainingapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.co3201weighttrainingapplication.R.color.textColour;
import static com.example.co3201weighttrainingapplication.R.color.white;


public class StatsFragment extends Fragment {
    Button button7Days;
    Button button30Days;
    Spinner barCharTitleDropDown;
    BarChart barChart;
    PieChart pieChart;
    RelativeLayout bmiCalc;
    TextView barChartKey;
    TextView bmiHeight;
    TextView bmiWeight;
    TextView bmiAge;
    TextView bmiScoreTextView;

    EditText bmiHeightEditText;
    EditText bmiWeightEditText;
    EditText bmiAgeEditText;

    Button btnBmiCalculate;

    float heightCM;
    float heightM;
    float weightKG;





    public StatsFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);













        barChart = view.findViewById(R.id.barChart);
        pieChart = view.findViewById(R.id.pieChart);

        barChartKey = view.findViewById(R.id.barchartMonths);

        bmiHeight = view.findViewById(R.id.bmiHeight);
        bmiWeight = view.findViewById(R.id.bmiWeight);
        bmiCalc = view.findViewById(R.id.bmiCalc);
        bmiHeightEditText = view.findViewById(R.id.bmiHeightEditText);
        bmiWeightEditText = view.findViewById(R.id.bmiWeightEditText);
        bmiScoreTextView = view.findViewById(R.id.bmiScore);

        btnBmiCalculate = view.findViewById(R.id.btnBmiCalculate);

        // ##### Bar chart
        ArrayList<BarEntry> userDataBarChart = new ArrayList<>();



            userDataBarChart.add(new BarEntry(1,HomeFragment.workoutsJan)); //jan
            userDataBarChart.add(new BarEntry(2,HomeFragment.workoutsFeb)); //feb
            userDataBarChart.add(new BarEntry(3,HomeFragment.workoutsMar)); //march
            userDataBarChart.add(new BarEntry(4,HomeFragment.workoutsApr)); //april
            userDataBarChart.add(new BarEntry(5,HomeFragment.workoutsMay)); //may
            userDataBarChart.add(new BarEntry(6,HomeFragment.workoutsJun)); //june
            userDataBarChart.add(new BarEntry(7,HomeFragment.workoutsJul)); //july
            userDataBarChart.add(new BarEntry(8,HomeFragment.workoutsAug)); //aug
            userDataBarChart.add(new BarEntry(9,HomeFragment.workoutsSep)); //sep
            userDataBarChart.add(new BarEntry(10,HomeFragment.workoutsOct)); //oct
            userDataBarChart.add(new BarEntry(11,HomeFragment.workoutsNov)); //nov
            userDataBarChart.add(new BarEntry(12,HomeFragment.workoutsDec)); //dec

        BarDataSet barDataSet = new BarDataSet(userDataBarChart, "");


        barDataSet.setValueTextColor(getResources().getColor(R.color.textColour));

        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        barDataSet.setValueTextSize(12f);
        BarData barData = new BarData(barDataSet);
        barChart.getLegend().setEnabled(false);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("");
        barChart.getXAxis().setTextColor(getResources().getColor(R.color.backgroundColour2));
        barChart.getAxisLeft().setTextColor(getResources().getColor(R.color.backgroundColour2));
        barChart.getAxisRight().setTextColor(getResources().getColor(R.color.backgroundColour2));
        barChart.getLegend().setTextColor(getResources().getColor(R.color.textColour));
        barData.setValueFormatter(new MyValueFormatter());

        barChart.animateY(2000);

        // #### pie chart

        ArrayList<PieEntry> userDataPieChart = new ArrayList<>();
        userDataPieChart.add(new PieEntry(HomeFragment.workoutBack,"BACK"));
        userDataPieChart.add(new PieEntry(HomeFragment.workoutArms,"ARMS"));
        userDataPieChart.add(new PieEntry(HomeFragment.workoutLegs,"LEGS"));
        userDataPieChart.add(new PieEntry(HomeFragment.workoutChest,"CHEST"));

        PieDataSet pieDataSet = new PieDataSet(userDataPieChart, "");
        pieDataSet.setValueTextColor(getResources().getColor(R.color.textColour2));

        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setValueTextSize(16f);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setText("");
        pieChart.animateY(2000);
        pieChart.getLegend().setTextColor(getResources().getColor(R.color.backgroundColour));
        pieChart.getLegend().setTextColor(getResources().getColor(R.color.textColour));

        Spinner barCharTitleDropDown = (Spinner) view.findViewById(R.id.barCharTitleDropDown);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.StatsTitle, R.layout.colour_spinner_layout);

        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        barCharTitleDropDown.setAdapter(arrayAdapter);

        barCharTitleDropDown.setBackgroundColor(getResources().getColor(R.color.backgroundColour2));

        barCharTitleDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case (0)://workouts per month
                        barChart.setVisibility(View.VISIBLE);
                        pieChart.setVisibility(View.INVISIBLE);
                        barChartKey.setVisibility(View.VISIBLE);
                        bmiCalc.setVisibility(View.INVISIBLE);

                        bmiHeight.setVisibility(View.INVISIBLE);
                        bmiWeight.setVisibility(View.INVISIBLE);
                        bmiHeightEditText.setVisibility(View.INVISIBLE);
                        bmiWeightEditText.setVisibility(View.INVISIBLE);

                        btnBmiCalculate.setVisibility(View.INVISIBLE);

                        break;
                    case (1)://muscle groups
                        pieChart.setVisibility(View.VISIBLE);
                        barChart.setVisibility(View.INVISIBLE);
                        barChartKey.setVisibility(View.INVISIBLE);
                        bmiCalc.setVisibility(View.INVISIBLE);

                        bmiHeight.setVisibility(View.INVISIBLE);
                        bmiWeight.setVisibility(View.INVISIBLE);

                        bmiHeightEditText.setVisibility(View.INVISIBLE);
                        bmiWeightEditText.setVisibility(View.INVISIBLE);

                        btnBmiCalculate.setVisibility(View.INVISIBLE);

                        break;
                    case(2):
                        bmiCalc.setVisibility(View.VISIBLE);
                        pieChart.setVisibility(View.INVISIBLE);
                        barChart.setVisibility(View.INVISIBLE);
                        barChartKey.setVisibility(View.INVISIBLE);

                        bmiHeight.setVisibility(View.VISIBLE);
                        bmiWeight.setVisibility(View.VISIBLE);

                        bmiHeightEditText.setVisibility(View.VISIBLE);
                        bmiWeightEditText.setVisibility(View.VISIBLE);

                        btnBmiCalculate.setVisibility(View.VISIBLE);
                        break;



                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        DecimalFormat df = new DecimalFormat("0.00");

        btnBmiCalculate.setOnClickListener(v -> {

            String bmiHeightString = bmiHeightEditText.getText().toString();
            String bmiWeightString = bmiWeightEditText.getText().toString();



            if (bmiHeightEditText.getText().toString().trim().equals("")){
                Intent popupWindowInjures = new Intent(getActivity(),PopupActivity.class);
                Bundle InjuresBoxesTicked = new Bundle();
                InjuresBoxesTicked.putString("WARNING_MESSAGE", "PLEASE ENTER YOUR HEIGHT AND WEIGHT");
                popupWindowInjures.putExtras(InjuresBoxesTicked);
                startActivity(popupWindowInjures);
            }else{
                heightCM = Float.parseFloat(bmiHeightString);
                weightKG = Float.parseFloat(bmiWeightString);


                heightM = heightCM/10;


                float heightMSquared = (float) Math.pow(heightM, 2);
                heightMSquared = heightMSquared/100;
                HomeFragment.bmiScore = (weightKG/heightMSquared);



                bmiScoreTextView.setText("BMI SCORE:\n" + df.format(HomeFragment.bmiScore));
            }


        });



    }



    private class MyValueFormatter extends ValueFormatter implements IValueFormatter{

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return "" + ((int) value);
        }
    }
}




