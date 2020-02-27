package com.example.mytestfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.MyViewHolder> {
    private ArrayList<person> todoList;
    private View view;
    private Context context;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.raw_layout,parent,false);
       MyViewHolder holder =new MyViewHolder(view);
        return holder;
    }

    public RecAdapter(ArrayList<person> todoList, Context context) {
        this.todoList = todoList;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      holder.txtEmail.setText(todoList.get(position).getEmail());
      holder.txtName.setText(todoList.get(position).getName());
      holder.txtPhone.setText(todoList.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView txtName;
        public TextView txtPhone;
        public TextView txtEmail;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.textName);
            txtPhone = itemView.findViewById(R.id.textPhone);
            txtEmail = itemView.findViewById(R.id.textEmail);
        }
    }
}
