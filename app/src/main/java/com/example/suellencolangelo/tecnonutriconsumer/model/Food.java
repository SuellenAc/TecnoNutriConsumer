package com.example.suellencolangelo.tecnonutriconsumer.model;

import org.parceler.Parcel;

/**
 * Created by suellencolangelo on 01/03/17.
 */

@Parcel(Parcel.Serialization.BEAN)
public class Food {
    private String description = "";
    private String measure = "";
    private Float amount = 0.0f;
    private Float weight = 0.0f;
    private Float energy = 0.0f;
    private Float carbohydrate = 0.0f;
    private Float fat = 0.0f;
    private Float protein = 0.0f;

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(Float carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getEnergy() {
        return energy;
    }

    public void setEnergy(Float energy) {
        this.energy = energy;
    }

    public Float getFat() {
        return fat;
    }

    public void setFat(Float fat) {
        this.fat = fat;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Float getProtein() {
        return protein;
    }

    public void setProtein(Float protein) {
        this.protein = protein;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }
}
