
package com.hegazy.myshop.data.model.getAddressModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAddressModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private GetAddressModelData data;

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

    public GetAddressModelData getData() {
        return data;
    }

    public void setData(GetAddressModelData data) {
        this.data = data;
    }

}
