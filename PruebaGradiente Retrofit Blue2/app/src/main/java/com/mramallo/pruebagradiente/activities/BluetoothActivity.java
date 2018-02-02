package com.mramallo.pruebagradiente.activities;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

    private final static int REQUEST_ENABLE_BT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        btDevices = findViewById(R.id.listViewDispositivos);
        textViewActiveBluetooth = findViewById(R.id.textoActiveBluetooth);

        mBTAdapter = BluetoothAdapter.getDefaultAdapter(); // get a handle on the bluetooth radio
        mBTArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        btDevices.setAdapter(mBTArrayAdapter);
        btDevices.setOnItemClickListener(mDevicesClickListener);


        if(!mBTAdapter.isEnabled()){
            textViewActiveBluetooth.setText("Active la conexión Bluetooth y vincule su placa Smart Shower");
        } else{
            textViewActiveBluetooth.setText("Seleccione su placa Smart Shower");
            listPairedDevice();
        }


    }


    private void listPairedDevice(){
        Set<BluetoothDevice> pairedDevices = mBTAdapter.getBondedDevices();
        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
                mBTArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    }

    private AdapterView.OnItemClickListener mDevicesClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            if(i != 2) {
                Toast.makeText(BluetoothActivity.this, "Seleccione otro dispositivo" /*+ adapterView.getItemAtPosition(i)*/, Toast.LENGTH_SHORT).show();
            } else if (i == 2){
                Intent in = new Intent(BluetoothActivity.this, LoginRegisterActivity.class);
                startActivity(in);
            }

        }
    };


}
