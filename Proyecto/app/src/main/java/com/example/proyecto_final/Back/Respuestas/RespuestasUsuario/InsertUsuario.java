package com.example.proyecto_final.Back.Respuestas.RespuestasUsuario;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertUsuario {

    @SerializedName("altaUsuario")
    @Expose
    private String altaUsuario;

    public String getAltaUsuario() {
        return altaUsuario;
    }

    public void setAltaUsuario(String altaUsuario) {
        this.altaUsuario = altaUsuario;
    }

}
