
package com.hegazy.myshop.data.model.orderDetailsModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetailsModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private OrderDetailsModelData data;

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

    public OrderDetailsModelData getData() {
        return data;
    }

    public void setData(OrderDetailsModelData data) {
        this.data = data;
    }

}
