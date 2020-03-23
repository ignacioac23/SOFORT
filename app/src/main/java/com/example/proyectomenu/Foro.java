package com.example.proyectomenu;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Foro extends AppCompatActivity {

    ListView lv;
    FirebaseListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listadatos);
        lv = (ListView)findViewById(R.id.listdatos);

        Query query= FirebaseDatabase.getInstance().getReference().child("Foro");
        FirebaseListOptions<datos> options= new FirebaseListOptions.Builder<datos>()
                .setLayout(R.layout.perfildatos)
                .setQuery(query, datos.class)
                .build();
        adapter= new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {

            }
        }

    }
}
