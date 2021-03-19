package com.example.proyecto_final.Back.Respuestas.RespuestasPacientes;


import java.util.List;

import com.example.proyecto_final.Back.models.Pacientes.Paciente;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RespuestaBuscarPaciente {

    @SerializedName("pacientes")
    @Expose
    private List<Paciente> pacientes = null;

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

}
