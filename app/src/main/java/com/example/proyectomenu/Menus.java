package com.example.proyectomenu;

import android.os.Bundle;


import com.example.proyectomenu.fragments_nav.cuentafragment;
import com.example.proyectomenu.fragments_nav.lineasfragment;
import com.example.proyectomenu.fragments_nav.menufragment;
import com.example.proyectomenu.fragments_nav.paradasfragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.appcompat.app.AppCompatActivity;


public class Menus extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.btmnavi);
        mMainNav=(BottomNavigationView)findViewById(R.id.main_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame,new menufragment()).commit();

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_menu:
                        selectedFragment= new menufragment();
                        break;
                    case R.id.nav_paradas:
                        selectedFragment= new paradasfragment();
                        break;
                    case R.id.nav_lineas:
                        selectedFragment= new lineasfragment();
                        break;
                    case R.id.nav_cuenta:
                        selectedFragment= new cuentafragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame,selectedFragment).commit();
                return true;
            }
        });
    }
}
