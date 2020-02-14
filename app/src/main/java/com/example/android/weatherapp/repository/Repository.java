package com.example.android.weatherapp.repository;

import com.example.android.weatherapp.model.Response;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private Repository(){

    }

    public static final Repository INSTANCE=new Repository();

    private Retrofit retro = new Retrofit.Builder()
            .baseUrl("https://api.darksky.net/forecast/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public WeatherService service = retro.create(WeatherService.class);
}
