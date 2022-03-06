
package com.hegazy.myshop.data.model.productDetailsModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailsModelData {

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
    private Double discount;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("in_favorites")
    @Expose
    private Boolean inFavorites;
    @SerializedName("in_cart")
    @Expose
    private Boolean inCart;
    @SerializedName("images")
    @Expose
    private List<String> images = null;

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

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getInFavorites() {
        return inFavorites;
    }

    public void setInFavorites(Boolean inFavorites) {
        this.inFavorites = inFavorites;
    }

    public Boolean getInCart() {
        return inCart;
    }

    public void setInCart(Boolean inCart) {
        this.inCart = inCart;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

}
