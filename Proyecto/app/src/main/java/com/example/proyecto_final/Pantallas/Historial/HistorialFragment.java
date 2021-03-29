package com.example.proyecto_final.Pantallas.Historial;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.proyecto_final.Back.Respuestas.RespuestasHistorial.RespuestaBuscarHistorial;
import com.example.proyecto_final.Back.Routes.HistorialRoutes;
import com.example.proyecto_final.Back.models.Historial.Historiales;
import com.example.proyecto_final.Back.models.Pacientes.Paciente;
import com.example.proyecto_final.R;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.proyecto_final.Pantallas.PantallasAcciones.EventosPantallasAcciones.fillSpinner;


public class HistorialFragment extends Fragment {



    // TODO: CLASES NECESARIAS PARA BUSCAR INFORMACIÓN DE LOS HISTORIALES DEL PACIENTE SELECCIONADO
    private HistorialRoutes rutaBuscarHistoriales;
    private Call<RespuestaBuscarHistorial> respuestaBuscarHistorial;

    // TODO: LISTA DONDE EL USUARIO VERÁ LOS HISTORIALES DEL PACIENTE
    private ListView listHistoriales;


    private Button buttonShowHistoriales;// TODO: BOTON DE LA VISTA TABBED
    private Spinner spinnerPacientes; // TODO: SPINNER CON LOS PACIENTES

    private View viewBuscador,viewTabbedFragment;




    public static HistorialFragment newInstance() {
        HistorialFragment fragment = new HistorialFragment();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // TODO: OBJETO DE LA CLASE VIEW CON EL QUE HAGO REFERENCIA AL LAYOUT DE ESTE FRAGMENT
        View vista =inflater.inflate(R.layout.fragment_historial, container, false);

        View viewBuscador = vista.findViewById(R.id.buscador);


        spinnerPacientes = (Spinner) viewBuscador.findViewById(R.id.spinnerBuscador);
        listHistoriales = (ListView) vista.findViewById(R.id.listaHisotoriales);

        Button button = (Button) viewBuscador.findViewById(R.id.buttonBuscador);

        fillSpinner(spinnerPacientes,viewBuscador);

      // TODO: ASIGNO LA ACCIÓN DE BUSCAR LOS HISTORIALES AL BOTON DE BUSQUEDA
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Paciente datos = new Paciente();
                datos.setId(spinnerPacientes.getSelectedItem().toString().split("-")[0]);
                datos.setNombre(spinnerPacientes.getSelectedItem().toString().split("-")[1]);
                buscarHistorial(datos);

            }
        });


        return vista;
    }

    // TODO: MÉTODO PARA BUSCAR EL HISTORIAL O HISTORIALES DEL PACIENTE
    private void buscarHistorial(Paciente datos){

        // TODO: ARRAY AUXILIAR DONDE GUARDAR LOS DATOS DE LOS HISTORIALES PARA LUEGO MOSTRARLOS
        ArrayList<String> aux = new ArrayList<String>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://proyectomercerosello.tk/back/historial/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        rutaBuscarHistoriales = retrofit.create(HistorialRoutes.class);
        respuestaBuscarHistorial = rutaBuscarHistoriales.buscarHistorial(datos.getId());
        respuestaBuscarHistorial.enqueue(new Callback<RespuestaBuscarHistorial>() {
            @Override
            public void onResponse(Call<RespuestaBuscarHistorial> call, Response<RespuestaBuscarHistorial> response) {

                switch (response.code()){

                    case 200:

                        for(Historiales datos : response.body().getHistoriales()){

                            aux.add("FECHA: "+datos.getFecha()+"\nMOTIVO: "+datos.getMotivo());

                        }
                        ArrayAdapter adaptador = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,aux);
                        listHistoriales.setAdapter(adaptador);
                        Log.i("bucle", "Entro en el bucle");



                    case 204:
                        Log.i("299", "Revisa la consulta");
                        break;

                }

            }

            @Override
            public void onFailure(Call<RespuestaBuscarHistorial> call, Throwable t) {
                Log.i("error", "Error a la hora de hacer la consulta");
            }
        });
    }


}