package com.example.android.weatherapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

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
}
