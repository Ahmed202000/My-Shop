
package com.hegazy.myshop.data.model.productDetailsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailsModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ProductDetailsModelData data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ProductDetailsModelData getData() {
        return data;
    }

    public void setData(ProductDetailsModelData data) {
        this.data = data;
    }

}
