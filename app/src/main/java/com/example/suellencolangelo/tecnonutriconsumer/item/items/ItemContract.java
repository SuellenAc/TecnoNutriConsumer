package com.example.suellencolangelo.tecnonutriconsumer.item.items;

import com.example.suellencolangelo.tecnonutriconsumer.model.Profile;
import com.example.suellencolangelo.tecnonutriconsumer.model.Item;

/**
 * Created by suellencolangelo on 26/02/17.
 */

public interface ItemContract {

    interface View {
        void openAuthor(Profile profile);
        void openFeedDetail(Item item);
        void onItemsRetrieved();
        void onFailure();
        void showNoDataView();
        void hideNoDataView();
    }

    // Implementado pelo Presenter
    interface Presenter {
        void retrieveItems();
        void retrieveOldItems();
        int itemCount();
        Item getItem(int position);
        void onAuthorClick ( int position);
        void onFeedClick ( int position);
    }
}
