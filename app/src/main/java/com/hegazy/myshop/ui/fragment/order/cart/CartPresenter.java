package com.hegazy.myshop.ui.fragment.order.cart;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.getCartModel.GetCartModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;
import static com.hegazy.myshop.helpar.HelperMethod.changeLang;

public class CartPresenter {

    CartInterface cartInterface;

    public CartPresenter(CartInterface cartInterface) {
        this.cartInterface = cartInterface;


    }


    public void getCart(String token){

        cartInterface.showProgress(R.string.please_wiht);

        getClient().getCart(token,changeLang()).enqueue(new Callback<GetCartModel>() {
            @Override
            public void onResponse(Call<GetCartModel> call, Response<GetCartModel> response) {

                try {

                    if (response.body().getStatus() == true)
                    {
                        cartInterface.onSuccess(response.body().getMessage());
                        cartInterface.getCart(response.body().getData().getCartItems());
                        cartInterface.getTotal(response.body().getData().getTotal() +""+"جنية");
                    }

                    else if (response.body().getStatus() == false)
                    {

                        cartInterface.onError(response.body().getMessage());

                    }
                }
                catch(Exception e)
                {
                }

            }

            @Override
            public void onFailure(Call<GetCartModel> call, Throwable t) {
                cartInterface.onFailure(t.getMessage());
            }
        });

    }




}
