package com.hegazy.myshop.ui.fragment.home.home;

import com.hegazy.myshop.data.model.categoriesModel.CategoriesModel;
import com.hegazy.myshop.ui.fragment.home.setting.categories.CategoryInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;
import static com.hegazy.myshop.helpar.HelperMethod.changeLang;

public class CategoriesHomePresenter {

    CategoryInterface categoryInterface;

    public CategoriesHomePresenter(CategoryInterface categoryInterface) {
        this.categoryInterface = categoryInterface;
    }


    public void Category(){

     //   categoryInterface.showProgress("Pleas Whit");

        getClient().getCategories(changeLang()).enqueue(new Callback<CategoriesModel>() {
            @Override
            public void onResponse(Call<CategoriesModel> call, Response<CategoriesModel> response) {

                try {

                    if (response.body().getStatus() == true)
                    {
                        categoryInterface.onSuccess(response.body().getMessage());
                        categoryInterface.getCategories(response.body().getData().getData());
                    }

                    else if (response.body().getStatus() == false)
                    {

                        categoryInterface.onError(response.body().getMessage());

                    }
                }
                catch(Exception e)
                {
                }

            }

            @Override
            public void onFailure(Call<CategoriesModel> call, Throwable t) {
                categoryInterface.onFailure(t.getMessage());

            }
        });

    }



}
