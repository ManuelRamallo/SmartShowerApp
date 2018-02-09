package com.mramallo.pruebagradiente.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mramallo.pruebagradiente.R;
import com.mramallo.pruebagradiente.interfaces.IAsistenteRegistro;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatosUsuarioFragment extends Fragment implements IAsistenteRegistro {


    EditText editTextNumeroPersonas;
    EditText textoDireccion;
    EditText editTextLitros;

    public DatosUsuarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_datos_usuario, container, false);

       textoDireccion = v.findViewById(R.id.editTextDireccion);

       editTextNumeroPersonas = v.findViewById(R.id.editTextNumeroPersonas);

       editTextLitros = v.findViewById(R.id.editTextLitros);


        return v;
    }


    @Override
    public boolean validarPaso() {

        if(textoDireccion.getText().toString().equals("")){
            return false;
        } if(editTextNumeroPersonas.getText().toString().equals("")){
            return false;
        }if(editTextLitros.getText().toString().equals("")){
            return false;
        }else {
            return true;
        }
    }
}
