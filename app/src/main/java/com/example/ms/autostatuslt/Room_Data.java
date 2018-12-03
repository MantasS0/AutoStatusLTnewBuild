package com.example.ms.autostatuslt;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "Data_table")
public class Room_Data {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "Liters")
    private Float liters;

    @NonNull
    @ColumnInfo(name = "Price")
    private Float price;

    @NonNull
    @ColumnInfo(name = "Distance")
    private Integer distance;

    @NonNull
    @ColumnInfo(name = "Date")
    private Date date;

    public Room_Data(int id, float liters, float price, int distance, Date date){
        this.id = id;
        this.liters = liters;
        this.price = price;
        this.distance = distance;
        this.date = date;
    }

    public int getId(){return id;}

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public Float getLiters() {
        return liters;
    }

    public void setLiters(@NonNull Float liters) {
        this.liters = liters;
    }

    @NonNull
    public Float getPrice() {
        return price;
    }

    public void setPrice(@NonNull Float price) {
        this.price = price;
    }

    @NonNull
    public Integer getDistance() {
        return distance;
    }

    public void setDistance(@NonNull Integer distance) {
        this.distance = distance;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }
}
