package com.hegazy.myshop.ui.fragment.order.dataShapeing;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.hegazy.myshop.R;
import com.hegazy.myshop.adapter.SpinnerAddressAdapter;
import com.hegazy.myshop.data.model.getAddressModel.GetAddressModel;
import com.hegazy.myshop.data.model.getAddressModel.GetAddressModelDatum;
import com.hegazy.myshop.data.model.promoCodeModel.PromoCodeModelData;
import com.hegazy.myshop.ui.fragment.BaseFragment;
import com.hegazy.myshop.ui.fragment.order.CongratulitionFragment;
import com.hegazy.myshop.ui.fragment.order.cart.CartFragment;
import com.hegazy.myshop.ui.fragment.order.validatePromoCode.ValidatePromoCodeInterface;
import com.hegazy.myshop.ui.fragment.order.validatePromoCode.ValidatePromoCodePresenter;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;
import static com.hegazy.myshop.data.locale.Const.USER_TOKEN;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.LoadData;
import static com.hegazy.myshop.helpar.HelperMethod.changeLang;
import static com.hegazy.myshop.helpar.HelperMethod.replaceFragment;
import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmOrderFragment extends BaseFragment implements ValidatePromoCodeInterface , ConfirmOrderInterface {


    public String total;
    @BindView(R.id.activity_continue_to_pay_iv_bake)
    ImageView activityContinueToPayIvBake;
    @BindView(R.id.fragment_confirm_order_sp_city)
    Spinner fragmentConfirmOrderSpCity;
    @BindView(R.id.fragment_confirm_order_tv_code)
    EditText fragmentConfirmOrderTvCode;
    @BindView(R.id.fragment_confirm_order_btn_send)
    Button fragmentConfirmOrderBtnSend;
    @BindView(R.id.fragment_confirm_order_tv_discount)
    TextView fragmentConfirmOrderTvDiscount;
    @BindView(R.id.fragment_confirm_order_lin_discount)
    LinearLayout fragmentConfirmOrderLinDiscount;
    @BindView(R.id.fragment_confirm_order_tv_total)
    TextView fragmentConfirmOrderTvTotal;
    @BindView(R.id.fragment_confirm_order_rb_cash_money)
    RadioButton fragmentConfirmOrderRbCashMoney;
    @BindView(R.id.fragment_confirm_order_btn_confirm)
    Button fragmentConfirmOrderBtnConfirm;
    @BindView(R.id.fragment_confirm_order_tv_total_final)
    TextView fragmentConfirmOrderTvTotalFinal;


    private List<GetAddressModelDatum> getAddressModelData = new ArrayList<>();
    SpinnerAddressAdapter spinnerAddressAdapter;
    private ProgressDialog dialog;


    private String token;
    private Integer idPromoCode =0;
    private Integer value;
    private int totalFinal;


    private ValidatePromoCodePresenter validatePromoCodePresenter;
    ConfirmOrderPresenter confirmOrderPresenter;
    private int pay;

    public ConfirmOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm_order, container, false);

        ButterKnife.bind(this, view);
        getAddress();

        fragmentConfirmOrderTvTotal.setText(total);
        validatePromoCodePresenter = new ValidatePromoCodePresenter(this);
        confirmOrderPresenter=new ConfirmOrderPresenter(this);


        return view;


    }


    //TODO GET OrderDetailsModelAddress
    private void getAddress() {

        token = LoadData(getActivity(), USER_TOKEN);

        dialog = showProgressDialog(getActivity(), "إنتظار");
        dialog.show();

        getClient().getAddress(token,changeLang()).enqueue(new Callback<GetAddressModel>() {
            @Override
            public void onResponse(Call<GetAddressModel> call, Response<GetAddressModel> response) {

                getAddressModelData.clear();
                getAddressModelData.add(new GetAddressModelDatum(getString(R.string.select_address)));


                dialog.dismiss();

                if (response.body().getStatus() == true) {

                    getAddressModelData.addAll(response.body().getData().getData());
                    spinnerAddressAdapter = new SpinnerAddressAdapter(getActivity(), getAddressModelData);
                    fragmentConfirmOrderSpCity.setAdapter(spinnerAddressAdapter);
                    spinnerAddressAdapter.notifyDataSetChanged();


                } else {

                    Toast.makeText(baseActivity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<GetAddressModel> call, Throwable t) {

            }
        });

    }


    @OnClick({R.id.activity_continue_to_pay_iv_bake, R.id.fragment_confirm_order_btn_send, R.id.fragment_confirm_order_rb_cash_money, R.id.fragment_confirm_order_btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_continue_to_pay_iv_bake:
                replaceFragment(getActivity().getSupportFragmentManager(), R.id.activity_order_fr_order, new CartFragment());

                break;
            case R.id.fragment_confirm_order_btn_send:

                String code = fragmentConfirmOrderTvCode.getText().toString();
                if (code.isEmpty()) {
                    fragmentConfirmOrderTvCode.setError(getActivity().getString(R.string.discount_code));
                } else {
                    validatePromoCodePresenter.promoCode(token, code);
                }
                break;
            case R.id.fragment_confirm_order_rb_cash_money:
                pay=1;
                break;
            case R.id.fragment_confirm_order_btn_confirm:

                if (fragmentConfirmOrderSpCity.getSelectedItemPosition()==0)
                {
                    Toast.makeText(baseActivity, getActivity().getString(R.string.select_address), Toast.LENGTH_SHORT).show();
                }
                else if (fragmentConfirmOrderRbCashMoney.isChecked()==false)
                {
                    Toast.makeText(baseActivity, getActivity().getString(R.string.payment_method), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int idAddress=getAddressModelData.get(fragmentConfirmOrderSpCity.getSelectedItemPosition()).getId();

                    confirmOrderPresenter.addOrder(token,idAddress,pay,false,idPromoCode);

                }


                break;
        }
    }

    @Override
    public void onSuccess(String massge) {
        // hide dialog
        dialog.cancel();
        MDToast.makeText(getActivity(), massge, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
        fragmentConfirmOrderLinDiscount.setVisibility(View.VISIBLE);


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


    @Override
    public void dataResponse(PromoCodeModelData data) {
        idPromoCode = data.getId();
        value = data.getValue();
        fragmentConfirmOrderTvDiscount.setText(value + "");
        totalFinal = Integer.parseInt(total) - value;
        fragmentConfirmOrderTvTotalFinal.setText(totalFinal + "");


    }



    @Override
    public void onSuccessOrder(String message) {

        // hide dialog
        dialog.cancel();
        MDToast.makeText(getActivity(), message, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
        replaceFragment(getActivity().getSupportFragmentManager(),R.id.activity_order_fr_order,new CongratulitionFragment());

    }

    @Override
    public void onErrorOrder(String mass) {
        // hide dialog
        dialog.cancel();
        // error
        MDToast.makeText(getActivity(), mass, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();
    }

    @Override
    public void onFailureOrder(String mass) {
        MDToast.makeText(getActivity(), mass, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();
        dialog.cancel();
    }


    @Override
    public void onbacks() {
    }
}
