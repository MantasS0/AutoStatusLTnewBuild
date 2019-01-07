package com.example.ms.autostatuslt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Room_DataListAdapter extends RecyclerView.Adapter<Room_DataListAdapter.Room_DataViewHolder> {

    class Room_DataViewHolder extends RecyclerView.ViewHolder {
        private final TextView dataItemView;

        private Room_DataViewHolder(View itemView) {
            super(itemView);
            dataItemView = itemView.findViewById(R.id.recyclerview_textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Room_Data> mRoomData; // Cached copy of words

    Room_DataListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public Room_DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_data, parent, false);
        return new Room_DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Room_DataViewHolder holder, int position) {
        if (mRoomData != null) {
            Room_Data current = mRoomData.get(position);
            holder.dataItemView.setText("" + current.getDistance() + "km - " + current.getLiters() + "l - " + current.getPrice() + "eu - " + current.getDate());
        } else {
            // Covers the case of data not being ready yet.
            holder.dataItemView.setText("No data yet.");
        }
    }

    void setRoomData(List<Room_Data> roomData){
        mRoomData = roomData;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mRoomData != null)
            return mRoomData.size();
        else return 0;
    }
}