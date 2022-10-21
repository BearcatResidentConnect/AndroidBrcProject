package com.example.brcandroid;

import org.json.JSONObject;

public abstract class BrcAPIResponse {
    public abstract void onSuccess(JSONObject response);
    public abstract void onFailure(Exception exception);
}



