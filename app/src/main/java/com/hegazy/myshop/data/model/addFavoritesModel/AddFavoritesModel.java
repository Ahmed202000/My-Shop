
package com.hegazy.myshop.data.model.addFavoritesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddFavoritesModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private AddFavoritesModelData data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AddFavoritesModelData getData() {
        return data;
    }

    public void setData(AddFavoritesModelData data) {
        this.data = data;
    }

}
