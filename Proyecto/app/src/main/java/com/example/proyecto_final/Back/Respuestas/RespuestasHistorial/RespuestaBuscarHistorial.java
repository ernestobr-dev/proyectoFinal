package com.example.proyecto_final.Back.Respuestas.RespuestasHistorial;


import java.util.List;

import com.example.proyecto_final.Back.models.Historial.Historiales;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RespuestaBuscarHistorial {

    @SerializedName("historiales")
    @Expose
    private List<Historiales> historiales = null;

    public List<Historiales> getHistoriales() {
        return historiales;
    }

    public void setHistoriales(List<Historiales> historiales) {
        this.historiales = historiales;
    }

}
