package com.example.android.weatherapp.view;


import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;

import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.weatherapp.Constants;
import com.example.android.weatherapp.FetchAddressIntentService;
import com.example.android.weatherapp.R;
import com.example.android.weatherapp.model.Currently;
import com.example.android.weatherapp.model.Daily;
import com.example.android.weatherapp.model.Hourly;
import com.example.android.weatherapp.model.Minutely;
import com.example.android.weatherapp.viewmodel.WeatherViewModel;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.tabs.TabLayout;

import java.util.Arrays;


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
    private TabLayout tabView;
    private static final String TAG = "HomeFragment";
    private FragmentManager manager;
    private Location loc;
    private CurrentFragment currentView;
    private HourlyFragment hourlyView;
    private DailyFragment dailyView;
    private AddressResultReceiver resultReceiver;
    private String addressOutput;
    private TextView locationView;
    private Fragment currentFragment;
    private MainActivity main;
    private boolean permissionGranted=false;
    private Place location;
    private Button backButton;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        location = HomeFragmentArgs.fromBundle(getArguments()).getLocation();
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        vm = new WeatherViewModel(getActivity().getApplication());
        locManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        manager = getChildFragmentManager();
        currentView = new CurrentFragment(currently);
        hourlyView = new HourlyFragment(hourly);
        dailyView = new DailyFragment(daily);
        main = (MainActivity) getActivity();

        resultReceiver= new AddressResultReceiver(new Handler());
        locationView = rootView.findViewById(R.id.addressContainer);
        backButton = rootView.findViewById(R.id.backToLocation);

        tabView = rootView.findViewById(R.id.tabContainer);
        tabView.addTab(tabView.newTab().setText("Now"));
//        tabView.addTab(tabView.newTab().setText("Minutely"));
        tabView.addTab(tabView.newTab().setText("Hourly"));
        tabView.addTab(tabView.newTab().setText("Daily"));
        tabView.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Log.d(TAG, "onTabSelected: "+tab);
                switch(tab.getText().toString()){
                    case "Now":
                        manager.beginTransaction().hide(currentFragment).show(currentView).commit();
                        currentFragment = currentView;
                        break;
                    case "Hourly":
                        manager.beginTransaction().hide(currentFragment).show(hourlyView).commit();
                        currentFragment = hourlyView;
                        break;
                    case "Daily":
                        manager.beginTransaction().hide(currentFragment).show(dailyView).commit();
                        currentFragment = dailyView;
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch(tab.getText().toString()){
                    case "Now":
                        manager.beginTransaction().hide(currentFragment).show(currentView).commit();
                        currentFragment = currentView;
                        break;
                    case "Hourly":
                        manager.beginTransaction().hide(currentFragment).show(hourlyView).commit();
                        currentFragment = hourlyView;
                        break;
                    case "Daily":
                        manager.beginTransaction().hide(currentFragment).show(dailyView).commit();
                        currentFragment = dailyView;
                        break;
                }
            }
        });

        manager.beginTransaction().add(R.id.fragContainer, currentView).addToBackStack(null).commit();
        manager.beginTransaction().add(R.id.fragContainer, hourlyView).hide(hourlyView).commit();
        manager.beginTransaction().add(R.id.fragContainer, dailyView).hide(dailyView).commit();
        currentFragment = currentView;

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.navigateTo();
            }
        });

        //set up your observers here.
        vm.getWeather().observe(getViewLifecycleOwner(), response -> {
            hourly = response.getHourly();
            minutely = response.getMinutely();
            currently = response.getCurrently();
            daily = response.getDaily();
            currentView.setCurrentWeather(currently, response.getTimezone());
            hourlyView.setHourlyWeather(hourly, response.getTimezone());
            dailyView.setDailyWeather(daily, response.getTimezone());
        });

        vm.getErrors().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
            }
        });
        if (Build.VERSION.SDK_INT>=23) {
            if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                getActivity().requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
            } else {
                locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 900000, 1000, mLocationListener);
                loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                startIntentService();
                permissionGranted = true;

            }
        }

        //Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getChildFragmentManager().findFragmentById(R.id.autocomplete_fragment);

// Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS));

// Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getLatLng()+" "+place.getAddress());
                main.navigateTo(place);
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
        return rootView;

    }

    @Override
    public void onStop() {
        super.onStop();
        vm.cancelInterval();
    }

    @Override
    public void onResume() {
        super.onResume();
        //start a periodic task to fetch weather data
        if (permissionGranted){
            if (location==null){
                vm.observableWeatherFetch(loc.getLatitude()+","+loc.getLongitude());
            }
            else{
                vm.observableWeatherFetch(location.getLatLng().latitude+","+location.getLatLng().longitude);
            }
        }
    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            if (location == null){
                vm.fetchWeather(Double.toString(location.getLatitude())+","+Double.toString(location.getLongitude()));
            }
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

    protected void startIntentService() {
        Intent intent = new Intent(getActivity(), FetchAddressIntentService.class);
        intent.putExtra(Constants.RECEIVER, resultReceiver);
        if (location==null){
            intent.putExtra(Constants.LOCATION_DATA_EXTRA, loc);
        }
        else{
            intent.putExtra(Constants.PLACE_DATA_EXTRA, location);
        }
        getActivity().startService(intent);
    }

    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            if (resultData == null) {
                return;
            }

            // Display the address string
            // or an error message sent from the intent service.
            addressOutput = resultData.getString(Constants.RESULT_DATA_KEY);
            if (addressOutput == null) {
                addressOutput = "";
            }
            locationView.setText(addressOutput);
            // Show a toast message if an address was found.
            if (resultCode == Constants.SUCCESS_RESULT) {
                Log.d(TAG, "onReceiveResult: ");
            }

        }
    }
}
