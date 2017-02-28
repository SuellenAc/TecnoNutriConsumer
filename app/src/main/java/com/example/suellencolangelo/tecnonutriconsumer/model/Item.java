package com.example.suellencolangelo.tecnonutriconsumer.model;

/**
 * Created by suellencolangelo on 26/02/17.
 */

public class Item {
    private String feedHash;
    private Integer id;
    private Profile profile;
    private Integer meal;
    private String date;
    private String image;
    private Float energy;
    private Float carbohydrate;
    private Float fat;
    private Float protein;
    private Float fatTrans;
    private Float fatSat;
    private Float fiber;
    private Float sugar;
    private Float sodium;
    private Float calcium;
    private Float iron;
    private Float moderation;
    private String locale;

    public Float getSugar() {
        return sugar;
    }

    public void setSugar(Float sugar) {
        this.sugar = sugar;
    }

    public Float getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(Float carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Float getFatSat() {
        return fatSat;
    }

    public void setFatSat(Float fatSat) {
        this.fatSat = fatSat;
    }

    public Float getFatTrans() {
        return fatTrans;
    }

    public void setFatTrans(Float fatTrans) {
        this.fatTrans = fatTrans;
    }

    public String getFeedHash() {
        return feedHash;
    }

    public void setFeedHash(String feedHash) {
        this.feedHash = feedHash;
    }

    public Float getFiber() {
        return fiber;
    }

    public void setFiber(Float fiber) {
        this.fiber = fiber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getIron() {
        return iron;
    }

    public void setIron(Float iron) {
        this.iron = iron;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Integer getMeal() {
        return meal;
    }

    public void setMeal(Integer meal) {
        this.meal = meal;
    }

    public Float getModeration() {
        return moderation;
    }

    public void setModeration(Float moderation) {
        this.moderation = moderation;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Float getProtein() {
        return protein;
    }

    public void setProtein(Float protein) {
        this.protein = protein;
    }

    public Float getSodium() {
        return sodium;
    }

    public void setSodium(Float sodium) {
        this.sodium = sodium;
    }

    public Float getCalcium() {
        return calcium;
    }

    public void setCalcium(Float calcium) {
        this.calcium = calcium;
    }
}
