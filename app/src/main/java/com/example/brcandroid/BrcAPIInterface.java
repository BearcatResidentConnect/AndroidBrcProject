package com.example.brcandroid;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class BrcAPIInterface {
    private final String basuUrl;
    private final RequestQueue requestQueue;

    public BrcAPIInterface(Context context, String basuUrl) {

        this.basuUrl = basuUrl;
        this.requestQueue = Volley.newRequestQueue(context);

    }

    public void brcGetAPI(String url, BrcAPIResponse apiResponse) {
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(
                        Request.Method.GET,
                        url,
                        null,
                        apiResponse::onSuccess,
                        apiResponse::onFailure
                );
        requestQueue.add(jsonObjectRequest);
    }

    public void brcPostAPI(JSONObject requestBody, String url, BrcAPIResponse apiResponse) {
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(
                        Request.Method.POST,
                        url,
                        requestBody,
                        apiResponse::onSuccess,
                        apiResponse::onFailure
                );
        requestQueue.add(jsonObjectRequest);
    }
}
