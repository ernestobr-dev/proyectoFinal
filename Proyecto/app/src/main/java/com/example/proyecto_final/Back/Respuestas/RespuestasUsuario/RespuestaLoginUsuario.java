package com.example.proyecto_final.Back.Respuestas.RespuestasUsuario;

import com.example.proyecto_final.Back.models.Usuario.LoginUsuario;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RespuestaLoginUsuario {

    @SerializedName("loginUsuario")
    @Expose
    private LoginUsuario loginUsuario;

    public LoginUsuario getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(LoginUsuario loginUsuario) {
        this.loginUsuario = loginUsuario;
    }
}
