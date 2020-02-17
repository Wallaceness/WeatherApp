package com.example.android.weatherapp.repository;

import com.example.android.weatherapp.model.Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("{key}/{location}")
    Observable<Response> fetchWeather(
            @Path("key") String key,
            @Path("location") String Path,
            @Query("exclude") String exclude,
            @Query("extend") String extend,
            @Query("units") String units,
            @Query("lang") String language
    );

    @GET("{key}/{location},{time}")
    Observable<Response> fetchPastWeather(
            @Path("key") String key,
            @Path("time") String when,
            @Path("location") String Path,
            @Query("exclude") String exclude,
            @Query("extend") String extend,
            @Query("units") String units,
            @Query("lang") String language
    );
}
