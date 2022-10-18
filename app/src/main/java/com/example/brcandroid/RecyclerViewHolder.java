package com.example.brcandroid;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {


    TextView tv1,tv2;
    ImageView imageView;

    public RecyclerViewHolder(View itemView){
        super(itemView);


        tv1= (TextView) itemView.findViewById(R.id.daftar_judul);

        tv2= (TextView) itemView.findViewById(R.id.daftar_des);

        imageView= (ImageView)itemView.findViewById(R.id.daftar_icon);
    }
}