package com.example.suellencolangelo.tecnonutriconsumer.author.profileDetail;

import com.example.suellencolangelo.tecnonutriconsumer.model.Item;
import com.example.suellencolangelo.tecnonutriconsumer.model.Profile;

/**
 * Created by suellencolangelo on 28/02/17.
 */

public class ProfileDetailContract {

    interface View {
        void openFeedDetail(Item item);
        void onItemsRetrieved();
        void onFailure();
    }

    // Implementado pelo Presenter
    interface Presenter {
        void setView(ProfileDetailContract.View view);
        void retrieveProfile();
        void retrieveOldProfileItems();
        int itemCount();
        Item getItem(int position);
        Profile getProfile();
        void onFeedClick ( int position);
    }
}
