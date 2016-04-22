package com.bmutinda.httpbuster;

import org.json.JSONObject;

public interface ApiCallback {
    void done(JSONObject jsonObject, Exception exception);
}