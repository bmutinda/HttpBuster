package com.bmutinda.httpbuster.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bmutinda.httpbuster.CustomApplication;
import com.bmutinda.httpbuster.R;
import com.bmutinda.httpbuster.api.ApiCallback;
import com.bmutinda.httpbuster.api.HttpBuster;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get BusterRequest
        CustomApplication.httpBuster.makeGetRequest("/", null, new ApiCallback() {
            @Override
            public void done(JSONObject jsonObject, Exception exception) {
                HttpBuster.log("GET -Request finished");

                if (jsonObject != null) {
                    HttpBuster.log("Respone GET = " + jsonObject.toString());
                }
            }
        });

        CustomApplication.httpBuster2.makePostRequest("/", null, new ApiCallback() {
            @Override
            public void done(JSONObject jsonObject, Exception exception) {
                HttpBuster.log("POST -Request finished");

                if (jsonObject != null) {
                    HttpBuster.log("Respone POST = " + jsonObject.toString());
                }
            }
        });
    }
}