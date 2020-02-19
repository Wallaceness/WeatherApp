package com.example.android.weatherapp.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.weatherapp.R;
import com.example.android.weatherapp.databinding.FragmentDailyBinding;
import com.example.android.weatherapp.model.Daily;


public class DailyFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private FragmentDailyBinding binder;
    Daily daily;


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
        return binder.getRoot();
    }

    public void setDailyWeather(Daily day){
        this.daily=day;
        binder.setDailyWeather(day);
    }

}
