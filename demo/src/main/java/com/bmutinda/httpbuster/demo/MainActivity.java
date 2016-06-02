package com.bmutinda.httpbuster.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bmutinda.httpbuster.ApiCallback;

import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runExample();
    }

    private void runExample(){

        // Get Request without any params added
        HttpBusterApplication.getHttpBuster().makeGetRequest("jokes/random", null, new ApiCallback() {
            @Override
            public void done(JSONObject jsonObject, Exception exception) {
                Log.e(TAG, "GET without params done");
                if ( jsonObject !=null){
                    Log.e(TAG, jsonObject.toString());
                }
            }
        });

        // Get Request with other parameters added
        HashMap<String, Object> map = new HashMap<>();
        map.put("firstName", "Mutinda");
        map.put("lastName", "Boniface");
        HttpBusterApplication.getHttpBuster().makeGetRequest("jokes/random/10", map, new ApiCallback() {
            @Override
            public void done(JSONObject jsonObject, Exception exception) {
                Log.e(TAG, "GET with params done");
                if ( jsonObject !=null){
                    Log.e(TAG, jsonObject.toString());
                }
            }
        });
    }

}
