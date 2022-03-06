
package com.hegazy.myshop.data.model.sendCodeModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class sendCodeModelData {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("points")
    @Expose
    public Double points;

}
