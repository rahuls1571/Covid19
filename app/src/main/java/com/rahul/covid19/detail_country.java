package com.rahul.covid19;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class detail_country extends AppCompatActivity {
    private int pos_country;
    TextView country_name,cases,today_cases,deaths,today_deaths,recovered;
    ImageView flag_img;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_country);
         country_name = findViewById(R.id.country_name);
         cases = findViewById(R.id.cases);
         today_cases=findViewById(R.id.today_cases);
         deaths = findViewById(R.id.deaths);
         today_deaths = findViewById(R.id.today_deaths);
         recovered = findViewById(R.id.recovered);
         flag_img = findViewById(R.id.flag_img);


        (Objects.requireNonNull(getSupportActionBar())).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

         Intent intent = getIntent();
         pos_country = intent.getIntExtra("position",0);
         country_name.setText(Countries.countries_modelList.get(pos_country).getCountry());
         cases.setText(Countries.countries_modelList.get(pos_country).getCases());
         today_cases.setText(Countries.countries_modelList.get(pos_country).getTodaycases());
         deaths.setText(Countries.countries_modelList.get(pos_country).getDeaths());
         today_deaths.setText(Countries.countries_modelList.get(pos_country).getTodaydeaths());
         recovered.setText(Countries.countries_modelList.get(pos_country).getRecovered());



    }
}
