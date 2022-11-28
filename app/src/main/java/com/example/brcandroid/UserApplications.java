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

public class UserApplications extends AppCompatActivity {

    private RecyclerModel myRModel ;
    private RecyclerView recyclerView=null;
    private Recyleradpater adapter = null;
    private GestureDetectorCompat detector = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_applications);
        myRModel = RecyclerModel.getSingleton();
        setUpEventModels();
    }

    private void setUpEventModels() {


        // Call API
        try{
            BrcAPIInterface ap = new BrcAPIInterface(UserApplications.this);
            ap.brcGetListAPI ("/user_applications", new BrcAPIListResponse() {


                @Override
                public void onSuccess(JSONArray response) {

                    //Toast.makeText(UserApplications.this,"Fetched Rentals List", Toast.LENGTH_SHORT).show();

                    Log.v("MSG " , response.toString());

                    for(int i=0; i< response.length();i++){
                        try {
                            JSONObject tmp= response.getJSONObject(i);
                            Log.v("Data", tmp.toString());
                            String propertyName= tmp.getString("rental_name");
                            String propertyEmail= tmp.getString("rental_email");
                            String appliedDate= "1-1-1";//tmp.getString("applied_date");
                            Log.v("propertyName", propertyName);
                            Log.v("propertyLoc", propertyEmail);
                            myRModel.eventsList.add(new RecyclerModel.Recycler(propertyName,propertyEmail, appliedDate));

                        } catch (JSONException e) {

                            Log.v("ERROR", "ERROR" + e);

                        }
                    }
                    Log.v("Setup EventList Size:", String.valueOf(myRModel.eventsList.size()));
                    adapter = new Recyleradpater(UserApplications.this, myRModel);
                    Log.v("adapter", String.valueOf(adapter.getItemCount()));

                    recyclerView = findViewById(R.id.recycler_view);
                    recyclerView.setAdapter(adapter);

                    recyclerView.setLayoutManager(new LinearLayoutManager(UserApplications.this));

                    detector = new GestureDetectorCompat(UserApplications.this, new RecyclerViewOnGestureListener());
                    recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(){
                        @Override
                        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                            return detector.onTouchEvent(e);
                        }
                    });


                }

                @Override
                public void onFailure(Exception exception) {

                    Toast.makeText(UserApplications.this, "Error Fetching Rentals", Toast.LENGTH_SHORT).show();
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
    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener implements GestureDetector.OnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(view);
                if (holder instanceof UserRentaladpater.MyViewHolder) {
                    int position = holder.getAdapterPosition();
                    Log.v("position selected", String.valueOf(position));
                    // handle single tap
				/*
                String sEventId= myEModel.eventsList.get(position).eventID;
                Log.v("Selected EventID: ",sEventId);
                Intent intent = new Intent(HomeActivity.this, DetailedEventActivity.class);
                intent.putExtra("eventID",sEventId);
                startActivity(intent);
*/
                    return true; // Use up the tap gesture
                }
            }            // we didn't handle the gesture so pass it on
            return false;
        }
    }
}