package com.hegazy.myshop.ui.activity.categoryProducts;

import com.hegazy.myshop.data.model.categoryProductsModel.CategoryProductsModelDatum;
import com.hegazy.myshop.data.model.searchProductModel.SearchProductModelDatum;

import java.util.List;

public interface CategoryProductsInterface {


    void getProductCategory(List<CategoryProductsModelDatum> productList);


    void showProgress(int title);

    void onSuccess(String massge);

    void onError(String mass);

    void onFailure(String mass);
}
