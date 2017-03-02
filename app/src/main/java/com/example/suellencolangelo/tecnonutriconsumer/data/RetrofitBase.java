package com.example.suellencolangelo.tecnonutriconsumer.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by suellencolangelo on 27/02/17.
 */

public class RetrofitBase {
    public static final String BASE_URL = "http://api.tecnonutri.com.br/";

    private static RetrofitBase mInstance;
    private Retrofit mRetrofit;

    private RetrofitBase() {
    }

    private Retrofit getRetrofit() {
        if (mRetrofit == null) {
            // formato de data
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .create();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return mRetrofit;
    }

    public static synchronized Retrofit getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitBase();
        }
        return mInstance.getRetrofit();
    }

}
