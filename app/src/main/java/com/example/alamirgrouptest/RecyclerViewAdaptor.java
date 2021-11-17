package com.example.alamirgrouptest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.MyViewHolder> {

    Context context;
    ArrayList arrId, arrName, arrPhone,arrGender;

    public RecyclerViewAdaptor(Context context, ArrayList arrId, ArrayList arrName, ArrayList arrPhone, ArrayList arrGender){
        this.context = context;
        this.arrId = arrId;
        this.arrName = arrName;
        this.arrPhone = arrPhone;
        this.arrGender = arrGender;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_data, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtName.setText(String.valueOf(arrName.get(position)));
        holder.txtPhone.setText(String.valueOf(arrPhone.get(position)));
        holder.txtGender.setText(String.valueOf(arrGender.get(position)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, updateActivity.class);
                intent.putExtra("id",String.valueOf(arrId.get(position)));
                intent.putExtra("name",String.valueOf(arrName.get(position)));
                intent.putExtra("phone",String.valueOf(arrPhone.get(position)));
                intent.putExtra("gender",String.valueOf(arrGender.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtID, txtName, txtPhone, txtGender;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtrowName);
            txtPhone = itemView.findViewById(R.id.txtrowPhone);
            txtGender = itemView.findViewById(R.id.txtrowGender);



        }
    }
}
