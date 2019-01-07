package com.example.ms.autostatuslt;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Room_Data.class}, version = 1)
public abstract class Room_Database extends RoomDatabase {

    public abstract Room_Data_DataAccessObject room_data_dataAccessObject();

    private static Room_Database INSTANCE; //was volatile

    static Room_Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Room_Database.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Room_Database.class, "room_database")
//                            .allowMainThreadQueries()  //this should not be here. I only put it until further notice, to test other functionality of the app.
//                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

//    private static RoomDatabase.Callback sRoomDatabaseCallback =
//            new RoomDatabase.Callback() {
//
//                @Override
//                public void onCreate(@NonNull SupportSQLiteDatabase db) {   // cia buvo onOpen, dabar pakeiciau i onCreate
//                    super.onCreate(db);
//                    new PopulateDbAsync(INSTANCE).execute();
//                }
//            };


//    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
//
//        private final Room_Data_DataAccessObject mDao;
//
//        PopulateDbAsync(Room_Database db) {
//            mDao = db.room_data_dataAccessObject();
//        }
//
//
//        @Override
//        protected Void doInBackground(final Void... params) {
//
//            Room_Data roomData = new Room_Data(20.35f, 22.95f, 200, "2018.12.05");
//            mDao.insert(roomData);
//            roomData = new Room_Data(50.00f, 80.10f, 500, "2018.12.08");
//            mDao.insert(roomData);
//
//            return null;
//        }
//    }


}




