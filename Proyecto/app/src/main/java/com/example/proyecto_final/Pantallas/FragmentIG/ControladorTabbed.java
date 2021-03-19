package com.example.proyecto_final.Pantallas.FragmentIG;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.proyecto_final.Back.models.Pacientes.Paciente;
import com.example.proyecto_final.Pantallas.Agenda.AgendaFragment;
import com.example.proyecto_final.Pantallas.Historial.HistorialFragment;
import com.example.proyecto_final.Pantallas.Resultados.ResultadosFragment;

public class ControladorTabbed<View> extends FragmentPagerAdapter {

    // TODO: NOMBRE DE LAS PANTALLAS A LAS QUE PUEDE ACCEDER EL USUARIO
    private final String[] pantallas = {"gestion","informacion"};


    private String[] secciones; // TODO: SECCIONES MANDAS DESDE TABBED FRAGMENT. SON LAS SECCIONES QUE CONTIENE CADA UNA DE LAS PANTALLAS
    private int numero; // TODO: NUMERO DE SECCIONES QUE TIEENE CADA UNA DE LAS PANTALLAS PRINCIPALES (GESTION E INFORMACIÓN)
    private String nombrePantalla; // TODO: NOMBRE DE LA PANTALLA PARA SABER QUE PANTALLAS MOSTRAR
    private View vistaFragmentTabbed;


    public ControladorTabbed(@NonNull FragmentManager fm,String[] secciones,int numero,String nombrePantalla,View vistaFragmentTabbed) {
        super(fm,numero);
        this.secciones = secciones;
        this.numero = numero;
        this.nombrePantalla = nombrePantalla;
        this.vistaFragmentTabbed = vistaFragmentTabbed;

    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        // TODO: DEPENDIENDO DE LA PANTALLA A LA QUE QUIERE ENTRAR EN EL USUARIO, LOS TITULOS DE LAS SECCIONES CAMBIARÁN

        // TODO: SI LA PANTALLA SELECCIONADA ES LA DE GESTION
        if(nombrePantalla.equals(pantallas[0]) ){

            switch (position){

                case 0:

                    return new AgendaFragment("11111111J");

                case 1:
                    return new AgendaFragment("11111111J");


                default:
                    return  null;


            }

        }

        // TODO: SI LA PANTALLA SELECCIONADA ES LA PANTALLA DE INFORMACIÓN
        else{

            if(position == 0){
                return new HistorialFragment();
            }
            else{
                return new ResultadosFragment();

            }


        }
    }

    @Override
    public int getCount() {
        return numero;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        // TODO: DEVUELVO EL TITULO DEPENDIENDO DE LA PANTALLA EN LA QUE ESTÉ EL USUARIO
        return secciones[position];
    }
}
