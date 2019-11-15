package com.justapptechnology.mydesignpattern.liveview_model;

import android.location.Location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LocationLiveModel extends ViewModel {


    private MutableLiveData<Location> locationMutableLiveData = new MutableLiveData<>();

    public LiveData<Location> getLocation(){
        if (locationMutableLiveData==null){
            locationMutableLiveData = new MutableLiveData<>();
//            locationMutableLiveData;
        }
        return locationMutableLiveData;
    }

    public void setLocationMutableLiveData(Location location) {
//        locationMutableLiveData = new MutableLiveData<>();
        locationMutableLiveData.postValue(location);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
