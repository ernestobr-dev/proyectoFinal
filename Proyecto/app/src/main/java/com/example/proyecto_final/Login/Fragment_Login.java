package com.example.proyecto_final.Login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto_final.Menu.MenuActivity;
import com.example.proyecto_final.R;
import com.example.proyecto_final.Registro.Registro;
import com.example.proyecto_final.Back.Respuestas.RespuestasUsuario.RespuestaLoginUsuario;
import com.example.proyecto_final.Back.Routes.MedicoRoutes;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_Login extends Fragment {

    // TODO: VISTA DEL FRAGMENT
    private View vista;

    // TODO: ELEMENTOS USADOS EN EL LOGIN
    private Button boton,registrarse;
    private EditText email,contraseña;

    // TODO: CLASES NECESARIAS
    private MedicoRoutes rutaLogin;
    private Call<RespuestaLoginUsuario> dniUsuario;
    private Intent principal;

    public Fragment_Login() {

    }


    public static Fragment_Login newInstance() {
        Fragment_Login fragment = new Fragment_Login();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.fragment__login, container, false);

        boton = (Button)vista.findViewById(R.id._btnLogin);
        registrarse = (Button)vista.findViewById(R.id.btnIrRegsitro);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**Intent intent = new Intent(getContext(), MenuActivity.class);
                startActivity(intent);**/
                email = (EditText)vista.findViewById(R.id._txtUsuario);
                contraseña = (EditText) vista.findViewById(R.id._txtContrasena);

                if(comprobarCamposVacios(email.getText().toString(),contraseña.getText().toString())){

                    consulta(email.getText().toString(),contraseña.getText().toString());

                }

            }
        });

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), Registro.class);
                startActivity(intent);



            }
        });

        return vista;
    }

    private boolean comprobarCamposVacios(String email, String contrasena)
    {

       if(email.isEmpty()){

           Toasty.warning(getContext(), getString(R.string.errorEmailVacio), Toast.LENGTH_SHORT,true).show();
           return false;
       }
       else if(contrasena.isEmpty()){

           Toasty.warning(getContext(), getString(R.string.errorContrasenaVacia), Toast.LENGTH_SHORT,true).show();

           return false;

       }
       else{
           return true;
       }

    }

    private void consulta(String email,String contrasena){

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://proyectomercerosello.tk/back/medico/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        rutaLogin = retrofit.create(MedicoRoutes.class);
        dniUsuario = rutaLogin.loginUsuario(email,contrasena);
        dniUsuario.enqueue(new Callback<RespuestaLoginUsuario>() {
            @Override
            public void onResponse(Call<RespuestaLoginUsuario> call, Response<RespuestaLoginUsuario> response) {

                switch (response.code()){
                    case 200:

                        Intent principal  = new Intent(getContext(), MenuActivity.class);
                        principal.putExtra("dniUsuario",response.body().getLoginUsuario().getRespuestaLogin().toString());
                        startActivity(principal);

                        break;

                    case 299:

                        Toasty.error(getContext(),response.body().getLoginUsuario().getRespuestaLogin().toString(),Toast.LENGTH_SHORT,true).show();

                        break;

                    default:
                        Log.i("500", response.body().getLoginUsuario().getRespuestaLogin());
                        break;
                }

            }

            @Override
            public void onFailure(Call<RespuestaLoginUsuario> call, Throwable t) {

                switch (t.hashCode()){
                    case 400:
                        Log.i("041", "ERROR");
                        break;
                }

            }
        });

    }
}