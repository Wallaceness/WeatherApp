package com.example.android.weatherapp.view;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.PermissionRequest;
import android.widget.Toast;

import com.example.android.weatherapp.R;
import com.example.android.weatherapp.model.Currently;
import com.example.android.weatherapp.model.Daily;
import com.example.android.weatherapp.model.DataItem;
import com.example.android.weatherapp.model.Hourly;
import com.example.android.weatherapp.model.Minutely;
import com.example.android.weatherapp.model.Response;
import com.example.android.weatherapp.viewmodel.WeatherViewModel;

import java.security.Permission;
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
    private LocationManager locManager;
    private int REQUEST_LOCATION=1;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        vm = new WeatherViewModel(getActivity().getApplication());
        locManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            getActivity().requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
        }
        else {
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 900000, 1000, mLocationListener);
        }


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
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            vm.fetchWeather(Double.toString(location.getLatitude())+","+Double.toString(location.getLongitude()));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(requireContext(), "Please enable location permission.", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode== REQUEST_LOCATION){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 900000, 1000, mLocationListener);
            }
            else{
                Toast.makeText(getContext(), "App needs location permission to show your weather conditions.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
