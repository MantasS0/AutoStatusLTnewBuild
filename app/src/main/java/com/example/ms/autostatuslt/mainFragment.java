package com.example.ms.autostatuslt;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.TypeConverter;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
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

    private TextView text_averageConsumptionInMain;
    private TextView text_averagePriceInMain;

    private Data_Repository mRepository;
    private DataViewModel mViewModel;
    private Room_DataListAdapter adapter;
    private float avgLiters;


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
                            vehicleSelectedCounter,
                            Float.parseFloat(liters),
                            Float.parseFloat(price),
                            Integer.parseInt(distance),
                            date);

                    mViewModel.insert(roomData);
                    clearFields();
//                    closeKeyboard();


                }
            }
        });
    }

    private void observerSetup() {

//        mViewModel.getAllData().observe(this, new Observer<List<Room_Data>>() {
//            @Override
//            public void onChanged(@Nullable final List<Room_Data> vehicleData) {
//                adapter.setRoomData(vehicleData);
//            }
//        });
        System.out.println("hello form observer setup");

        mViewModel.getVehicleDataResults().observe(this,
                new Observer<List<Room_Data>>() {
                    @Override
                    public void onChanged(@Nullable final List<Room_Data> vehicleData) {

                        if (vehicleData.size() > 0) {
                            calculateAvgConsumption();
                            text_averageConsumptionInMain.setText("Average consuption: " + avgLiters + " l/100km");
                        } else {
                            text_averageConsumptionInMain.setText("No data yet");
                        }
                    }
                });
    }
//    private List<Float> listLitrai;
//
//private List<Float> vykdyti () throws NullPointerException {
//    List<?> listTarpinis;
//        listTarpinis = convertObjectToList(mViewModel.getVehicleLiters().getValue().toArray());
//
//        return listLitrai;
//    }

//    public int getItemCount() {
//        if (mRoomData != null)
//            return mRoomData.size();
//        else return 0;
//    }

    private void calculateAvgConsumption() {
//        List<Float> liters = new ArrayList<>();

//            adapter.getItemCount();
//            Float[] fl1 = mViewModel.getVehicleLiters().getValue().toArray(new Float[0]);

//            Object[] lai = mViewModel.getVehicleLiters().getValue().toArray();
//            Object[] lai = mViewModel.findVehicleLiters(vehicleSelectedCounter);
//
//            for (Object l : lai) {
//                liters.add(Float.parseFloat(l.toString()));
//            }
//            float sumLiters = 0;
//            float avgLiters = 0;
//            for (Float liter : liters) {
//                sumLiters += liter;
//            }
//            avgLiters = sumLiters / liters.size();

//        List<Room_Data> allData = mRepository.getAllData().getValue();//mViewModel.getAllData().getValue();
//
//        List<Integer> allDistanceList = new ArrayList<>();

        float liters = 0;
        float price = 0;

//        for (Room_Data entry : allData) {
//            if (entry.getVehicleSelectedCounter().equals(vehicleSelectedCounter)) {
//                allDistanceList.add(entry.getDistance());
//                liters += entry.getLiters();
//                price += entry.getPrice();
//            }
//        }


        mViewModel.findVehicleData(vehicleSelectedCounter);
        List<Room_Data> vehicleDataList = mRepository.vehicleData.getValue();
        List<Integer> vehicleDistanceList = new ArrayList<>();
        if(vehicleDataList != null){
            for (Room_Data entry : vehicleDataList) {
                vehicleDistanceList.add(entry.getDistance());
                liters += entry.getLiters();
            }
        }

        int distanceDifference = calcDistance(vehicleDistanceList);
        avgLiters = (liters / distanceDifference) * 100;
        System.out.println("hello");
        System.out.println(vehicleDataList);
        System.out.println(avgLiters);


//        text_averageConsumptionInMain.setText("Average consuption: " + avgLiters + " l/100km");

//        List<?> list = new ArrayList<>();
//        list = Arrays.asList((Object[])lai);
//
//        Float[] y = list.toArray(new Float[list.toArray().length]);
//        liters.addAll(Arrays.asList(y));
//        float avgCons = 0;
//
//        for (int i = 0; i<y.length; i++){
//            float l = y[i];
//            avgCons +=l;
//
//        }
//        liters = (convertObjectToList(mViewModel.getVehicleLiters().getValue().toArray()));
//        convertObjectToList(lai);
//        liters = vykdyti();
//
//       liters = new ArrayList<Float>.addAll(Float.parseFloat(mViewModel.getVehicleLiters().getValue().toArray()));
    }

    private int calcDistance(List<Integer> allDistanceList) {
        int minDistance = allDistanceList.get(0);
        int maxDistance = 0;
        for (Integer currentDistance : allDistanceList) {
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
            } else if (currentDistance > maxDistance) {
                maxDistance = currentDistance;
            }
        }
        return maxDistance - minDistance;
    }


    public static List<?> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[]) obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>) obj);
        }
        return list;
    }

//    private void closeKeyboard() {
//        getActivity().getWindow().setSoftInputMode(
//                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
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

        text_averageConsumptionInMain = getView().findViewById(R.id.text_averageConsumptionInMain);
        text_averagePriceInMain = getView().findViewById(R.id.text_averagePriceInMain);

        listenerSetup();
        observerSetup();
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
//        calculateAvgConsumption();
//        try {
//            calculateAvgConsumption();
//        } catch (NullPointerException e) {
//            System.out.println("NullPointerException");
//        }

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
