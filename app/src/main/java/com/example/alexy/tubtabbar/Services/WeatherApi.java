package com.example.alexy.tubtabbar.Services;

import com.example.alexy.tubtabbar.Entities.Hour;
import com.example.alexy.tubtabbar.Entities.Line;
import com.example.alexy.tubtabbar.Entities.Stop;
import com.example.alexy.tubtabbar.R;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
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
    //"weather?id=3031009&APPID="
    @GET("weather")
    Call<String> getWeather(@Query("id") int cityId, @Query("APPID") String appId);
}
