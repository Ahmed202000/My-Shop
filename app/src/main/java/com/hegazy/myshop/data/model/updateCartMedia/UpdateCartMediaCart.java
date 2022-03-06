
package com.hegazy.myshop.data.model.updateCartMedia;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateCartMediaCart {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("product")
    @Expose
    private UpdateCartMediaProduct product;

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

    public UpdateCartMediaProduct getProduct() {
        return product;
    }

    public void setProduct(UpdateCartMediaProduct product) {
        this.product = product;
    }

}
