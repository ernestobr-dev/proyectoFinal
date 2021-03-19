package com.example.proyecto_final.Back.models.Pacientes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paciente {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nombre")
    @Expose
    private String nombre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}