package com.bmutinda.httpbuster;

import com.squareup.okhttp.Response;

import org.json.JSONObject;

public interface ApiCallback {
    void done(Response response, JSONObject jsonObject, Exception exception);
}