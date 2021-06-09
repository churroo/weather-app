package com.pradyanti_1313617004.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pradyanti_1313617004.weatherapp.Adapter.HourAdapter;
import com.pradyanti_1313617004.weatherapp.Model.ForecastDay;
import com.pradyanti_1313617004.weatherapp.Model.Hour;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DetailForecastActivity extends AppCompatActivity {

    TextView avgTemp, maxtemp, mintemp, maxwind, total_precip, condition, date_forecast, detail_location;
    ImageView icon_condition_day;
    private RecyclerView recyclerView;
    private HourAdapter hourAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static DetailForecastActivity detailForecastActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_forecast);

        ForecastDay forecastDay = getIntent().getParcelableExtra("ForecastDayList");
//        Log.d("Cek", "ForecastDay " + String.valueOf(forecastDay.size()));

        avgTemp = findViewById(R.id.tv_avg_temp);
        maxtemp = findViewById(R.id.tv_max_temp);
        mintemp = findViewById(R.id.tv_min_temp);
        maxwind = findViewById(R.id.tv_max_wind);
        total_precip = findViewById(R.id.tv_total_precip);
        condition = findViewById(R.id.tv_condition_day);
        icon_condition_day = findViewById(R.id.icon_condition_day);
        date_forecast = findViewById(R.id.tv_forecast_date);

        String date = forecastDay.getDate();
        Date date_time = new Date();
        try {
            date_time = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String new_date = new SimpleDateFormat("EEE, dd MMM").format(date_time);
        date_forecast.setText(new_date);
        double avgtemp = forecastDay.getDay().getAvgtemp_c();
        avgTemp.setText((int)avgtemp + "°C");
        double maxTemp = forecastDay.getDay().getMaxtemp_c();
        maxtemp.setText((int)maxTemp + "°C");
        double minTemp = forecastDay.getDay().getMintemp_c();
        mintemp.setText((int)minTemp + "°C");
        maxwind.setText(Double.toString(forecastDay.getDay().getMaxwind_kph()) + " km/h");
        total_precip.setText(Double.toString(forecastDay.getDay().getTotalprecip_mm()) + " mm");
        condition.setText(forecastDay.getDay().getCondition().getText());
        Glide.with(DetailForecastActivity.this)
                .load("http:" + forecastDay.getDay().getCondition().getIcon())
                .into(icon_condition_day);


        recyclerView = findViewById(R.id.rv_hour_forecast);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        detailForecastActivity = this;

        ArrayList<Hour> hourArrayList = forecastDay.getHourArrayList();
        hourAdapter = new HourAdapter(hourArrayList);
        recyclerView.setAdapter(hourAdapter);

    }
}