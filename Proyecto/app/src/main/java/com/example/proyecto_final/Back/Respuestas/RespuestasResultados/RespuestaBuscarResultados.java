package com.example.proyecto_final.Back.Respuestas.RespuestasResultados;

import java.util.List;

import com.example.proyecto_final.Back.models.Resultados.Resultado;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RespuestaBuscarResultados {

    @SerializedName("resultados")
    @Expose
    private List<Resultado> resultados = null;

    public List<Resultado> getResultados() {
        return resultados;
    }

    public void setResultados(List<Resultado> resultados) {
        this.resultados = resultados;
    }


}
