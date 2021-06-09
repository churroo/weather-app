package com.pradyanti_1313617004.weatherapp.Model;

import com.google.gson.annotations.SerializedName;

public class CurrentModel {

    @SerializedName("location")
    private Location location;

    @SerializedName("current")
    private Current current;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }
}
