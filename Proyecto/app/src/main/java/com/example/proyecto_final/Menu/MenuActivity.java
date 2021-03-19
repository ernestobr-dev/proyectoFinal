package com.example.proyecto_final.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.FrameMetrics;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.proyecto_final.Pantallas.Agenda.AgendaFragment;
import com.example.proyecto_final.Pantallas.FragmentIG.TabbedFragment;
import com.example.proyecto_final.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    // TODO: ELEMENOS NECESARIOS EN LA PANTALLA PRINCPAL
    private BottomNavigationView menu; // TODO: BARRA DE NAVEGACIÓN INFERIOR
    private ViewPager viewPager; // TODO: ELEMENTO PARA PODER MOSTRAR LA INFORMACION AL CLICAR EN CADA UNO DE LOS ICONOS INFEREIORES

    // TODO: VARIABLES
    private String dni; // TODO: VARIABLE DONDE SE RECOGE EL DNI DEL USUARIO LOGUEADO O REGISTRADO


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        menu = (BottomNavigationView)findViewById(R.id._menu);
        menu.setOnNavigationItemSelectedListener(this);

        dni = getIntent().getExtras().getString("dniUsuario");

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.opAgenda:
                mostrarFragmentos(new AgendaFragment(dni));
                return true;

            case R.id.opInformacion:

                mostrarFragmentos(new TabbedFragment(getResources().getStringArray(R.array.seccionesInfomracion),
                        getResources().getStringArray(R.array.seccionesInfomracion).length,
                        "informacion"));
                return true;



        }

        return false;


    }

    public void mostrarFragmentos(Fragment fragment){

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

    }


}