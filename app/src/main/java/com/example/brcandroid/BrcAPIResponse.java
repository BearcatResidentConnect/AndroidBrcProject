package com.example.brcandroid;

import android.util.Log;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public abstract class BrcAPIResponse {
    public abstract void onSuccess(JSONObject response);
    public abstract void onFailure(Exception exception);
//    public void onErrorResponse( VolleyError volleyError ) {
//        try {
//            String responseBody = new String( volleyError.networkResponse.data, "utf-8" );
//            JSONObject jsonObject = new JSONObject( responseBody );
//
//            Log.v("Error", jsonObject.toString());
//        } catch ( JSONException e ) {
//            //Handle a malformed json response
//        } catch (UnsupportedEncodingException error){
//
//        }
//    }
}



