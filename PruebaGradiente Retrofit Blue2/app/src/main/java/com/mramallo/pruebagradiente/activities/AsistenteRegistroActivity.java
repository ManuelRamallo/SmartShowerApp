package com.mramallo.pruebagradiente.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mramallo.pruebagradiente.API.InterfaceRequestApi;
import com.mramallo.pruebagradiente.API.ServiceGenerator;
import com.mramallo.pruebagradiente.Constant.PreferencesKeys;
import com.mramallo.pruebagradiente.Model.User;
import com.mramallo.pruebagradiente.R;
import com.mramallo.pruebagradiente.fragments.DatosUsuarioFragment;
import com.mramallo.pruebagradiente.interfaces.IAsistenteRegistro;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsistenteRegistroActivity extends AppCompatActivity {
    Fragment currentFragment = null;
    EditText etAddress, etNumPers, etLimitConsum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistente_registro);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(((IAsistenteRegistro)currentFragment).validarPaso()) {
                    SharedPreferences prefs = AsistenteRegistroActivity.this.getSharedPreferences("datos", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();



                        String nombre = prefs.getString(PreferencesKeys.USER_NAME, "Sin nombre");
                        String apellidos = prefs.getString(PreferencesKeys.USER_SURNAME, "Sin apellidos");
                        String email = prefs.getString(PreferencesKeys.USER_EMAIL, "Sin email");
                        String pass = prefs.getString(PreferencesKeys.USER_PASSWORD, "Sin password");

                        etAddress = findViewById(R.id.editTextDireccion);
                        String address = etAddress.getText().toString();

                        etNumPers = findViewById(R.id.editTextNumeroPersonas);
                        int numPers = Integer.parseInt(etNumPers.getText().toString());

                        etLimitConsum = findViewById(R.id.editTextLitros);
                        int limitConsum = Integer.parseInt(etLimitConsum.getText().toString());

                        editor.putString(PreferencesKeys.USER_ADDRESS, address);
                        editor.putInt(PreferencesKeys.USER_NUMPERS, numPers);
                        editor.putInt(PreferencesKeys.USER_LIMITCONSUM, limitConsum);

                        editor.commit();


                        User userRegistered = new User();

                        userRegistered.setNombre(nombre);
                        userRegistered.setApellidos(apellidos);
                        userRegistered.setEmail(email);
                        userRegistered.setPassword(pass);
                        userRegistered.setDireccion(address);
                        userRegistered.setLimite_consumo(limitConsum);
                        userRegistered.setNum_personas(numPers);

                        InterfaceRequestApi api = ServiceGenerator.createService(InterfaceRequestApi.class);

                        Call<User> call = api.registerUser(userRegistered);


                        call.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {

                                if (response.code() == HttpsURLConnection.HTTP_CREATED) {
                                    Toast.makeText(AsistenteRegistroActivity.this, "Se ha registrado correctamente", Toast.LENGTH_SHORT).show();

                                    Intent i = new Intent (AsistenteRegistroActivity.this, BluetoothActivity.class);
                                    startActivity(i);

                                } else
                                    Toast.makeText(AsistenteRegistroActivity.this, "Error, vuelva a intentarlo", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Log.e("TAG", "onFailure register: " + t.toString());

                            }
                        });




                } else {
                    Toast.makeText(AsistenteRegistroActivity.this, "Complete todos los campos del formulario", Toast.LENGTH_SHORT).show();

                }

            }
        });

        currentFragment = new DatosUsuarioFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.contenedorAsistente, currentFragment).commit();

    }

    /*private void pasarSiguienteFragment() {
        if(currentFragment instanceof DatosUsuarioFragment) {
            currentFragment = new ConexionFragment();
        } else {
            //TODO Cambiar loginregister por la pagina principal que queramos mostrar
            Intent i = new Intent(this, LoginRegisterActivity.class);
            startActivity(i);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorAsistente, currentFragment).commit();
    }*/

}
