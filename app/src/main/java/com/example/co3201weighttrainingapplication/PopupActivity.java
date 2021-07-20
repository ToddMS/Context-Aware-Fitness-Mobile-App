package com.example.co3201weighttrainingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class PopupActivity extends AppCompatActivity {

    Button buttonPopupClose;
    TextView warningMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        warningMessageTextView = findViewById(R.id.warningMessage);

        Intent incomingIntent = getIntent();
        String warningMessage = incomingIntent.getStringExtra("WARNING_MESSAGE");

        warningMessageTextView.setText(warningMessage);




        buttonPopupClose = (Button) findViewById(R.id.buttonPopupClose);
        buttonPopupClose.setOnClickListener(v -> finish());

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(height*.3));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 20;
        params.y = 20;

        getWindow().setAttributes(params);
    }
}