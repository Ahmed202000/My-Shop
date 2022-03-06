package com.hegazy.myshop.ui.fragment.order.dataShapeing;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.promoCodeModel.PromoCodeModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;
import static com.hegazy.myshop.helpar.HelperMethod.changeLang;

public class ConfirmOrderPresenter {

    ConfirmOrderInterface confirmOrderInterface;

    public ConfirmOrderPresenter(ConfirmOrderInterface confirmOrderInterface) {
        this.confirmOrderInterface = confirmOrderInterface;
    }

    public void addOrder(String token ,int  idAddress,int payment,boolean use_points,int promo_code_id)
    {
        confirmOrderInterface.showProgress(R.string.please_wiht);

        getClient().addOrder(token,changeLang(),idAddress,payment,use_points,promo_code_id).enqueue(new Callback<PromoCodeModel>() {
            @Override
            public void onResponse(Call<PromoCodeModel> call, Response<PromoCodeModel> response) {

                try {

                    if (response.body().getStatus()==true) {

                        confirmOrderInterface.onSuccessOrder(response.body().getMessage());
                    }
                    else
                    {
                        confirmOrderInterface.onSuccessOrder(response.body().getMessage());

                    }

                }
                catch (Exception e)
                {

                }
            }

            @Override
            public void onFailure(Call<PromoCodeModel> call, Throwable t) {

            }
        });
    }
}
