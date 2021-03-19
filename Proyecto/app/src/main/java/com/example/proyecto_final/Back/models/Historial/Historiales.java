package com.example.proyecto_final.Back.models.Historial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Historiales {

    @SerializedName("motivo")
    @Expose
    private String motivo;
    @SerializedName("fecha")
    @Expose
    private String fecha;

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
