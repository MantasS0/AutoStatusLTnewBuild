package com.example.ms.autostatuslt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.example.ms.autostatuslt.MainActivity.currentVehicle;
import static com.example.ms.autostatuslt.MainActivity.vehicleName_1;
import static com.example.ms.autostatuslt.MainActivity.vehicleName_2;
import static com.example.ms.autostatuslt.MainActivity.vehicleName_3;
import static com.example.ms.autostatuslt.MainActivity.vehicleSelectedCounter;

public class mainFragment extends Fragment {

    private Button buttonNavigateSettingsFragment;
    private Button buttonNavigateStatisticsFragment;
    private Button buttonAddToDatabase;

    private EditText editText_liters;
    private EditText editText_price;
    private EditText editText_distance;
    private EditText editText_date;

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


        editText_liters = view.findViewById(R.id.textEdit_liters);
        editText_price = view.findViewById(R.id.textEdit_price);
        editText_distance = view.findViewById(R.id.textEdit_kilometers);
        editText_date = view.findViewById(R.id.textEdit_date);

        buttonAddToDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Entry added to " + currentVehicle + " statistics.", Toast.LENGTH_SHORT).show();
                Room_Data roomData = new Room_Data(
                        Float.parseFloat(editText_liters.getText().toString()),
                        Float.parseFloat(editText_price.getText().toString()),
                        Integer.parseInt(editText_distance.getText().toString()),
                        editText_date.getText().toString());
                Room_Database.getDatabase(getContext()).room_data_dataAccessObject().insert(roomData);
                editText_liters.setText("");
                editText_price.setText("");
                editText_distance.setText("");
                editText_date.setText("");

                //Integer.parseInt(Room_Data.getId().toString()),

//                Intent replyIntent = new Intent();
//                if (TextUtils.isEmpty(mEditWordView.getText())) {
//                    setResult(RESULT_CANCELED, replyIntent);
//                } else {
//                    String word = mEditWordView.getText().toString();
//                    replyIntent.putExtra(EXTRA_REPLY, word);
//                    setResult(RESULT_OK, replyIntent);
//                }
//                finish();
            }
        });

        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

}
