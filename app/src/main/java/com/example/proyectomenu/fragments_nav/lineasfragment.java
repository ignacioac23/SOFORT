package com.example.proyectomenu.fragments_nav;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.proyectomenu.Mapa;
import com.example.proyectomenu.R;



public class lineasfragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lineasfragment, container, false);
        Button boton602 = (Button) view.findViewById(R.id.boton602);
        boton602.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Mapa.class);
                startActivity(in);

            }
        });
        return view;
    }
}
