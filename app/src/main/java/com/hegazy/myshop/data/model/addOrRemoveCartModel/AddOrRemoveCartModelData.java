
package com.hegazy.myshop.data.model.addOrRemoveCartModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddOrRemoveCartModelData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("product")
    @Expose
    private AddOrRemoveCartModelProduct product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public AddOrRemoveCartModelProduct getProduct() {
        return product;
    }

    public void setProduct(AddOrRemoveCartModelProduct product) {
        this.product = product;
    }

}
