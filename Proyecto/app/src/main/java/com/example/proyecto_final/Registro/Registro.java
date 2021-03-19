package com.example.proyecto_final.Registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto_final.R;
import com.example.proyecto_final.Back.Respuestas.RespuestasUsuario.InsertUsuario;
import com.example.proyecto_final.Back.Routes.MedicoRoutes;
import com.example.proyecto_final.Back.models.Usuario.AltaUsuario;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    // TODO: ELEMENTOS USADOS EN EL DISEÑO DE LA PANTALLA DE REGISTRO
    private EditText dni,email,contrasena,repetirContrasena;
    private Button confirmar,volver;

    // TODO: CLASES NECESARIAS EN EL REGISTRO
    private MedicoRoutes rutaRegistro;
    private Call<InsertUsuario> nuevo;
    private AltaUsuario usuario;
    private Intent principal, login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuario = new AltaUsuario();

        dni = (EditText) findViewById(R.id.dniUsuarioRegistro);
        email = (EditText) findViewById(R.id.emailUsuarioRegistro);
        contrasena = (EditText) findViewById(R.id.contrasenaRegistro);
        repetirContrasena = (EditText)findViewById(R.id.contrasenaComprobacion);

        confirmar  = (Button)findViewById(R.id.botonConfirmarRegistro);
        volver = (Button) findViewById(R.id.botonVolverLogin);

        confirmar.setOnClickListener(this);
        volver.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.botonConfirmarRegistro:

                if(comprobarCamposVacios() && comprobarEmail() && comprobacionDNI() && comprobarLetraDNI()){

                    consulta();

                }

                break;


            case R.id.botonVolverLogin:



                break;

        }

    }

    // TODO: COMPRUEBO QUE EL USUARIO HA RELLENADO TODOS LOS CAMPOS Y QUE LAS CONTRASEÑAS COINCIDEN
    public boolean comprobarCamposVacios(){

        if(email.getText().toString().isEmpty()){

            Toasty.warning(this, getString(R.string.errorEmailVacio), Toast.LENGTH_SHORT,true).show();
            return false;

        }
        else if(dni.getText().toString().isEmpty()){

            Toasty.warning(this, getString(R.string.errorDniVacio), Toast.LENGTH_SHORT,true).show();

            return false;

        }
        else if(contrasena.getText().toString().isEmpty()){

            Toasty.warning(this, getString(R.string.errorContrasenaVacia), Toast.LENGTH_SHORT,true).show();

            return false;

        }
        else if(repetirContrasena.getText().toString().isEmpty()){

            Toasty.warning(this, getString(R.string.errorContrasenaRepeVacia), Toast.LENGTH_SHORT,true).show();

            return false;

        }
        else if(!contrasena.getText().toString().equals(repetirContrasena.getText().toString())){

            Toasty.warning(this, getString(R.string.errorContrasenasNoIguales), Toast.LENGTH_SHORT,true).show();

            return false;

        }
        else{
            return true;
        }
    }

    // TODO: MÉTODO PARA COMPROBAR EMAIL VALIDO
    public boolean comprobarEmail(){

        Pattern patternEmail = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
        Matcher matcherEmail = patternEmail.matcher(email.getText().toString());

        if(matcherEmail.matches()){
            return  true;
        }
        else{
            Toasty.warning(this, getString(R.string.errorFormatoEmail), Toast.LENGTH_SHORT,true).show();
            return false;
        }

    }

    // TODO: COMRPOBAR DNI VALIDO
    public boolean comprobacionDNI(){

        Pattern patterDni = Pattern.compile("[0-9]{8}[A-Z a-z]");
        Matcher matcherDni = patterDni.matcher(dni.getText().toString());

        if(matcherDni.matches()){
            return  true;
        }
        else{
            Toasty.warning(this, getString(R.string.errorFormatoDni), Toast.LENGTH_SHORT,true).show();

            return false;
        }

    }

    // TODO: COMPROBAR LETRA DEL DNI
    public boolean comprobarLetraDNI()
    {
        String letrasDni = "TRWAGMYFPDXBNJZSQVHLCKE";
        String[] datosDni = {dni.getText().toString().substring(0,8),dni.getText().toString().substring(8)};
        int numeros = Integer.parseInt(datosDni[0]),resto;

        resto = numeros%23;

        if(datosDni[1].equals(String.valueOf(letrasDni.charAt(resto)))){
            return true;
        }
        else{
            Toasty.warning(this, getString(R.string.errorLetraDni), Toast.LENGTH_SHORT,true).show();

            return false;
        }


    }

    // TODO: REALIZO LA CONSULTA CON LA BASE DE DATOS
    public void consulta(){

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://proyectomercerosello.tk/back/medico/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        rutaRegistro = retrofit.create(MedicoRoutes.class);

        usuario.setDni(dni.getText().toString());
        usuario.setEmail(email.getText().toString());
        usuario.setContrasena(contrasena.getText().toString());

        nuevo = rutaRegistro.nuevoUsuario(usuario);

        nuevo.enqueue(new Callback<InsertUsuario>() {
            @Override
            public void onResponse(Call<InsertUsuario> call, Response<InsertUsuario> response) {

                switch (response.code()){

                    case 200:

                        Toasty.success(getApplicationContext(),response.body().getAltaUsuario().toString(), Toast.LENGTH_SHORT,true).show();

                        principal = new Intent(getApplicationContext(), Menu.class);
                        principal.putExtra("dniUsuario",dni.getText().toString());
                        startActivity(principal);

                        break;

                    case 299:
                        Toasty.error(getApplicationContext(), response.body().getAltaUsuario().toString(), Toast.LENGTH_SHORT,true).show();

                        break;

                }

            }

            @Override
            public void onFailure(Call<InsertUsuario> call, Throwable t) {

                Log.i("Fallo_Registro", "Error");

            }
        });

    }


}