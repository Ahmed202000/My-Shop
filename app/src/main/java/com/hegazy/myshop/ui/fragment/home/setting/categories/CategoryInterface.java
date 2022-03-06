package com.hegazy.myshop.ui.fragment.home.setting.categories;

import com.hegazy.myshop.data.model.categoriesModel.CategoriesModelDatum;
import com.hegazy.myshop.data.model.homeModel.HomeModelBanner;
import com.hegazy.myshop.data.model.homeModel.HomeModelProduct;

import java.util.List;

public interface CategoryInterface {


    void getCategories(List<CategoriesModelDatum> categoriesModelDataList);


    void showProgress(int title);

    void onSuccess(String massge);

    void onError(String mass);

    void onFailure(String mass);
}
