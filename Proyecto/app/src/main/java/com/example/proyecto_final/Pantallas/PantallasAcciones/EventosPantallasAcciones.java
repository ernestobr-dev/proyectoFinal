package com.example.proyecto_final.Pantallas.PantallasAcciones;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyecto_final.Back.Respuestas.RespuestasPacientes.RespuestaBuscarPaciente;
import com.example.proyecto_final.Back.Routes.PacientesRoutes;
import com.example.proyecto_final.Back.models.Pacientes.Paciente;
import com.example.proyecto_final.Pantallas.PantallasAcciones.Gestion.GestionActivity;
import com.example.proyecto_final.R;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventosPantallasAcciones {

    public static View.OnClickListener abrirPantallas = new View.OnClickListener() {

        Intent gestionIntent,informacionIntent;

        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.buttonGestion:

                    gestionIntent = new Intent(v.getContext(),GestionActivity.class);
                    v.getContext().startActivity(gestionIntent);

                    break;

                case R.id.buttonInformacion:



                    break;

            }

        }
    };


    public static void fillSpinner(Spinner spinner,View view){
        PacientesRoutes rutaBuscarPaciente;
        Call<RespuestaBuscarPaciente> respuesta;


        ArrayList<String> auxiliar = new ArrayList<String>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://proyectomercerosello.tk/back/pacientes/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        rutaBuscarPaciente = retrofit.create(PacientesRoutes.class);
        respuesta = rutaBuscarPaciente.buscarPaciente();


        respuesta.enqueue(new Callback<RespuestaBuscarPaciente>() {
            @Override
            public void onResponse(Call<RespuestaBuscarPaciente> call, Response<RespuestaBuscarPaciente> response) {

                // TODO: ARRAY AUXILIAR DONDOE ALMACENAR LOS DATOS RECIBIDOS DE LA BASE DE DATOS


                if(response.code() == 204){
                    Toasty.error(view.getContext(),"Error con los datos de la base de datos", Toast.LENGTH_SHORT,true).show();

                }
                else{
                    for(Paciente paciente: response.body().getPacientes()){
                        auxiliar.add(paciente.getId()+"-"+paciente.getNombre());
                    }




                    // TODO: OBJETO ARRAY ADAPTER PARA PODER MOSTRAR LOS DATOS EN EL SPINNER
                    ArrayAdapter<String> adaptador = new ArrayAdapter<String>(view.getContext(), R.layout.support_simple_spinner_dropdown_item, auxiliar);
                    spinner.setAdapter(adaptador);

                }

            }

            @Override
            public void onFailure(Call<RespuestaBuscarPaciente> call, Throwable t) {

            }
        });
    }

}
