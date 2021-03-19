package com.example.proyecto_final.Back.models.Agenda;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BuscarEvento {

    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("id")
    @Expose
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}