package com.padcmyanmar.padc9.housebuyingapp.network.dataagents;

import com.padcmyanmar.padc9.housebuyingapp.data.vos.HouseRentingVO;

import java.util.List;

public interface EventDataAgent {
    void getEvents(String accessToken,GetEventForNetworkDelegate delegate);

    interface GetEventForNetworkDelegate{
        void onSuccess(List<HouseRentingVO> event);
        void onFailure(String errorMessage);
    }
}

