package com.example.couchbaselite_crud;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;



public class MainActivity extends Activity implements View.OnClickListener{

    Switch swtState;
    EditText edtName, edtDesc;
    Button btnCrear;
    Button btnVer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName = (EditText) findViewById(R.id.edtName);
        edtDesc = (EditText)findViewById(R.id.edtDescription);
        btnCrear = (Button)findViewById(R.id.btnCrear);
        btnVer = (Button)findViewById(R.id.btnVer);
        swtState=(Switch)findViewById(R.id.swtState);
        btnCrear.setOnClickListener(this);
        btnVer.setOnClickListener(this);
        swtState.setOnClickListener(this);

    }


    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCrear:
                CDBManager cdbManager = new CDBManager(
                        getApplicationContext(), "ugb", "localhost",
                        "4984", "admin", "1234");
                cdbManager.startDB();
                String name = edtName.getText().toString();
                String desc = edtDesc.getText().toString();
                boolean state = swtState.isChecked();
                TareasDAO dao = new TareasDAO(cdbManager.database);
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
                edtName.setText("");
                edtDesc.setText("");
            case R.id.btnVer:
                Intent i = new Intent(MainActivity.this, ListadoTareas.class);
                startActivity(i);
                break;
        }
    }
}