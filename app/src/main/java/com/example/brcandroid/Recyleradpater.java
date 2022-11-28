package com.example.brcandroid;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.BreakIterator;


public class Recyleradpater extends RecyclerView.Adapter<Recyleradpater.MyViewHolder> {
    Context context;
    private RecyclerModel myModel;

    public Recyleradpater(Context context, RecyclerModel myModel){
        this.context= context;
        this.myModel = myModel;
    }

    @NonNull
    @Override
    public Recyleradpater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list,parent,false);
        return new Recyleradpater.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Recyleradpater.MyViewHolder holder, int position) {
        holder.tvProLoc.setText(myModel.eventsList.get(position).PropertyName);
        holder.tvProAdd.setText(myModel.eventsList.get(position).PropertyEmail);
        holder.tvProDate.setText(myModel.eventsList.get(position).AppliedDate);
        Log.v("position", String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return myModel.eventsList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvProLoc,tvProAdd, tvProDate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProLoc = itemView.findViewById(R.id.tvProLoc);
            tvProAdd = itemView.findViewById(R.id.tvProAdd);
            tvProDate = itemView.findViewById(R.id.tvProDate);
        }
    }

}
