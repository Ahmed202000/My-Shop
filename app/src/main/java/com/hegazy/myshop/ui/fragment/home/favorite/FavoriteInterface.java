package com.hegazy.myshop.ui.fragment.home.favorite;

import com.hegazy.myshop.data.model.favoritesModel.FavoritesModelData;
import com.hegazy.myshop.data.model.favoritesModel.FavoritesModelDatum;
import com.hegazy.myshop.data.model.favoritesModel.FavoritesModelProduct;
import com.hegazy.myshop.data.model.homeModel.HomeModelBanner;
import com.hegazy.myshop.data.model.homeModel.HomeModelProduct;

import java.util.List;

public interface FavoriteInterface {


    void getFavorite(List<FavoritesModelDatum> favoritesList);


    void showProgress(int title);

    void onSuccess(String massge);

    void onError(String mass);

    void onFailure(String mass);
}
