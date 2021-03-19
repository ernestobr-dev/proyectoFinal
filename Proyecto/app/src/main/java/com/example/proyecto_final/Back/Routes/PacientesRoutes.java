package com.example.proyecto_final.Back.Routes;

import com.example.proyecto_final.Back.Respuestas.RespuestasPacientes.RespuestaBuscarPaciente;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PacientesRoutes {

    @GET("buscar")
    Call<RespuestaBuscarPaciente> buscarPaciente();
}
