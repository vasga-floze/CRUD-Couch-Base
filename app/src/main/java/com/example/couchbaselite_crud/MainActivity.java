package com.example.couchbaselite_crud;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CDBManager cdbManager = new CDBManager(
                getApplicationContext(), "ugb", "localhost",
                "4984", "admin", "1234");
        cdbManager.startDB();
        cdbManager.startReplication();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCrear:
                CDBManager cdbManager = new CDBManager(
                        getApplicationContext(), "ugb", "localhost",
                        "4984", "admin", "1234");
                cdbManager.startDB();
                String name = edtName.getText().toStrting();
                String desc = edtDesc.getText().toString();
                boolean state = swtState.isChecked();
                TareasDAO dao = new TareasDAO()(cdbManager.database);
                Tareas t = new Tareas();
                t.setActive(state);
                t.setDescription(desc);
                t.setName(name);
                boolean inserted = dao.insert(t);
                if(inserted) {
                    Toast.makeText(MainActivity.this, "Registro guardado",
                            Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"Error al insertar",
                            Toast.LENGTH_LONG).show();
                }
        }
    }
}