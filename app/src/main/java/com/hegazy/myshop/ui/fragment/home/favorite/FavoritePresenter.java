package com.hegazy.myshop.ui.fragment.home.favorite;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.favoritesModel.FavoritesModel;
import com.hegazy.myshop.data.model.homeModel.HomeModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;
import static com.hegazy.myshop.helpar.HelperMethod.changeLang;

public class FavoritePresenter {

    FavoriteInterface favoriteInterface;

    public FavoritePresenter(FavoriteInterface favoriteInterface) {
        this.favoriteInterface = favoriteInterface;
    }


    public void favorite(String token){

        favoriteInterface.showProgress(R.string.please_wiht);

        getClient().getFavorites(token,changeLang()).enqueue(new Callback<FavoritesModel>() {
            @Override
            public void onResponse(Call<FavoritesModel> call, Response<FavoritesModel> response) {

                try {

                    if (response.body().getStatus() == true)
                    {
                        favoriteInterface.onSuccess(response.body().getMessage());
                        favoriteInterface.getFavorite(response.body().getData().getData());
                    }

                    else if (response.body().getStatus() == false)
                    {

                        favoriteInterface.onError(response.body().getMessage());

                    }
                }
                catch(Exception e)
                {
                }

            }

            @Override
            public void onFailure(Call<FavoritesModel> call, Throwable t) {

            }
        });

    }




}
