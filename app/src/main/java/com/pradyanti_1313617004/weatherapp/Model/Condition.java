package com.pradyanti_1313617004.weatherapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Condition implements Parcelable {

    @SerializedName("text")
    private String text;

    @SerializedName("icon")
    private String icon;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    protected Condition(Parcel in) {
        text = in.readString();
        icon = in.readString();
    }

    public static final Creator<Condition> CREATOR = new Creator<Condition>() {
        @Override
        public Condition createFromParcel(Parcel in) {
            return new Condition(in);
        }

        @Override
        public Condition[] newArray(int size) {
            return new Condition[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeString(icon);
    }
}
