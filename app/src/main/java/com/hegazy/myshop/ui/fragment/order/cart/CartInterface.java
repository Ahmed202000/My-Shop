package com.hegazy.myshop.ui.fragment.order.cart;

import com.hegazy.myshop.data.model.categoryProductsModel.CategoryProductsModelDatum;
import com.hegazy.myshop.data.model.getCartModel.GetCartModelCartItem;

import java.util.List;

public interface CartInterface {


    void getCart(List<GetCartModelCartItem> cartList);
    void getTotal(String totalCart);


    void showProgress(int title);

    void onSuccess(String massge);

    void onError(String mass);

    void onFailure(String mass);
}
