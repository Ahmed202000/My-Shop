package com.hegazy.myshop.ui.activity.setting.orderDetails;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.hegazy.myshop.R;
import com.hegazy.myshop.adapter.OrderDetailsProductAdapter;
import com.hegazy.myshop.data.model.orderDetailsModel.OrderDetailsModelData;
import com.hegazy.myshop.data.model.orderDetailsModel.OrderDetailsModelProduct;
import com.hegazy.myshop.helpar.HelperMethod;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hegazy.myshop.data.locale.Const.USER_TOKEN;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.LoadData;
import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;

public class OrderDetailsActivity extends AppCompatActivity implements OrderDetailsInterface{

    @BindView(R.id.activity_my_orders_iv_bake)
    ImageView activityMyOrdersIvBake;
    @BindView(R.id.activity_order_details_tv_order_id)
    TextView activityOrderDetailsTvOrderId;
    @BindView(R.id.activity_order_details_tv_total)
    TextView activityOrderDetailsTvTotal;
    @BindView(R.id.activity_order_details_tv_discount)
    TextView activityOrderDetailsTvDiscount;
    @BindView(R.id.activity_order_details_tv_vat)
    TextView activityOrderDetailsTvVat;
    @BindView(R.id.activity_order_details_tv_final_total)
    TextView activityOrderDetailsTvFinalTotal;
    @BindView(R.id.activity_order_details_tv_order_date)
    TextView activityOrderDetailsTvOrderDate;
    @BindView(R.id.activity_order_details_tv_pay)
    TextView activityOrderDetailsTvPay;
    @BindView(R.id.activity_order_details_tv_product)
    RecyclerView activityOrderDetailsTvProduct;

    private OrderDetailsPresenter orderDetailsPresenter;

    private List<OrderDetailsModelProduct> orderDetailsModelProductList = new ArrayList<>();
    OrderDetailsProductAdapter orderDetailsProductAdapter;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        ButterKnife.bind(this);

        int id=getIntent().getExtras().getInt("idOrder");
        String token = LoadData(this, USER_TOKEN);
        orderDetailsPresenter = new OrderDetailsPresenter(this);
        orderDetailsPresenter.getOrderDetails(token, HelperMethod.changeLang(),id);
    }

    @Override
    public void Product(List<OrderDetailsModelProduct> getOrderProduct) {
        orderDetailsModelProductList.addAll(getOrderProduct);
        orderDetailsProductAdapter=new OrderDetailsProductAdapter(this,this,orderDetailsModelProductList);
        activityOrderDetailsTvProduct.setAdapter(orderDetailsProductAdapter);
        orderDetailsProductAdapter.notifyDataSetChanged();

        dialog.dismiss();

    }

    @Override
    public void showProgress(String title) {
        dialog = showProgressDialog(this, title);
        dialog.show();
    }


    @Override
    public void onSuccess(String massge) {
        MDToast.makeText(this, massge, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
        dialog.dismiss();
    }

    @Override
    public void onError(String mass) {
        dialog.dismiss();
        MDToast.makeText(this, mass, MDToast.LENGTH_SHORT, MDToast.TYPE_INFO).show();

    }

    @Override
    public void onFailure(String mass) {
        MDToast.makeText(this, mass, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();

        dialog.dismiss();

    }

    @Override
    public void responseData(OrderDetailsModelData data) {

        activityOrderDetailsTvOrderId.setText(data.getId()+"");
        activityOrderDetailsTvTotal.setText(String.valueOf(data.getCost()));
        activityOrderDetailsTvDiscount.setText(data.getDiscount()+"");
        activityOrderDetailsTvVat.setText(String.valueOf(data.getVat()));
        activityOrderDetailsTvFinalTotal.setText(data.getTotal()+"");
        activityOrderDetailsTvPay.setText(data.getPaymentMethod());
        activityOrderDetailsTvOrderDate.setText(data.getDate());

        dialog.dismiss();


    }



    @OnClick(R.id.activity_my_orders_iv_bake)
    public void onViewClicked() {
        onBackPressed();
        finish();
    }


}