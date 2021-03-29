package com.example.proyecto_final.Pantallas.ModificarEvento;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.proyecto_final.R;
import com.example.proyecto_final.Back.Respuestas.RespuestasAgenda.RespuestaModificarEvento;
import com.example.proyecto_final.Back.Routes.AgendaRoutes;
import com.example.proyecto_final.Back.models.Agenda.ModEvento;
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

public class UpdateEvento extends AppCompatDialogFragment {

    // TODO: VARIABLES CON LAS QUE SE RECOGE LOS DATOS DE LA PANTALLA DE LA AGENDA
    private String tipoRecibo, nombreRecibo, fechaRecibo, dniUsuario;
    private int dia,anio,mes;
    private boolean bandera =false;;

    // TODO: ELEMENTOS DE LA PANTALLA
    private TextView fecha;
    private EditText tipo,nombre;
    private DatePickerDialog.OnDateSetListener cuadroFecha;


    // TODO: CLASES NECESARIAS
    private GregorianCalendar calendario;
    private AgendaRoutes rutaModificar;
    private ModEvento modificaciones,datosEvento;
    private Call<RespuestaModificarEvento> respuestaModificar;

    // TODO: CONSTRUCTOR DONDE RECIBIR TODOS LOS PARÁMETROS PARA RELLENAR LOS CAMPOS
    public UpdateEvento(ModEvento datosRecibo){
        this.datosEvento = datosRecibo;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        LayoutInflater inflater = getActivity().getLayoutInflater();
        View vista = inflater.inflate(R.layout.layout_modevento,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(vista.getContext());

        calendario = new GregorianCalendar();

        rellenarCampos(vista);// TODO: RELLENAR CAMPOS DE LA PANTALLA

        accionFecha(); // TODO: MUESTRA EL CALENDARIO QUE EL USUARIO USARÁ PARA ELEGIR LA FECHA NUEVA DEL EVENTO

        modificaciones = new ModEvento(); // TODO: OBJETO DE LA CLASE ALTA EVENTO QUE RECOGERÁ LOS NUEVOS DATOS DEL EVENTO

        accionFecha(); // TODO: MÉTODO USADO PARA MOSTRAR EL CALENDARIO




            builder.setView(vista)
                    .setTitle("Modificar evento")
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if(comprobarCampos(vista)){
                                modificaciones.setTipo(tipo.getText().toString());
                                modificaciones.setNombre(nombre.getText().toString());
                                modificaciones.setFecha(transformarFecha());
                                modificaciones.setId(datosEvento.getId());
                                bandera = true;
                                mostrarConfirmacion(modificaciones);
                            }


                        }
                    });

        return builder.create();
    }


    private void rellenarCampos(View vista){

        tipo = (EditText) vista.findViewById(R.id.tipoModificarEvento);
        nombre = (EditText)vista.findViewById(R.id.nombreModificarEvento);
        fecha = (TextView) vista.findViewById(R.id.fechaModificarEvento);

        // TODO: LOS DATOS RECOGIDOS EN EL OBJETO DE LA CLASE SON USADOS AHORA PARA RELLENAR LOS CAMPOS QUE EL USUARIO POSTERIORMENTE MODIFICARÁ
        tipo.setText(String.valueOf(datosEvento.getTipo().toString().split(":")[1]));
        Log.i("TAG", datosEvento.getTipo());
        nombre.setText(String.valueOf(datosEvento.getNombre().split(":")[1]));
        fecha.setText(datosEvento.getFecha());


    }


    // TODO: METODO CON EL EVENTO CLIC DE LA FECHA
    private void accionFecha(){

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
    private boolean comprobarCampos(View vista){

        if(tipo.getText().toString().isEmpty()){

            Toasty.warning(vista.getContext(),"EL CAMPO 'TIPO DE EVENTO' ESTA VACIO", Toast.LENGTH_SHORT,true).show();
            return false;

        }
        else if(nombre.getText().toString().isEmpty()){

            Toasty.warning(vista.getContext(),"EL CAMPO 'NOMBRE DE EVENTO' ESTA VACIO", Toast.LENGTH_SHORT,true).show();
            return false;

        }
        else if(fecha.getText().toString().equals(getString(R.string.eligirFecha))){

            Toasty.warning(vista.getContext(),"TIENE QUE ELEGIR UNA FECHA", Toast.LENGTH_SHORT,true).show();
            return false;

        }
        else{
            return true;
        }

    }



    // TODO: METODO PARA TRANSFORMAR LA FECHA ELEGIDA POR EL USUARIO EN UN FORMATO QUE ENTIENDA LA BASE DE DATOS
    private String transformarFecha(){

        String fechaQuery;
        String cogerFecha = fecha.getText().toString().split("-")[1];
        if(Integer.parseInt(cogerFecha)<10 && !cogerFecha.startsWith("0")){
            fechaQuery = fecha.getText().toString().split("-")[0]+"-0"+fecha.getText().toString().split("-")[1]+"-"+fecha.getText().toString().split("-")[2];
        }
        else{
            fechaQuery = fecha.getText().toString();
        }

        return fechaQuery;
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


    // TODO: MÉTODO CON EL QUE SE REALIZA LA ACTUALIZACIÓN DE LOS DATOS EN LA BASE DE DATOS
    private void actualizarDatos(ModEvento nuevosDatos,AlertDialog dialogo){

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://proyectomercerosello.tk/back/agenda/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        rutaModificar = retrofit.create(AgendaRoutes.class);
        respuestaModificar = rutaModificar.modificarEvento(nuevosDatos);
        Log.i("cuerpo", nuevosDatos.getFecha());
        respuestaModificar.enqueue(new Callback<RespuestaModificarEvento>() {
            @Override
            public void onResponse(Call<RespuestaModificarEvento> call, Response<RespuestaModificarEvento> response) {

                if(response.code() == 204){
                    Log.i("error", "No funciona ");


                }
                else if(response.code() == 200){
                    Log.i("correcto", "Funciona");
                    dialogo.dismiss();

                }

            }

            @Override
            public void onFailure(Call<RespuestaModificarEvento> call, Throwable t) {
                Log.i("error", "No funciona ");
            }
        });
    }

    // TODO: MÉTODO CON EL QUE SE MUESTRA UNA VENTANA PARA QUE EL USUARIO CONFIRME EL CAMBIO DE LOS DATOS DE LA BASE DE DATOS
    private void mostrarConfirmacion(ModEvento nuevosDatos){


        final AlertDialog n = new AlertDialog.Builder(getContext())
                .setTitle("¿ESTA SEGURO DE REALIZAR LOS CAMBIOS?")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Confirmar",null)
                .create();

        n.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {

                Button cerrar = n.getButton(AlertDialog.BUTTON_NEGATIVE);
                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long l) {

                        cerrar.setText("Cerrando ("+((l/1000)+1)+")");
                    }

                    @Override
                    public void onFinish() {

                        if(n.isShowing()){
                            n.dismiss();
                        }

                    }
                }.start();

                Button confirmar = n.getButton(AlertDialog.BUTTON_POSITIVE);
                confirmar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        actualizarDatos(nuevosDatos,n);

                    }
                });

            }
        });

        n.show();


    }
}
