package com.example.proyecto_final.Pantallas.AltaEvento;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.proyecto_final.R;
import com.example.proyecto_final.Back.Respuestas.RespuestasAgenda.RespuestaAltaEvento;
import com.example.proyecto_final.Back.Routes.AgendaRoutes;
import com.example.proyecto_final.Back.models.Agenda.AltaEventoAgenda;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NuevoEvento extends AppCompatDialogFragment {

    // TODO: ELEMENTOS NECESARIOS
    private EditText tipo,nombre;
    private TextView fecha;
    private DatePickerDialog.OnDateSetListener cuadroFecha;


    // TODO: VARIABLES
    private String dniUsuario;
    private int anio,mes,dia;

    // TODO: CLASES NECESARIAS
    private GregorianCalendar calendario;
    private Call<RespuestaAltaEvento> respuesta;
    private AltaEventoAgenda nuevo;
    private AgendaRoutes rutaAlta;

    public NuevoEvento(String dniUsuario){
        this.dniUsuario = dniUsuario;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View vista = inflater.inflate(R.layout.layout_altaevento,null);

        calendario = new GregorianCalendar();
        anio = calendario.get(GregorianCalendar.YEAR);
        mes = calendario.get(GregorianCalendar.MONTH);
        dia = calendario.get(GregorianCalendar.DAY_OF_MONTH);

        tipo = (EditText)vista.findViewById(R.id.tipoModificarEvento);
        nombre = (EditText)vista.findViewById(R.id.nombreModificarEvento);
        fecha = (TextView) vista.findViewById(R.id.fechaAltaEvento);


        nuevo = new AltaEventoAgenda();

        mostrarCalendario();


        builder.setView(vista)
                .setTitle("Añadir nuevo evento")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: AÑADIR PARA OCULTAR EL CUADRO DE DIALOGO
                    }
                })
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: AÑADIR MÉTODO DE ALTA EN LA BASE DE DATOS

                        if(comprobarCampos() && comprobarFecha(calendario)){

                            nuevo.setTipo(tipo.getText().toString());
                            nuevo.setNombre(nombre.getText().toString());
                            nuevo.setFecha(transformarFecha());
                            nuevo.setUsuario(dniUsuario);
                            consulta(nuevo);

                        }

                    }
                });

        return builder.create();
    }

    // TODO: METODO PARA TRANSFORMAR LA FECHA ELEGIDA POR EL USUARIO EN UN FORMATO QUE ENTIENDA LA BASE DE DATOS
    private String transformarFecha(){

        String fechaQuery;
        String cogerFecha = fecha.getText().toString().split("-")[1];
        if(Integer.parseInt(cogerFecha)<10){
            fechaQuery = fecha.getText().toString().split("-")[0]+"-0"+fecha.getText().toString().split("-")[1]+"-"+fecha.getText().toString().split("-")[2];
        }
        else{
            fechaQuery = fecha.getText().toString();
        }

        return fechaQuery;
    }

    // TODO: MÉTODO PARA MOSTRAR EL CALENDARIO Y QUE EL USUARIO ELIGA FECHA
    private void mostrarCalendario(){
        fecha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                cuadroFecha = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int anio, int mes, int dia) {

                        fecha.setText(anio+"-"+(mes+1)+"-"+dia);

                    }
                };
                DatePickerDialog dialogo = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light,cuadroFecha,anio,mes,dia);
                dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogo.show();

            }
        });
    }


    // TODO: METODO PARA COMPROBAR QUE TODOS LOS CAMPOS HAN SIDO RELLENADOS CORRECTAMENTE
    private boolean comprobarCampos(){

        if(tipo.getText().toString().isEmpty()){

            Toasty.warning(getContext(),"EL CAMPO 'TIPO DE EVENTO' ESTA VACIO", Toast.LENGTH_SHORT,true).show();
            return false;

        }
        else if(nombre.getText().toString().isEmpty()){

            Toasty.warning(getContext(),"EL CAMPO 'NOMBRE DE EVENTO' ESTA VACIO", Toast.LENGTH_SHORT,true).show();
            return false;

        }
        else if(fecha.getText().toString().equals(getString(R.string.eligirFecha))){

            Toasty.warning(getContext(),"TIENE QUE ELEGIR UNA FECHA", Toast.LENGTH_SHORT,true).show();
            return false;

        }
        else{
            return true;
        }

    }

    // TODO: METODO PARA COMPROBAR QUE LA FECHA ELEGIDA NO ES ANTERIOR A LA FECHA ACTUAL
    private boolean comprobarFecha(GregorianCalendar calendario){

        boolean bandera = false;

        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
        try {
            Date fecha1 = dateFormat.parse(String.valueOf(calendario.get(GregorianCalendar.YEAR)+"-"+(calendario.get(GregorianCalendar.MONTH)+1)+"-"+calendario.get(GregorianCalendar.DAY_OF_MONTH)));
            Date fecha2 = dateFormat.parse(fecha.getText().toString());

           if(fecha2.after(fecha1) || fecha2.equals(fecha1)){

               bandera = true;
           }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return bandera;

    }

    // TODO: METODO PARA AÑADIR EL EVENTO A LA BASE DE DATOS
    private void consulta(AltaEventoAgenda nuevo){

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://proyectomercerosello.tk/back/agenda/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        rutaAlta = retrofit.create(AgendaRoutes.class);
        respuesta = rutaAlta.nuevoEvento(nuevo);
        respuesta.enqueue(new Callback<RespuestaAltaEvento>() {
            @Override
            public void onResponse(Call<RespuestaAltaEvento> call, Response<RespuestaAltaEvento> response) {

                switch (response.code()){

                    case 200:

                        Toasty.success(getActivity().getApplicationContext(),response.body().getAltaAgenda().toString(),Toast.LENGTH_SHORT,true).show();

                        break;

                    case 299:

                        Toasty.error(getContext(),response.body().getAltaAgenda().toString(),Toast.LENGTH_SHORT,true).show();


                        break;
                }

            }

            @Override
            public void onFailure(Call<RespuestaAltaEvento> call, Throwable t) {
                Toasty.error(getContext(),"ERROR AL REALIZAR EL ALTA",Toast.LENGTH_SHORT,true).show();

            }
        });

    }

}
