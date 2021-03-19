package com.example.proyecto_final.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.proyecto_final.MainActivity;
import com.example.proyecto_final.R;

public class SplashActivity extends AppCompatActivity {

    public static final int DURACION = 5000;

    private Animation animacion;
    private ImageView imagen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        animacion = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splash_animation);
        imagen = (ImageView)findViewById(R.id.imageSplash);
        imagen.setAnimation(animacion);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                finish();
            }
        },DURACION);

    }
}