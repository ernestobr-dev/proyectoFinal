package com.example.proyecto_final.Pantallas.Agenda;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_final.Pantallas.AltaEvento.NuevoEvento;
import com.example.proyecto_final.R;
import com.example.proyecto_final.Back.Respuestas.RespuestasAgenda.RespuestaBuscarEvento;
import com.example.proyecto_final.Back.Routes.AgendaRoutes;
import com.example.proyecto_final.Back.models.Agenda.BuscarEvento;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgendaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgendaFragment extends Fragment {

    // TODO: ELEMENTOS NECESARIOS
    private TextView elegirFecha;
    private Button buscarEvento,botonOpciones;
    private RecyclerView mostrarListado;
    private ArrayList<String> listado;
    private Call<RespuestaBuscarEvento> respuestaBuscarEvento;
    private DatePickerDialog.OnDateSetListener cuadroFecha;
    private AlertDialog.Builder cuadroDialogo;

    // TODO: VARIABLES
    private String dni;
    private int anio,mes,dia;
    private GregorianCalendar calendario;

    // TODO: CLASES NECESARIAS EN LA PANTALLA DE LA AGENDA
    private AdaptadorEventos adaptadorEventos;
    private AgendaRoutes rutaBuscar;
    private Call<RespuestaBuscarEvento> eventosAgenda;






     // TODO: ELIMINAR EVENTO
    private void eliminarEvento(){

    }

    public AgendaFragment(String dni) {
        this.dni = dni;
    }


    public static AgendaFragment newInstance(String dni) {
        AgendaFragment fragment = new AgendaFragment(dni);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_agenda, container, false);

        elegirFecha = (TextView)vista.findViewById(R.id.elegirFecha);
        buscarEvento = (Button) vista.findViewById(R.id.buscarEventos);
        botonOpciones = (Button) vista.findViewById(R.id.nuevoEvento);
        mostrarListado = (RecyclerView) vista.findViewById(R.id.eventos);
        mostrarListado.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));


        calendario = new GregorianCalendar();
        anio = calendario.get(GregorianCalendar.YEAR);
        mes = calendario.get(GregorianCalendar.MONTH);
        dia = calendario.get(GregorianCalendar.DAY_OF_MONTH);


        elegirFecha.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {

                cuadroFecha = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int anio, int mes, int dia) {

                        elegirFecha.setText(anio+"-"+(mes+1)+"-"+dia);

                    }
                };
                DatePickerDialog dialogo = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light,cuadroFecha,anio,mes,dia);
                dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogo.show();

            }
        });


        buscarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: COMPRUEBO QUE HA ELEGIDO UNA FECHA
                if(!elegirFecha.getText().toString().equals(getString(R.string.eligirFecha))){


                    // TODO: SI HA ELEIGO UNA FECHA, LLAMO AL METODO PARA BUSCAR LAS FECHAS
                   buscar(vista);

                }
                // TODO: EN CASO CONSTRARIO,  MUESTRO UN MENSAJE AVISANDO AL USUARIO DE QUE EL TIENE QUE ELEGIR UNA FECHA
                else{
                    Toasty.warning(getContext(),"Tiene que elegir una fecha",Toast.LENGTH_SHORT,true).show();
                }

            }
        });

        // TODO: AL PULSAR EN EL ICONO DE OPCIONES, EL USUARIO VE UNA VENTANA DONDE PODER AÑADIR UN NUEVE EVENTO
        botonOpciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NuevoEvento nuevo = new NuevoEvento(dni);
                nuevo.show(getChildFragmentManager(),"Nuevo Evento");

            }
        });

        return vista;
    }

    // TODO: MÉTODO PARA BUSCAR LOS EVENTOS DE ESA FECHA
    private void buscar(View vista){

        String fechaQuery,
                mesFecha = elegirFecha.getText().toString().split("-")[1],
                diaFecha = elegirFecha.getText().toString().split("-")[2];

        if(Integer.parseInt(mesFecha)<10 && Integer.parseInt(diaFecha)<10){
            fechaQuery = elegirFecha.getText().toString().split("-")[0]+"-0"+elegirFecha.getText().toString().split("-")[1]+"-0"+elegirFecha.getText().toString().split("-")[2];

        }
        else if(Integer.parseInt(mesFecha)<10){
            fechaQuery = elegirFecha.getText().toString().split("-")[0]+"-0"+elegirFecha.getText().toString().split("-")[1]+"-"+elegirFecha.getText().toString().split("-")[2];
        }
        else if(Integer.parseInt(diaFecha)<10){
            fechaQuery = elegirFecha.getText().toString().split("-")[0]+"-"+elegirFecha.getText().toString().split("-")[1]+"-0"+elegirFecha.getText().toString().split("-")[2];
        }
        else{
            fechaQuery = elegirFecha.getText().toString();
        }

        listado = new ArrayList<String>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://proyectomercerosello.tk/back/agenda/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        rutaBuscar = retrofit.create(AgendaRoutes.class);
        respuestaBuscarEvento = rutaBuscar.listadoEventos(fechaQuery.toString(),dni);
        respuestaBuscarEvento.enqueue(new Callback<RespuestaBuscarEvento>() {
            @Override
            public void onResponse(Call<RespuestaBuscarEvento> call, Response<RespuestaBuscarEvento> response) {



                switch (response.code()){

                    case 200:

                        for(BuscarEvento e: response.body().getBuscarEvento()){

                            listado.add(e.getTipo()+'-'+e.getNombre()+'-'+e.getId());

                        }
                        adaptadorEventos = new AdaptadorEventos(listado,vista.getContext(),getChildFragmentManager(),fechaQuery);
                        mostrarListado.setAdapter(adaptadorEventos);


                        break;

                    case 204:

                        Toasty.error(vista.getContext(),"NO HAY DATOS QUE MOSTRAR", Toast.LENGTH_SHORT,true).show();

                        break;

                }
            }

            @Override
            public void onFailure(Call<RespuestaBuscarEvento> call, Throwable t) {
                Toasty.error(vista.getContext(),"NO HAY DATOS QUE MOSTRAR", Toast.LENGTH_SHORT,true).show();

            }
        });

    }

}