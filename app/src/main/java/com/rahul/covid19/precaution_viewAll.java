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

public class precaution_viewAll extends AppCompatActivity {

    RecyclerView precaution_Recycler_view;
    ArrayList<precaution_list> precaution_lists;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precaution_view_all);

        (Objects.requireNonNull(getSupportActionBar())).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        precaution_Recycler_view = findViewById(R.id.precaution_view);
        precaution_lists = new ArrayList<>();

        precaution_lists.add(new precaution_list("Clear","Clean your hands often. Use soap and water.",R.drawable.clean));
        precaution_lists.add(new precaution_list("Cover","Cover your nose and mouth with your bent elbow or a tissue when you cough or sneeze.",R.drawable.cover));
        precaution_lists.add(new precaution_list("Distance","Maintain a safe distance from anyone who is coughing or sneezing.",R.drawable.distance));
        precaution_lists.add(new precaution_list("Home","Stay home if you feel unwell.",R.drawable.home));
        precaution_lists.add(new precaution_list("Soap","Washing your hands with soap and water or using alcohol-based hand rub kills viruses that may be on your hands.",R.drawable.soap));

        precaution_Recycler_view(precaution_lists);

    }
    public void precaution_Recycler_view(ArrayList<precaution_list> precaution_lists)
    {
        precaution_AdapterList precaution_List = new precaution_AdapterList(precaution_lists);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        precaution_Recycler_view.setLayoutManager(layoutManager1);
        precaution_Recycler_view.setAdapter(precaution_List);
        precaution_List.notifyDataSetChanged();
    }

}
