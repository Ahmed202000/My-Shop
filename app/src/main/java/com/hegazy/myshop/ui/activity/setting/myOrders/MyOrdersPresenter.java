package com.hegazy.myshop.ui.activity.setting.myOrders;

import com.hegazy.myshop.data.model.getAddressModel.GetAddressModel;
import com.hegazy.myshop.data.model.myOrderModel.MyOrderModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;

public class MyOrdersPresenter {

    MyOrdersInterface myOrdersInterface;


    public MyOrdersPresenter(MyOrdersInterface myOrdersInterface) {
        this.myOrdersInterface = myOrdersInterface;
    }

    public void MyOrders(String token,String lang,int page)
    {

        myOrdersInterface.showProgress("Pleas Whit");

        getClient().myOrderModel(token,lang,page).enqueue(new Callback<MyOrderModel>() {
            @Override
            public void onResponse(Call<MyOrderModel> call, Response<MyOrderModel> response) {

                try {

                    if (response.body().getStatus()==true) {

                        myOrdersInterface.onSuccess(response.body().getMessage());
                        myOrdersInterface.getMyOrders(response.body().getData().getData());
                    }
                    else
                    {
                        myOrdersInterface.onError(response.body().getMessage());

                    }

                }
                catch (Exception e)
                {

                }
            }

            @Override
            public void onFailure(Call<MyOrderModel> call, Throwable t) {

            }
        });
    }
}
