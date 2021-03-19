package com.example.proyecto_final.Back.Respuestas.RespuestasAgenda;

import java.util.List;

import com.example.proyecto_final.Back.models.Agenda.BuscarEvento;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespuestaBuscarEvento {

    @SerializedName("buscarEvento")
    @Expose
    private List<BuscarEvento> buscarEvento = null;

    public List<BuscarEvento> getBuscarEvento() {
        return buscarEvento;
    }

    public void setBuscarEvento(List<BuscarEvento> buscarEvento) {
        this.buscarEvento = buscarEvento;
    }

}
