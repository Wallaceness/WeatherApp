package com.example.android.weatherapp.view;

import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.weatherapp.R;
import com.example.android.weatherapp.databinding.FragmentHourlyBinding;
import com.example.android.weatherapp.model.DataItem;
import com.example.android.weatherapp.model.Hourly;

import java.util.ArrayList;
import java.util.List;


public class HourlyFragment extends Fragment {
    private FragmentHourlyBinding binder;
    private Hourly hourly;
    private ViewPager2 viewPager;
    WeatherDataAdapter pagerAdapter;
    ArrayList<DataItem> weather = new ArrayList<DataItem>();
    ImageView icon;
    MainActivity main;
    String Timezone;

    private OnFragmentInteractionListener mListener;

    public HourlyFragment() {
        // Required empty public constructor
    }

    public HourlyFragment(Hourly hours){
        hourly=hours;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_hourly, container, false);
        View rootView = binder.getRoot();
        pagerAdapter = new WeatherDataAdapter(this, weather, "hourly");
        viewPager = rootView.findViewById(R.id.hourlyPager);
        viewPager.setAdapter(pagerAdapter);
        icon = rootView.findViewById(R.id.iconHourly);
        main = (MainActivity) getActivity();
        return rootView;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void setHourlyWeather(Hourly hours, String timezone){
        this.hourly = hours;
        binder.setHourlyforecast(hours);
        icon.setBackground(main.renderIcon(hourly.getIcon()));
        pagerAdapter.updateWeather(hourly.getData(), timezone);
    }
}
