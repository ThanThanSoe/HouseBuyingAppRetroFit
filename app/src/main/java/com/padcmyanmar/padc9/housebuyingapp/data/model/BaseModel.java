package com.padcmyanmar.padc9.housebuyingapp.data.model;

import com.padcmyanmar.padc9.housebuyingapp.network.dataagents.RetrofitDataAgentImpl;

public abstract class BaseModel {
    /*protected HttpUrlConnectionDataAgentImpl mDataAgent;
    BaseModel() { this.mDataAgent = HttpUrlConnectionDataAgentImpl.getObjInstance(); }*/

    /*protected OkHttpDataAgentImpl mDataAgent;
    BaseModel() { this.mDataAgent = OkHttpDataAgentImpl.getObjInstance(); }*/

    protected RetrofitDataAgentImpl mDataAgent;
    BaseModel() { this.mDataAgent = RetrofitDataAgentImpl.getObjInstance(); }
}
