package com.example.android.weatherapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.android.weatherapp.model.Response;
import com.example.android.weatherapp.repository.APIKey;
import com.example.android.weatherapp.repository.Repository;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeatherViewModel extends AndroidViewModel {
    private MutableLiveData<Response> Weather= new MutableLiveData<Response>();
    private MutableLiveData<String> errors = new MutableLiveData<String>();
    private static final String TAG = "WeatherViewModel";
    private Disposable subscriber;

    public WeatherViewModel(@NonNull Application application) {
        super(application);
    }

    public Repository mainRepo = Repository.INSTANCE;

    public void fetchWeather(String location){
        mainRepo.service.fetchWeather(APIKey.key, location, "", "", "", "")
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

    public void observableWeatherFetch(String location){
        Data.Builder data = new Data.Builder();
        data.putString("Location", location);
        PeriodicWorkRequest fetch = new PeriodicWorkRequest.Builder(ApiWorker.class, PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS).setInputData(data.build()).build();
        WorkManager.getInstance(getApplication()).enqueueUniquePeriodicWork("WEATHER_NOTIFICATIONS",ExistingPeriodicWorkPolicy.KEEP,fetch);
        subscriber = Observable.interval(0, 5, TimeUnit.MINUTES, Schedulers.io())
                .map(tick-> {
                    Log.d(TAG, "observableWeatherFetch: ");
                    this.fetchWeather(location);
                return 1;})
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it->{
            Log.d("IntervalExample", it.toString());

        });
    }

    public void cancelInterval(){
        if (subscriber!=null && !subscriber.isDisposed()){
            subscriber.dispose();
        }
    }
}
