package com.pradyanti_1313617004.weatherapp.Adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pradyanti_1313617004.weatherapp.Model.Hour;
import com.pradyanti_1313617004.weatherapp.R;

import java.util.ArrayList;

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.ListViewHolder> {

    ArrayList<Hour> mHourArrayList;

    public HourAdapter(ArrayList<Hour> hourArrayList) {
        mHourArrayList = hourArrayList;
    }


    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hour_forecast, parent, false);
        ListViewHolder listViewHolder = new ListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(HourAdapter.ListViewHolder holder, int position) {
        String split_date = mHourArrayList.get(position).getTime();
        String[] date = split_date.split(" ");
        holder.tv_time.setText(date[1]);
        holder.tv_temp_hour.setText(Double.toString(mHourArrayList.get(position).getTemp())+ "°C");
        holder.tv_condition_hour.setText(mHourArrayList.get(position).getCondition().getText());
        Glide.with(holder.itemView.getContext())
                .load("http:" + mHourArrayList.get(position).getCondition().getIcon())
                .into(holder.iconHour);

    }

    @Override
    public int getItemCount() {
        return mHourArrayList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tv_time, tv_temp_hour, tv_condition_hour;
        ImageView iconHour;

        public ListViewHolder(View itemView) {
            super(itemView);

            tv_time = itemView.findViewById(R.id.tv_time);
            tv_temp_hour = itemView.findViewById(R.id.tv_temp_hour);
            tv_condition_hour = itemView.findViewById(R.id.tv_condition_hour);
            iconHour = itemView.findViewById(R.id.icon_condition_hour);
        }
    }
}
