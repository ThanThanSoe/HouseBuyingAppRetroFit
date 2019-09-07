package com.padcmyanmar.padc9.housebuyingapp.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.padcmyanmar.padc9.housebuyingapp.data.model.HouseModelImpl;

public abstract class BaseActivity extends AppCompatActivity {
    protected HouseModelImpl houseModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        houseModel = HouseModelImpl.getObjInstance();
    }

    protected void showIndefiniteSnackBar(String message){
        final Snackbar snackbar = Snackbar.make(getWindow().getDecorView(),message,Snackbar.LENGTH_INDEFINITE);

    }
}

