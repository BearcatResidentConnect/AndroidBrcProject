package com.example.brcandroid;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerModel {

    public static class Recycler{
        public String PropertyName, PropertyEmail, AppliedDate;

        public Recycler(String PropertyName, String PropertyEmail, String AppliedDate) {
            this.PropertyName = PropertyName;
            this.PropertyEmail = PropertyEmail;
            this.AppliedDate = AppliedDate;
        }

    }
    private static RecyclerModel theModel = null;
    public static RecyclerModel getSingleton(){
        theModel = new RecyclerModel();
        return theModel;
    }

    public ArrayList<Recycler> eventsList;
    private RecyclerModel(){
        eventsList = new ArrayList<Recycler>();
    }



}
