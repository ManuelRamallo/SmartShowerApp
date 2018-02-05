package com.mramallo.pruebagradiente.activities;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.mramallo.pruebagradiente.R;

import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {


    ListView btDevices;
    TextView textViewActiveBluetooth;

    private BluetoothAdapter mBTAdapter;
    private ArrayAdapter<String> mBTArrayAdapter;

    public static String EXTRA_DEVICE_ADDRESS = "device_address";
    public static String NOMBRE_DISPOSITIVO = "NOMBRE_DISPOSITIVO";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);


    }

    @Override
    protected void onResume() {
        super.onResume();
        
        checkBtState();

        btDevices = findViewById(R.id.listViewDispositivos);

        textViewActiveBluetooth = findViewById(R.id.textoActiveBluetooth);
        mBTArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        btDevices.setAdapter(mBTArrayAdapter);
        btDevices.setOnItemClickListener(mDevicesClickListener);

        mBTAdapter = BluetoothAdapter.getDefaultAdapter();

        Set<BluetoothDevice> pairedDevices = mBTAdapter.getBondedDevices();

        if(pairedDevices.size() > 0){
            textViewActiveBluetooth.setText("Seleccione su placa Smart Shower");
            for (BluetoothDevice device : pairedDevices) {
                mBTArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }


    }


    //Checkea si el bluetooth está conectado o no y manda permisos para activarlo dentro de la App
    private void checkBtState() {
        mBTAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBTAdapter == null){
            Toast.makeText(this, "El dispositivo no soporta Bluetooth", Toast.LENGTH_SHORT).show();
        }else {
            if(mBTAdapter.isEnabled()){
                Log.i("Blue", "Bluetooth Activado");
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }


    }


    //Metodo donde seleccionas un dispositivo y por MAC te manda un intent
    private AdapterView.OnItemClickListener mDevicesClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            String info = ((TextView) view).getText().toString();
            String address = info.substring(info.length() - 17);

            Intent intentConect = new Intent(BluetoothActivity.this, ConexionPlacaActivity.class);
            intentConect.putExtra(EXTRA_DEVICE_ADDRESS, address);
            intentConect.putExtra(NOMBRE_DISPOSITIVO, info);
            startActivity(intentConect);


        }
    };


}
