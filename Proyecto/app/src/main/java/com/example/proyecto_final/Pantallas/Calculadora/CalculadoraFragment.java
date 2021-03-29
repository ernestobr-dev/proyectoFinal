package com.example.proyecto_final.Pantallas.Calculadora;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto_final.R;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculadoraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculadoraFragment extends Fragment {

    /*
        TODO: Taza = 250 ML,   Cuchara = 15 ml, Cucharita = 5 ml,  Gotas = 1ml
        TODO: Formula =  peso * dosis * prestacion (ml/mg)
     */


    private View view;
    private Button buttonCalcular;
    private EditText editTextPeso,editTextDosis,editTextPresentacion;
    private TextView textViewResultado;
    private Spinner spinner;

    private final String[] categorias = {"Pastillas", "Taza","Cuchara","Cucharita","Gotas"};


    public CalculadoraFragment() {

    }



    public static CalculadoraFragment newInstance() {
        CalculadoraFragment fragment = new CalculadoraFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_calculadora, container, false);

        spinner = (Spinner) view.findViewById(R.id.spinnerTipo);
        spinner.setAdapter(new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_dropdown_item,categorias));

        editTextDosis = (EditText) view.findViewById(R.id.txtDosis);
        editTextPeso = (EditText) view.findViewById(R.id.txtPeso);
        editTextPresentacion= (EditText)  view.findViewById(R.id.txtPresentacion);

        buttonCalcular = (Button) view.findViewById(R.id.btnCalcular);

        textViewResultado = (TextView) view.findViewById(R.id.txtResultado);


        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(comprobarCamposVacios() && comprobarValores()){
                    int total;
                    total = (Integer.parseInt(editTextPeso.getText().toString())*Integer.parseInt(editTextDosis.getText().toString())*Integer.parseInt(editTextPresentacion.getText().toString()))/Integer.parseInt(editTextPresentacion.getText().toString());
                    textViewResultado.setText(total+" "+spinner.getSelectedItem().toString());

                }


            }
        });



        return view;
    }

    private boolean comprobarCamposVacios(){

        if(editTextDosis.getText().toString().isEmpty()){
            Toasty.warning(getContext(),"EL CAMPO DOSIS ES ERRONEO", Toast.LENGTH_SHORT,true).show();
            return false;

        }
        else if(editTextPeso.getText().toString().isEmpty() ){
            Toasty.warning(getContext(),"EL CAMPO PESO ES ERRONEO", Toast.LENGTH_SHORT,true).show();
            return false;

        }

        else if(editTextPresentacion.getText().toString().isEmpty() ){
            Toasty.warning(getContext(),"EL CAMPO PRESENTACIÓN ES ERRONEO", Toast.LENGTH_SHORT,true).show();
            return false;

        }
        else{
            return true;
        }

    }

    private boolean comprobarValores(){
        if(Integer.parseInt(editTextDosis.getText().toString())<=0){
            Toasty.warning(getContext(),"EL CAMPO DOSIS TIENE QUE SER MAYOR QUE CERO", Toast.LENGTH_SHORT,true).show();
            return false;

        }
        else if(Integer.parseInt(editTextPeso.getText().toString())<=0 ){
            Toasty.warning(getContext(),"EL CAMPO PESO TIENE QUE SER MAYOR QUE CERO", Toast.LENGTH_SHORT,true).show();
            return false;

        }

        else if(Integer.parseInt(editTextPresentacion.getText().toString())<=0 ){
            Toasty.warning(getContext(),"EL CAMPO PRESENTACIÓN TIENE QUE SER MAYOR QUE CERO", Toast.LENGTH_SHORT,true).show();
            return false;

        }
        else{
            return true;
        }
    }
}