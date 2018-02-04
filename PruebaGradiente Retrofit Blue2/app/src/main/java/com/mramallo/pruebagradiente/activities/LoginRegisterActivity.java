package com.mramallo.pruebagradiente.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mramallo.pruebagradiente.R;

public class LoginRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button botonRegistro;
    Button botonInicioSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        //Boton de registro, al pulsarlo manda al MainActivity, el de registro.
        botonRegistro = findViewById(R.id.botonRegistro);

        //Boton de inicio de sesion, al pulsarlo manda al InicioSesionActivity, el de login
        botonInicioSesion = findViewById(R.id.botonLogin);

        eventListener();

    }

    private void eventListener() {
        botonRegistro.setOnClickListener(this);
        botonInicioSesion.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int seleccion = v.getId();

        switch (seleccion){
            case R.id.botonRegistro:
                Intent intent = new Intent(LoginRegisterActivity.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.botonLogin:
                Intent intent2 = new Intent(LoginRegisterActivity.this, InicioSesionActivity.class);
                startActivity(intent2);
                break;
        }


    }
}
