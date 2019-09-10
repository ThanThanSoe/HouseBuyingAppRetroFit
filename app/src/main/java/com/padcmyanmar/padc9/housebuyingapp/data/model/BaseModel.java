package com.padcmyanmar.padc9.housebuyingapp.data.model;

import android.content.Context;

import com.padcmyanmar.padc9.housebuyingapp.network.dataagents.RetrofitDataAgentImpl;
import com.padcmyanmar.padc9.housebuyingapp.persistence.HousesDatabase;

public abstract class BaseModel {
    /*protected HttpUrlConnectionDataAgentImpl mDataAgent;
    BaseModel() { this.mDataAgent = HttpUrlConnectionDataAgentImpl.getObjInstance(); }*/

    /*protected OkHttpDataAgentImpl mDataAgent;
    BaseModel() { this.mDataAgent = OkHttpDataAgentImpl.getObjInstance(); }*/

    protected RetrofitDataAgentImpl mDataAgent;

    protected HousesDatabase mDatabase;
    BaseModel(Context context) {
        this.mDataAgent = RetrofitDataAgentImpl.getObjInstance();
        mDatabase = HousesDatabase.getObjInstance(context);
    }
}
