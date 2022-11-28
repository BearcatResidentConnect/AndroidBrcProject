package com.example.brcandroid;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class UserRentaladpater extends RecyclerView.Adapter<UserRentaladpater.MyViewHolder> {
    Context context;
    private EventModel myModel;

    public UserRentaladpater(Context context, EventModel myModel){
        this.context= context;
        this.myModel = myModel;
    }

    @NonNull
    @Override
    public UserRentaladpater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_user_rental,parent,false);
        return new UserRentaladpater.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRentaladpater.MyViewHolder holder, int position) {
        holder.tvProLoc.setText(myModel.eventsList.get(position).PropertyName);
        holder.tvProAdd.setText(myModel.eventsList.get(position).PropertyLocation);
        Log.v("position", String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return myModel.eventsList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvProLoc,tvProAdd,tvId;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProLoc = itemView.findViewById(R.id.tvProLoc);
            tvProAdd = itemView.findViewById(R.id.tvProAdd);
        }
    }

}
