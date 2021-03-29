package com.example.proyecto_final.Pantallas.PantallasAcciones;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.proyecto_final.R;

import static com.example.proyecto_final.Pantallas.PantallasAcciones.EventosPantallasAcciones.abrirPantallas;


public class AccionesFragment extends Fragment {

    private View viewAcciones;
    private Button btnGestion, btnInformacion;

    public AccionesFragment() {
        // Required empty public constructor
    }


    public static AccionesFragment newInstance() {
        AccionesFragment fragment = new AccionesFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.viewAcciones = inflater.inflate(R.layout.fragment_acciones, container, false);


        btnGestion = (Button) viewAcciones.findViewById(R.id.buttonGestion);
        btnInformacion = (Button) viewAcciones.findViewById(R.id.buttonInformacion);

        btnGestion.setOnClickListener(abrirPantallas);
        btnInformacion.setOnClickListener(abrirPantallas);


        return this.viewAcciones;
    }


}