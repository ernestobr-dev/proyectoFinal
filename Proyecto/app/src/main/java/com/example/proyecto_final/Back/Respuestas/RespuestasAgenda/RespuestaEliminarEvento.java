package com.example.proyecto_final.Back.Respuestas.RespuestasAgenda;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespuestaEliminarEvento {

    @SerializedName("eliminarEvento")
    @Expose
    private String eliminarEvento;

    public String getEliminarEvento() {
        return eliminarEvento;
    }

    public void setEliminarEvento(String eliminarEvento) {
        this.eliminarEvento = eliminarEvento;
    }


}
