package com.example.android.weatherapp.view;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.weatherapp.R;
import com.example.android.weatherapp.databinding.FragmentCurrentBinding;
import com.example.android.weatherapp.model.Currently;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentFragment extends Fragment {
    private FragmentCurrentBinding binder;
    Currently currentWeather;


    public CurrentFragment(Currently currentWeather) {
        this.currentWeather = currentWeather;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_current, container, false);
        return binder.getRoot();
    }

    public void setCurrentWeather(Currently currently){
        this.currentWeather=currently;
        binder.setCurrentWeather(currentWeather);
    }
}
