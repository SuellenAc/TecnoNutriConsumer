package com.example.suellencolangelo.tecnonutriconsumer.item.items;

import android.text.TextUtils;

import com.example.suellencolangelo.tecnonutriconsumer.data.ItemsEndpoint;
import com.example.suellencolangelo.tecnonutriconsumer.data.RetrofitBase;
import com.example.suellencolangelo.tecnonutriconsumer.model.Item;
import com.example.suellencolangelo.tecnonutriconsumer.model.ItemsRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by suellencolangelo on 26/02/17.
 */

public class ItemPresenter implements ItemContract.Presenter, Callback<ItemsRequest> {
    private ItemsRequest mItemsRequest;
    private ItemContract.View mView;

    public ItemPresenter(ItemContract.View view) {
        this.mView = view;
        mItemsRequest = new ItemsRequest();
    }

    @Override
    public void retrieveItems() {
        ItemsEndpoint apiService = RetrofitBase.getInstance().create(ItemsEndpoint.class);
        Call<ItemsRequest> call = apiService.getItems();
        call.enqueue(this);
    }

    @Override
    public void retrieveOldItems() {
        ItemsEndpoint apiService = RetrofitBase.getInstance().create(ItemsEndpoint.class);
        Call<ItemsRequest> call = apiService.getOldItems(mItemsRequest.getP() + 1, mItemsRequest.getT());
        call.enqueue(this);
    }

    @Override
    public int itemCount() {
        if (mItemsRequest != null) {
            return mItemsRequest.getItems().size();
        }
        return 0;
    }

    @Override
    public Item getItem(int position) {
        if (mItemsRequest != null) {
            return mItemsRequest.getItems().get(position);
        }
        return new Item();
    }

    @Override
    public void onAuthorClick(int position) {
        mView.openAuthor(getItem(position).getProfile());
    }

    @Override
    public void onFeedClick(int position) {
        mView.openFeedDetail(getItem(position));
    }


    @Override
    public void onResponse(Call<ItemsRequest> call, Response<ItemsRequest> response) {
        // Verifica se é primeira requisição, nesse caso substitui os dados pelos da requisição.

        String query = call.request().url().query();
        boolean isFirstRequest = TextUtils.isEmpty(query);

        if (response.isSuccessful()) {
            if (isFirstRequest) {
                mItemsRequest = response.body();
            } else {
                mItemsRequest.copyAndAdd(response.body());
            }
            if (mItemsRequest.getItems().size() == 0) {
                mView.showNoDataView();
            } else {
                mView.hideNoDataView();
            }
            mView.onItemsRetrieved();
        } else {
            mView.onFailure();
        }
    }

    @Override
    public void onFailure(Call<ItemsRequest> call, Throwable t) {
        if (mItemsRequest.getItems().size() == 0) {
            mView.showNoDataView();
        } else {
            mView.hideNoDataView();
        }
        mView.onFailure();
    }
}
