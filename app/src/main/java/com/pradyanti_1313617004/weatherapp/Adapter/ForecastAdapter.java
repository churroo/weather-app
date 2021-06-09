package com.pradyanti_1313617004.weatherapp.Adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pradyanti_1313617004.weatherapp.DetailForecastActivity;
import com.pradyanti_1313617004.weatherapp.Model.Forecast;
import com.pradyanti_1313617004.weatherapp.Model.ForecastDay;
import com.pradyanti_1313617004.weatherapp.Model.ForecastModel;
import com.pradyanti_1313617004.weatherapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ListViewHolder> {

    ArrayList<ForecastDay> mForecastListDay;

    public ForecastAdapter(ArrayList<ForecastDay> forecastDays) {
        mForecastListDay = forecastDays;
    }

    private OnItemClickCallBack onItemClickCallBack;

    //OnClick method
    public void setOnItemClickCallBack(OnItemClickCallBack onItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast_weather, parent, false);
        ListViewHolder listViewHolder = new ListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(ForecastAdapter.ListViewHolder holder, int position) {
        String date = mForecastListDay.get(position).getDate();
        Date date_time = new Date();
        try {
            date_time = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String new_date = new SimpleDateFormat("E, dd MMM").format(date_time);
        holder.tvDate.setText(new_date);
        holder.tvAvgTemp.setText(Double.toString(mForecastListDay.get(position).getDay().getAvgtemp_c()) + "°C");
        holder.tvCondition.setText(mForecastListDay.get(position).getDay().getCondition().getText());
        Glide.with(holder.itemView.getContext())
                .load("http:" + mForecastListDay.get(position).getDay().getCondition().getIcon())
                .into(holder.imgCondition);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), DetailForecastActivity.class);
//                intent.putExtra("ForecastDayList", mForecastListDay.get(position));
//                v.getContext().startActivity(intent);

                onItemClickCallBack.onItemClicked(mForecastListDay.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mForecastListDay.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        public TextView tvDate, tvAvgTemp, tvCondition;
        ImageView imgCondition;

        public ListViewHolder(View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tv_date);
            tvAvgTemp = itemView.findViewById(R.id.tv_avg_temp);
            tvCondition = itemView.findViewById(R.id.tv_condition_forecast);
            imgCondition = itemView.findViewById(R.id.img_icon_condition);

        }
    }

    public interface OnItemClickCallBack {
        void onItemClicked(ForecastDay forecastDayData);
    }

}
