package com.bmutinda.httpbuster;

import org.json.JSONObject;

public interface ApiCallback {
    void done(BusterResponse busterResponse, JSONObject jsonObject, Exception exception);
}