package com.mramallo.pruebagradiente.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mramallo.pruebagradiente.R;

public class ConexionPlacaActivity extends AppCompatActivity {

    TextView textViewDipositivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion_placa);

        textViewDipositivo = findViewById(R.id.textoDispositivo);

        String nombreDispositivo = getIntent().getExtras().getString("NOMBRE_DISPOSITIVO");

        textViewDipositivo.setText("Conectado con " + nombreDispositivo);
    }
}
