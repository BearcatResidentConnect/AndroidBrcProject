package com.example.brcandroid;

import java.util.ArrayList;

public class EventModel {

    public static class Events{
        public String PropertyName, PropertyLocation;
        public String PropertyId, PropertyEmail;


        public Events(String PropertyName, String PropertyLocation, String PropertyId, String PropertyEmail) {
            this.PropertyName = PropertyName;
            this.PropertyLocation = PropertyLocation;
            this.PropertyId = PropertyId;
            this.PropertyEmail = PropertyEmail;
        }

    }
    private static EventModel theModel = null;
    public static EventModel getSingleton(){
        theModel = new EventModel();
        return theModel;
    }

    public ArrayList<Events> eventsList;
    private EventModel(){
        eventsList = new ArrayList<Events>();
    }



}
