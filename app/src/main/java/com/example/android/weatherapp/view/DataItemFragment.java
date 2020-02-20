package com.example.android.weatherapp.view;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.weatherapp.R;
import com.example.android.weatherapp.databinding.FragmentDataItemBinding;
import com.example.android.weatherapp.model.Currently;
import com.example.android.weatherapp.model.DataItem;


/**
 * A simple {@link Fragment} subclass.
 */
public class DataItemFragment extends Fragment {
    private DataItem weatherItem;
    private FragmentDataItemBinding binder;


    public DataItemFragment(DataItem item) {
        this.weatherItem = item;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_data_item, container, false);
        setCurrentWeather(weatherItem);
        return binder.getRoot();
    }

    public void setCurrentWeather(DataItem item){
        this.weatherItem = item;
        binder.setCurrentWeather(item);
    }

}
