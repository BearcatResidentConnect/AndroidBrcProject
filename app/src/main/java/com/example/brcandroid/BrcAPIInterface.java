package com.example.brcandroid;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONObject;

public class BrcAPIInterface {
    private final String basuUrl;
    private final RequestQueue requestQueue;

    public BrcAPIInterface(Context context, String basuUrl) {

        this.basuUrl = basuUrl;
        this.requestQueue = Volley.newRequestQueue(context);

    }

    public void brcGetAPI(String url, JSONObject requestBody,  BrcAPIResponse apiResponse) {

        //Toast.makeText(this, "Unexpected error occurred", Toast.LENGTH_SHORT).show();
        Log.v("Before Request : ", "Sending request to " + this.basuUrl + url);
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(
                        Request.Method.GET,
                        this.basuUrl + url,
                        requestBody,
                        apiResponse::onSuccess,
                        apiResponse::onFailure
                );
        requestQueue.add(jsonObjectRequest);
    }

    public void brcPostAPI(String url,JSONObject requestBody, BrcAPIResponse apiResponse) {

        Log.v("Before Request : ", "Sending request to " + this.basuUrl + url);
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(
                        Request.Method.POST,
                        this.basuUrl + url,
                        requestBody,
                        apiResponse::onSuccess,
                        apiResponse::onFailure
                );
        requestQueue.add(jsonObjectRequest);
    }


}
