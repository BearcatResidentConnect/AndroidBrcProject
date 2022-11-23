package com.example.brcandroid;

import org.json.JSONArray;

public abstract class BrcAPIListResponse {
    public abstract void onSuccess(JSONArray response);
    public abstract void onFailure(Exception exception);
}



