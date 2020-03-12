package com.example.android.weatherapp.viewmodel;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.android.weatherapp.R;
import com.example.android.weatherapp.model.Currently;
import com.example.android.weatherapp.model.Response;
import com.example.android.weatherapp.repository.APIKey;
import com.example.android.weatherapp.repository.Repository;
import com.example.android.weatherapp.view.MainActivity;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ApiWorker extends Worker {
    private static final String TAG = "ApiWorker";
    private int notifyId = 1;

    public ApiWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String location = getInputData().getString("Location");
        Repository.INSTANCE.service.fetchWeather(APIKey.key, location, "", "", "", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
                        Currently currently = response.getCurrently();
                        //create weather notification
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        PendingIntent pending = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                        NotificationCompat.Builder builder= new NotificationCompat.Builder(getApplicationContext(), "WEATHER_CHANNEL")
                                .setSmallIcon(getIconId(currently.getIcon()))
                                .setContentTitle("Current Weather Conditions: ")
                                .setContentText(currently.getSummary()+", "+currently.getTemperature()+" Degrees")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .setContentIntent(pending)
                                .setAutoCancel(true);

                        Notification note = builder.build();
                        NotificationManagerCompat notificationMan = NotificationManagerCompat.from(getApplicationContext());
                        notificationMan.notify(notifyId, note);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        Log.d(TAG, location);
        Log.d(TAG, location);
        return Result.success();
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
}
