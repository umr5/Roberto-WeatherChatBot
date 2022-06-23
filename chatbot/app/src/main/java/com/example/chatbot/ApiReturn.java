package com.example.chatbot;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class ApiReturn {
    String urlCity = "https://www.metaweather.com/api/location/search/?query=";
    String urlForecast = "https://www.metaweather.com/api/location/";
    ArrayList dataList = new ArrayList<>();
    ArrayList<double[]> forecast = new ArrayList<>();
    ArrayList<String> stateData = new ArrayList<>();
    private Context context;

    public ApiReturn(Context context) {
        this.context = context;
    }


    public void getWoeID(String city){

        RequestQueue req = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlCity.concat(city), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    int woeId = response.getJSONObject(0).getInt("woeid");
                    dataList.add(woeId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        req.add(jsonArrayRequest);

    }
    public void getData(){
        RequestQueue req = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlForecast.concat(dataList.get(0).toString()), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    double[][] data = new double[6][2];
                    for (int i = 0; i < data.length; i++) {
                        data[i][0] = response.getJSONArray("consolidated_weather").getJSONObject(i).getDouble("min_temp");
                        data[i][1] = response.getJSONArray("consolidated_weather").getJSONObject(i).getDouble("max_temp");

                    }
                    for (int i = 0; i < data.length; i++) {
                            forecast.add(data[i]);

                    }
                    String[] state = new String[6];
                    for (int i = 0; i < state.length; i++){
                        state[i] = response.getJSONArray("consolidated_weather").getJSONObject(i).getString("weather_state_name");
                    }
                    for (int i = 0; i < state.length; i++){
                        stateData.add(state[i]);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        req.add(jsonObjectRequest);
    }
    public double getTempMin(int i){

        double[] temp = forecast.get(i);

        return Math.round(temp[0] * 100.0) / 100.0;

    }
    public double getTempMax(int i){

        double[] temp = forecast.get(i);

        return Math.round(temp[1] * 100.0) / 100.0;

    }
    public String state(int i){
        String state = stateData.get(i);
        return state;
    }
}
