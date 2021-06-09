package com.pradyanti_1313617004.weatherapp.Model;

import com.google.gson.annotations.SerializedName;

public class AirQuality {

    @SerializedName("co")
    private float co;

    @SerializedName("o3")
    private float o3;

    @SerializedName("no2")
    private float no2;

    @SerializedName("so2")
    private float so2;

    @SerializedName("us-epa-index")
    private int us_epa_index;

    public int getUs_epa_index() {
        return us_epa_index;
    }

    public void setUs_epa_index(int us_epa_index) {
        this.us_epa_index = us_epa_index;
    }

    public float getCo() {
        return co;
    }

    public void setCo(float co) {
        this.co = co;
    }

    public float getO3() {
        return o3;
    }

    public void setO3(float o3) {
        this.o3 = o3;
    }

    public float getNo2() {
        return no2;
    }

    public void setNo2(float no2) {
        this.no2 = no2;
    }

    public float getSo2() {
        return so2;
    }

    public void setSo2(float so2) {
        this.so2 = so2;
    }
}
