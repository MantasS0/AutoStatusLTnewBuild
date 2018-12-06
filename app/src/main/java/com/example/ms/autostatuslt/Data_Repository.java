package com.example.ms.autostatuslt;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class Data_Repository {

    private Room_Data_DataAccessObject mDataDAO;
    private LiveData<List<Room_Data>> mAllData;

    Data_Repository(Application application){
        Room_Database db = Room_Database.getDatabase(application);
        mDataDAO = db.room_data_dataAccessObject();
        mAllData = mDataDAO.getAllData();
    }

    LiveData<List<Room_Data>> getAllData() {
        return mAllData;
    }

    public void insert (Room_Data room_data){ new insertAsyncTask(mDataDAO).execute(room_data);
    }

    private static class insertAsyncTask extends AsyncTask<Room_Data, Void, Void> {

        private Room_Data_DataAccessObject mAsyncTaskDao;

        insertAsyncTask(Room_Data_DataAccessObject dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Room_Data... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
