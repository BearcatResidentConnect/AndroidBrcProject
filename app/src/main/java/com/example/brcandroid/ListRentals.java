package com.example.brcandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ListRentals extends AppCompatActivity {

    private EventModel myEModel ;
    private RecyclerView recyclerView=null;
    private UserRentaladpater adapter = null;
    private GestureDetectorCompat detector = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rentals);
        myEModel = EventModel.getSingleton();
        setUpEventModels();
    }

    private void setUpEventModels() {


        // Call API
        try{
            BrcAPIInterface ap = new BrcAPIInterface(ListRentals.this);
            ap.brcGetListAPI ("/rental-postings", new BrcAPIListResponse() {


                @Override
                public void onSuccess(JSONArray response) {

                    Toast.makeText(ListRentals.this,"Fetched Rentals List", Toast.LENGTH_SHORT).show();

                    Log.v("MSG " , response.toString());

                    for(int i=0; i< response.length();i++){
                        try {
                            JSONObject  tmp= response.getJSONObject(i);
                            Log.v("Data", tmp.toString());
                            String propertyName= tmp.getString("name");
                            String propertyLoc= tmp.getString("address1");
                            String PropertyId= tmp.getString("rental_id");
                            String PropertyEmail= tmp.getString("email");
                            Log.v("propertyName", propertyName);
                            Log.v("propertyLoc", propertyLoc);
                            Log.v("PropertyId", PropertyId);
                            Log.v("PropertyEmail", PropertyEmail);
                            myEModel.eventsList.add(new EventModel.Events(propertyName,propertyLoc,PropertyId, PropertyEmail));

                        } catch (JSONException e) {

                            Log.v("ERROR", "ERROR" + e);

                        }
                    }
                    Log.v("Setup EventList Size:", String.valueOf(myEModel.eventsList.size()));
                    adapter = new UserRentaladpater(ListRentals.this, myEModel);
                    Log.v("adapter", String.valueOf(adapter.getItemCount()));

                    recyclerView = findViewById(R.id.recyclerview);
                    recyclerView.setAdapter(adapter);

                    recyclerView.setLayoutManager(new LinearLayoutManager(ListRentals.this));

                    detector = new GestureDetectorCompat(ListRentals.this, new RecyclerViewOnGestureListener());
                    recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(){
                        @Override
                        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                            return detector.onTouchEvent(e);
                        }
                    });


                }

                @Override
                public void onFailure(Exception exception) {

                    Toast.makeText(ListRentals.this, "Error Fetching Rentals", Toast.LENGTH_SHORT).show();
                    Log.v("Exception: ", "Exception " + exception);


                }

            });
        } catch(Exception e){

        }

    }


    public void onClickRegisterRental(View v){

        //open user register page

        Intent userHomeIntent = new Intent(this, RentalRegister.class);
        startActivity(userHomeIntent);
        finish();

    }
    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(view);
                if (holder instanceof UserRentaladpater.MyViewHolder) {
                    int position = holder.getAdapterPosition();
                    Log.v("position selected", String.valueOf(position));
                    // handle single tap

                    String sEventId= myEModel.eventsList.get(position).PropertyId;
                    String sPopertyName= myEModel.eventsList.get(position).PropertyName;
                    String sPopertyAddress= myEModel.eventsList.get(position).PropertyLocation;
                    String sPopertyEmail= myEModel.eventsList.get(position).PropertyEmail;
                Log.v("Selected EventID: ",sEventId);
                Intent intent = new Intent(ListRentals.this, RentalDetailView.class);
                intent.putExtra("eventID",sEventId);
                intent.putExtra("propertyName",sPopertyName);
                intent.putExtra("propertyAdd",sPopertyAddress);
                intent.putExtra("propertyEmail",sPopertyEmail);
                startActivity(intent);

                    return true; // Use up the tap gesture
                }
            }            // we didn't handle the gesture so pass it on
            return false;
        }
    }
}