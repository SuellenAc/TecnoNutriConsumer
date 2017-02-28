package com.example.suellencolangelo.tecnonutriconsumer.item.detail;

import com.example.suellencolangelo.tecnonutriconsumer.model.Item;

/**
 * Created by suellencolangelo on 26/02/17.
 */

public interface ItemDetailsContract {

    interface View{
        void reloadData(Item item);
        void showError();
    }

    interface Presenter {
        Item getItem();
        void updateItem();
    }

}
