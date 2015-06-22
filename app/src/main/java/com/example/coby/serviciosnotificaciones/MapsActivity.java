package com.example.coby.serviciosnotificaciones;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.SphericalUtil;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMapClickListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private final LatLng SANTIAGO = new LatLng(21.811999, -105.206034);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mMap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SANTIAGO, 15));
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.addMarker(new MarkerOptions().position(SANTIAGO).title("UT").snippet("Universidad Tecnologica")
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)).anchor(0.5f,0.5f));
        mMap.setOnMapClickListener(this);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }


    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    @Override
    public void onMapClick(LatLng puntoPulsado) {
        Marker nuevo = mMap.addMarker(new MarkerOptions().position(puntoPulsado).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        //donde estoy
        Location hola = mMap.getMyLocation();
        ////
        double metros = SphericalUtil.computeDistanceBetween(SANTIAGO, nuevo.getPosition());
        double desdeMiDistancia = SphericalUtil.computeDistanceBetween(new LatLng(hola.getLatitude(),hola.getLongitude()), nuevo.getPosition());
        Toast.makeText(this,"Distancia en " + metros, Toast.LENGTH_LONG).show();
        Toast.makeText(this,"Mi Distancia en " + desdeMiDistancia, Toast.LENGTH_LONG).show();
    }
}
