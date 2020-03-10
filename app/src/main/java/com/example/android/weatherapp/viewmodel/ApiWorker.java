package com.example.android.weatherapp.viewmodel;

import android.app.Application;
import android.content.Context;
import android.location.Address;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class ApiWorker extends Worker {
    WeatherViewModel WVM = new WeatherViewModel((Application)getApplicationContext());
    Location location;

    public ApiWorker(@NonNull Context context, @NonNull WorkerParameters workerParams, Location location) {
        super(context, workerParams);
        this.location = location;
    }

    @NonNull
    @Override
    public Result doWork() {
        WVM.fetchWeather(Double.toString(location.getLatitude()) + "," + Double.toString(location.getLongitude()));
        return Result.success();
    }
}
