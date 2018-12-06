package com.example.ms.autostatuslt;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Insert;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.example.ms.autostatuslt.MainActivity.NEW_WORD_ACTIVITY_REQUEST_CODE;
import static com.example.ms.autostatuslt.MainActivity.currentVehicle;
import static com.example.ms.autostatuslt.MainActivity.vehicleName_1;
import static com.example.ms.autostatuslt.MainActivity.vehicleName_2;
import static com.example.ms.autostatuslt.MainActivity.vehicleName_3;
import static com.example.ms.autostatuslt.MainActivity.vehicleSelectedCounter;

public class mainFragment extends Fragment {

//    public static final Room_Data EXTRA_REPLY = ;

    private Button buttonNavigateSettingsFragment;
    private Button buttonNavigateStatisticsFragment;
    private Button buttonAddToDatabase;

    private EditText editText_liters;
    private EditText editText_price;
    private EditText editText_distance;
    private EditText editText_date;


    private DataViewModel mViewModel;
    private Room_DataListAdapter adapter;

    private void listenerSetup() {
        Button buttonAddToDatabase = getView().findViewById(R.id.button_addToDataBase);


        buttonAddToDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String liters = editText_liters.getText().toString();
                String price = editText_price.getText().toString();
                String distance = editText_distance.getText().toString();
                String date = editText_date.getText().toString();

                if (TextUtils.isEmpty(editText_liters.getText())
                        || TextUtils.isEmpty(editText_price.getText())
                        || TextUtils.isEmpty(editText_distance.getText())
                        || TextUtils.isEmpty(editText_date.getText())) {
                    Toast.makeText(getActivity(), "Please fill in all the fields.", Toast.LENGTH_SHORT).show();
                } else {
                    Room_Data roomData = new Room_Data(
                            Float.parseFloat(liters),
                            Float.parseFloat(price),
                            Integer.parseInt(distance),
                            date);

                    mViewModel.insert(roomData);
                    clearFields();
                }
            }
        });
    }

//    private void observerSetup() {
//
//        mViewModel.getAllData().observe(this, new Observer<List<Room_Data>>() {
//            @Override
//            public void onChanged(@Nullable final List<Room_Data> data) {
//                adapter.setRoomData(data);
//            }
//        });
//
//
//    }


    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        editText_liters = getView().findViewById(R.id.textEdit_liters);
        editText_price = getView().findViewById(R.id.textEdit_price);
        editText_distance = getView().findViewById(R.id.textEdit_kilometers);
        editText_date = getView().findViewById(R.id.textEdit_date);

        listenerSetup();
//        observerSetup();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
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

        if (vehicleSelectedCounter == 1) {
            currentVehicle = vehicleName_1;
        } else if (vehicleSelectedCounter == 2) {
            currentVehicle = vehicleName_2;
        } else if (vehicleSelectedCounter == 3) {
            currentVehicle = vehicleName_3;
        }


        TextView textViewVehicleName = view.findViewById(R.id.text_main_vehicleName);
        textViewVehicleName.setText(currentVehicle);

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


//        editText_liters = view.findViewById(R.id.textEdit_liters);
//        editText_price = view.findViewById(R.id.textEdit_price);
//        editText_distance = view.findViewById(R.id.textEdit_kilometers);
//        editText_date = view.findViewById(R.id.textEdit_date);
//
//        buttonAddToDatabase.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Entry added to " + currentVehicle + " statistics.", Toast.LENGTH_SHORT).show();
//
////                Intent replyIntent = new Intent();
//                if (TextUtils.isEmpty(editText_liters.getText())
//                        || TextUtils.isEmpty(editText_price.getText())
//                        || TextUtils.isEmpty(editText_distance.getText())
//                        || TextUtils.isEmpty(editText_date.getText())) {
//                    Toast.makeText(getActivity(), "Please fill in all the fields.", Toast.LENGTH_SHORT).show();
//                } else {
//                    Room_Data roomData = new Room_Data(
//                            Float.parseFloat(editText_liters.getText().toString()),
//                            Float.parseFloat(editText_price.getText().toString()),
//                            Integer.parseInt(editText_distance.getText().toString()),
//                            editText_date.getText().toString());
//
////                    AddToDatabase.execute(roomData);
//
////                    replyIntent.putExtra(EXTRA_REPLY, roomData);
////                    setResult(RESULT_OK, replyIntent);
////                    Room_Database.getDatabase(getContext()).room_data_dataAccessObject().insert(roomData);
////                    AsyncTask.execute(runnable);
//
//                    mViewModel.insert(roomData);
//
//                    clearFields();
//
//                }
//            }
//        });


        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

    private void clearFields() {
        editText_liters.setText("");
        editText_price.setText("");
        editText_distance.setText("");
        editText_date.setText("");
    }

}
