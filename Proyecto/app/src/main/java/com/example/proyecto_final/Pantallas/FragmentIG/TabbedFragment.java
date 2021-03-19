package com.example.proyecto_final.Pantallas.FragmentIG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyecto_final.Back.Respuestas.RespuestasHistorial.RespuestaBuscarHistorial;
import com.example.proyecto_final.Back.Respuestas.RespuestasPacientes.RespuestaBuscarPaciente;
import com.example.proyecto_final.Back.Routes.HistorialRoutes;
import com.example.proyecto_final.Back.Routes.PacientesRoutes;
import com.example.proyecto_final.Back.models.Pacientes.Paciente;
import com.example.proyecto_final.R;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TabbedFragment extends Fragment {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ControladorTabbed controlador;


    // TODO: ELEMENTOS NECESARIOS EN PANTALLA
    private Spinner listadoDePacientes;
    private Button buscar;



    // TODO: PARÁMETROS QUE PASO AL CONTROLADOR Y QUE RECIBO DEL MENU
    private String[] secciones;
    private int numero;
    private String nombrePantalla;






    // TODO: RECOGE EL ARRAY CON LOS NOMBRES DE LAS SECCIONES QUE TIENE CADA PANTALLA
    public TabbedFragment(String[] secciones, int numero,String nombrePantalla) {

        this.secciones = secciones;
        this.numero = numero;
        this.nombrePantalla = nombrePantalla;



    }

    public static TabbedFragment newInstance(String[] secciones, int numero,String nombrePantalla) {
        TabbedFragment fragment = new TabbedFragment(secciones,numero,nombrePantalla);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_tabbed, container, false);

        tabLayout = (TabLayout)vista.findViewById(R.id.tabLayout);
        viewPager = (ViewPager)vista.findViewById(R.id.viewPager);


        // TODO: COMPROBARCION DE LA PANTALLA EN LA QUE ESTÁ EL USUARIO
        if(nombrePantalla.equals("informacion")){
            // TODO: PASO AL CONTROLADOR LOS DATOS NECESARIOS POR PARAMETRO
            controlador = new ControladorTabbed(getFragmentManager(),getResources().getStringArray(R.array.seccionesInfomracion),
                    getResources().getStringArray(R.array.seccionesInfomracion).length,
                    nombrePantalla,
                    vista
            );
        }
        else{
            // TODO: PASO AL CONTROLADOR LOS DATOS NECESARIOS POR PARAMETRO
            controlador = new ControladorTabbed(getFragmentManager(),getResources().getStringArray(R.array.seccionesGestion),
                    getResources().getStringArray(R.array.seccionesGestion).length,
                    nombrePantalla,
                    vista
            );
        }


        viewPager.setAdapter(controlador);
        tabLayout.setupWithViewPager(viewPager);

        return vista;
    }






}