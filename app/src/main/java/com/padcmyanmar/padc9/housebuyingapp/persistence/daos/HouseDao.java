package com.padcmyanmar.padc9.housebuyingapp.persistence.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.padcmyanmar.padc9.housebuyingapp.data.vos.HouseRentingVO;

import java.util.List;

@Dao
public abstract class HouseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long[] insertHouse(List<HouseRentingVO> houses);

    @Query("SELECT * FROM house")
    public abstract List<HouseRentingVO> getAllHousesFromDB();

    @Query("SELECT * FROM house WHERE id=:id")
    public abstract HouseRentingVO getEventById(int id);
}
