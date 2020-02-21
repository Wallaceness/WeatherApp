package com.example.android.weatherapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.android.weatherapp.R;

public class MainActivity extends AppCompatActivity {
    private NavHostFragment NavHost;
    private FragmentManager boss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boss=getSupportFragmentManager();
        NavHost = (NavHostFragment)boss.findFragmentById(R.id.nav_host_fragment);
    }

    public void navigateTo(int id){
        NavHostFragment.findNavController(NavHost).navigate(id);
    }

    public void renderIcon(String name, ImageView icon){
        switch(name){
            case "clear-day":
                icon.setBackground(getDrawable(R.drawable.sun));
                break;
            case "clear-night":
                icon.setBackground(getDrawable(R.drawable.moon));
                break;
            case "rain":
                icon.setBackground(getDrawable(R.drawable.rain));
                break;
            case "snow":
                icon.setBackground(getDrawable(R.drawable.snow));
                break;
            case "sleet":
                icon.setBackground(getDrawable(R.drawable.sleet));
                break;
            case "wind":
                icon.setBackground(getDrawable(R.drawable.windy));
                break;
            case "fog":
                icon.setBackground(getDrawable(R.drawable.foggy));
                break;
            case "cloudy":
                icon.setBackground(getDrawable(R.drawable.clouds));
                break;
            case "partly-cloudy-day":
                icon.setBackground(getDrawable(R.drawable.partly_cloudy));
                break;
            case "partly-cloudy-night":
                icon.setBackground(getDrawable(R.drawable.moon_cloudy));
                break;
            default:

        }
    }
}
