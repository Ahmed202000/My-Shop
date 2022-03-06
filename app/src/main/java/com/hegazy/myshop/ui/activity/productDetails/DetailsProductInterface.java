package com.hegazy.myshop.ui.activity.productDetails;

import com.hegazy.myshop.data.model.deleteFavoritesModel.DeleteFavoritesModelData;
import com.hegazy.myshop.data.model.productDetailsModel.ProductDetailsModelData;
import com.hegazy.myshop.data.model.searchProductModel.SearchProductModelDatum;

import java.util.List;

public interface DetailsProductInterface {


    void getData(ProductDetailsModelData data);


    void showProgress(int title);

    void onSuccess(String massge);

    void onError(String mass);

    void onFailure(String mass);
}
