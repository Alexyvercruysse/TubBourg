package com.example.alexy.tubtabbar.Services;

import com.example.alexy.tubtabbar.Entities.Hour;
import com.example.alexy.tubtabbar.Entities.Line;
import com.example.alexy.tubtabbar.Entities.Stop;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by iem on 03/02/2017.
 */

public interface TubApi {
    String baseUrl = "http://192.168.240.51/coursPhp/web/index.php/";

    static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("hour/list")
    Call<Map<String, Hour>> listHours();

    @GET("stop/list")
    Call<Map<String, Stop>> listStops();

    @GET("line/list")
    Call<Map<String, Line>> listLines();

}
