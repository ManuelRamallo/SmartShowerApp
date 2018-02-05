package com.mramallo.pruebagradiente.fragments;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.mramallo.pruebagradiente.R;
import com.mramallo.pruebagradiente.activities.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResumenFragment extends Fragment {
    PieChart pieChart ;
    ArrayList<Entry> entries ;
    ArrayList<String> PieEntryLabels ;
    Legend legend;

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
        listaDetalles = view.findViewById(R.id.listViewDetails);

        detalles.add("Litros máximos diarios: 500");
        detalles.add("Litros gastados hoy: 45");
        detalles.add("Litros ahorrados: 455");

        ArrayAdapter adaptadorDetalles = new ArrayAdapter<>(
                getActivity(),
               R.layout.lista_item_blanco,
               detalles
        );

        listaDetalles.setAdapter(adaptadorDetalles);


        return view;

    }






    public void AddValuesToPIEENTRY(){

        entries.add(new BarEntry(2f, 0));
        entries.add(new BarEntry(6f, 1));


    }

    public void AddValuesToPieEntryLabels(){
        PieEntryLabels.add("Litros gastados");
        PieEntryLabels.add("Litros por gastar");
    }

}
