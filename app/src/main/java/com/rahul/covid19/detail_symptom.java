package com.rahul.covid19;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class detail_symptom extends AppCompatActivity {
     TextView title_detailSymptom,DetailSymptom;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_symptom);

        title_detailSymptom = findViewById(R.id.title_detailSymptom);
        DetailSymptom = findViewById(R.id.DetailSymptom);
        (Objects.requireNonNull(getSupportActionBar())).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        String title_symptom = intent.getStringExtra("title");
        String detail_symptom = intent.getStringExtra("detail");
        if(title_symptom != null) {
            title_detailSymptom.setText(title_symptom);
            DetailSymptom.setText(detail_symptom);
        }


    }
}
