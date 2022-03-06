
package com.hegazy.myshop.data.model.addOrRemoveCartModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddOrRemoveCartModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private AddOrRemoveCartModelData data;

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

    public AddOrRemoveCartModelData getData() {
        return data;
    }

    public void setData(AddOrRemoveCartModelData data) {
        this.data = data;
    }

}
