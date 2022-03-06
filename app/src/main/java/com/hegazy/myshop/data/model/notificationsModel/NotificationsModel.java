
package com.hegazy.myshop.data.model.notificationsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationsModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private NotificationsModelData data;

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

    public NotificationsModelData getData() {
        return data;
    }

    public void setData(NotificationsModelData data) {
        this.data = data;
    }

}
