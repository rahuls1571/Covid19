package com.rahul.covid19;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class symptoms_AdapterList extends RecyclerView.Adapter<symptoms_AdapterList.ViewHolder> {

    private symptoms_Alldetail Detail_Symptom =new symptoms_Alldetail();

    private ArrayList<symptoms_list> symptoms_lists;
    private Context context;

     public symptoms_AdapterList(ArrayList<symptoms_list> symptoms_lists,Context context){
        this.symptoms_lists =symptoms_lists;
        this.context = context;
    }

    @NonNull
    @Override
    public symptoms_AdapterList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_symptoms,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final symptoms_AdapterList.ViewHolder holder, int position) {
        final String symptoms_title = symptoms_lists.get(position).getTitle();
        String symptoms_detail = symptoms_lists.get(position).getDetail();
        int symptoms_image = symptoms_lists.get(position).getImg();

        holder.title.setText(symptoms_title);
        holder.detail.setText(symptoms_detail);
        holder.img.setImageResource(symptoms_image);

        holder.cough_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,detail_symptom.class);
                switch (symptoms_title) {
                    case "fever":
                        intent.putExtra("title", "Fever");
                        intent.putExtra("detail", Detail_Symptom.getfeverDetail());
                        context.startActivity(intent);
                        break;
                    case "sore_throat":
                        intent.putExtra("title", "Sore Throat");
                        intent.putExtra("detail", Detail_Symptom.getSore_ThroatDetail());
                        context.startActivity(intent);
                        break;
                    case "Fatigue":
                        intent.putExtra("title", "Fatigue");
                        intent.putExtra("detail", Detail_Symptom.getFatigueDetail());
                        context.startActivity(intent);
                        break;
                    case "Pain":
                        intent.putExtra("title", "Pain");
                        intent.putExtra("detail", Detail_Symptom.getPaindetail());
                        context.startActivity(intent);
                        break;
                    case "cough":
                        intent.putExtra("title", "Cough");
                        intent.putExtra("detail", Detail_Symptom.getCoughDetail());
                        context.startActivity(intent);
                        break;
                    default:
                        context.startActivity(intent);
                        break;
                }
            }
        });

    }
    @Override
    public int getItemCount() {
        return symptoms_lists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
           private TextView title;
           private TextView detail;
           private ImageView img;
           LinearLayout cough_detail;

       public  ViewHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textSymptoms);
            detail = itemView.findViewById(R.id.textSymptomsDetail);
            img = itemView.findViewById(R.id.Symptoms_image);
            cough_detail = itemView.findViewById(R.id.detail);

        }

    }
}
