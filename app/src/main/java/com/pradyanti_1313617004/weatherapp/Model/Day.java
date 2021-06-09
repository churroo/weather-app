package com.pradyanti_1313617004.weatherapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Day implements Parcelable {

    @SerializedName("avgtemp_c")
    private double avgtemp_c;

    @SerializedName("maxtemp_c")
    private double maxtemp_c;

    @SerializedName("mintemp_c")
    private double mintemp_c;

    @SerializedName("maxwind_kph")
    private double maxwind_kph;

    @SerializedName("totalprecip_mm")
    private double totalprecip_mm;

    @SerializedName("condition")
    private Condition condition;

    public double getMaxtemp_c() {
        return maxtemp_c;
    }

    public void setMaxtemp_c(double maxtemp_c) {
        this.maxtemp_c = maxtemp_c;
    }

    public double getMintemp_c() {
        return mintemp_c;
    }

    public void setMintemp_c(double mintemp_c) {
        this.mintemp_c = mintemp_c;
    }

    public double getMaxwind_kph() {
        return maxwind_kph;
    }

    public void setMaxwind_kph(double maxwind_kph) {
        this.maxwind_kph = maxwind_kph;
    }

    public double getTotalprecip_mm() {
        return totalprecip_mm;
    }

    public void setTotalprecip_mm(double totalprecip_mm) {
        this.totalprecip_mm = totalprecip_mm;
    }

    public double getAvgtemp_c() {
        return avgtemp_c;
    }

    public void setAvgtemp_c(double avgtemp_c) {
        this.avgtemp_c = avgtemp_c;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    protected Day(Parcel in) {
        avgtemp_c = in.readDouble();
        maxtemp_c = in.readDouble();
        mintemp_c = in.readDouble();
        maxwind_kph = in.readDouble();
        totalprecip_mm = in.readDouble();
        condition = in.readParcelable(Condition.class.getClassLoader());
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(avgtemp_c);
        dest.writeDouble(maxtemp_c);
        dest.writeDouble(mintemp_c);
        dest.writeDouble(maxwind_kph);
        dest.writeDouble(totalprecip_mm);
        dest.writeParcelable(condition, flags);
    }
}
