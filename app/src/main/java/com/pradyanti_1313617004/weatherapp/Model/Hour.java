package com.pradyanti_1313617004.weatherapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Hour implements Parcelable {

    @SerializedName("time")
    private String time;

    @SerializedName("temp_c")
    private double temp;

    @SerializedName("condition")
    private Condition condition;

    @SerializedName("wind_kph")
    private double wind_kph;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public double getWind_kph() {
        return wind_kph;
    }

    public void setWind_kph(double wind_kph) {
        this.wind_kph = wind_kph;
    }

    protected Hour(Parcel in) {
        time = in.readString();
        temp = in.readDouble();
        wind_kph = in.readDouble();
        condition = in.readParcelable(Condition.class.getClassLoader());
    }

    public static final Creator<Hour> CREATOR = new Creator<Hour>() {
        @Override
        public Hour createFromParcel(Parcel in) {
            return new Hour(in);
        }

        @Override
        public Hour[] newArray(int size) {
            return new Hour[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(time);
        dest.writeDouble(temp);
        dest.writeDouble(wind_kph);
        dest.writeParcelable(condition, flags);
    }
}
