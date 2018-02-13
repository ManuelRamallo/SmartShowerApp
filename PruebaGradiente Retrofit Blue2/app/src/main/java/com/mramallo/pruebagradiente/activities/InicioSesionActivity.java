package com.mramallo.pruebagradiente.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mramallo.pruebagradiente.API.InterfaceRequestApi;
import com.mramallo.pruebagradiente.API.ServiceGenerator;
import com.mramallo.pruebagradiente.Constant.PreferencesKeys;
import com.mramallo.pruebagradiente.Model.User;
import com.mramallo.pruebagradiente.R;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InicioSesionActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail, etPass;
    TextView textoRegistro;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        textoRegistro = findViewById(R.id.textViewRegistro);
        btnLogin = findViewById(R.id.buttonLogin);

        etEmail = findViewById(R.id.editTextEmailRegister);
        etPass = findViewById(R.id.editTextPasswordLogin);

        eventListener();

    }

    private void eventListener() {
        textoRegistro.setOnClickListener(this);
        btnLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int seleccion = v.getId();

        switch (seleccion){
            case R.id.textViewRegistro:
                Intent intent = new Intent(InicioSesionActivity.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.buttonLogin:
                User userLoged = new User();

                userLoged.setEmail(etEmail.getText().toString());
                userLoged.setPassword(etPass.getText().toString());

                InterfaceRequestApi api = ServiceGenerator.createService(InterfaceRequestApi.class);

                Call<User> call = api.loginUser(userLoged);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User   > response) {

                        if (response.code() == HttpsURLConnection.HTTP_OK) {
                            User result = response.body();

                            SharedPreferences prefs = InicioSesionActivity.this.getSharedPreferences("datos", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();

                            editor.putString(PreferencesKeys.USER_NAME, result.getNombre());
                            editor.putString(PreferencesKeys.USER_SURNAME, result.getApellidos());
                            editor.putString(PreferencesKeys.USER_EMAIL, result.getEmail());
                            editor.putString(PreferencesKeys.USER_ADDRESS, result.getDireccion());
                            editor.putString(PreferencesKeys.USER_TOKEN, result.getToken());
                            editor.putInt(PreferencesKeys.USER_NUMPERS, result.getNum_personas());
                            editor.putInt(PreferencesKeys.USER_LIMITCONSUM, result.getLimite_consumo());

                            editor.commit();

                            Intent i = new Intent(InicioSesionActivity.this, HomeActivity.class);
                            startActivity(i);
                            finish();

                        } else {
                            Toast.makeText(InicioSesionActivity.this, "Fallo crítico", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("TAG","onFailure login: "+t.toString());

                    }
                });
        }
    }
}
