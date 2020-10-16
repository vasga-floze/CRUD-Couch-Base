package com.example.couchbaselite_crud;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.MyViewHolder> {
    ArrayList<Tareas>tareas;
    public TareasAdapter(ArrayList<Tareas> tareas){this.tareas=tareas;}

    @NonNull
    @Override
    public TareasAdapter.MyViewHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View tareaViewItem = inflater.inflate(R.layout.tarea_item,
                viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(tareaViewItem);
        return viewHolder;
    }

    @Override
      public void onBindViewHolder(@NonNull TareasAdapter.MyViewHolder viewHolder, int i) {
        Tareas tarea = tareas.get(i);
        viewHolder.txtId.setText(tarea.getId());
        viewHolder.txtName.setText(tarea.getName());
    }

    @Override
    public int getItemCount() {
        return tareas.size();
    }

    //Recibe las vistas y parsea la vista
    public class MyViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        public TextView txtId, txtName;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtName = itemView.findViewById(R.id.txtName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View itemView) {
            txtId = itemView.findViewById(R.id.txtId);
            Intent i = new Intent(itemView.getContext(), TareaDescription.class);
            i.putExtra("id", txtId.getText().toString());
            itemView.getContext().startActivity(i);
        }
    }
}
