package com.example.proyecto_final.Back.Respuestas.RespuestasAgenda;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespuestaAltaEvento {

    @SerializedName("altaAgenda")
    @Expose
    private String altaAgenda;

    public String getAltaAgenda() {
        return altaAgenda;
    }

    public void setAltaAgenda(String altaAgenda) {
        this.altaAgenda = altaAgenda;
    }
}
