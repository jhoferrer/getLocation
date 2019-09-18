package com.example.notebook.mypao;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class telalocal extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String TAG = telalocal.class.getSimpleName();
    private boolean mLocationPermissionGranted;
    boolean local = false;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final int DEFAULT_ZOOM = 20;
    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private Location mLastKnownLocation;
    private CameraPosition mCameraPosition;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        setContentView(R.layout.activity_telalocal);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }

    public void onMapReady(GoogleMap map) {
        mMap = map;
        getLocationPermission();
        updateLocationUI();
        getDeviceLocation();
        finish();
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.*/

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */

            try {
                if (mLocationPermissionGranted) {
                        Task<Location> locationResult;
                        locationResult = mFusedLocationProviderClient.getLastLocation();
                        locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task2) {
                                if (task2.isSuccessful()) {
                                    // Set the map's camera position to the current location of the device.
                                    mLastKnownLocation = task2.getResult();
                                    if (mLastKnownLocation != null) {
                                        callIntentService(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());
                                    }
                                } else {
                                    Log.d(TAG, "Current location is null. Using defaults.");
                                    Log.e(TAG, "Exception: %s", task2.getException());
                                }
                            }
                        });
                }
            } catch (SecurityException e) {
                Log.e("Exception: %s", e.getMessage());
            }
    }


    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                mMap.getUiSettings().setZoomControlsEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }
    public void callIntentService(double lat, double lon) {
        Intent pega_address = new Intent(this, LocationIntentService.class);
        pega_address.putExtra("lat", lat);
        pega_address.putExtra("lon", lon);
        startService(pega_address);
    }
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults){
        switch (requestCode){
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:{
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
                else
                    finish();
            }
        }
    }
}



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.*/


