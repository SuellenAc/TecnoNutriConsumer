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
        void setView(ItemDetailsContract.View view);
        Item getItem();
        void updateItem();
        int getItemCount();
        // Retorna o objeto que o adapter deve mostrar - pode ser item ou food.
        Object getObjectAdapter(int position);
    }

}
