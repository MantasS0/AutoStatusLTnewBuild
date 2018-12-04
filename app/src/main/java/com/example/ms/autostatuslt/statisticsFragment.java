package com.example.ms.autostatuslt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.ms.autostatuslt.MainActivity.currentVehicle;
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
        View rootView = inflater.inflate(R.layout.fragment_statistics, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final Room_DataListAdapter adapter = new Room_DataListAdapter(getActivity());
        recyclerView.setAdapter(adapter);


        return rootView;
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        TextView textViewVehicleName = view.findViewById(R.id.text_statistics_vehicleName);
        textViewVehicleName.setText(currentVehicle);

        //11 uzduoties lapo galas. neaisku kaip veikia.
//        RecyclerView recyclerView = findViewById(R.id.recyclerview);
//        final Room_DataListAdapter adapter = new Room_DataListAdapter(this);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

}
