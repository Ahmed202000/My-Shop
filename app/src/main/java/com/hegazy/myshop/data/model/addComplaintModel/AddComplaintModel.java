
package com.hegazy.myshop.data.model.addComplaintModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddComplaintModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private AddComplaintModelData data;

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

    public AddComplaintModelData getData() {
        return data;
    }

    public void setData(AddComplaintModelData data) {
        this.data = data;
    }

}
