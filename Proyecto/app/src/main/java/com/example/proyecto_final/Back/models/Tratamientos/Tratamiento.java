package com.example.proyecto_final.Back.models.Tratamientos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tratamiento {

    @SerializedName("motivo")
    @Expose
    private String motivo;
    @SerializedName("tratamiento")
    @Expose
    private String tratamiento;
    @SerializedName("tiempo")
    @Expose
    private String tiempo;

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

}