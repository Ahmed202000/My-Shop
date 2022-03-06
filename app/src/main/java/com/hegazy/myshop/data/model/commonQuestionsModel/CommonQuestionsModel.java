
package com.hegazy.myshop.data.model.commonQuestionsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonQuestionsModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private CommonQuestionsModelData data;

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

    public CommonQuestionsModelData getData() {
        return data;
    }

    public void setData(CommonQuestionsModelData data) {
        this.data = data;
    }

}
