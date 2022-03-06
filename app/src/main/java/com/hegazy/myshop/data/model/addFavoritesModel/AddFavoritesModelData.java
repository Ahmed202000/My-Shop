
package com.hegazy.myshop.data.model.addFavoritesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddFavoritesModelData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product")
    @Expose
    private AddFavoritesModelProduct product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AddFavoritesModelProduct getProduct() {
        return product;
    }

    public void setProduct(AddFavoritesModelProduct product) {
        this.product = product;
    }

}
