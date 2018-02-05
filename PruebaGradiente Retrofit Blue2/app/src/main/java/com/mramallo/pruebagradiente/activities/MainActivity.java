package com.mramallo.pruebagradiente.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mramallo.pruebagradiente.Constant.PreferencesKeys;
import com.mramallo.pruebagradiente.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNombre, etApellidos, etEmail, etPassword;
    TextView iniciarSesion;
    Button btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarSesion = findViewById(R.id.textViewInicioSesion);
        btnRegistro = findViewById(R.id.buttonRegister);
        etNombre = findViewById(R.id.editTextNombreRegister);
        etApellidos = findViewById(R.id.editTextApellidosRegister);
        etEmail = findViewById(R.id.editTextEmailRegister);
        etPassword= findViewById(R.id.editTextPasswordRegister);


        eventListener();

    }

    private void eventListener() {
        iniciarSesion.setOnClickListener(this);
        btnRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int seleccion = v.getId();

        switch (seleccion){
            case R.id.textViewInicioSesion:
                Intent intent = new Intent(MainActivity.this, InicioSesionActivity.class);
                startActivity(intent);

            case R.id.buttonRegister:
                if (etNombre.getText().toString().length() == 0){
                    etNombre.setError("Escriba su nombre por favor");
                } else if(etApellidos.getText().toString().length() == 0) {
                    etApellidos.setError("Escriba sus apellidos por favor");
                } else if(etEmail.getText().toString().length() == 0){
                    etEmail.setError("Escriba su email por favor");
                } else if(etPassword.getText().toString().length() == 0){
                    etPassword.setError("Escriba su contraseña por favor");
                } else {
                    SharedPreferences prefs = MainActivity.this.getSharedPreferences("datos", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(PreferencesKeys.USER_NAME, etNombre.getText().toString());
                    editor.putString(PreferencesKeys.USER_SURNAME, etApellidos.getText().toString());
                    editor.putString(PreferencesKeys.USER_EMAIL, etEmail.getText().toString());
                    editor.putString(PreferencesKeys.USER_PASSWORD, etPassword.getText().toString());

                    editor.commit();

                    //TODO Terminar de pasar los preferencias

                    Intent intentAsistente = new Intent(MainActivity.this, AsistenteRegistroActivity.class);
                    startActivity(intentAsistente);
                    finish();
                }
        }
    }
}
