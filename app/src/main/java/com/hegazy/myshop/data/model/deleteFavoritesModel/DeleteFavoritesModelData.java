
package com.hegazy.myshop.data.model.deleteFavoritesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteFavoritesModelData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product")
    @Expose
    private DeleteFavoritesModelProduct product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DeleteFavoritesModelProduct getProduct() {
        return product;
    }

    public void setProduct(DeleteFavoritesModelProduct product) {
        this.product = product;
    }

}
