package com.padcmyanmar.padc9.housebuyingapp.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.padcmyanmar.padc9.housebuyingapp.data.vos.HouseRentingVO;
import com.padcmyanmar.padc9.housebuyingapp.persistence.daos.HouseDao;
import com.padcmyanmar.padc9.housebuyingapp.utils.HouseRentingConstants;

@Database(entities = {HouseRentingVO.class},version = 1, exportSchema = false)
public abstract class HousesDatabase extends RoomDatabase {
    public abstract HouseDao houseDao();

    private static HousesDatabase objInstance;

    public static HousesDatabase getObjInstance(Context context){
        if(objInstance == null){
            objInstance = Room.databaseBuilder(context, HousesDatabase.class, HouseRentingConstants.HOUSE_DB)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return objInstance;
    }

    public boolean areEventsExistInDB(){
        return !houseDao().getAllHousesFromDB().isEmpty();
    }
}
