package com.example.proyecto_final.Back.Routes;



import com.example.proyecto_final.Back.Respuestas.RespuestasTratamientos.RespuestaBuscarTratamientos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TratamientosRoutes {

    @GET("buscar")
    Call<RespuestaBuscarTratamientos> buscarTratamientos(@Query("paciente")String paciente);

}
