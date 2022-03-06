package com.hegazy.myshop.ui.activity.categoryProducts;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.categoryProductsModel.CategoryProductsModel;
import com.hegazy.myshop.data.model.searchProductModel.SearchProductModel;
import com.hegazy.myshop.helpar.HelperMethod;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;
import static com.hegazy.myshop.helpar.HelperMethod.changeLang;

public class CategoryProductsPresenter {

    CategoryProductsInterface categoryProductsInterface;

    public CategoryProductsPresenter(CategoryProductsInterface categoryProductsInterface) {
        this.categoryProductsInterface = categoryProductsInterface;


    }


    public void categoryProducts(String token,int id){

        categoryProductsInterface.showProgress(R.string.please_wiht);

        getClient().getCategoryProducts(token, changeLang(),id).enqueue(new Callback<CategoryProductsModel>() {
            @Override
            public void onResponse(Call<CategoryProductsModel> call, Response<CategoryProductsModel> response) {

                try {

                    if (response.body().getStatus() == true)
                    {
                        categoryProductsInterface.onSuccess(response.body().getMessage());
                        categoryProductsInterface.getProductCategory(response.body().getData().getData());
                    }

                    else if (response.body().getStatus() == false)
                    {

                        categoryProductsInterface.onError(response.body().getMessage());

                    }
                }
                catch(Exception e)
                {
                }

            }

            @Override
            public void onFailure(Call<CategoryProductsModel> call, Throwable t) {

            }
        });

    }




}
