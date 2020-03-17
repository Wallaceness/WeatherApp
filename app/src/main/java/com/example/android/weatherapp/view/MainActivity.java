package com.example.android.weatherapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.android.weatherapp.Constants;
import com.example.android.weatherapp.R;
import com.example.android.weatherapp.repository.APIKey;
import com.example.android.weatherapp.viewmodel.ApiWorker;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private NavHostFragment NavHost;
    private FragmentManager boss;
    private static final String TAG = "MainActivity";

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
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), APIKey.googleCloudKey, Locale.US);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.darkSky){
            Uri url = Uri.parse("https://darksky.net/dev");
            Intent redirect = new Intent(Intent.ACTION_VIEW, url);
            if(redirect.resolveActivity(getPackageManager())!=null){
                startActivity(redirect);
            } else {
                Log.d("Dark Sky Link", "Can't handle this!");
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void navigateTo(Place place){
        HomeFragmentDirections.ActionHomeFragmentSelf action = HomeFragmentDirections.actionHomeFragmentSelf();
        action.setLocation(place);
        NavHostFragment.findNavController(NavHost).navigate(action);
    }

    public void navigateTo(){
        NavHostFragment.findNavController(NavHost).navigate(R.id.homeFragment);
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

}

