package com.pradyanti_1313617004.weatherapp.Model;

import com.google.gson.annotations.SerializedName;

public class Astro {

    @SerializedName("sunrise")
    private String sunrise;

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }
}
