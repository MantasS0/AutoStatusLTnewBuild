package com.example.ms.autostatuslt;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.ms.autostatuslt.MainActivity.vehicleName_1;
import static com.example.ms.autostatuslt.MainActivity.vehicleName_2;
import static com.example.ms.autostatuslt.MainActivity.vehicleName_3;
import static com.example.ms.autostatuslt.MainActivity.vehicleSelectedCounter;

public class mainFragment extends Fragment {

    private Button buttonNavigateSettingsFragment;
    private Button buttonNavigateStatisticsFragment;
    private Button buttonAddToDatabase;

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        buttonNavigateSettingsFragment = view.findViewById(R.id.button_settingsFragment);
        buttonNavigateStatisticsFragment = view.findViewById(R.id.button_statisticsFragment);
        buttonAddToDatabase = view.findViewById(R.id.button_addToDataBase);

        TextView textViewVehicleName = view.findViewById(R.id.text_main_vehicleName);
        if (vehicleSelectedCounter == 1) {
            textViewVehicleName.setText(vehicleName_1);
        } else if (vehicleSelectedCounter == 2) {
            textViewVehicleName.setText(vehicleName_2);
        } else if (vehicleSelectedCounter == 3) {
            textViewVehicleName.setText(vehicleName_3);
        }


        buttonNavigateSettingsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Going to settings", Toast.LENGTH_SHORT).show();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, new settingsFragment());
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack("Settings fragment");
                ft.commit();
            }
        });

        buttonNavigateStatisticsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Going to statistics", Toast.LENGTH_SHORT).show();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, new statisticsFragment());
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack("Statistics fragment");
                ft.commit();
            }
        });

        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

}
