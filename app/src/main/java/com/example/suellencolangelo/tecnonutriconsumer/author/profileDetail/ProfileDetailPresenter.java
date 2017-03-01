package com.example.suellencolangelo.tecnonutriconsumer.author.profileDetail;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.suellencolangelo.tecnonutriconsumer.data.ProfileEndPoint;
import com.example.suellencolangelo.tecnonutriconsumer.data.RetrofitBase;
import com.example.suellencolangelo.tecnonutriconsumer.model.Item;
import com.example.suellencolangelo.tecnonutriconsumer.model.Profile;
import com.example.suellencolangelo.tecnonutriconsumer.model.ProfileRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by suellencolangelo on 28/02/17.
 */

public class ProfileDetailPresenter implements ProfileDetailContract.Presenter, Callback<ProfileRequest> {
    private ProfileRequest mProfileRequest;

    @NonNull
    private ProfileDetailContract.View mView;

    public ProfileDetailPresenter(@NonNull Profile profile, @NonNull ProfileDetailContract.View view) {
        this.mProfileRequest = new ProfileRequest();
        mProfileRequest.setProfile(profile);
        this.mView = view;
    }

    @Override
    public void retrieveProfile() {
        ProfileEndPoint apiService = RetrofitBase.getInstance().create(ProfileEndPoint.class);
        Call<ProfileRequest> call = apiService.getProfile(mProfileRequest.getProfile().getId());
        call.enqueue(this);
    }

    @Override
    public void retrieveOldProfileItems() {
        ProfileEndPoint apiService = RetrofitBase.getInstance().create(ProfileEndPoint.class);
        Call<ProfileRequest> call = apiService.getOldProfileItems(mProfileRequest.getProfile().getId(),
                mProfileRequest.getP()+1,
                mProfileRequest.getT());
        call.enqueue(this);
    }

    @Override
    public int itemCount() {
        if (mProfileRequest != null) {
            return mProfileRequest.getItems().size();
        }
        return 0;
    }

    @Override
    public Item getItem(int position) {
        if (mProfileRequest != null) {
            return mProfileRequest.getItems().get(position);
        }
        return new Item();
    }

    @Override
    public Profile getProfile() {
        if (mProfileRequest != null) {
            return mProfileRequest.getProfile();
        }
        return new Profile();
    }

    @Override
    public void onFeedClick(int position) {
        mView.openFeedDetail(getItem(position));
    }

    @Override
    public void onResponse(Call<ProfileRequest> call, Response<ProfileRequest> response) {
        // Verifica se é primeira requisição, nesse caso substitui os dados pelos da requisição.
        String query = call.request().url().query();
        boolean isFirstRequest = TextUtils.isEmpty(query);

        if (response.isSuccessful()){
            if (isFirstRequest){
                mProfileRequest = response.body();
            } else {
                mProfileRequest.copyAndAdd(response.body());
            }
            mView.onItemsRetrieved();
        } else {
            mView.onFailure();
        }
    }

    @Override
    public void onFailure(Call<ProfileRequest> call, Throwable t) {
        mView.onFailure();
    }
}
