package com.example.proyecto_final.Back.Respuestas.RespuestasAgenda;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespuestaModificarEvento {

    @SerializedName("modificarEvento")
    @Expose
    private String modificarEvento;

    public String getModificarEvento() {
        return modificarEvento;
    }

    public void setModificarEvento(String modificarEvento) {
        this.modificarEvento = modificarEvento;
    }

}
