package com.rahul.covid19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class District_Adapter extends ArrayAdapter<District_Model> {

    private Context context;
    private List<District_Model> District_list;
    private List<District_Model> District_listFilter;

    public District_Adapter(@NonNull Context context,List<District_Model> District_list ) {
        super(context, R.layout.district_layout,District_list);
        this.context =context;
        this.District_list = District_list;
        this.District_listFilter = District_list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.district_layout,null,true);
        TextView district_name = view.findViewById(R.id.district_name);
        TextView district_active = view.findViewById(R.id.district_active);
        TextView district_deaths = view.findViewById(R.id.district_deaths);
        TextView district_recovered = view.findViewById(R.id.district_recovered);
        TextView district_confirmed = view.findViewById(R.id.district_confirmed);

        district_active.setText(District_listFilter.get(position).getActive());
        district_name.setText(District_listFilter.get(position).getDistrict());
        district_deaths.setText(District_listFilter.get(position).getDeaths());
        district_recovered.setText(District_listFilter.get(position).getRecovered());
        district_confirmed.setText(District_listFilter.get(position).getConfirmed());

        return view;
    }

    @Override
    public int getCount(){
        return District_listFilter.size();
    }

    @Nullable
    @Override
    public District_Model getItem(int position) {
        return District_listFilter.get(position);
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
                    filterResults.count = District_list.size();
                    filterResults.values = District_list;
                }
                else {
                    List<District_Model> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for (District_Model itemModel : District_list) {
                        if (itemModel.getDistrict().toLowerCase().contains(searchStr)) {
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
                District_listFilter = (List<District_Model>) results.values;
                District_Affected.district_modelList = (List<District_Model>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

}
