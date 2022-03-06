
package com.hegazy.myshop.data.model.categoriesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoriesModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private CategoriesModelData data;

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

    public CategoriesModelData getData() {
        return data;
    }

    public void setData(CategoriesModelData data) {
        this.data = data;
    }

}
