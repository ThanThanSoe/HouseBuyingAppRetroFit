package com.padcmyanmar.padc9.housebuyingapp.data.model;

import com.padcmyanmar.padc9.housebuyingapp.data.vos.HouseRentingVO;
import com.padcmyanmar.padc9.housebuyingapp.network.dataagents.EventDataAgent;
import com.padcmyanmar.padc9.housebuyingapp.utils.HouseRentingConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HouseModelImpl extends BaseModel implements EventModel{
    private Map<Integer, HouseRentingVO> houseRentingDataRepository;
    private static HouseModelImpl objInstance;

    private HouseModelImpl() {
        houseRentingDataRepository = new HashMap<>();
    }

    public static HouseModelImpl getObjInstance(){
        if (objInstance == null){
            objInstance = new HouseModelImpl();
        }
        return objInstance;
    }

    public List<HouseRentingVO> getAllEvent(){
        return null;
    }
    @Override
    public void getEvent(final GetEventFromDataLayerDelegate delegate) {
        mDataAgent.getEvents(HouseRentingConstants.DUMMY_ACCESS_TOKEN,new EventDataAgent.GetEventForNetworkDelegate() {

            @Override
            public void onSuccess(List<HouseRentingVO> event) {
                for (HouseRentingVO houseRentingVO : event){
                    houseRentingDataRepository.put(houseRentingVO.getId(),houseRentingVO);
                }
                delegate.onSuccess(event);
            }

            @Override
            public void onFailure(String errorMessage) {
                delegate.onFailure(errorMessage);
            }
        });
    }

    @Override
    public HouseRentingVO findHouseById(int houseId) {
        HouseRentingVO houseVO = houseRentingDataRepository.get(houseId);
        return houseVO;
    }

    public List<HouseRentingVO> getDataRepository(){
        List<HouseRentingVO> houseVOList = new ArrayList<>(houseRentingDataRepository.values());
        return houseVOList;
    }

    public List<HouseRentingVO> findHouseByName(String name){
        List<HouseRentingVO> houseRentingVOList = new ArrayList<>(houseRentingDataRepository.values());
        List<HouseRentingVO> returnList = new ArrayList<>();
        for (HouseRentingVO houseRentVO :
                houseRentingVOList) {
            if(houseRentVO.getName().contains(name)){
                returnList.add(houseRentVO);
            }
        }
        return returnList;
    }

}
