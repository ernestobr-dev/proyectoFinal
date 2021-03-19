package com.example.proyecto_final.Back.models.Resultados;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Resultado {

    @SerializedName("archivo")
    @Expose
    private String archivo;
    @SerializedName("nombre")
    @Expose
    private String nombre;

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}