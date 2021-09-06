package com.tillster.smartquilt;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.tillster.smartquilt.Retrofit.IQuilt;
import com.tillster.smartquilt.Retrofit.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    TextView JSON;
    CompositeDisposable compositeDisposable;
    IQuilt service;
    String TAG ="boom";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JSON = findViewById(R.id.JSONResponse);

        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getRetrofit();
        service = retrofit.create(IQuilt.class);

        getWeatherInformation();
    }


    private void getWeatherInformation()
    {
        compositeDisposable.add(service.getShape()

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Quilt>() {
                               @Override
                               public void accept(Quilt quilt) throws Exception
                               {
                                   Toast.makeText(MainActivity.this, "ENTERED", Toast.LENGTH_SHORT).show();
                                   JSON.setText(quilt.getName());



                               }

                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception
                               {
                                   Toast.makeText(MainActivity.this, ""+ throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                   Log.i(TAG, "error: " + throwable.getMessage());
                               }
                           }
                )
        );


    }



}