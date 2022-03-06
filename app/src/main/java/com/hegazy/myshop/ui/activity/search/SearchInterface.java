package com.hegazy.myshop.ui.activity.search;

import com.hegazy.myshop.data.model.homeModel.HomeModelBanner;
import com.hegazy.myshop.data.model.homeModel.HomeModelProduct;
import com.hegazy.myshop.data.model.searchProductModel.SearchProductModelData;
import com.hegazy.myshop.data.model.searchProductModel.SearchProductModelDatum;

import java.util.List;

public interface SearchInterface {


    void getProductSearch(List<SearchProductModelDatum> productList);


    void showProgress(int title);

    void onSuccess(String massge);

    void onError(String mass);

    void onFailure(String mass);
}
