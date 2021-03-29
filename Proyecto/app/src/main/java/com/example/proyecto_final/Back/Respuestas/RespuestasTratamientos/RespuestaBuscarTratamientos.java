package com.example.proyecto_final.Back.Respuestas.RespuestasTratamientos;

import java.util.List;

import com.example.proyecto_final.Back.models.Tratamientos.Tratamiento;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespuestaBuscarTratamientos {

    @SerializedName("tratamientos")
    @Expose
    private List<Tratamiento> tratamientos = null;

    public List<Tratamiento> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }

}
