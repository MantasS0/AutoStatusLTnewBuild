package com.example.ms.autostatuslt;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DataViewModel mDataViewModel;

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    public static Integer vehicleSelectedCounter = 1;

    public static String vehicleName_1 = "Vehicle 1";
    public static String vehicleName_2 = "Vehicle 2";
    public static String vehicleName_3 = "Vehicle 3";

    public static String currentVehicle = vehicleName_1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container, new mainFragment());
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();





//        ft.add(R.id.mainFragment);
//        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//
//        ft.addToBackStack(getName());
//        ft.commit();

//         Replace the contents of the container with the new fragment
//        ft.replace(R.id.your_placeholder, new FooFragment());
//        // or ft.add(R.id.your_placeholder, new FooFragment());
//        // Complete the changes added above
//        ft.commit();
    }
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            Room_Data roomData = new Room_Data(data.getStringExtra(mainFragment.EXTRA_REPLY));
//            mDataViewModel.insert(roomData);
//        } else {
//            Toast.makeText(
//                    getApplicationContext(),
//                    R.string.empty_not_saved,
//                    Toast.LENGTH_LONG).show();
//        }
//    }
}
