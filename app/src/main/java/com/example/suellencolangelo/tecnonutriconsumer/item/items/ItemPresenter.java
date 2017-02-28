package com.example.suellencolangelo.tecnonutriconsumer.item.items;

import com.example.suellencolangelo.tecnonutriconsumer.data.ItemsEndpointInterface;
import com.example.suellencolangelo.tecnonutriconsumer.data.RetrofitBase;
import com.example.suellencolangelo.tecnonutriconsumer.model.Item;
import com.example.suellencolangelo.tecnonutriconsumer.model.ItemsRequestData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by suellencolangelo on 26/02/17.
 */

public class ItemPresenter implements ItemContract.Presenter, Callback<ItemsRequestData> {
    private List<Item> mItems;
    private ItemContract.View mView;

    public ItemPresenter(ItemContract.View view) {
        this.mView = view;
        mItems = new ArrayList<>();
    }

    @Override
    public void retrieveItems() {
        ItemsEndpointInterface apiService =
                RetrofitBase.createRetrofitInstance().create(ItemsEndpointInterface.class);
        Call<ItemsRequestData> call = apiService.getItems();
        call.enqueue(this);
    }

    @Override
    public void retrieveOldItems() {

    }

    @Override
    public int itemCount() {
        if (mItems != null) {
            return mItems.size();
        }
        return 0;
    }

    @Override
    public Item getItem(int position) {
        if (mItems != null) {
            return mItems.get(position);
        }
        return null;
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
    public void onResponse(Call<ItemsRequestData> call, Response<ItemsRequestData> response) {
        if (response.isSuccessful()){
            mItems = response.body().getItems();
            mView.onItemsRetrieved();
        }
    }

    @Override
    public void onFailure(Call<ItemsRequestData> call, Throwable t) {
        mView.onCallFailure();
    }
}
