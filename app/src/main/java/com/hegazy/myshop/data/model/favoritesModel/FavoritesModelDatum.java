
package com.hegazy.myshop.data.model.favoritesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavoritesModelDatum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product")
    @Expose
    private FavoritesModelProduct product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FavoritesModelProduct getProduct() {
        return product;
    }

    public void setProduct(FavoritesModelProduct product) {
        this.product = product;
    }

}
