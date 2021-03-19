package com.example.proyecto_final.Back.Routes;

import com.example.proyecto_final.Back.Respuestas.RespuestasResultados.RespuestaBuscarResultados;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ResultadosRoutes {

    @GET("buscar")
    Call<RespuestaBuscarResultados> buscarResultado(@Query("paciente")String paciente);

}
