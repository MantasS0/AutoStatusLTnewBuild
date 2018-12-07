package com.example.ms.autostatuslt;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface Room_Data_DataAccessObject {
    @Insert
    void insert(Room_Data roomData_entry);
    @Query("SELECT * from Data_table")
    LiveData<List<Room_Data>> getAllData();
    @Query("DELETE FROM Data_table")
    void deleteAll();
    @Query("DELETE FROM Data_table WHERE vehicleSelectedCounter = :vehicleCounter")
    void deleteVehicleEntries(int vehicleCounter);
    @Query("SELECT * FROM Data_table WHERE vehicleSelectedCounter = :vehicleCounter")
    List<Room_Data> findVehicle(int vehicleCounter);
    @Query("SELECT * FROM Data_table WHERE vehicleSelectedCounter = :vehicleCounter IN (Liters)")
    LiveData<List<Room_Data>> getAllLiters(int vehicleCounter);
    @Query("SELECT * FROM Data_table WHERE vehicleSelectedCounter = :vehicleCounter IN (Price)")
    LiveData<List<Room_Data>> getAllPrice(int vehicleCounter);
    @Query("SELECT * FROM Data_table WHERE vehicleSelectedCounter = :vehicleCounter IN (Distance)")
    LiveData<List<Room_Data>> getAllDistance(int vehicleCounter);
}
