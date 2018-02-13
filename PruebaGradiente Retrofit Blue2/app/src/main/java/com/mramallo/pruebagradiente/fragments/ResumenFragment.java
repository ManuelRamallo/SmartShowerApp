package com.mramallo.pruebagradiente.fragments;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.google.gson.JsonObject;
import com.mramallo.pruebagradiente.API.InterfaceRequestApi;
import com.mramallo.pruebagradiente.API.ServiceGenerator;
import com.mramallo.pruebagradiente.Constant.PreferencesKeys;
import com.mramallo.pruebagradiente.Model.ArduinoDevice;
import com.mramallo.pruebagradiente.Model.Consumo;
import com.mramallo.pruebagradiente.Model.Consumo2;
import com.mramallo.pruebagradiente.Model.User;
import com.mramallo.pruebagradiente.R;
import com.mramallo.pruebagradiente.activities.ColorTemplate;
import com.mramallo.pruebagradiente.activities.HomeActivity;

import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResumenFragment extends Fragment {
    PieChart pieChart ;
    ArrayList<Entry> entries ;
    ArrayList<String> PieEntryLabels ;
    Legend legend;
    int consumoTotal = 0;
    TextView tvLimiteLitros, tvLitrosGastados;

    ArrayList<String> detalles = new ArrayList<>();
    ListView listaDetalles;
    PieDataSet pieDataSet ;
    PieData pieData ;

    public ResumenFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resumen, container, false);
        pieChart = (PieChart) view.findViewById(R.id.chart1);

        
        entries = new ArrayList<>();

        PieEntryLabels = new ArrayList<String>();
        pieChart.setDescription("");
        pieChart.setDescriptionTextSize(0);
        AddValuesToPIEENTRY();

        AddValuesToPieEntryLabels();

        pieDataSet = new PieDataSet(entries, "");

        pieData = new PieData(PieEntryLabels, pieDataSet);

        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextSize(0f);

        pieChart.setData(pieData);

        pieChart.animateY(3000);

        //lista de pantalla resumen
        //listaDetalles = view.findViewById(R.id.listViewDetails);
        tvLimiteLitros = getActivity().findViewById(R.id.textViewLimiteLitros);
        tvLitrosGastados = getActivity().findViewById(R.id.textViewLitrosGastados);

        SharedPreferences prefs = getActivity().getSharedPreferences("datos", Context.MODE_PRIVATE);

        int litrosMax = prefs.getInt(PreferencesKeys.USER_LIMITCONSUM, -1);
        //detalles.add("Litros máximos diarios: " +litrosMax +"L");

        //TODO HACER EL RETROFIT
        final InterfaceRequestApi api = ServiceGenerator.createService(InterfaceRequestApi.class);
        final String id_placa = "1";
        final String token = "Bearer " + prefs.getString(PreferencesKeys.USER_TOKEN, "No hay token");


//
//        tvLimiteLitros.setText("Litros máximos gastados: " +litrosMax +"L");
       //Log.i("RESULT", "Limite Litros: " +tvLimiteLitros.getText().toString() +" Litros gastados: " +tvLitrosGastados);


        Call<Consumo2[]> call = api.showConsumeToday(token, new ArduinoDevice(id_placa));

        call.enqueue(new Callback<Consumo2[]>() {
            @Override
            public void onResponse(Call<Consumo2[]> call, Response<Consumo2[]> response) {

                Consumo2[] result = response.body();

                //Toast.makeText(getActivity(), token + "   el id de la placa es " + id_placa, Toast.LENGTH_SHORT).show();

                if (response.code() == 200){

                    for (Consumo2 consumo : result){
                        consumoTotal += consumo.getConsumo();
                    }

                    //Toast.makeText(getActivity(), "Dentro de enqueue: " + consumoTotal, Toast.LENGTH_SHORT).show();
                    //tvLimiteLitros.setText("Litros cosumidos hoy: " +consumoTotal+"L");

                }

               else {
                    Toast.makeText(getActivity(), "FAIL, NO HACE CONSUMO", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Consumo2[]> call, Throwable t) {

            }
        });



//        Toast.makeText(getActivity(), "Fuera de enqueue: " + consumoTotal, Toast.LENGTH_SHORT).show();
//
//        detalles.add("Litros consumidos: " + consumoTotal+"L");
        //TODO Datos por defectos, por si no funciona el retrofit
/*
        detalles.add("Litros ahorrados: 455L");*/

//        ArrayAdapter adaptadorDetalles = new ArrayAdapter<>(
//                getActivity(),
//               R.layout.lista_item_blanco,
//               detalles
//        );

        //listaDetalles.setAdapter(adaptadorDetalles);


        return view;

    }

//    public void rellenarDatos (int consumoTotal) {
//        detalles.add("Litros consumidos: " + consumoTotal+"L");
//    }

    public void AddValuesToPIEENTRY(){

        entries.add(new BarEntry(2f, 0));
        entries.add(new BarEntry(6f, 1));


    }

    public void AddValuesToPieEntryLabels(){
        PieEntryLabels.add("Litros gastados");
        PieEntryLabels.add("Litros por gastar");
    }

}
