package com.rahul.covid19;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class District_Affected extends AppCompatActivity {
    EditText Search_district;
    ListView district_List;
    District_Model district_model;
    public static List<District_Model> district_modelList = new ArrayList<>();
    District_Adapter district_adapter;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district__affected);

          Search_district = findViewById(R.id.Search_district);
          district_List = findViewById(R.id.district_list);

        (Objects.requireNonNull(getSupportActionBar())).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fetchDistrictdata();

        Search_district.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                district_adapter.getFilter().filter(s);
                district_adapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    private void fetchDistrictdata() {
        String url = "https://api.covid19india.org/state_district_wise.json";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Creating object of JSONObject
                    JSONArray jsonArray = (response).getJSONArray("state");

                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String active = jsonObject.getString("active");
                        String deaths = jsonObject.getString("deceased");
                        String recovered = jsonObject.getString("recovered");
                        String state = jsonObject.getString("district");
                        String confirmed = jsonObject.getString("confirmed");



                        district_model = new District_Model(active,deaths,recovered,state,confirmed);
                        district_modelList.add(district_model);

                    }

                    district_adapter = new District_Adapter(District_Affected.this,district_modelList);
                    district_List.setAdapter(district_adapter);


                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(District_Affected.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


}