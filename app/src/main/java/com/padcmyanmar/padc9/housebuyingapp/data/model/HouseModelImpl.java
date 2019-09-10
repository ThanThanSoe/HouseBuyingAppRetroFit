package com.padcmyanmar.padc9.housebuyingapp.data.model;

import android.content.Context;

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

    private HouseModelImpl(Context context) {
        super(context);
        houseRentingDataRepository = new HashMap<>();
    }

    public static void initializeEventModel (Context context){
        objInstance = new HouseModelImpl(context);
    }

    //public static HouseModelImpl getObjInstance(Context context){

    public static HouseModelImpl getObjInstance(){
        if (objInstance == null){
            //objInstance = new HouseModelImpl(context);
            throw new RuntimeException(HouseRentingConstants.EM_HOUSE_MODEL_NOT_INITIAL);
        }
        return objInstance;
    }

    public List<HouseRentingVO> getAllEvent(){
        return null;
    }
    @Override
    public void getEvent(final GetEventFromDataLayerDelegate delegate) {
        /*mDataAgent.getEvents(HouseRentingConstants.DUMMY_ACCESS_TOKEN,new EventDataAgent.GetEventForNetworkDelegate() {

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
        });*/

        if(mDatabase.areEventsExistInDB()){
            List<HouseRentingVO> houseFromDB = mDatabase.houseDao().getAllHousesFromDB();
            delegate.onSuccess(houseFromDB);
        }else {
            mDataAgent.getEvents(HouseRentingConstants.DUMMY_ACCESS_TOKEN, new EventDataAgent.GetEventForNetworkDelegate() {
                @Override
                public void onSuccess(List<HouseRentingVO> event) {
                    mDatabase.houseDao().insertHouse(event);
                    delegate.onSuccess(event);
                }

                @Override
                public void onFailure(String errorMessage) {
                    delegate.onFailure(errorMessage);
                }
            });
        }
    }

    @Override
    public HouseRentingVO findHouseById(int houseId) {
        //HouseRentingVO houseVO = houseRentingDataRepository.get(houseId);
        HouseRentingVO houseVO = mDatabase.houseDao().getEventById(houseId);
        return houseVO;
    }

    public List<HouseRentingVO> getDataRepository(){
        List<HouseRentingVO> houseVOList = new ArrayList<>(mDatabase.houseDao().getAllHousesFromDB());//houseRentingDataRepository.values()
        return houseVOList;
    }

    public List<HouseRentingVO> findHouseByName(String name){
        List<HouseRentingVO> houseRentingVOList = new ArrayList<>(mDatabase.houseDao().getAllHousesFromDB());//houseRentingDataRepository.values()
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
