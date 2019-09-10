package com.padcmyanmar.padc9.housebuyingapp;

import android.app.Application;

import com.padcmyanmar.padc9.housebuyingapp.data.model.HouseModelImpl;

public class HousesApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HouseModelImpl.initializeEventModel(getApplicationContext());
    }
}
