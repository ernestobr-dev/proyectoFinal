package com.example.proyecto_final.Back.Routes;

import com.example.proyecto_final.Back.Respuestas.RespuestasAgenda.RespuestaAltaEvento;
import com.example.proyecto_final.Back.Respuestas.RespuestasAgenda.RespuestaBuscarEvento;
import com.example.proyecto_final.Back.Respuestas.RespuestasAgenda.RespuestaEliminarEvento;
import com.example.proyecto_final.Back.Respuestas.RespuestasAgenda.RespuestaModificarEvento;
import com.example.proyecto_final.Back.models.Agenda.AltaEventoAgenda;
import com.example.proyecto_final.Back.models.Agenda.ModEvento;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface AgendaRoutes {

    @GET("buscar")
    Call<RespuestaBuscarEvento> listadoEventos(
      @Query("fecha") String fecha,
      @Query("usuario") String usuario
    );


    @POST("alta")
    Call<RespuestaAltaEvento> nuevoEvento(@Body AltaEventoAgenda nuevo);

    @PUT("modificar")
    Call<RespuestaModificarEvento> modificarEvento(@Body ModEvento modificar);

    @DELETE("eliminar")
    Call<RespuestaEliminarEvento> eliminarEvento(@Query("id")String id);

}
