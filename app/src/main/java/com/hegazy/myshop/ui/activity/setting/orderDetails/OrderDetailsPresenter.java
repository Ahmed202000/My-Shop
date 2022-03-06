package com.hegazy.myshop.ui.activity.setting.orderDetails;

import com.hegazy.myshop.data.model.myOrderModel.MyOrderModel;
import com.hegazy.myshop.data.model.orderDetailsModel.OrderDetailsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;

public class OrderDetailsPresenter {

    OrderDetailsInterface orderDetailsInterface;


    public OrderDetailsPresenter(OrderDetailsInterface orderDetailsInterface) {
        this.orderDetailsInterface = orderDetailsInterface;
    }

    public void getOrderDetails(String token,String lang,int id)
    {

        orderDetailsInterface.showProgress("Pleas Whit");

        getClient().orderDetails(token,lang,id).enqueue(new Callback<OrderDetailsModel>() {
            @Override
            public void onResponse(Call<OrderDetailsModel> call, Response<OrderDetailsModel> response) {

                try {

                    if (response.body().getStatus()==true) {

                        orderDetailsInterface.onSuccess(response.body().getMessage());
                        orderDetailsInterface.Product(response.body().getData().getProducts());
                        orderDetailsInterface.responseData(response.body().getData());
                    }
                    else
                    {
                        orderDetailsInterface.onError(response.body().getMessage());

                    }

                }
                catch (Exception e)
                {

                }
            }

            @Override
            public void onFailure(Call<OrderDetailsModel> call, Throwable t) {
                orderDetailsInterface.onError(t.getMessage());

            }
        });
    }
}
