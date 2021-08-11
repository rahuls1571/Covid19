package com.rahul.covid19;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class symptoms_viewAll extends AppCompatActivity  {
    RecyclerView symptoms_Recycler_view;
    ArrayList<symptoms_list> symptoms_lists;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms_view_all);

        (Objects.requireNonNull(getSupportActionBar())).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        symptoms_Recycler_view = findViewById(R.id.symptoms_view);

        symptoms_lists = new ArrayList<>();
        symptoms_lists.add(new symptoms_list("cough","Coughing can have causes that aren't due to underlying disease",R.drawable.cough));
        symptoms_lists.add(new symptoms_list("fever","A temporary increase in average body temperature of 98.6°F (37°C)",R.drawable.fever));
        symptoms_lists.add(new symptoms_list("sore_throat","Pain or irritation in the throat that can occur with or without swallowing, often accompanies infections, such as a cold or flu.",R.drawable.sore_throat));
        symptoms_lists.add(new symptoms_list("Fatigue","Feeling overtired, with low energy and a strong desire to sleep that interferes with normal daily activities",R.drawable.fatigue));
        symptoms_lists.add(new symptoms_list("Pain","A painful sensation in any part of the head, ranging from sharp to dull, that may occur with other symptoms.",R.drawable.pain));

        symptoms_Recycler_view(symptoms_lists);

    }
    public void symptoms_Recycler_view(ArrayList<symptoms_list> symptoms_lists)
    {
        symptoms_AdapterList symptoms_List = new symptoms_AdapterList(symptoms_lists,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        symptoms_Recycler_view.setLayoutManager(layoutManager);
        symptoms_Recycler_view.setAdapter(symptoms_List);
        symptoms_List.notifyDataSetChanged();
    }


}
