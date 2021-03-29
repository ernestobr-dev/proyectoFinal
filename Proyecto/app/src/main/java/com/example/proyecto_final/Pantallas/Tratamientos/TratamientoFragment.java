package com.example.proyecto_final.Pantallas.Tratamientos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.proyecto_final.Back.Respuestas.RespuestasTratamientos.RespuestaBuscarTratamientos;
import com.example.proyecto_final.Back.Routes.TratamientosRoutes;
import com.example.proyecto_final.R;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TratamientoFragment extends Fragment {

    private View view,viewBuscador;
    private Button buttonBuscar;
    private Spinner spinnerPacientes;

    private Call<RespuestaBuscarTratamientos> respuesta;
    private TratamientosRoutes ruta;


    public TratamientoFragment() {
        // Required empty public constructor
    }



    public static TratamientoFragment newInstance( ) {
        TratamientoFragment fragment = new TratamientoFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_tratamiento, container, false);
        viewBuscador = (View) view.findViewById(R.id.buscador);

        buttonBuscar = (Button) viewBuscador.findViewById(R.id.buttonBuscador);
        spinnerPacientes = (Spinner) viewBuscador.findViewById(R.id.spinnerBuscador);

        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }


    /**
     * Método con el que realizar la búsqueda de tratamientos de un paciente
     */
    private void consulta(String idPaciente){

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://proyectomercerosello.tk/back/historial/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ruta = retrofit.create(TratamientosRoutes.class);
        respuesta = ruta.buscarTratamientos(idPaciente);
        respuesta.enqueue(new Callback<RespuestaBuscarTratamientos>() {
            @Override
            public void onResponse(Call<RespuestaBuscarTratamientos> call, Response<RespuestaBuscarTratamientos> response) {




            }

            @Override
            public void onFailure(Call<RespuestaBuscarTratamientos> call, Throwable t) {

                Toasty.error(getContext(),"NO HAY TRATAMIENTOS DISPONIBLES PARA ESTE PACIENTE", Toast.LENGTH_SHORT,true).show();

            }
        });

    }

}