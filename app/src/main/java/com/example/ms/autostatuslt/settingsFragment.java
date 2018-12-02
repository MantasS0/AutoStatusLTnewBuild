package com.example.ms.autostatuslt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static com.example.ms.autostatuslt.MainActivity.vehicleName_1;
import static com.example.ms.autostatuslt.MainActivity.vehicleName_2;
import static com.example.ms.autostatuslt.MainActivity.vehicleName_3;
import static com.example.ms.autostatuslt.MainActivity.vehicleSelectedCounter;


public class settingsFragment extends Fragment {

    public Integer vehicleRenameCounter = 1;

    public EditText editTextRenameVehicle;
    public String vehicleRenameFieldValue = "";


    private Button buttonSetVehicle1;
    private Button buttonSetVehicle2;
    private Button buttonSetVehicle3;
    private Button buttonRenameAuto;

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        buttonSetVehicle1 = view.findViewById(R.id.button_auto1);
        buttonSetVehicle1.setText(vehicleName_1);
        buttonSetVehicle2 = view.findViewById(R.id.button_auto2);
        buttonSetVehicle2.setText(vehicleName_2);
        buttonSetVehicle3 = view.findViewById(R.id.button_auto3);
        buttonSetVehicle3.setText(vehicleName_3);


        buttonRenameAuto = view.findViewById(R.id.button_renameAuto);

        buttonSetVehicle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), vehicleName_1 + " is selected", Toast.LENGTH_SHORT).show();
                vehicleSelectedCounter = 1;
            }
        });

        buttonSetVehicle1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getActivity(), "Please rename " + vehicleName_1 + " below", Toast.LENGTH_SHORT).show();
                vehicleRenameCounter = 1;
                view.findViewById(R.id.vehicleRenamingLayout).setVisibility(View.VISIBLE);
                return true;
            }
        });

        buttonSetVehicle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), vehicleName_2 + " is selected", Toast.LENGTH_SHORT).show();
                vehicleSelectedCounter = 2;
            }
        });

        buttonSetVehicle2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getActivity(), "Please rename " + vehicleName_2 + " below", Toast.LENGTH_SHORT).show();
                vehicleRenameCounter = 2;
                view.findViewById(R.id.vehicleRenamingLayout).setVisibility(View.VISIBLE);
                return true;
            }
        });

        buttonSetVehicle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), vehicleName_3 + " is selected", Toast.LENGTH_SHORT).show();
                vehicleSelectedCounter = 3;
            }
        });

        buttonSetVehicle3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getActivity(), "Please rename " + vehicleName_3 + " below", Toast.LENGTH_SHORT).show();
                vehicleRenameCounter = 3;
                view.findViewById(R.id.vehicleRenamingLayout).setVisibility(View.VISIBLE);
                return true;
            }
        });


        editTextRenameVehicle = view.findViewById(R.id.editText_renameAuto);

        buttonRenameAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (vehicleRenameCounter == 1) {
                    vehicleRenameFieldValue = editTextRenameVehicle.getText().toString();
                    vehicleName_1 = vehicleRenameFieldValue;
                    buttonSetVehicle1.setText(vehicleName_1);
                } else if (vehicleRenameCounter == 2) {
                    vehicleRenameFieldValue = editTextRenameVehicle.getText().toString();
                    vehicleName_2 = vehicleRenameFieldValue;
                    buttonSetVehicle2.setText(vehicleName_2);
                } else if (vehicleRenameCounter == 3) {
                    vehicleRenameFieldValue = editTextRenameVehicle.getText().toString();
                    vehicleName_3 = vehicleRenameFieldValue;
                    buttonSetVehicle3.setText(vehicleName_3);
                }
                editTextRenameVehicle.setText("");

                view.findViewById(R.id.vehicleRenamingLayout).setVisibility(View.GONE);
            }
        });

        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

}
