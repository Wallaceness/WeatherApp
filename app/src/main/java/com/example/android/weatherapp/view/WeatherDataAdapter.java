package com.example.android.weatherapp.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.android.weatherapp.model.DataItem;

import java.util.List;

public class WeatherDataAdapter extends FragmentStateAdapter {

    private List<DataItem> weatherList;
    private String type;
    private String timezone;

    public WeatherDataAdapter(Fragment fragment, List<DataItem> weather, String type) {
        super(fragment);
        weatherList = weather;
        this.type=type;
    }

    public void updateWeather(List<DataItem> weatherList, String Timezone){
        this.weatherList = weatherList;
        this.timezone = Timezone;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        DataItemFragment dataI = new DataItemFragment(weatherList.get(position), type, timezone);
        return dataI;
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }
}
