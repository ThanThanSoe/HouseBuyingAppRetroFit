package com.padcmyanmar.padc9.housebuyingapp.network.responses;

import com.google.gson.annotations.SerializedName;
import com.padcmyanmar.padc9.housebuyingapp.data.vos.HouseRentingVO;
import com.padcmyanmar.padc9.housebuyingapp.utils.HouseRentingConstants;

import java.util.List;

public class GetEventResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<HouseRentingVO> houseRentingList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<HouseRentingVO> getHouseRentingList() {
        return houseRentingList;
    }

    public void setHouseRentingList(List<HouseRentingVO> houseRentingList) {
        this.houseRentingList = houseRentingList;
    }

    public Boolean isResponseOK(){
        return code == HouseRentingConstants.CODE_RESPONSE_OK && houseRentingList != null;
    }



}

