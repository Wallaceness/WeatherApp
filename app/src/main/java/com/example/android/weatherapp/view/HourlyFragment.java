package com.example.android.weatherapp.view;

import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.weatherapp.R;
import com.example.android.weatherapp.databinding.FragmentHourlyBinding;
import com.example.android.weatherapp.model.Hourly;


public class HourlyFragment extends Fragment {
    private FragmentHourlyBinding binder;
    private Hourly hourly;

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
        return binder.getRoot();
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

    public void setHourlyWeather(Hourly hours){
        this.hourly = hours;
        binder.setHourlyforecast(hourly);
    }
}
