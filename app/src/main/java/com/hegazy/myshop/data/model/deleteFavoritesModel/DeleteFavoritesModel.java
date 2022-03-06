
package com.hegazy.myshop.data.model.deleteFavoritesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteFavoritesModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private DeleteFavoritesModelData data;

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

    public DeleteFavoritesModelData getData() {
        return data;
    }

    public void setData(DeleteFavoritesModelData data) {
        this.data = data;
    }

}
