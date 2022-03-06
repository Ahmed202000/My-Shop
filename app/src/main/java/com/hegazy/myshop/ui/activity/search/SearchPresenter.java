package com.hegazy.myshop.ui.activity.search;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.homeModel.HomeModel;
import com.hegazy.myshop.data.model.searchProductModel.SearchProductModel;
import com.hegazy.myshop.helpar.HelperMethod;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;
import static com.hegazy.myshop.data.locale.Const.USER_TOKEN;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.LoadData;

public class SearchPresenter {

    SearchInterface searchInterface;

    public SearchPresenter(SearchInterface searchInterface) {
        this.searchInterface = searchInterface;


    }


    public void search(String text,String token){

        searchInterface.showProgress(R.string.please_wiht);

        getClient().getProductSearch(token, HelperMethod.changeLang(),text).enqueue(new Callback<SearchProductModel>() {
            @Override
            public void onResponse(Call<SearchProductModel> call, Response<SearchProductModel> response) {

                try {

                    if (response.body().getStatus() == true)
                    {
                        searchInterface.onSuccess(response.body().getMessage());
                        searchInterface.getProductSearch(response.body().getData().getData());
                    }

                    else if (response.body().getStatus() == false)
                    {

                        searchInterface.onError(response.body().getMessage());

                    }
                }
                catch(Exception e)
                {
                }

            }

            @Override
            public void onFailure(Call<SearchProductModel> call, Throwable t) {

            }
        });

    }




}
