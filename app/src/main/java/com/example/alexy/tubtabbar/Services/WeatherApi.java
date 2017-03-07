package com.example.alexy.tubtabbar.Services;

import com.example.alexy.tubtabbar.Entities.WeatherObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by iem on 07/03/2017.
 */

public interface WeatherApi {
    String baseUrl = "http://api.openweathermap.org/data/2.5/";
    static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("weather")
    Call<WeatherObject> getWeatherObject(@Query("id") int cityId, @Query("APPID") String appId);
}
