package com.example.suellencolangelo.tecnonutriconsumer.model;

/**
 * Created by suellencolangelo on 28/02/17.
 */

public class ProfileRequest extends ItemsRequest {
    private Profile profile;

    public Profile getProfile() {
        if (profile == null) {
            profile = new Profile();
        }
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }


    /***
     * Copia os campos e adicionar os registros de feed ao fim da lista de items
     */
    @Override
    public void copyAndAdd(ItemsRequest anotherItemRequest) {
        super.copyAndAdd(anotherItemRequest);
        if (anotherItemRequest instanceof ProfileRequest) {
            setProfile(((ProfileRequest) anotherItemRequest).getProfile());
        }
    }
}
