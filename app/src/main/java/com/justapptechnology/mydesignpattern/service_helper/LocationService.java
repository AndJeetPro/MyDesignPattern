package com.justapptechnology.mydesignpattern.service_helper;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.justapptechnology.mydesignpattern.constant_helper.ConstantHelper;
import com.justapptechnology.mydesignpattern.interface_helper.LocationInterface;
import com.justapptechnology.mydesignpattern.liveview_model.LocationLiveModel;

public class LocationService extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener{


    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private LocationManager locationManager;
    private double latitude;
    private double longitude;
    private Context mContext;
    private int mInterval = 10;
    private boolean isGpsEnabled = false;
    private boolean isNetworkProvider = false;
    private Location location;
    private LocationInterface locationInterface;


//    public LocationService(LocationInterface locationInterface) {
//        this.locationInterface = locationInterface;
//    }

    private void createLocationRequest(){
        locationRequest = new LocationRequest();
        locationRequest.setInterval(mInterval*1000);
        locationRequest.setFastestInterval((mInterval/2)*1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        locationRequest.setSmallestDisplacement(ConstantHelper.MIN_DISTANCE_CHANGE_FOR_UPDATES);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = LocationService.this;
//        locationInterface = LocationService.this;
        createLocationRequest();
        getLocation();
    }

    private void getLocation() {

        locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
        isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkProvider = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);


        if (isGpsEnabled){

            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED){



            }else {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        ConstantHelper.MIN_TIME_BW_UPDATES,
                        ConstantHelper.MIN_DISTANCE_CHANGE_FOR_UPDATES,LocationService.this);

                if (locationManager!=null){

                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    startApiClient();
                    if (location!=null){
//                        locationInterface.onLocationUpdate(location);
                        LocationLiveModel locationLiveModel = new LocationLiveModel();
                        locationLiveModel.setLocationMutableLiveData(location);
                        Log.i("TAG",""+location.getLongitude()+""+location.getLatitude());
                    }
                }
            }

        }else if (isNetworkProvider){

            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED){



            }else {

                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        ConstantHelper.MIN_TIME_BW_UPDATES,
                        ConstantHelper.MIN_DISTANCE_CHANGE_FOR_UPDATES,LocationService.this);

                if (locationManager!=null){

                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    startApiClient();
                    if (location!=null){
//                        locationInterface.onLocationUpdate(location);
                        LocationLiveModel locationLiveModel = new LocationLiveModel();
                        locationLiveModel.setLocationMutableLiveData(location);
                        Log.i("TAG",""+location.getLongitude()+""+location.getLatitude());
                    }

                }
            }
        }else {



        }

    }

    private void startApiClient(){

        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(LocationService.this) == ConnectionResult.SUCCESS){
            googleApiClient = new GoogleApiClient.Builder(LocationService.this).addApi(LocationServices.API).addConnectionCallbacks(LocationService.this)
                    .addOnConnectionFailedListener(LocationService.this).build();

            if (!googleApiClient.isConnected() || !googleApiClient.isConnecting()){
                googleApiClient.connect();
            }

        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onLocationChanged(Location location) {

        if (location!=null){
//            locationInterface.onLocationUpdate(location);
            Log.i("TAG",""+location.getLongitude()+""+location.getLatitude());
            LocationLiveModel locationLiveModel = new LocationLiveModel();
            locationLiveModel.setLocationMutableLiveData(location);
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}
