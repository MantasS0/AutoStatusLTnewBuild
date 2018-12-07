package com.example.ms.autostatuslt;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import java.util.List;
import static com.example.ms.autostatuslt.MainActivity.vehicleSelectedCounter;

public class Data_Repository implements AsyncResult {

    private Room_Data_DataAccessObject mDataDAO;
    private LiveData<List<Room_Data>> mAllData;
    private MutableLiveData<List<Room_Data>> searchResults = new MutableLiveData<>();
    private LiveData<List<Room_Data>> vehicleLiters; //= new MutableLiveData<>();
    private LiveData<List<Room_Data>> vehiclePrice;
    private LiveData<List<Room_Data>> vehicleDistance;

    Data_Repository(Application application) {
        Room_Database db = Room_Database.getDatabase(application);
        mDataDAO = db.room_data_dataAccessObject();
        mAllData = mDataDAO.getAllData();
        vehicleLiters = mDataDAO.getAllLiters(vehicleSelectedCounter);
        vehiclePrice = mDataDAO.getAllPrice(vehicleSelectedCounter);
        vehiclePrice = mDataDAO.getAllDistance(vehicleSelectedCounter);
    }

    public void insertEntry(Room_Data room_data) {
        new queryAsyncTask.insertAsyncTask(mDataDAO).execute(room_data);
    }

    public void deleteVehicle(Integer vehicleCounter) {
        new queryAsyncTask.deleteAsyncTask(mDataDAO).execute(vehicleCounter);
    }

    public void findVehicleData(Integer vehicleCounter) {
        queryAsyncTask task = new queryAsyncTask(mDataDAO);
        task.delegate = this;
        task.execute(vehicleCounter);
    }

    public LiveData<List<Room_Data>> getAllData() {
        return mAllData;
    }

    public MutableLiveData<List<Room_Data>> getVehicleDataResults() {
        return searchResults;}
    public LiveData<List<Room_Data>> getVehicleLiters() {return vehicleLiters;}
    public LiveData<List<Room_Data>> getVehiclePrice() {return vehiclePrice;}
    public LiveData<List<Room_Data>> getVehicleDistance() {return vehicleDistance;}
//
//    public void insert(Room_Data room_data) {
//        new insertAsyncTask(mDataDAO).execute(room_data);
//    }
//
//    private static class insertAsyncTask extends AsyncTask<Room_Data, Void, Void> {
//
//        private Room_Data_DataAccessObject mAsyncTaskDao;
//
//
//        insertAsyncTask(Room_Data_DataAccessObject dao) {
//            mAsyncTaskDao = dao;
//        }
//        @Override
//        protected Void doInBackground(final Room_Data... params) {
//            mAsyncTaskDao.insert(params[0]);
//            return null;
//        }
//
//    }

    @Override
    public void asyncFinished(List<Room_Data> results) {
        searchResults.setValue(results);
    }

    private static class queryAsyncTask extends AsyncTask<Integer, Void, List<Room_Data>> {

        private Room_Data_DataAccessObject asyncTaskDao;
        private Data_Repository delegate = null;

        queryAsyncTask(Room_Data_DataAccessObject dao) {asyncTaskDao = dao;}

        @Override
        protected List<Room_Data> doInBackground(final Integer... params) {
            return asyncTaskDao.findVehicle(params[0]);
        }

        @Override
        protected void onPostExecute(List<Room_Data> result) {
            delegate.asyncFinished(result);
        }

        private static class insertAsyncTask extends AsyncTask<Room_Data, Void, Void> {

            private Room_Data_DataAccessObject asyncTaskDao;

            insertAsyncTask(Room_Data_DataAccessObject dao) {
                asyncTaskDao = dao;
            }

            @Override
            protected Void doInBackground(final Room_Data... params) {
                asyncTaskDao.insert(params[0]);
                return null;
            }
        }

        private static class deleteAsyncTask extends AsyncTask<Integer, Void, Void> {

            private Room_Data_DataAccessObject asyncTaskDao;

            deleteAsyncTask(Room_Data_DataAccessObject dao) {
                asyncTaskDao = dao;
            }

            @Override
            protected Void doInBackground(final Integer... params) {
                asyncTaskDao.deleteVehicleEntries(params[0]);
                return null;
            }
        }
    }

}
