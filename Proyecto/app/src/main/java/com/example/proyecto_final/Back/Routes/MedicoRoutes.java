package com.example.proyecto_final.Back.Routes;

import com.example.proyecto_final.Back.Respuestas.RespuestasUsuario.InsertUsuario;
import com.example.proyecto_final.Back.Respuestas.RespuestasUsuario.RespuestaLoginUsuario;
import com.example.proyecto_final.Back.models.Usuario.AltaUsuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface MedicoRoutes {

    // TODO: RUTA LOGIN USUARIO
    @GET("login")
    Call<RespuestaLoginUsuario> loginUsuario(
            @Query("email") String email,
            @Query("contraseña") String contraseña
    );


    //TODO: RUTA ALTA USUARIOS
    @POST("alta")
    Call<InsertUsuario> nuevoUsuario(@Body AltaUsuario nuevo);

}
