package com.mramallo.pruebagradiente.activities;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.mramallo.pruebagradiente.Model.ArduinoDevice;
import com.mramallo.pruebagradiente.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.HTTP;

public class ConexionPlacaActivity extends AppCompatActivity {

    TextView textViewDipositivo;
    Button btn_conexion;
    EditText ssid, pass;

    String deviceName, deviceMAC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion_placa);

        textViewDipositivo = findViewById(R.id.textoDispositivo);
        btn_conexion = findViewById(R.id.btn_conexion);
        ssid = findViewById(R.id.editTextSSID);
        pass = findViewById(R.id.editTextPass);

        String deviceName = getIntent().getExtras().getString(BluetoothActivity.NOMBRE_DISPOSITIVO);
        final String deviceMac = getIntent().getExtras().getString(BluetoothActivity.EXTRA_DEVICE_ADDRESS);
        textViewDipositivo.setText("Conectado con " + deviceName);


        btn_conexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

                BluetoothDevice device = btAdapter.getRemoteDevice(deviceMac);
                //BluetoothSocket socket = device.createRfcommSocketToServiceRecord("wtf????");
                new ConnectAsyncTask().execute(device);
            }
        });
    }


    private class ConnectAsyncTask extends AsyncTask<BluetoothDevice, Integer, Boolean> {

        private BluetoothSocket mmSocket;
        private BluetoothDevice mmDevice;

        @Override
        protected Boolean doInBackground(BluetoothDevice... device) {

            mmDevice = device[0];
            InputStream input;
            OutputStream output;
            String UUIDarduino = null;
            Response<ArduinoDevice> response = null;
            try {

                String mmUUID = "00001101-0000-1000-8000-00805F9B34FB";
                mmSocket = mmDevice.createInsecureRfcommSocketToServiceRecord(UUID.fromString(mmUUID));
                mmSocket.connect();

                input = mmSocket.getInputStream();
                output = mmSocket.getOutputStream();

                output.write("0".getBytes());
                output.write(ssid.getText().toString().getBytes());
                output.flush();
                //output.
                Thread.sleep(150);
                output.write("1".getBytes());
                output.write(pass.getText().toString().getBytes());

                // Algoritmo
                // 1. Recoger el UUID
                // 2. Enviar al api
                //     2.1 Código 400, enviamos mensaje a arduino para repetir el proceso
                //     2.2 Código 201, enviamos mensaje a arduino de ok.
                byte[] buffer = new byte[1024];
                do {
                    int bytes = input.available();
                    input.read(buffer, 0, bytes);

                    UUIDarduino = new String(buffer, Charset.defaultCharset());

                    InterfaceRequestApi api = ServiceGenerator.createService(InterfaceRequestApi.class);

                    String token = getSharedPreferences("datos", Context.MODE_PRIVATE).getString(PreferencesKeys.USER_TOKEN, null);
                    //TODO ¿Y si token == null?
                    Call<ArduinoDevice> registerDevice = api.registerDevice(token, new ArduinoDevice(UUIDarduino));

                    response = registerDevice.execute();

                    if (response.code() == 400)
                        //output.write("ERROR".getBytes());
                        output.write("2".getBytes());


                } while (response.code() == 400);

               // output.write("OK".getBytes());
                output.write("3".getBytes());
                // Ahora, la placa arduino puede almacenar el UUID en la EEPROM.

            } catch (IOException ex) {
                return Boolean.FALSE;
            } catch (InterruptedException e) {
                //e.printStackTrace();
                return Boolean.FALSE;

            }

            return Boolean.TRUE;
        }

        @Override
        protected void onPostExecute(Boolean result) {

            //btSocket = result;
            //Enable Button
            //btToggle.setEnabled(true);
            if (result == Boolean.TRUE) {
                Toast.makeText(ConexionPlacaActivity.this, "Datos enviados correctamente", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ConexionPlacaActivity.this, HomeActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(ConexionPlacaActivity.this, "Error al recibir los datos de vuelta desde Arduino", Toast.LENGTH_SHORT).show();
                //Nos vamos a la m... porque hubo un error
            }

        }


    }

}
