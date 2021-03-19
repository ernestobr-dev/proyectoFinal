package com.example.proyecto_final.Back.Routes;

import com.example.proyecto_final.Back.Respuestas.RespuestasHistorial.RespuestaBuscarHistorial;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HistorialRoutes {

    @GET("buscar")
    Call<RespuestaBuscarHistorial> buscarHistorial (@Query("paciente") String paciente);

}
