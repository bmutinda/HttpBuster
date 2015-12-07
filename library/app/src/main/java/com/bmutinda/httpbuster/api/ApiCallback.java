package com.bmutinda.httpbuster.api;

import org.json.JSONObject;

public interface ApiCallback {
    void done( JSONObject jsonObject, Exception exception );
}