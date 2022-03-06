
package com.hegazy.myshop.data.model.sendEmailModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendEmailModel {

    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public SendEmailModelData data;

}
