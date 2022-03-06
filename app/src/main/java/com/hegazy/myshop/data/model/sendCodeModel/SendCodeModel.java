
package com.hegazy.myshop.data.model.sendCodeModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendCodeModel {

    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public sendCodeModelData data;

}
