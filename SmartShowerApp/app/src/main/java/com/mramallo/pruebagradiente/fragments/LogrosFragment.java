package com.mramallo.pruebagradiente.fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mramallo.pruebagradiente.R;
import com.mramallo.pruebagradiente.activities.DialogosLogros;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogrosFragment extends Fragment {


    public LogrosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentManager fragmentManager = getFragmentManager();
        DialogosLogros dialogo = new DialogosLogros();
        dialogo.show(fragmentManager, "tagPersonalizado");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logros, container, false);
    }

}
