package com.example.ms.autostatuslt;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static Integer vehicleSelectedCounter = 1;

    public static String vehicleName_1 = "Vehicle 1";
    public static String vehicleName_2 = "Vehicle 2";
    public static String vehicleName_3 = "Vehicle 3";


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
}
