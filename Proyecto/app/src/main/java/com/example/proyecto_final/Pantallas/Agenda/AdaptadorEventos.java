package com.example.proyecto_final.Pantallas.Agenda;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.proyecto_final.Pantallas.ModificarEvento.UpdateEvento;
import com.example.proyecto_final.R;
import com.example.proyecto_final.Back.Respuestas.RespuestasAgenda.RespuestaEliminarEvento;
import com.example.proyecto_final.Back.Routes.AgendaRoutes;
import com.example.proyecto_final.Back.models.Agenda.ModEvento;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdaptadorEventos extends RecyclerView.Adapter<AdaptadorEventos.ViewHolder> {


    // TODO: VARIABLES QUE SON NECESARIAS
    private ArrayList<String> mData;
    private int i = 0;
    private String fechaRecibo;

    // TODO: CLASES QUE SON NECESARIAS
    private LayoutInflater mInflater;
    private Context context;
    private FragmentManager fragment;
    private ModEvento datosRecibo;
    private UpdateEvento update;
    private Call<RespuestaEliminarEvento> eliminar;
    private AgendaRoutes rutaEliminar;



    public AdaptadorEventos(ModEvento datosRecibo){
        this.datosRecibo = datosRecibo;
    }

    public AdaptadorEventos(ArrayList<String> itemlist, Context context,FragmentManager fragment,String fechaRecibo) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemlist;
        this.fragment = fragment;
        this.fechaRecibo = fechaRecibo;
    }

    @NonNull
    @Override
    public AdaptadorEventos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_eventos, null);
        return new AdaptadorEventos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binData(mData);
        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datosRecibo = new ModEvento();
                datosRecibo.setId(mData.get(position).split("-")[2]);
                datosRecibo.setNombre(holder.nombre.getText().toString());
                datosRecibo.setTipo(holder.tipo.getText().toString());
                datosRecibo.setFecha(fechaRecibo);

                generarCuadroDialogo(datosRecibo,position);


            }
        });


    }

    public void setItems(ArrayList<String> items) {
        mData = items;
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nombre,tipo;
        private ImageView imagen;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            tipo = (TextView) itemView.findViewById(R.id.tipo);
            imagen = (ImageView) itemView.findViewById(R.id.imagenEvento);
            datosRecibo = new ModEvento();

        }

        public void binData(ArrayList<String> lista) {

            Log.i("tipo", tipo.getText().toString());
            nombre.setText(nombre.getText().toString()+lista.get(i).split("-")[1]);
            tipo.setText( "Tipo:"+lista.get(i).split("-")[0]);

            i++;

        }
    }

    // TODO: MÉTODO PARA GENERAR EL CUADRO DE DIALOGO
    private void generarCuadroDialogo(ModEvento datos,int posicion){

        // TODO: NOMBRE DE LAS OPCIONES DEL ICONO
        CharSequence[] titulos = {"Modificar evento","Eliminar evento"};
        String tituloOpciones = "Opciones de la Agenda",
                mensaje = "Eliga una de las opciones";

        AlertDialog.Builder alerta = new AlertDialog.Builder(context)
                .setTitle(tituloOpciones)
                .setItems(titulos, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {

                        if(titulos[position].equals("Modificar evento")){

                            mostrarModificarDatos(datos);

                        }
                        else if(titulos[position].equals("Eliminar evento")){

                            ventanaEliminarEvento(datos.getId(), posicion);
                            
                        }

                    }
                })
                ;

        alerta.show();

    }

    // TODO: MÉTODO PARA MOSTRAR LA PANTALLA DE MODIFICAR LOS DATOS
    private void mostrarModificarDatos(ModEvento datos){

        update = new UpdateEvento(datos);

        update.show(this.fragment,"MODIFICAR");

    }


    // TODO: MÉTODO PARA MOSTRAR EL CUADRO DE DIALOGO PARA LA CONFIMACIÓN DE LA ELIMINACION DE DATOS DE LA AGENDA
    private void ventanaEliminarEvento(String idEliminar, int posicion){

        // TODO: CREACIÓN DEL CUADRO DE DIALOGO Y DE SUS OPCIONES
        final AlertDialog ventana = new AlertDialog.Builder(context)
                .setTitle("¿ESTÁ SEGURO DE QUE QUIERE ELIMINAR EL EVENTO?")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Confirmar",null)
                .create();


        // TODO: ENLAZO LAS ACCIONES CON CADA UNO DE LOS BOTONES DEL CUADRO DE DIALOGO
        ventana.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {

                // TODO: DEFINO LOS BOTONES DEL CUADRO DE DIALOGO
                Button confirmar = ventana.getButton(AlertDialog.BUTTON_POSITIVE),
                        cancelar = ventana.getButton(AlertDialog.BUTTON_NEGATIVE);

                // TODO:EN CASO DE CONFIRMAR LA ELIMINCACIÓN DEL EVENTO, SE PROCEDE A REALIZAR LA CONSULTA A LA BASE DE DATOS
                confirmar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eliminarEventos(idEliminar, posicion);

                        ventana.dismiss();
                    }
                });

                // TODO: SI CANCELA LA OPERACIÓN, EL USUARIO VERÁ UN MENSAJE EN PANTALLA MOSTRANDO SU CANCELACIÓN
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toasty.error(context,"ACCIÓN CANCELADA",Toast.LENGTH_SHORT,true).show();
                        ventana.dismiss();
                    }
                });


            }
        });

        ventana.show();

    }

    // TODO: MÉTODO PARA REALIZAR LA CONSULTA DE ELIMINACIÓN DE EVENTOS
    private void eliminarEventos(String id, int posicion){

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://proyectomercerosello.tk/back/agenda/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        rutaEliminar = retrofit.create(AgendaRoutes.class);
        eliminar = rutaEliminar.eliminarEvento(id);
        eliminar.enqueue(new Callback<RespuestaEliminarEvento>() {
            @Override
            public void onResponse(Call<RespuestaEliminarEvento> call, Response<RespuestaEliminarEvento> response) {

                switch (response.code()){

                    case 200:

                        Toasty.success(context,"EVENTO ELIMINADO CORRECTAMENTE", Toast.LENGTH_SHORT,true).show();

                        break;

                    case 299:
                        Toasty.error(context,"EL EVENTO NO HA PODIDO SER ELIMINADO", Toast.LENGTH_SHORT,true).show();
                        break;

                }

            }

            @Override
            public void onFailure(Call<RespuestaEliminarEvento> call, Throwable t) {
                Log.i("error_eliminar", "Error al eliminar");
            }
        });

    }

}