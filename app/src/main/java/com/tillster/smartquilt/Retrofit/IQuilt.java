package com.tillster.smartquilt.Retrofit;

import com.tillster.smartquilt.Quilt;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IQuilt
{


    @GET("/")
    Observable<Quilt> getShape();

}
