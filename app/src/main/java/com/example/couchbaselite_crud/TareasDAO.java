package com.example.couchbaselite_crud;

import android.util.Log;
import org.w3c.dom.Document;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;
import javax.xml.transform.Result;

public class TareasDAO {
    Database db;

    public TareasDAO(Database db){
        this.db = db;
    }



    public Tareas getByID(String id){
        Tareas tarea = new Tareas();
        Log.i("getId", id);
        Document tareaDoc = db.getDocument(id);
        if(tareaDoc.contains("name")){
            Log.i("getId", tareaDoc, getId());
            tarea.setId(tareaDoc.getId());
            tarea.setName(tareaDoc.getString("name"));
            tarea.setActive(tareaDoc.getBoolean("active"));
            tarea.setDescription(tareaDoc.getString("description"));
        }else{
            tarea = null;
        }
        return tarea;
    }

    public boolean insert(Tareas tarea){
        MutableDocument mutableDoc = new MutableDocument()
                .setString("name",tarea.getName())
                .setString("description", tarea.getDescription())
                .setBoolean("active", tarea.isActive())
                .setString("type", "Tareas");
        try {
            db.save(mutableDoc);
            return true;
        } catch (CouchbaseLiteExeption e){
            e.printStackTrace();
        }
        return false;
    }

    public  boolean update (Tareas tarea){
        MutableDocument mutableDoc = db.getDocument(tarea.getId()).toMutable();
        mutableDoc.setString("name", tarea.name)
                .setString("description",tarea.description)
                .setBoolean("active", tarea.isActive());
        try {
            db.save(mutableDoc);
            return true;
        } catch (CouchbaseLiteExeption e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Tareas tarea){
        Document document = db.getDocument(tarea.getId());
        try {
            db.delete(document);
            return true;
        }catch(CouchbaseLiteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Tareas> getAll() {
        ArrayList<Tareas> tareas = new ArrayList<>();
        Query query = QueryBuilder.select(
                SelectResult.expression(Meta.id),
                SelectResult.property("name"))
                .from(DataSource.database(db)).where(
                        Expression.property("type")
                        .equalTo(Expression.string("Tareas"))
                );
                try {
                    ResultSet rs = query.execute();
                    for (Result result : rs) {
                        Tareas tarea = new Tareas();
                        tarea.setName(result.getString("name"));
                        tarea.setDescription(result.getString("description"));
                        tarea.setActive(result.getBoolean("active"));
                        tarea.setId(result.getString("id"));
                        tareas.add(tarea);
                    }
                }
                    catch(CouchbaseLiteException e) {
                        e.printStackTrace();
                }
                return tareas;
        )

    }
}
