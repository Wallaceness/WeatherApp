package com.example.android.weatherapp.view;


import android.os.Bundle;

import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.weatherapp.R;
import com.example.android.weatherapp.model.Currently;
import com.example.android.weatherapp.model.Daily;
import com.example.android.weatherapp.model.DataItem;
import com.example.android.weatherapp.model.Hourly;
import com.example.android.weatherapp.model.Minutely;
import com.example.android.weatherapp.model.Response;
import com.example.android.weatherapp.viewmodel.WeatherViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private WeatherViewModel vm;
    private Hourly hourly;
    private Minutely minutely;
    private Currently currently;
    private Daily daily;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.fragment_home, container, false);
        vm = new WeatherViewModel(getActivity().getApplication());
        vm.fetchWeather("37.8267,-122.4233");

        vm.getWeather().observe(getViewLifecycleOwner(), response -> {
            hourly = response.getHourly();
            minutely = response.getMinutely();
            currently = response.getCurrently();
            daily = response.getDaily();
        });

        vm.getErrors().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
            }
        });
        return rootView;

    }

}
