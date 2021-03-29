package com.example.proyecto_final.Pantallas.PantallasAcciones.Gestion.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.proyecto_final.Pantallas.Historial.HistorialFragment;
import com.example.proyecto_final.Pantallas.Resultados.ResultadosFragment;
import com.example.proyecto_final.Pantallas.Tratamientos.TratamientoFragment;
import com.example.proyecto_final.R;

import java.util.ArrayList;


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.stringHistoriales,R.string.stringResultados};
    private final Context mContext;
    private ArrayList<Fragment> screens = new ArrayList<Fragment>();

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        screens.add(new HistorialFragment());
        screens.add(new ResultadosFragment());

        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        return screens.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return screens.size();
    }
}