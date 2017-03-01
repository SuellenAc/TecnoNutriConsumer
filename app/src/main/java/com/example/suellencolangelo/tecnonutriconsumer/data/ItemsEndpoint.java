package com.example.suellencolangelo.tecnonutriconsumer.data;

import com.example.suellencolangelo.tecnonutriconsumer.model.ItemRequest;
import com.example.suellencolangelo.tecnonutriconsumer.model.ItemsRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by suellencolangelo on 27/02/17.
 */

public interface ItemsEndpoint {
    @GET("api/v4/feed")
    Call<ItemsRequest> getItems();

    @GET("api/v4/feed")
    Call<ItemsRequest> getOldItems(@Query("p") int pagination, @Query("t") int track);

    @GET("api/v4/feed/{feedHash}")
    Call<ItemRequest> getItem(@Path("feedHash") String hash);

}
