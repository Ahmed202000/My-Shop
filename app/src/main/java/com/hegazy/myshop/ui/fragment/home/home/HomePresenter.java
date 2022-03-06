package com.hegazy.myshop.ui.fragment.home.home;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.homeModel.HomeModel;
import com.hegazy.myshop.ui.fragment.auth.login.ChickStatusInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;
import static com.hegazy.myshop.helpar.HelperMethod.changeLang;

public class HomePresenter  {

    HomeInterface homeInterface;

    public HomePresenter(HomeInterface homeInterface) {
        this.homeInterface = homeInterface;
    }


    public void home(String token){

        homeInterface.showProgress(R.string.please_wiht);

        getClient().getHome(token,changeLang()).enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {

                try {

                    if (response.body().getStatus() == true)
                    {
                        homeInterface.onSuccess(response.body().getMessage());
                        homeInterface.getProduct(response.body().getData().getProducts());
                        homeInterface.getBanner(response.body().getData().getBanners());
                    }

                    else if (response.body().getStatus() == false)
                    {

                        homeInterface.onError(response.body().getMessage());

                    }
                }
                catch(Exception e)
                {
                }

            }

            @Override
            public void onFailure(Call<HomeModel> call, Throwable t) {

            }
        });

    }




}
