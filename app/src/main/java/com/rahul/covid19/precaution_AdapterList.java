package com.rahul.covid19;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class precaution_AdapterList extends RecyclerView.Adapter<precaution_AdapterList.ViewHolder>{
    private ArrayList<precaution_list> precaution_lists;

    public precaution_AdapterList(ArrayList<precaution_list> symptoms_lists){
        this.precaution_lists =symptoms_lists;
    }
    @NonNull
    @Override
    public precaution_AdapterList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_precautions,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String symptoms_title = precaution_lists.get(position).getTitle();
        String symptoms_detail = precaution_lists.get(position).getDetail();
        int symptoms_image = precaution_lists.get(position).getImg();

        holder.title.setText(symptoms_title);
        holder.detail.setText(symptoms_detail);
        holder.img.setImageResource(symptoms_image);
    }

    @Override
    public int getItemCount() {
        return precaution_lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView detail;
        private ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textPrecaution);
            detail = itemView.findViewById(R.id.textPrecautionDetail);
            img = itemView.findViewById(R.id.Precaution_image);
        }
    }
}
