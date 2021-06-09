package com.pradyanti_1313617004.weatherapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ForecastDay implements Parcelable {

    @SerializedName("date")
    private String date;

    @SerializedName("day")
    private Day day;

    @SerializedName("astro")
    private Astro astro;

    @SerializedName("hour")
    private ArrayList<Hour> hourArrayList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Astro getAstro() {
        return astro;
    }

    public void setAstro(Astro astro) {
        this.astro = astro;
    }

    protected ForecastDay(Parcel in) {
        date = in.readString();
        day = in.readParcelable(Day.class.getClassLoader());
        hourArrayList = in.createTypedArrayList(Hour.CREATOR);
    }

    public static final Creator<ForecastDay> CREATOR = new Creator<ForecastDay>() {
        @Override
        public ForecastDay createFromParcel(Parcel in) {
            return new ForecastDay(in);
        }

        @Override
        public ForecastDay[] newArray(int size) {
            return new ForecastDay[size];
        }
    };

    public ArrayList<Hour> getHourArrayList() {
        return hourArrayList;
    }

    public void setHourArrayList(ArrayList<Hour> hourArrayList) {
        this.hourArrayList = hourArrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeParcelable(day, flags);
        dest.writeTypedList(hourArrayList);
    }
}
