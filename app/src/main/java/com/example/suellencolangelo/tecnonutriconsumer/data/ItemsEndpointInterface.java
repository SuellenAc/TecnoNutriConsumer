package com.example.suellencolangelo.tecnonutriconsumer.data;

import com.example.suellencolangelo.tecnonutriconsumer.model.ItemsRequestData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by suellencolangelo on 27/02/17.
 */

public interface ItemsEndpointInterface {

        @GET("api/v4/feed")
        Call<ItemsRequestData> getItems();

        @GET("api/v4/feed?p={p}&t={t}")
        Call<ItemsRequestData> getOldItems(@Path("p") int pagination, @Path("t") int track);

}
