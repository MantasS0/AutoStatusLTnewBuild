package com.example.ms.autostatuslt;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class DataViewModel extends AndroidViewModel {

    private Data_Repository mRepository;
    private LiveData<List<Room_Data>> mAllData;

    public DataViewModel (Application application) {
        super(application);
        mRepository = new Data_Repository(application);
        mAllData = mRepository.getAllData();
    }

    LiveData<List<Room_Data>> getAllData() {return mAllData;}

    public void insert(Room_Data roomData) {mRepository.insert(roomData);}
}
