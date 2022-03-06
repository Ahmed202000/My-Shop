
package com.hegazy.myshop.data.model.myOrderModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyOrderModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private MyOrderModelData data;

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

    public MyOrderModelData getData() {
        return data;
    }

    public void setData(MyOrderModelData data) {
        this.data = data;
    }

}
