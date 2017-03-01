package com.example.suellencolangelo.tecnonutriconsumer.data;
import com.example.suellencolangelo.tecnonutriconsumer.model.ProfileRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by suellencolangelo on 28/02/17.
 */

public interface ProfileEndPoint {
    @GET("api/v4/profile/{id}")
    Call<ProfileRequest> getProfile(@Path("id") int idProfile);

    @GET("api/v4/profile/{id}")
    Call<ProfileRequest> getOldProfileItems(@Path("id") int idProfile, @Query("p") int pagination, @Query("t") int track);
}
