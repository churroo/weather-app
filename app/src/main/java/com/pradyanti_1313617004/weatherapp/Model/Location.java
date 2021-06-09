package com.pradyanti_1313617004.weatherapp.Model;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
