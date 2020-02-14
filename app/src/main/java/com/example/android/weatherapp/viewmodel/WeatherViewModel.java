package com.example.android.weatherapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.weatherapp.model.Response;
import com.example.android.weatherapp.repository.Repository;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeatherViewModel extends AndroidViewModel {
    private MutableLiveData<Response> Weather= new MutableLiveData<Response>();
    private MutableLiveData<String> errors = new MutableLiveData<String>();


    public WeatherViewModel(@NonNull Application application) {
        super(application);
    }

    public Repository mainRepo = Repository.INSTANCE;

    public void fetchWeather(String location){
        mainRepo.service.fetchWeather(location, "", "", "", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
                        Weather.postValue(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        errors.postValue(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Response> getWeather() {
        return Weather;
    }

    public LiveData<String> getErrors() {
        return errors;
    }
}
