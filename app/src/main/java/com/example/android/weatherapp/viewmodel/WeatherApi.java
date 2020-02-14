package com.example.android.weatherapp.viewmodel;

import com.example.android.weatherapp.model.Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("/")
    Observable<Response> fetchWeather(
            @Path("location") String Path,
            @Query("exclude") String exclude,
            @Query("extend") String extend,
            @Query("units") String units,
            @Query("lang") String language
    );

    @GET("/")
    Observable<Response> fetchPastWeather(
            @Path("time") String when,
            @Path("location") String Path,
            @Query("exclude") String exclude,
            @Query("extend") String extend,
            @Query("units") String units,
            @Query("lang") String language
    );
}
