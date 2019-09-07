package com.padcmyanmar.padc9.housebuyingapp.network.dataagents;

import com.padcmyanmar.padc9.housebuyingapp.network.HouseApi;
import com.padcmyanmar.padc9.housebuyingapp.network.responses.GetEventResponse;
import com.padcmyanmar.padc9.housebuyingapp.utils.HouseRentingConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataAgentImpl implements EventDataAgent{
    public static RetrofitDataAgentImpl objInstance;
    private HouseApi mHouseApi;
    public RetrofitDataAgentImpl(){
        final OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15,TimeUnit.SECONDS)
                .readTimeout(15,TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HouseRentingConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();

        mHouseApi = retrofit.create(HouseApi.class);
    }

    public static RetrofitDataAgentImpl getObjInstance(){
        if (objInstance == null){
            objInstance = new RetrofitDataAgentImpl();
        }return objInstance;
    }

    @Override
    public void getEvents(String accessToken,final GetEventForNetworkDelegate delegate) {
        Call<GetEventResponse> eventsCall =  mHouseApi.getAllHouses(accessToken);
        eventsCall.enqueue(new Callback<GetEventResponse>() {

            @Override
            public void onResponse(Call<GetEventResponse> call, Response<GetEventResponse> response) {
                GetEventResponse getEventsResponses = response.body();
                delegate.onSuccess(getEventsResponses.getHouseRentingList());
            }

            @Override
            public void onFailure(Call<GetEventResponse> call, Throwable t) {
                delegate.onFailure(t.getLocalizedMessage());
            }
        });
    }

}

