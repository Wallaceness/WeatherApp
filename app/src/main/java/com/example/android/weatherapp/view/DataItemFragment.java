package com.example.android.weatherapp.view;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.weatherapp.R;
import com.example.android.weatherapp.databinding.FragmentDataItemBinding;
import com.example.android.weatherapp.model.Currently;
import com.example.android.weatherapp.model.DataItem;

import java.sql.Time;


/**
 * A simple {@link Fragment} subclass.
 */
public class DataItemFragment extends Fragment {
    private DataItem weatherItem;
    private FragmentDataItemBinding binder;
    private String type;
    private String Timezone;
    MainActivity main;
    ImageView icon;


    public DataItemFragment(DataItem item, String type, String timezone) {
        this.weatherItem = item;
        this.type=type;
        this.Timezone = timezone;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_data_item, container, false);
        binder.setItemType(type);
        binder.setTimezone(Timezone);
        View rootView = binder.getRoot();
        icon = rootView.findViewById(R.id.iconItem);
        main=(MainActivity) getActivity();
        setCurrentWeather(weatherItem);
        return rootView;
    }

    public void setCurrentWeather(DataItem item){
        this.weatherItem = item;
        binder.setItemWeather(item);
        icon.setBackground(main.renderIcon(item.getIcon()));
    }

}
