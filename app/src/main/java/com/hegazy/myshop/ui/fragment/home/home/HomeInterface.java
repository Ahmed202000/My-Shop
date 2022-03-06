package com.hegazy.myshop.ui.fragment.home.home;

import com.hegazy.myshop.data.model.categoriesModel.CategoriesModelDatum;
import com.hegazy.myshop.data.model.homeModel.HomeModelBanner;
import com.hegazy.myshop.data.model.homeModel.HomeModelProduct;

import java.util.List;

public interface HomeInterface {


    void getProduct(List<HomeModelProduct> productList);
    void getBanner(List<HomeModelBanner> bannerList);


    void showProgress(int title);

    void onSuccess(String massge);

    void onError(String mass);

    void onFailure(String mass);
}
