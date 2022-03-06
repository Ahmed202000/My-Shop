
package com.hegazy.myshop.data.model.promoCodeModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromoCodeModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private PromoCodeModelData data;

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

    public PromoCodeModelData getData() {
        return data;
    }

    public void setData(PromoCodeModelData data) {
        this.data = data;
    }

}
