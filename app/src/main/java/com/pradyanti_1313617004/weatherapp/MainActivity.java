package com.pradyanti_1313617004.weatherapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pradyanti_1313617004.weatherapp.Adapter.ForecastAdapter;
import com.pradyanti_1313617004.weatherapp.Model.CurrentModel;
import com.pradyanti_1313617004.weatherapp.Model.ForecastDay;
import com.pradyanti_1313617004.weatherapp.Model.ForecastModel;
import com.pradyanti_1313617004.weatherapp.Model.HistoryModel;
import com.pradyanti_1313617004.weatherapp.Retrofit.ApiClient;
import com.pradyanti_1313617004.weatherapp.Retrofit.ApiInterface;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    TextView last_update, temp_c, location, condition, cloud, humidity, precip_mm, pressure_mb, wind_kph, gust_kph, us_epa_index, co, o3, no2;
    ImageView imgCondition, history_icon;
    TextView date, avgtemp, forecast_condition, history_date, history_condition, history_avgtemp;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private ForecastAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static MainActivity mainActivity;
    private static final String TAG = "MainActivity";
    LinearLayout eror_layout;

    ArrayList<ForecastDay> forecastDayList;
    ArrayList<ForecastDay> historyList;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshLayout = findViewById(R.id.swipe_refresh);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);
        eror_layout = findViewById(R.id.eror_layout);

        location = findViewById(R.id.tv_location);
        last_update = findViewById(R.id.tv_last_update);
        temp_c = findViewById(R.id.tv_temp);
        condition = findViewById(R.id.tv_condition);
        cloud = findViewById(R.id.tv_cloud);
        humidity = findViewById(R.id.tv_humidity);
        precip_mm = findViewById(R.id.tv_precip);
        pressure_mb = findViewById(R.id.tv_presure);
        wind_kph = findViewById(R.id.tv_wind);
        gust_kph = findViewById(R.id.tv_wind_gust);
        imgCondition = findViewById(R.id.icon_condition);

        us_epa_index = findViewById(R.id.tv_us_epa_index);
        co = findViewById(R.id.tv_co);
        o3 = findViewById(R.id.tv_O3);
        no2 = findViewById(R.id.tv_no2);

        history_date = findViewById(R.id.tv_date_history);
        history_condition = findViewById(R.id.tv_condition_history);
        history_icon = findViewById(R.id.history_icon);
        history_avgtemp = findViewById(R.id.tv_avgtemp_history);

        date = findViewById(R.id.tv_date);
        avgtemp = findViewById(R.id.tv_avg_temp);
        forecast_condition = findViewById(R.id.tv_condition_forecast);

        recyclerView = findViewById(R.id.rv_forecast);
        //mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mainActivity = this;

        getHistoryDataFromApi();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getHistoryDataFromApi() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd ");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -24);
        String yesterday = dateFormat.format(calendar.getTime());
        yesterday.replace('/', '-');
        Log.d(TAG, String.valueOf(dateFormat.format(calendar.getTime())));

        ApiClient.apiInterface().getHistoryData("history.json?key=a10472fd23c5489b92b113358212305&q=Jakarta&dt=" + yesterday).enqueue(new Callback<HistoryModel>() {
            @Override
            public void onResponse(Call<HistoryModel> call, Response<HistoryModel> response) {

                 historyList = response.body().getForecast().getForecastDayList();

                Log.d(TAG, "onResponse: Sukses masuk on respone history");
                eror_layout.setVisibility(View.GONE);

                getDataForecastFromApi();
            }

            @Override
            public void onFailure(Call<HistoryModel> call, Throwable t) {
                refreshLayout.setRefreshing(false);
                Log.d(TAG, "onFailure: Gagal masuk history");
                eror_layout.setVisibility(View.VISIBLE);
            }
        });

    }

    private void getDataForecastFromApi() {
        ApiClient.apiInterface().getForecastData()
                .enqueue(new Callback<ForecastModel>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(Call<ForecastModel> call, Response<ForecastModel> response) {

                        ForecastModel forecastModel = response.body();

                        int us_epa = forecastModel.getCurrent().getAir_quality().getUs_epa_index();
                        String result;
                        if (us_epa == 1) {
                            result = "Good";
                        } else if(us_epa == 2) {
                            result = "Moderate";
                        } else if(us_epa == 3) {
                            result = "Unhealthy for sensitive group";
                        } else if(us_epa == 4) {
                            result = "Unhealthy";
                        } else if(us_epa == 5) {
                            result = "Very Unhealthy";
                        } else {
                            result = "Hazardous";
                        }

                        location.setText(forecastModel.getLocation().getName());
                        String date = forecastModel.getCurrent().getLast_updated();
                        Date date_time = new Date();
                        try {
                            date_time = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String new_date = new SimpleDateFormat("E, dd MMM HH:mm").format(date_time);
                        last_update.setText(new_date);
                        double temp = forecastModel.getCurrent().getTemp_c();
                        temp_c.setText((int)temp + "°C");
                        condition.setText(forecastModel.getCurrent().getCondition().getText());
                        cloud.setText(Integer.toString(forecastModel.getCurrent().getCloud()) + "%");
                        humidity.setText(Integer.toString(forecastModel.getCurrent().getHumidity()) + "%");
                        precip_mm.setText(Double.toString(forecastModel.getCurrent().getPrecip_mm()) + " mm");
                        double pressure = forecastModel.getCurrent().getPressure_mb();
                        pressure_mb.setText((int) pressure + " mb");
                        wind_kph.setText(Double.toString(forecastModel.getCurrent().getWind_kph()) + " km/h");
                        gust_kph.setText(Double.toString(forecastModel.getCurrent().getGust_kph()) + " km/h");
                        Glide.with(MainActivity.this)
                                .load("http:" + forecastModel.getCurrent().getCondition().getIcon())
                                .into(imgCondition);
                        us_epa_index.setText(result);
                        co.setText(Float.toString(forecastModel.getCurrent().getAir_quality().getCo()) + " μg/m3");
                        o3.setText(Float.toString(forecastModel.getCurrent().getAir_quality().getO3())+ " μg/m3");
                        no2.setText(Float.toString(forecastModel.getCurrent().getAir_quality().getNo2())+ " μg/m3");

                        String date_history = historyList.get(0).getDate();
                        Date date_time_history = new Date();
                        try {
                            date_time_history = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(date_history);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String format_date_history = new java.text.SimpleDateFormat("E, dd MMM").format(date_time_history);
                        history_date.setText(format_date_history);
                        history_condition.setText(historyList.get(0).getDay().getCondition().getText());
                        double avg_temp = historyList.get(0).getDay().getAvgtemp_c();
                        history_avgtemp.setText((int) avg_temp + "°C");
                        Glide.with(MainActivity.this)
                                .load("http:" + historyList.get(0).getDay().getCondition().getIcon())
                                .into(history_icon);

                        forecastDayList = response.body().getForecast().getForecastDayList();

                        mAdapter = new ForecastAdapter(forecastDayList);
                        mAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(mAdapter);

                        mAdapter.setOnItemClickCallBack(new ForecastAdapter.OnItemClickCallBack() {
                            @Override
                            public void onItemClicked(ForecastDay forecastDayData) {
                                showForecastData(forecastDayData);
                            }
                        });

                        //date_forecast.setText("Location " + response.body().getLocation().getName());
                        //date_forecast.setText("Date " + response.body().getForecast().getForecastDayList().get(1).getDate());

                        refreshLayout.setRefreshing(false);
                        Log.d(TAG, "onResponse: Sukses masuk on respone forecast");
                        eror_layout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<ForecastModel> call, Throwable t) {
                        refreshLayout.setRefreshing(false);
                        Log.d(TAG, "onFailure: Gagal masuk forecast");
                        eror_layout.setVisibility(View.VISIBLE);
                    }
                });
    }


    private void showForecastData(ForecastDay forecastDayData) {
        Intent intent = new Intent(MainActivity.this, DetailForecastActivity.class);
        intent.putExtra("ForecastDayList", forecastDayData);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onRefresh() {
        Log.d(TAG, "onRefresh: Sukses masuk refresh");
        getHistoryDataFromApi();
    }
}
