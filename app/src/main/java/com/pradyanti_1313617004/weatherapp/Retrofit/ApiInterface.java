package com.pradyanti_1313617004.weatherapp.Retrofit;


import com.pradyanti_1313617004.weatherapp.Model.CurrentModel;
import com.pradyanti_1313617004.weatherapp.Model.ForecastModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    // 1. Ngambil current date
    // 2. Kurangi current date sebesar 1 hari
    // 3. Masukan yesterday date to history url

    @GET("current.json?key=a10472fd23c5489b92b113358212305&q=Jakarta&aqi=yes")
    Call<CurrentModel> getCurrentData();

    @GET("forecast.json?key=a10472fd23c5489b92b113358212305&q=Jakarta&days=3&aqi=yes&alerts=no")
    Call<ForecastModel> getForecastData();


}
