package com.example.proyecto_final.Pantallas.Resultados;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.proyecto_final.R;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class AdaptadorResultados extends BaseAdapter {

    private Context contexto;

    private TextView nombre;
    private Button pdf;

    private ArrayList<String> datos;

    private Intent showPdf;

    public AdaptadorResultados(Context contexto,ArrayList<String> datos){
        this.contexto = contexto;
        this.datos = datos;
    }


    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(contexto);
        View vista = inflater.inflate(R.layout.lista_resultados,null);


        nombre = (TextView) vista.findViewById(R.id.mostrarNombre);
        pdf = (Button) vista.findViewById(R.id.btnPdf);

        nombre.setText(datos.get(position).toString().split("-")[1]);
        pdf.setText(datos.get(position).toString().split("-")[0]);

        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: AÑADIR PARA ABRIR UNA NUEVA VENTANA CON EL PDF
                Uri link = Uri.parse(pdf.getText().toString());
                Intent i = new Intent(Intent.ACTION_VIEW,link);
                Log.i("TAG", pdf.getText().toString());
                startActivity(contexto,i,null);
            }
        });

        return vista;
    }

    


}
