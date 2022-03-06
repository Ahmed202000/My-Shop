
package com.hegazy.myshop.data.model.homeModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeModelBanner {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("category")
    @Expose
    private Object category;
    @SerializedName("product")
    @Expose
    private Object product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Object getCategory() {
        return category;
    }

    public void setCategory(Object category) {
        this.category = category;
    }

    public Object getProduct() {
        return product;
    }

    public void setProduct(Object product) {
        this.product = product;
    }

}
