package com.example.suellencolangelo.tecnonutriconsumer.item.detail;

import android.support.annotation.NonNull;

import com.example.suellencolangelo.tecnonutriconsumer.data.ItemsEndpoint;
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
    public void setView(ItemDetailsContract.View view) {
        this.mView = view;
    }

    @Override
    public Item getItem() {
        return mItem;
    }

    @Override
    public void updateItem() {
        ItemsEndpoint apiService =
                RetrofitBase.getInstance().create(ItemsEndpoint.class);
        Call<ItemRequest> call = apiService.getItem(mItem.getFeedHash());
        call.enqueue(this);
    }

    @Override
    public int getItemCount() {
        return mItem.getFoods().size() + 2; // Dados do autor + total
    }

    @Override
    public Object getObjectAdapter(int position) {
        if (position==0 || position==getItemCount()-1){
            return mItem;
        }
        return mItem.getFoods().get(position-1);
    }

    @Override
    public void onResponse(Call<ItemRequest> call, Response<ItemRequest> response) {
        if (mView == null) {
            return;
        }
        if (response.isSuccessful()) {
            mItem = response.body().getItem();
            mView.reloadData(mItem);
        } else {
            mView.showError();
        }
    }

    @Override
    public void onFailure(Call<ItemRequest> call, Throwable t) {
        if (mView != null) {
            mView.showError();
        }
    }
}
