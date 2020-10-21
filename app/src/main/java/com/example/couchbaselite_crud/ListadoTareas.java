package com.example.couchbaselite_crud;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListadoTareas extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_tareas);

        recyclerView = findViewById(R.id.recyclerTareas);
        CDBManager cdbManager = new CDBManager(this, "ugb",
                "localhost", "4545", "admin","1234");

        cdbManager.startDB();
        TareasDAO dao = new TareasDAO(cdbManager.database);
        ArrayList<Tareas> tareas = dao.getAll();

        TareasAdapter adapter = new TareasAdapter(tareas);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onResume(){
        super.onResume();
        CDBManager cdbManager = new CDBManager(this,"ugb",
                "localhost", "4545", "admin", "1234");
        cdbManager.startDB();
        TareasDAO dao = new TareasDAO(cdbManager.database);
        ArrayList<Tareas> tareas = dao.getAll();

        TareasAdapter adapter = new TareasAdapter(tareas);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

}
