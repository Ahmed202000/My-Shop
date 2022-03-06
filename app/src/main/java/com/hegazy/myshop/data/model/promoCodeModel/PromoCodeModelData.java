
package com.hegazy.myshop.data.model.promoCodeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromoCodeModelData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("percentage")
    @Expose
    private Integer percentage;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("usage_per_user")
    @Expose
    private Integer usagePerUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getUsagePerUser() {
        return usagePerUser;
    }

    public void setUsagePerUser(Integer usagePerUser) {
        this.usagePerUser = usagePerUser;
    }

}
