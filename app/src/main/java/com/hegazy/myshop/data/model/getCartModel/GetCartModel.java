
package com.hegazy.myshop.data.model.getCartModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCartModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private GetCartModelData data;

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

    public GetCartModelData getData() {
        return data;
    }

    public void setData(GetCartModelData data) {
        this.data = data;
    }

}
