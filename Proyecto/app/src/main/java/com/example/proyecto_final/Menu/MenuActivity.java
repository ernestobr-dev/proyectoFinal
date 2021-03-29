package com.example.proyecto_final.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.proyecto_final.Pantallas.Agenda.AgendaFragment;
import com.example.proyecto_final.Pantallas.Calculadora.CalculadoraFragment;

import com.example.proyecto_final.Pantallas.PantallasAcciones.AccionesFragment;
import com.example.proyecto_final.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {

    // TODO: ELEMENOS NECESARIOS EN LA PANTALLA PRINCPAL
    private BottomNavigationView menu; // TODO: BARRA DE NAVEGACIÓN INFERIOR
    private ViewPager viewPager; // TODO: ELEMENTO PARA PODER MOSTRAR LA INFORMACION AL CLICAR EN CADA UNO DE LOS ICONOS INFEREIORES

    // TODO: VARIABLES
    private String dni; // TODO: VARIABLE DONDE SE RECOGE EL DNI DEL USUARIO LOGUEADO O REGISTRADO


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,
                    new AgendaFragment(dni)).commit();
        }

        dni = getIntent().getExtras().getString("dniUsuario");

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.opAgenda:
                            selectedFragment = new AgendaFragment(dni);
                            break;
                        case R.id.opCalculadora:
                            selectedFragment = new CalculadoraFragment();
                            break;

                        case R.id.opAcciones:
                            selectedFragment = new AccionesFragment();
                            break;

                        case R.id.opBuscador:
                            /**
                             * Añadir fragmento del buscador
                             */
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,
                            selectedFragment).commit();
                    return true;
                }
            };

}