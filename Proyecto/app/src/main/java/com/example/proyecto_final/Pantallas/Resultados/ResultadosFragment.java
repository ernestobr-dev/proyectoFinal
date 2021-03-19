package com.example.proyecto_final.Pantallas.Resultados;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyecto_final.Back.Respuestas.RespuestasPacientes.RespuestaBuscarPaciente;
import com.example.proyecto_final.Back.Respuestas.RespuestasResultados.RespuestaBuscarResultados;
import com.example.proyecto_final.Back.Routes.PacientesRoutes;
import com.example.proyecto_final.Back.Routes.ResultadosRoutes;
import com.example.proyecto_final.Back.models.Pacientes.Paciente;
import com.example.proyecto_final.Back.models.Resultados.Resultado;
import com.example.proyecto_final.Pantallas.FragmentIG.TabbedFragment;
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


public class ResultadosFragment extends Fragment {



    // TODO: VISTA DE LA PANTALLA ACTUAL
    private View view;

    // TODO: ELEMENTO DONDE SE MUESTRAN LOS ICONOS DE LOS RESULTADOS DE LAS PRUEBAS
    private GridView iconosResultados;

    // TODO: CLASES NECESARIAS PARA PODER REALIZAR LA CONSULTA A LA BASE DE DATOS
    private ResultadosRoutes ruta;
    private Call<RespuestaBuscarResultados> respuesta;

    // TODO: ARRA LIST CON EL QUE RECOGO LOS DATOS QUE RECIBO DE LA BASE DE DATOS
    private ArrayList<String> datosRecogo;

    // TODO: BOTON DE LA VISTA TABBED
    private Button buttonFindResultados;

    // TODO: LISTA DE PACIENTES
    private Spinner spinner;

    // TODO: ADAPTADOR DE LA VISTA DE LOS ICONOS DE LOS RESULTADOS
    private AdaptadorResultados adaptador;


    public ResultadosFragment() {

    }


    public static ResultadosFragment newInstance() {
        ResultadosFragment fragment = new ResultadosFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // TODO: VISTA DE LA PANTALLA ACTUAL
        view = inflater.inflate(R.layout.fragment_resultados, container, false);


        buttonFindResultados = (Button) view.findViewById(R.id.buscarResultados);
        iconosResultados = (GridView) view.findViewById(R.id.tablaResultados);
        spinner = (Spinner) view.findViewById(R.id.listaPacientesResultados);

        rellenarSpinner(view);


        buttonFindResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: AL HACER CLICK EN EL BOTON, RECOGO EL PACIENTE QUE HA EL USUARIO PARA PODER REALIZAR LA CONSULTA
                spinner = (Spinner)view.findViewById(R.id.listaPacientesResultados);
                consulta(spinner.getSelectedItem().toString().split("-")[0]);
                Log.i("resultados", "Entro en la parte de resultados");

            }
        });

        return view;
    }

    // TODO: MÉTODO CON EL QUE HAGO LA CONSULTA A LA BASE DE DATOS
    private void consulta(String idPaciente) {

        datosRecogo = new ArrayList<String>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://proyectomercerosello.tk/back/resultados/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

         ruta = retrofit.create(ResultadosRoutes.class);
         respuesta = ruta.buscarResultado(idPaciente);
         respuesta.enqueue(new Callback<RespuestaBuscarResultados>() {
             @Override
             public void onResponse(Call<RespuestaBuscarResultados> call, Response<RespuestaBuscarResultados> response) {

                 switch (response.code()){

                     case 200:

                         Log.i("respuesta", String.valueOf(response.body().getResultados().size()));

                         // TODO: RECORRO CADA UNO DE LOS RESULTADOS Y LOS AÑADO A LA LISTA QUE LUEGO PASO COMO PARÁMETRO AL MÉTODO DE ACCIONES
                         for(Resultado r : response.body().getResultados()){

                            datosRecogo.add(r.getArchivo()+"-"+r.getNombre());

                         }

                         // TODO: OBJETO DE LA CLASE ADAPATADOR PARA PODER MOSTRAR AL USUARIO EL ICONO CON EL NOMBRE DEL RESULTADO
                         adaptador = new AdaptadorResultados(getContext(),datosRecogo);

                         // TODO: AÑADO EL ADAPTADOR AL ELEMENTOS GRIDVIEW DE ESTA PANTALLA
                         iconosResultados.setAdapter(adaptador);


                         break;

                     case 299:

                         Toasty.error(getContext(),"NO HAY RESULTADOS DISPONIBLES PARA ESTE PACIENTE", Toast.LENGTH_SHORT,true).show();

                         break;


                 }


             }

             @Override
             public void onFailure(Call<RespuestaBuscarResultados> call, Throwable t) {

             }
         });

    }


    private void rellenarSpinner(View view){
        // TODO: CLASES NECESARIAS PARA PODER REALIZAR LA CONSULTA A LA BASE DE DATOS Y RELLENAR EL SPINNER CON LOS PACIENTES
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


                if(response.code() == 299){
                    Toasty.error(view.getContext(),"ERROR AL CONECTAR CON LA BASE DE DATOS", Toast.LENGTH_SHORT,true).show();

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