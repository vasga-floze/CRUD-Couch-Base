package com.example.couchbaselite_crud;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class TareaDescription {
    String id = "";
    Switch swtActive;
    Button btnDelete, btnUpdate;
    TareasDAO dao;
    EditText edtName, edtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea_description);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        id = getIntent().getExtras().getString("id");
        CDBManager cdbManager = new CDBManager(getApplicationContext(),
                "ugb","localhost","4545","admin","1234");
        cdbManager.startDB();
        dao = new TareasDAO(cdbManager.database);
        Tareas tarea = dao.getById(id);
        edtName = findViewById(R.id.edtName);
        edtDescription = findViewById(R.id.edtDescription);
        swtActive = findViewById(R.id.swtActive);
        if (tarea != null) {
            edtName.setText(tarea.getName());
            edtDescription.setText(tarea.getDescription());
            swtActive.setChecked(tarea.isActive());
            btnDelete.setOnClickListener(this);
            btnUpdate.setOnClickListener(this);
        }else{
            edtDescription.setEnable(false);
            edtDescription.setEnable(false);
            swtActive.setEnable(false);
            btnUpdate.setEnable(false);
            btnDelete.setEnable(false);
        }
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnDelete:
                Tareas tarea = new Tareas();
                tarea.id = id;
                boolean res = dao.delete(tarea);
                if(res){
                    Toast.makeText(TareaDescription.this, "Tarea eliminada",
                            Toast.LENGTH_LONG).show();
                    finish();
                }else{
                   Toast.makeText(TareaDescription.this, "Error",
                           Toast.LENGTH_LONG).show();
                }
                 break;
            case R.id.btnUpdate:
                Tareas tarea2 = new Tareas();
                tarea2.id = id;
                tarea2.setName(edtName.getText().toString());
                tarea2.setDescription(edtDescription.getText().toString());
                tarea2.setActive(swtActive.isChecked());
                boolean res2 = dao.update(tarea2);
                if(res2){
                    Toast.makeText(TareaDescription.this, "Tarea Actualizada",
                            Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(TareaDescription.this, "Error",
                            Toast.LENGTH_LONG).show();
                }
                break;
              }
    }

}
