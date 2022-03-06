
package com.hegazy.myshop.data.model.getCartModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCartModelData {

    @SerializedName("cart_items")
    @Expose
    private List<GetCartModelCartItem> cartItems = null;
    @SerializedName("sub_total")
    @Expose
    private Double subTotal;
    @SerializedName("total")
    @Expose
    private Double total;

    public List<GetCartModelCartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<GetCartModelCartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
