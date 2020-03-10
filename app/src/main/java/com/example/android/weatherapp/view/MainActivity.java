package com.example.android.weatherapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.android.weatherapp.R;
import com.example.android.weatherapp.viewmodel.ApiWorker;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private NavHostFragment NavHost;
    private FragmentManager boss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boss=getSupportFragmentManager();
        NavHost = (NavHostFragment)boss.findFragmentById(R.id.nav_host_fragment);
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O) {
            NotificationChannel weatherChannel = new NotificationChannel("WEATHER_CHANNEL", "WeatherCh", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(weatherChannel);
        }
        PeriodicWorkRequest fetch = new PeriodicWorkRequest.Builder(ApiWorker.class, 5000, TimeUnit.MINUTES).build();
        WorkManager.getInstance(this).enqueue(fetch);
    }

    public void navigateTo(int id){
        NavHostFragment.findNavController(NavHost).navigate(id);
    }

    public Drawable renderIcon(String name){
        switch(name){
            case "clear-day":
                return getDrawable(R.drawable.sun);
            case "clear-night":
                return getDrawable(R.drawable.moon);
            case "rain":
                return getDrawable(R.drawable.rain);
            case "snow":
                return getDrawable(R.drawable.snow);
            case "sleet":
                return getDrawable(R.drawable.sleet);
            case "wind":
                return getDrawable(R.drawable.windy);
            case "fog":
                return getDrawable(R.drawable.foggy);
            case "cloudy":
                return getDrawable(R.drawable.clouds);
            case "partly-cloudy-day":
                return getDrawable(R.drawable.partly_cloudy);
            case "partly-cloudy-night":
                return getDrawable(R.drawable.moon_cloudy);
            default:
                return getDrawable(R.drawable.ic_launcher_foreground);
        }
    }

    public int getIconId(String name){
        switch(name){
            case "clear-day":
                return R.drawable.sun;
            case "clear-night":
                return R.drawable.moon;
            case "rain":
                return R.drawable.rain;
            case "snow":
                return R.drawable.snow;
            case "sleet":
                return R.drawable.sleet;
            case "wind":
                return R.drawable.windy;
            case "fog":
                return R.drawable.foggy;
            case "cloudy":
                return R.drawable.clouds;
            case "partly-cloudy-day":
                return R.drawable.partly_cloudy;
            case "partly-cloudy-night":
                return R.drawable.moon_cloudy;
            default:
                return R.drawable.ic_launcher_foreground;
        }
    }

    private void startWeatherTask(){

    }
}

