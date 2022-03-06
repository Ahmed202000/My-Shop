
package com.hegazy.myshop.data.model.myOrderModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyOrderModelDatum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("total")
    @Expose
    private Double total;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
