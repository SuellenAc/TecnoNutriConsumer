package com.example.suellencolangelo.tecnonutriconsumer.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suellencolangelo on 27/02/17.
 */

public class ItemsRequestData {
    private Boolean success;
    private Integer t;
    private Integer p;
    private List<Item> items;

    public ItemsRequestData(){
        items = new ArrayList<>();
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getT() {
        return t;
    }

    public void setT(Integer t) {
        this.t = t;
    }

    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
