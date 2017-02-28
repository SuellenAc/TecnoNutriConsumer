package com.example.suellencolangelo.tecnonutriconsumer.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by suellencolangelo on 27/02/17.
 */

public class RetrofitBase {
    public static final String BASE_URL = "http://api.tecnonutri.com.br/";

    public static Retrofit createRetrofitInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
