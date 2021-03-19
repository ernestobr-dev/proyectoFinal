package com.example.proyecto_final.Back.models.Usuario;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginUsuario {

    @SerializedName("respuestaLogin")
    @Expose
    private String respuestaLogin;

    public String getRespuestaLogin() {
        return respuestaLogin;
    }

    public void setRespuestaLogin(String respuestaLogin) {
        this.respuestaLogin = respuestaLogin;
    }

}