
package com.hegazy.myshop.data.model.orderDetailsModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderDetailsModelData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("points")
    @Expose
    private Double points;
    @SerializedName("vat")
    @Expose
    private Double vat;
    @SerializedName("total")
    @Expose
    private Double total;
    @SerializedName("points_commission")
    @Expose
    private Double pointsCommission;
    @SerializedName("promo_code")
    @Expose
    private String promoCode;
    @SerializedName("payment_method")
    @Expose
    private String paymentMethod;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("address")
    @Expose
    private OrderDetailsModelAddress address;
    @SerializedName("products")
    @Expose
    private List<OrderDetailsModelProduct> products = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPointsCommission() {
        return pointsCommission;
    }

    public void setPointsCommission(Double pointsCommission) {
        this.pointsCommission = pointsCommission;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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

    public OrderDetailsModelAddress getAddress() {
        return address;
    }

    public void setAddress(OrderDetailsModelAddress address) {
        this.address = address;
    }

    public List<OrderDetailsModelProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderDetailsModelProduct> products) {
        this.products = products;
    }

}
