
package com.hegazy.myshop.data.model.homeModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeModelData {

    @SerializedName("banners")
    @Expose
    private List<HomeModelBanner> banners = null;
    @SerializedName("products")
    @Expose
    private List<HomeModelProduct> products = null;
    @SerializedName("ad")
    @Expose
    private String ad;

    public List<HomeModelBanner> getBanners() {
        return banners;
    }

    public void setBanners(List<HomeModelBanner> banners) {
        this.banners = banners;
    }

    public List<HomeModelProduct> getProducts() {
        return products;
    }

    public void setProducts(List<HomeModelProduct> products) {
        this.products = products;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

}
