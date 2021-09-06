package com.tillster.smartquilt.Retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient
{

    private  static Retrofit retrofit;

    public static Retrofit getRetrofit()
    {
        if (retrofit== null)
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.8.1")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        return retrofit;
    }
}
