package com.pradyanti_1313617004.weatherapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Forecast {

    @SerializedName("forecastday")
    private ArrayList<ForecastDay> forecastDayList;

    public ArrayList<ForecastDay> getForecastDayList() {
        return forecastDayList;
    }

    public void setForecastDayList(ArrayList<ForecastDay> forecastDayList) {
        this.forecastDayList = forecastDayList;
    }
}
