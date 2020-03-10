package com.example.android.weatherapp.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.weatherapp.R;
import com.example.android.weatherapp.databinding.FragmentDailyBinding;
import com.example.android.weatherapp.model.Daily;
import com.example.android.weatherapp.model.DataItem;

import java.util.ArrayList;
import java.util.List;


public class DailyFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private FragmentDailyBinding binder;
    Daily daily;
    ViewPager2 pager;
    WeatherDataAdapter pagerAdapter;
    ArrayList<DataItem> weather = new ArrayList<>();
    MainActivity main;
    ImageView icon;


    public DailyFragment() {
        // Required empty public constructor
    }

    public DailyFragment(Daily days){
        daily=days;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_daily, container, false);
        View rootView= binder.getRoot();
        pager = rootView.findViewById(R.id.dailyPager);
        pagerAdapter=new WeatherDataAdapter(this, weather, "daily");
        main = (MainActivity) getActivity();
        icon = rootView.findViewById(R.id.iconDaily);
        pager.setAdapter(pagerAdapter);
        return rootView;
    }

    public void setDailyWeather(Daily day){
        this.daily=day;
        binder.setDailyWeather(day);
        weather = (ArrayList<DataItem>)day.getData();
        icon.setBackground(main.renderIcon(day.getIcon()));
        pagerAdapter.updateWeather(weather);

    }

}
