package com.example.couchbaselite_crud;

public class Tareas {

    String id;
    String rev;
    String name;
    String description;
    boolean active;

    public Tareas(String id, String rev, String name, String description, boolean active) {
        this.id = id;
        this.rev = rev;
        this.name = name;
        this.description = description;
        this.active = active;
    }

    //El setter para poner un valor, una propiedad

    public Tareas(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString(){
        return name;
    }
}
