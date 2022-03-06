
package com.hegazy.myshop.data.model.deleteFavoritesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteFavoritesModelProduct {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("old_price")
    @Expose
    private Double oldPrice;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("image")
    @Expose
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
