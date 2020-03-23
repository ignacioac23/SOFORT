package com.example.proyectomenu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.google.android.gms.maps.model.CameraPosition.builder;


public  class Mapa extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    private GoogleMap mMap;
    private DatabaseReference mDatabase;
    private ArrayList<Marker> tmpRealTimeMarkers = new ArrayList<>();
    private ArrayList<Marker> realTimeMarkers = new ArrayList<>();
    private TextView txtTarifas;








    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);


        Toolbar toolbar =findViewById(R.id.toolbar);
        toolbar.setTitle("GRAN VALPARAÍSO S.A");

        setSupportActionBar(toolbar);
        toolbar.setSubtitle("LINEA 602");

        // txtTarifas=findViewById(R.id.tarifas);

        //txtTarifas.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //public void onClick(View v) {
        //  startActivity( new Intent(Mapa.this, popup.class));
        //  }
        //  });














        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        countDownTimer();

    }



    private void countDownTimer() {
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.e("seconds remaining: ", "" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                //Toast.makeText(Mapa.this, "Punto Actualizado", Toast.LENGTH_SHORT).show();


                onMapReady(mMap);


            }
        }.start();
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
        else {

        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mDatabase.child("usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //for (Marker marker: realTimeMarkers){
                //  marker.remove();
                //}

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){

                    MapsPojo mp= snapshot.getValue(MapsPojo.class);
                    Double latitud= mp.getLatitud();
                    Double longuitud=mp.getLonguitud();
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(latitud,longuitud));
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.marcadorbus));
                    markerOptions.title("Autobús 602");
                    tmpRealTimeMarkers.add(mMap.addMarker(markerOptions));




















                    // tmpRealTimeMarkers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(latitud,longuitud))
                    //       .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marcadorbus))
                    //     .title("Autobus 602")));


                }



                realTimeMarkers.clear();
                realTimeMarkers.addAll(tmpRealTimeMarkers);
                tmpRealTimeMarkers.clear();
                countDownTimer();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        LatLng Garita = new LatLng(-32.919046,-71.504013);
        LatLng e18 = new LatLng(-32.921045,-71.506747);
        LatLng e17 = new LatLng(-32.921192,-71.507077);
        LatLng e16 = new LatLng(-32.921322,-71.507471);
        LatLng e15 = new LatLng(-32.921582,-71.507407);
        LatLng e14= new LatLng(-32.921663,-71.507180);
        LatLng e13 = new LatLng(-32.923307,-71.506618);
        LatLng e12= new LatLng(-32.923730,-71.506586);
        LatLng e11 = new LatLng(-32.923676,-71.510289);
        LatLng e10 = new LatLng(-32.923637,-71.516550);
        LatLng e9 = new LatLng(-32.926734,-71.516727);
        LatLng e8 = new LatLng(-32.927941,-71.517342);
        LatLng e7 = new LatLng(-32.928752,-71.517965);
        LatLng e6 = new LatLng(-32.929958,-71.518692);
        LatLng e5 = new LatLng(-32.933610,-71.525291);
        LatLng e4 = new LatLng(-32.933875,-71.531176);
        LatLng e3 = new LatLng(-32.933917,-71.538577);
        LatLng e2 = new LatLng(-32.934055,-71.542542);
        LatLng e1 = new LatLng(-32.934606,-71.544020);
        LatLng p0 = new LatLng(-32.938369,-71.546546);
        LatLng p1 = new LatLng(-32.943308,-71.546937);
        LatLng p2 = new LatLng(-32.948766,-71.545258);
        LatLng p3 = new LatLng(-32.953567,-71.544033);
        LatLng p4 = new LatLng(-32.961811,-71.544639);
        LatLng p5 = new LatLng(-32.967045,-71.544083);
        LatLng p6 = new LatLng(-32.967575,-71.543767);
        LatLng p7 = new LatLng(-32.968889,-71.540989);
        LatLng p8 = new LatLng(-32.970330,-71.539095);
        LatLng p9 = new LatLng(-32.971315,-71.539032);
        LatLng p10 = new LatLng(-32.971718,-71.543805);
        LatLng p11= new LatLng(-32.973514,-71.544336);
        LatLng p12= new LatLng(-32.974276,-71.545550);
        LatLng p13 = new LatLng(-32.975165,-71.545843);
        LatLng p14 = new LatLng(-32.978380,-71.545444);
        LatLng p15= new LatLng(-32.981706,-71.547945);
        LatLng p16= new LatLng(-32.983325,-71.548092);
        LatLng p17= new LatLng(-32.984363,-71.547413);
        LatLng p18= new LatLng(-32.987454,-71.546402);
        LatLng p19= new LatLng(-32.989073,-71.546455);
        LatLng p20= new LatLng(-32.990378,-71.546907);
        LatLng p21= new LatLng(-32.995238,-71.547538);
        LatLng p22= new LatLng(-33.005184,-71.550176);
        LatLng p23= new LatLng(-33.005222,-71.548869);
        LatLng p24= new LatLng(-33.006356,-71.549229);
        LatLng p25= new LatLng(-33.007301,-71.548440);


        LatLng p26= new LatLng(-33.023495,-71.551778);
        LatLng p27= new LatLng(-33.023903,  -71.552088);

        LatLng p28= new LatLng(-33.023071,  -71.558122);
        LatLng p29= new LatLng(-33.022836 , -71.559662);
        LatLng p30= new LatLng(-33.022579  ,-71.561199);
        LatLng p31= new LatLng(-33.023715, -71.561391);
        LatLng p32= new LatLng(-33.023621 , -71.562190);
        LatLng p33 = new LatLng(-33.023790 , -71.562525);
        LatLng p34 = new LatLng(-33.024680  ,-71.562709);
        LatLng p35 = new LatLng(-33.023930, -71.565916);
        LatLng p36 = new LatLng(-33.023663 , -71.567826);
        LatLng p37= new LatLng(-33.023898 , -71.568882);
        LatLng p38 = new LatLng(-33.025242 ,  -71.572803);
        LatLng p40 = new LatLng(-33.027298  , -71.575741);
        LatLng p41 = new LatLng(-33.037663   ,-71.576897);
        LatLng p42= new LatLng(-33.027152,   -71.580187);
        LatLng p43= new LatLng(-33.025954 ,    -71.582036);
        LatLng p44= new LatLng(-33.025621  ,  -71.583175);
        LatLng p45= new LatLng(-33.025958   ,-71.584460);
        LatLng p46= new LatLng(-33.027087,  -71.585667);
        LatLng p47= new LatLng(-33.028941 , -71.586571);
        LatLng p48= new LatLng(-33.029850  ,-71.587258);
        LatLng p49= new LatLng(-33.030880,   -71.588627);
        LatLng p50= new LatLng(-33.032372 ,  -71.589828);
        LatLng p51= new LatLng(-33.032953  ,-71.591286);
        LatLng p52= new LatLng(-33.033557,   -71.594062);
        LatLng p53= new LatLng(-33.034320 ,   -71.596994);
        LatLng p54= new LatLng(-33.034985  ,  -71.597866);
        LatLng p55= new LatLng(-33.037083   , -71.599513);
        LatLng p56= new LatLng(-33.037776    , -71.600781);
        LatLng p57= new LatLng(-33.038525,    -71.602580);
        LatLng p58= new LatLng(-33.039241 , -71.603353);
        LatLng p59= new LatLng(-33.039817  , -71.604336);
        LatLng p60= new LatLng(-33.042518,  -71.605372);
        LatLng p61 = new LatLng(-33.043286,      -71.606026);
        LatLng p62= new LatLng(-33.044635  ,   -71.610036);
        LatLng p63= new LatLng(-33.043647   ,-71.614834);
        LatLng p64 = new LatLng(-33.043455 ,  -71.619487);
        LatLng p65 = new LatLng(-33.042795  , -71.622090);
        LatLng p66 =new LatLng(-33.038404, -71.627899);
        LatLng p67= new LatLng(-33.034161,   -71.629641);
        LatLng p68= new LatLng(-33.033480 ,   -71.630032);
        LatLng p69= new LatLng(-33.032432  ,-71.629731);
        LatLng p70= new LatLng(-33.031543   ,-71.629866);
        LatLng p71= new LatLng(-33.030821,  -71.628671);
        LatLng p72= new LatLng(-33.027785 ,  -71.629501);
        LatLng p73= new LatLng(-33.026908  ,  -71.631343);
        LatLng p74= new LatLng(-33.025602  , -71.631790);
        LatLng p75= new LatLng(-33.024561   , -71.631953);
        LatLng p76= new LatLng(-33.022651    , -71.633075);
        LatLng p77= new LatLng(-33.021966,    -71.633882);
        LatLng p78= new LatLng(-33.021555 ,  -71.633773);
        LatLng p79= new LatLng(-33.022916  ,-71.634917);
        LatLng p80= new LatLng(-33.023163,  -71.636943);
        LatLng p81= new LatLng(-33.024159 , -71.638349);
        LatLng p82= new LatLng(-33.026214  , -71.638850);
        LatLng p83= new LatLng(-33.026607   ,-71.638534);
        LatLng p84= new LatLng(-33.026516,    -71.635701);
        LatLng p85= new LatLng(-33.027950 , -71.633729);
        LatLng p86= new LatLng(-33.029311  , -71.633228);
        LatLng p87= new LatLng(-33.029950   , -71.633903);
        LatLng p88= new LatLng(-33.030206    , -71.634721);
        LatLng p89= new LatLng(-33.030645 ,  -71.634219);
        LatLng p90= new LatLng(-33.030827,  -71.633391);
        LatLng p91= new LatLng(-33.031723 ,  -71.634513);
        LatLng p92= new LatLng(-33.029996  ,-71.636388);
        LatLng p93= new LatLng(-33.034225,  -71.642119);
        LatLng p94= new LatLng(-33.035797,  -71.642446);
        LatLng p95= new LatLng(-33.037276 ,  -71.644483);
        LatLng p96= new LatLng(-33.039508  , -71.644681);
        LatLng p97= new LatLng(-33.042712   ,-71.646439);
        LatLng p98= new LatLng(-33.042739 ,  -71.647224);
        LatLng p99= new LatLng(-33.042134,    -71.648130);
        LatLng p100= new LatLng(-33.041304 ,  -71.648662);
        LatLng Garita2= new LatLng(-33.040823 ,  -71.650925);
        LatLng Ref =new LatLng(-33.030601,-71.516666);



        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);


        mMap.addMarker(new MarkerOptions().position(Garita).title("Garita").icon(BitmapDescriptorFactory.fromResource(R.mipmap.garita)));
        mMap.addMarker(new MarkerOptions().position(Garita2).title("Garita").icon(BitmapDescriptorFactory.fromResource(R.mipmap.garita)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Ref));





        mMap.setMinZoomPreference(11f);
        Polyline polyline = mMap.addPolyline(
                new PolylineOptions()
                        .add(Garita)
                        .add(e18)
                        .add(e17)
                        .add(e16)
                        .add(e15)
                        .add(e14)
                        .add(e13)
                        .add(e12)
                        .add(e11)
                        .add(e10)
                        .add(e9)
                        .add(e8)
                        .add(e7)
                        .add(e6)
                        .add(e5)
                        .add(e4)
                        .add(e3)
                        .add(e2)
                        .add(e1)
                        .add(p0)
                        .add(p1)
                        .add(p2)
                        .add(p3)
                        .add(p4)
                        .add(p5)
                        .add(p6)
                        .add(p7)
                        .add(p8)
                        .add(p9)
                        .add(p10)
                        .add(p11)
                        .add(p12)
                        .add(p13)
                        .add(p14)
                        .add(p15)
                        .add(p16)
                        .add(p17)
                        .add(p18)
                        .add(p19)
                        .add(p20)
                        .add(p21)
                        .add(p22)
                        .add(p23)
                        .add(p24)
                        .add(p25)
                        .add(p26)
                        .add(p27)
                        .add(p28)
                        .add(p29)
                        .add(p30)
                        .add(p31)
                        .add(p32)
                        .add(p33)
                        .add(p34)
                        .add(p35)
                        .add(p36)
                        .add(p37)
                        .add(p38)
                        .add(p40)
                        .add(p41)
                        .add(p42)
                        .add(p43)
                        .add(p44)
                        .add(p45)
                        .add(p46)
                        .add(p47)
                        .add(p48)
                        .add(p49)
                        .add(p50)
                        .add(p51)
                        .add(p52)
                        .add(p53)
                        .add(p54)
                        .add(p55)
                        .add(p56)
                        .add(p57)
                        .add(p58)
                        .add(p59)
                        .add(p60)
                        .add(p61)
                        .add(p62)
                        .add(p63)
                        .add(p64)
                        .add(p65)
                        .add(p66)
                        .add(p67)
                        .add(p68)
                        .add(p69)
                        .add(p70)
                        .add(p71)
                        .add(p72)
                        .add(p73)
                        .add(p74)
                        .add(p75)
                        .add(p76)
                        .add(p77)
                        .add(p78)
                        .add(p79)
                        .add(p80)
                        .add(p81)
                        .add(p82)
                        .add(p83)
                        .add(p84)
                        .add(p85)
                        .add(p86)
                        .add(p87)
                        .add(p88)
                        .add(p89)
                        .add(p90)
                        .add(p91)
                        .add(p92)
                        .add(p93)
                        .add(p94)
                        .add(p95)
                        .add(p96)
                        .add(p97)
                        .add(p98)
                        .add(p99)
                        .add(p100)
                        .add(Garita2)



                        .width(10f)
                        .color(Color.BLACK
                        )

        );


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();




        if (id==R.id.tarifas){
            Intent Tarifas = new Intent(this, popup.class);
            startActivity(Tarifas);
        }
        if (id==R.id.volver){
            Intent volver = new Intent(this, MainActivity.class);
            startActivity(volver);

        }



        return true;
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "Mi Ubicación", Toast.LENGTH_SHORT).show();

        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }



}


