package com.example.proyecto_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.proyecto_final.Splash.SplashActivity;

public class MainActivity extends AppCompatActivity {

    private Intent i ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        i = new Intent(getApplicationContext(), SplashActivity.class);
        startActivity(i);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}