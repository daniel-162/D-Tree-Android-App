package com.example.d_treeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Define variables
    Button btn_geData;
    EditText et_filterByCity;
    ListView lv_dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign values
        btn_geData = findViewById(R.id.btn_geData);
        et_filterByCity = findViewById(R.id.et_filterByCity);
        lv_dataList = findViewById(R.id.lv_dataList);

        //Listeners
        btn_geData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServerData serverData = new ServerData(MainActivity.this);

                //Check if edit text is empty
                String filterText = et_filterByCity.getText().toString();
                if (filterText.matches("")) {
                    //Get all users from Server Data
                    serverData.getAllUsers(new ServerData.VolleyResponseListener() {
                        @Override
                        public void onError(String message) {
                            Toast.makeText(MainActivity.this, "Well, Error!!!!", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onResponse(List<UserModel> userModels) {
                            //list view
                            ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, userModels);
                            lv_dataList.setAdapter(arrayAdapter);
                        }
                    });
                }else {
                    //Get filtered users from Server Data
                    serverData.getFilteredUsers(et_filterByCity.getText().toString(), new ServerData.VolleyResponseListener() {
                        @Override
                        public void onError(String message) {
                            Toast.makeText(MainActivity.this, "Well, Error!!!!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(List<UserModel> userModels) {
                            //list view
                            ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, userModels);
                            lv_dataList.setAdapter(arrayAdapter);
                        }
                    });
                }

            }
        });
    }
}