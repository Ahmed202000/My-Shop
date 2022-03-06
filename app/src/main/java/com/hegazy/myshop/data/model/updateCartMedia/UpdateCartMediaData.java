
package com.hegazy.myshop.data.model.updateCartMedia;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateCartMediaData {

    @SerializedName("cart")
    @Expose
    private UpdateCartMediaCart cart;
    @SerializedName("sub_total")
    @Expose
    private Integer subTotal;
    @SerializedName("total")
    @Expose
    private Integer total;

    public UpdateCartMediaCart getCart() {
        return cart;
    }

    public void setCart(UpdateCartMediaCart cart) {
        this.cart = cart;
    }

    public Integer getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Integer subTotal) {
        this.subTotal = subTotal;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
