package com.example.suellencolangelo.tecnonutriconsumer.item.detail;

import android.support.annotation.NonNull;

import com.example.suellencolangelo.tecnonutriconsumer.data.ItemsEndpointInterface;
import com.example.suellencolangelo.tecnonutriconsumer.data.RetrofitBase;
import com.example.suellencolangelo.tecnonutriconsumer.model.Item;
import com.example.suellencolangelo.tecnonutriconsumer.model.ItemRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by suellencolangelo on 26/02/17.
 */

public class ItemDetailsPresenter implements ItemDetailsContract.Presenter, Callback<ItemRequest> {
    @NonNull
    private Item mItem;

    private ItemDetailsContract.View mView;

    public ItemDetailsPresenter(Item item, ItemDetailsContract.View view) {
        this.mItem = item;
        this.mView = view;
    }

    @Override
    public Item getItem() {
        return mItem;
    }

    @Override
    public void updateItem() {
        ItemsEndpointInterface apiService =
                RetrofitBase.createRetrofitInstance().create(ItemsEndpointInterface.class);
        Call<ItemRequest> call = apiService.getItem(mItem.getFeedHash());
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ItemRequest> call, Response<ItemRequest> response) {
        if (response.isSuccessful()) {
            mView.reloadData(response.body().getItem());
        } else {
            mView.showError();
        }
    }

    @Override
    public void onFailure(Call<ItemRequest> call, Throwable t) {
        mView.showError();
    }
}
