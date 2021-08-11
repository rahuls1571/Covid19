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

public class State_adapter  extends ArrayAdapter<State_Model> {

    private Context context;
    private List<State_Model> State_list;
    private List<State_Model> State_listFilter;


    public State_adapter(@NonNull Context context,List<State_Model> State_list ) {
        super(context, R.layout.state_layout,State_list);
        this.context =context;
        this.State_list = State_list;
        this.State_listFilter = State_list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.state_layout,null,true);
        TextView state_name = view.findViewById(R.id.state_name);
        TextView state_active = view.findViewById(R.id.state_active);
        TextView state_deaths = view.findViewById(R.id.state_deaths);
        TextView state_recovered = view.findViewById(R.id.state_recovered);
        TextView state_confirmed = view.findViewById(R.id.state_confirmed);

        state_active.setText(State_listFilter.get(position).getActive());
        state_name.setText(State_listFilter.get(position).getState());
        state_deaths.setText(State_listFilter.get(position).getDeaths());
        state_recovered.setText(State_listFilter.get(position).getRecovered());
        state_confirmed.setText(State_listFilter.get(position).getConfirmed());

        return view;
    }


    @Override
    public int getCount(){
        return State_listFilter.size();
    }

    @Nullable
    @Override
    public State_Model getItem(int position) {
        return State_listFilter.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public  Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = State_list.size();
                    filterResults.values = State_list;

                }
                else {
                    List<State_Model> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();
                    for (State_Model itemModel : State_list) {
                        if (itemModel.getState().toLowerCase().contains(searchStr)) {
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
                State_listFilter = (List<State_Model>) results.values;
                State_Affected.state_modelList = (List<State_Model>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
