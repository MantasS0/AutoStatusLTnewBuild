package com.example.ms.autostatuslt;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

public class DataViewModel extends AndroidViewModel {

    private Data_Repository mRepository;
    private LiveData<List<Room_Data>> mAllData;
    private MutableLiveData<List<Room_Data>> mFindVehicleData;
    private LiveData<List<Room_Data>> mVehicleLiters;
    private LiveData<List<Room_Data>> mVehiclePrice;
    private LiveData<List<Room_Data>> mVehicleDistance;

    public DataViewModel (Application application) {
        super(application);
        mRepository = new Data_Repository(application);
        mAllData = mRepository.getAllData();
        mFindVehicleData = mRepository.getVehicleDataResults();
        mVehicleLiters = mRepository.getVehicleLiters();
        mVehiclePrice = mRepository.getVehiclePrice();
        mVehicleDistance = mRepository.getVehicleDistance();

    }

    MutableLiveData<List<Room_Data>> getVehicleDataResults() {
        return mFindVehicleData;
    }

    LiveData<List<Room_Data>> getAllData() {return mAllData;}

    LiveData<List<Room_Data>> getVehicleLiters() {return mVehicleLiters;}
    LiveData<List<Room_Data>> getVehiclePrice() {return mVehiclePrice;}
    LiveData<List<Room_Data>> getVehicleDistance() {return mVehicleDistance;}

     public void insert(Room_Data roomData) {mRepository.insertEntry(roomData);}

    public void findVehicleData(int vehicleCounter) {
        mRepository.findVehicleData(vehicleCounter);
    }

    public void deleteVehicle(int vehicleCounter) {
        mRepository.deleteVehicle(vehicleCounter);
    }

}
