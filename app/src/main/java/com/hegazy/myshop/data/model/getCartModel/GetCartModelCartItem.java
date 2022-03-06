
package com.hegazy.myshop.data.model.getCartModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCartModelCartItem {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("product")
    @Expose
    private GetCartModelProduct product;

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

    public GetCartModelProduct getProduct() {
        return product;
    }

    public void setProduct(GetCartModelProduct product) {
        this.product = product;
    }

}
