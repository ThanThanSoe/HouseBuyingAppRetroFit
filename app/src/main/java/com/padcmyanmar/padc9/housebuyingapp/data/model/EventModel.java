package com.padcmyanmar.padc9.housebuyingapp.data.model;

import com.padcmyanmar.padc9.housebuyingapp.data.vos.HouseRentingVO;

import java.util.List;

public interface EventModel {
    void getEvent(GetEventFromDataLayerDelegate delegate);
    HouseRentingVO findHouseById(int houseId);

    interface GetEventFromDataLayerDelegate{
        void onSuccess(List<HouseRentingVO> event);
        void onFailure(String errorMessage);
    }
}
