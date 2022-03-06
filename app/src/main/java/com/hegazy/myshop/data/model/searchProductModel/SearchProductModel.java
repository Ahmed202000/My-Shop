
package com.hegazy.myshop.data.model.searchProductModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchProductModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private SearchProductModelData data;

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

    public SearchProductModelData getData() {
        return data;
    }

    public void setData(SearchProductModelData data) {
        this.data = data;
    }

}
