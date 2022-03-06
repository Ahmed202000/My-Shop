package com.hegazy.myshop.ui.activity.productDetails;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.productDetailsModel.ProductDetailsModel;
import com.hegazy.myshop.data.model.searchProductModel.SearchProductModel;
import com.hegazy.myshop.helpar.HelperMethod;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;

public class DetailsProductPresenter {

    DetailsProductInterface detailsProductInterface;

    public DetailsProductPresenter(DetailsProductInterface detailsProductInterface) {
        this.detailsProductInterface = detailsProductInterface;


    }


    public void DetailsProduct(String token,int id){

        detailsProductInterface.showProgress(R.string.please_wiht);

        getClient().getProductDetails(token, HelperMethod.changeLang(),id).enqueue(new Callback<ProductDetailsModel>() {
            @Override
            public void onResponse(Call<ProductDetailsModel> call, Response<ProductDetailsModel> response) {

                try {

                    if (response.body().getStatus() == true)
                    {
                        detailsProductInterface.onSuccess(response.body().getMessage());
                        detailsProductInterface.getData(response.body().getData());
                    }

                    else if (response.body().getStatus() == false)
                    {

                        detailsProductInterface.onError(response.body().getMessage());

                    }
                }
                catch(Exception e)
                {
                }

            }

            @Override
            public void onFailure(Call<ProductDetailsModel> call, Throwable t) {

                detailsProductInterface.onFailure(t.getMessage());

            }
        });

    }




}
