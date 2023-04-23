package com.example.d_treeapi;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServerData {

    public static final String QUERY = "https://exercise-b342.restdb.io/rest/group-1";
    Context context;

    public ServerData(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(List <UserModel> userModels);
    }

    //Get all users from API
    public void getAllUsers(VolleyResponseListener volleyResponseListener) {

        final List<UserModel> userModels = new ArrayList<>();

        String url = QUERY;
        //Request JSON array from url
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            for(int i=0;i<response.length(); i++){
                                UserModel user = new UserModel();
                                JSONObject userData = (JSONObject) response.get(i);
                                user.setId(userData.getString("_id"));
                                user.setName(userData.getString("NAME"));
                                user.setSurname(userData.getString("SURNAME"));
                                user.setAge(userData.getInt("AGE"));
                                user.setCity(userData.getString("CITY"));
                                userModels.add(user);
                            }
                            volleyResponseListener.onResponse(userModels);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Something Happened couldn't get All Users!!!!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap header = new HashMap();
                header.put("Content-Type", "application/json");
                header.put("x-apikey", "63722be4c890f30a8fd1f370");
                header.put("cache-control", "no-cache");

                return header;
            }
        };

        MySingleton.getInstance(context).addToRequestQueue(request);
    }


    //Get filtered data
    public void getFilteredUsers(String filter, VolleyResponseListener volleyResponseListener) {



        final List<UserModel> userModels = new ArrayList<>();
        //Add parameter city filter
        String url = QUERY + String.format("?q={}&filter=%s",filter);
        //Request JSON array from url
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            for(int i=0;i<response.length(); i++){
                                UserModel user = new UserModel();
                                JSONObject userData = (JSONObject) response.get(i);
                                user.setId(userData.getString("_id"));
                                user.setName(userData.getString("NAME"));
                                user.setSurname(userData.getString("SURNAME"));
                                user.setAge(userData.getInt("AGE"));
                                user.setCity(userData.getString("CITY"));
                                userModels.add(user);
                            }
                            //An empty array here suggests an invalid city, this can be expanded to show available cities
                            if(userModels.isEmpty()) {
                                getCityList();
                            } else {
                                volleyResponseListener.onResponse(userModels);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Something Happened couldn't get Filtered Users!!!!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap header = new HashMap();
                header.put("Content-Type", "application/json");
                header.put("x-apikey", "63722be4c890f30a8fd1f370");
                header.put("cache-control", "no-cache");

                return header;
            }
        };

        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    //Get all users from API
    public void getCityList() {

        final List<String> userCities = new ArrayList<>();

        String url = QUERY;
        //Request JSON array from url
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            for(int i=0;i<response.length(); i++){
                                JSONObject userData = (JSONObject) response.get(i);
                                userCities.add(userData.getString("CITY"));
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //User typed invalid city, Inform user available cities
                        Toast.makeText(context, "Invalid city!, available cities are " + userCities.stream().distinct().collect(Collectors.toList()), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Something Happened couldn't get All Users!!!!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap header = new HashMap();
                header.put("Content-Type", "application/json");
                header.put("x-apikey", "63722be4c890f30a8fd1f370");
                header.put("cache-control", "no-cache");

                return header;
            }
        };

        MySingleton.getInstance(context).addToRequestQueue(request);
    }


}
