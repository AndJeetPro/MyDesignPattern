package com.justapptechnology.mydesignpattern.broadcast_helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.justapptechnology.mydesignpattern.interface_helper.LocationInterface;

public class LatLangBroadCastReceiver extends BroadcastReceiver {

    private LocationInterface locationInterface;

    @Override
    public void onReceive(Context context, Intent intent) {

        locationInterface = (LocationInterface) context;

    }
}
