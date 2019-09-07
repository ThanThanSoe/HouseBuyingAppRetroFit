package com.padcmyanmar.padc9.housebuyingapp.network;

import com.padcmyanmar.padc9.housebuyingapp.network.responses.GetEventResponse;
import com.padcmyanmar.padc9.housebuyingapp.utils.HouseRentingConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HouseApi {
    @FormUrlEncoded
    @POST(HouseRentingConstants.GET_HOUSES)
    Call<GetEventResponse> getAllHouses(@Field(HouseRentingConstants.ACCESS_TOKEN) String accessToken);
}

