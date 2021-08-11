package com.rahul.covid19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Country_Adapter extends ArrayAdapter<Countries_Model> {

     private Context context;
     private List<Countries_Model> Country_list;
    private List<Countries_Model> Country_listFilter;

    public Country_Adapter(@NonNull Context context,List<Countries_Model> Country_list ) {
        super(context, R.layout.country_layout,Country_list);
        this.context =context;
        this.Country_list = Country_list;
        this.Country_listFilter = Country_list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_layout,null,true);
        TextView country_name = view.findViewById(R.id.country_name);
        ImageView flag_img = view.findViewById(R.id.flag_img);
        country_name.setText(Country_listFilter.get(position).getCountry());
        Glide.with(context).load(Country_listFilter.get(position).getFlag()).into(flag_img);

        return view;
    }

    @Override
    public int getCount(){
        return Country_listFilter.size();
    }

    @Nullable
    @Override
    public Countries_Model getItem(int position) {
        return Country_listFilter.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = Country_list.size();
                    filterResults.values = Country_list;

                }
                else {
                    List<Countries_Model> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();
                    for (Countries_Model itemModel : Country_list) {
                        if (itemModel.getCountry().toLowerCase().contains(searchStr)) {
                            resultsModel.add(itemModel);
                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;

                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                Country_listFilter = (List<Countries_Model>) results.values;
                Countries.countries_modelList = (List<Countries_Model>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
