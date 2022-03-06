package com.hegazy.myshop.ui.fragment.order.cart;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hegazy.myshop.R;
import com.hegazy.myshop.adapter.CartAdapter;
import com.hegazy.myshop.data.model.getCartModel.GetCartModelCartItem;
import com.hegazy.myshop.ui.activity.HomeActivity;
import com.hegazy.myshop.ui.activity.OrderActivity;
import com.hegazy.myshop.ui.fragment.BaseFragment;
import com.hegazy.myshop.ui.fragment.order.dataShapeing.ConfirmOrderFragment;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hegazy.myshop.data.locale.Const.USER_TOKEN;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.LoadData;
import static com.hegazy.myshop.helpar.HelperMethod.replaceFragment;
import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends BaseFragment implements CartInterface {


    @BindView(R.id.fragment_cart_iv_bake)
    ImageView fragmentCartIvBake;
    @BindView(R.id.fragment_cart_iv_delete)
    ImageView fragmentCartIvDelete;
    @BindView(R.id.fragment_home_rv_product)
    RecyclerView fragmentHomeRvProduct;
    @BindView(R.id.fragment_cart_tv_total_cart)
    TextView fragmentCartTvTotalCart;
    @BindView(R.id.lin)
    LinearLayout lin;
    @BindView(R.id.fragment_cart_btn_go_shopping)
    Button fragmentCartBtnGoShopping;
    @BindView(R.id.fragment_cart_lin_no_data)
    LinearLayout fragmentCartLinNoData;


    private List<GetCartModelCartItem> getCartModelCartItems = new ArrayList<>();
    CartAdapter cartAdapter;
    LinearLayoutManager linearLayoutManager;

    private ProgressDialog dialog;

    CartPresenter cartPresenter;
    private String token;
    private OrderActivity activity;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        ButterKnife.bind(this, view);

        cartPresenter = new CartPresenter(this);

        inti();
        return view;


    }

    public void inti() {
        activity = (OrderActivity) getActivity();
        token = LoadData(getActivity(), USER_TOKEN);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        fragmentHomeRvProduct.setLayoutManager(linearLayoutManager);
        cartPresenter.getCart(token);
    }


    @Override
    public void getCart(List<GetCartModelCartItem> cartList) {

        getCartModelCartItems.clear();
        getCartModelCartItems.addAll(cartList);
        cartAdapter = new CartAdapter(getActivity(), activity, getCartModelCartItems, CartFragment.this);
        fragmentHomeRvProduct.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        if (getCartModelCartItems.size() == 0) {
            fragmentCartLinNoData.setVisibility(View.VISIBLE);
            lin.setVisibility(View.GONE);

        } else {
            lin.setVisibility(View.VISIBLE);
            fragmentCartLinNoData.setVisibility(View.GONE);

        }

    }

    @Override
    public void getTotal(String totalCart) {
        fragmentCartTvTotalCart.setText(totalCart);


    }


    @Override
    public void onSuccess(String massge) {
        // hide dialog
        dialog.cancel();
        MDToast.makeText(getActivity(), massge, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();

    }

    @Override
    public void onError(String mass) {
        // hide dialog
        dialog.cancel();
        // error
        MDToast.makeText(getActivity(), mass, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();

    }

    @Override
    public void onFailure(String mass) {
        MDToast.makeText(getActivity(), mass, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();
        dialog.cancel();
    }

    @Override
    public void showProgress(int title) {
        dialog = showProgressDialog(getActivity(), getString(title));
        dialog.show();
    }


    @OnClick({R.id.fragment_cart_btn_go_shopping, R.id.fragment_cart_iv_bake, R.id.fragment_cart_iv_delete, R.id.fragment_cart_btn_confirm, R.id.fragment_cart_btn_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_cart_iv_bake:
                startActivity(new Intent(getActivity(), HomeActivity.class));
                break;
            case R.id.fragment_cart_iv_delete:
                break;
            case R.id.fragment_cart_btn_confirm:
                ConfirmOrderFragment confirmOrderFragment = new ConfirmOrderFragment();
                confirmOrderFragment.total = fragmentCartTvTotalCart.getText().toString();
                replaceFragment(getActivity().getSupportFragmentManager(), R.id.activity_order_fr_order, confirmOrderFragment);

                break;
            case R.id.fragment_cart_btn_more:
                startActivity(new Intent(getActivity(), HomeActivity.class));
                break;

            case R.id.fragment_cart_btn_go_shopping:
                startActivity(new Intent(getActivity(), HomeActivity.class));
                break;

        }
    }

    @Override
    public void onBack() {
        startActivity(new Intent(getActivity(), HomeActivity.class));

    }
}
