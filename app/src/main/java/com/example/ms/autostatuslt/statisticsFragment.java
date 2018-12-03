package com.example.ms.autostatuslt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.ms.autostatuslt.MainActivity.vehicleName_1;
import static com.example.ms.autostatuslt.MainActivity.vehicleName_2;
import static com.example.ms.autostatuslt.MainActivity.vehicleName_3;
import static com.example.ms.autostatuslt.MainActivity.vehicleSelectedCounter;

public class statisticsFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        TextView textViewVehicleName = view.findViewById(R.id.text_statistics_vehicleName);
        if (vehicleSelectedCounter == 1) {
            textViewVehicleName.setText(vehicleName_1);
        } else if (vehicleSelectedCounter == 2) {
            textViewVehicleName.setText(vehicleName_2);
        } else if (vehicleSelectedCounter == 3) {
            textViewVehicleName.setText(vehicleName_3);
        }

        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

}
