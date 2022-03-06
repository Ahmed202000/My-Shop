package com.hegazy.myshop.ui.fragment.order.validatePromoCode;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.promoCodeModel.PromoCodeModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;
import static com.hegazy.myshop.helpar.HelperMethod.changeLang;

public class ValidatePromoCodePresenter {

    ValidatePromoCodeInterface validatePromoCodeInterface;

    public ValidatePromoCodePresenter(ValidatePromoCodeInterface validatePromoCodeInterface) {
        this.validatePromoCodeInterface = validatePromoCodeInterface;
    }

    public void promoCode(String token ,String code)
    {
        validatePromoCodeInterface.showProgress(R.string.please_wiht);

        getClient().promoCode(token,changeLang(),code).enqueue(new Callback<PromoCodeModel>() {
            @Override
            public void onResponse(Call<PromoCodeModel> call, Response<PromoCodeModel> response) {

                try {

                    if (response.body().getStatus()==true) {

                        validatePromoCodeInterface.onSuccess(response.body().getMessage());
                        validatePromoCodeInterface.dataResponse(response.body().getData());
                    }
                    else
                    {
                        validatePromoCodeInterface.onError(response.body().getMessage());

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
