package com.justapptechnology.mydesignpattern;

import android.content.Intent;
import android.location.Location;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.justapptechnology.mydesignpattern.interface_helper.LocationInterface;
import com.justapptechnology.mydesignpattern.liveview_model.LocationLiveModel;
import com.justapptechnology.mydesignpattern.service_helper.LocationService;

public class DetatlItemActivity extends AppCompatActivity implements OnMapReadyCallback, LocationInterface {

    private static final String TAG = "TAG";
    View view;
    LinearLayout main_layout;
    private GoogleMap mMap;

    private LocationInterface locationInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_item_activity);

        Log.i(TAG,"onCreate_DetatlItemActivity");

        main_layout = findViewById(R.id.main_layout);
        locationInterface = this;

//        LocationService locationService = new LocationService(locationInterface);
        startService(new Intent(DetatlItemActivity.this, LocationService.class));


        FragmentManager fragmentManager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

//        LayoutInflater inflater = getLayoutInflater();
//        view = inflater.inflate(R.layout.action_bar_universal,main_layout,false);
        ImageView back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ad = new Intent();
                ad.putExtra("Manage","demo");
                setResult(2,ad);
                onBackPressed();
            }
        });

        registerLiveData();
    }

    private void registerLiveData() {

        LocationLiveModel model = ViewModelProviders.of(DetatlItemActivity.this).get(LocationLiveModel.class);

        model.getLocation().observe(DetatlItemActivity.this, new Observer<Location>() {
            @Override
            public void onChanged(Location location) {

                Log.i(TAG,"--->"+location.getLatitude()+"\n"+location.getLongitude());
            }
        });

    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    finishAndRemoveTask();
                }else {
                    finishAffinity();
                }

            }
        },200);

    }

    @Override
    public void finishAffinity() {
        overridePendingTransition(0,0);
        super.finishAffinity();
    }

    @Override
    public void finishAndRemoveTask() {
        overridePendingTransition(0,0);
        super.finishAndRemoveTask();
    }

    @Override
    public void finish() {
        overridePendingTransition(0,0);
        super.finish();
    }


    @Override
    protected void onStop() {
        Log.i(TAG,"stop_DetatlItemActivity");
        super.onStop();
    }

    @Override
    protected void onStart() {
        Log.i(TAG,"onStart_DetatlItemActivity");
        super.onStart();
    }


    @Override
    protected void onPause() {
        Log.i(TAG,"onPause_DetatlItemActivity");
        super.onPause();
    }


    @Override
    protected void onResume() {
        Log.i(TAG,"onResume_DetatlItemActivity");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG,"onRestart_DetatlItemActivity");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"onDestroy_DetatlItemActivity");
        super.onDestroy();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(false);


        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onLocationUpdate(Location location) {

        Log.i(TAG,"---"+location.getLatitude()+"\n"+location.getLongitude());

        mMap.clear();
        LatLng sydney = new LatLng(location.getLatitude(),location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(sydney).title("ABC"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}
