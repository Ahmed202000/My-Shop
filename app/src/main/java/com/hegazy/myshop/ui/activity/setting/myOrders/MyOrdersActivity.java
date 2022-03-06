package com.hegazy.myshop.ui.activity.setting.myOrders;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hegazy.myshop.R;
import com.hegazy.myshop.adapter.MyOrdersAdapter;
import com.hegazy.myshop.data.model.myOrderModel.MyOrderModelDatum;
import com.hegazy.myshop.helpar.EndlessRecyclerViewScrollListener;
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

public class MyOrdersActivity extends AppCompatActivity implements MyOrdersInterface{


    @BindView(R.id.activity_my_orders_iv_bake)
    ImageView activityMyOrdersIvBake;
    @BindView(R.id.activity_my_orders_rv_orders)
    RecyclerView activityMyOrdersRvOrders;


    private List<MyOrderModelDatum> myOrderModelDatumList = new ArrayList<>();
    MyOrdersAdapter myOrdersAdapter;
    LinearLayoutManager linearLayoutManager;
    private MyOrdersPresenter myOrdersPresenter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        ButterKnife.bind(this);

        myOrdersPresenter = new MyOrdersPresenter(this);
        inti();
    }

    public void inti() {

        myOrderModelDatumList.clear();

        String token = LoadData(this, USER_TOKEN);
        linearLayoutManager=new LinearLayoutManager(this);
        activityMyOrdersRvOrders.setLayoutManager(linearLayoutManager);

        EndlessRecyclerViewScrollListener onEndLess = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                myOrdersPresenter.MyOrders(token, HelperMethod.changeLang(),page);

            }
        };
        activityMyOrdersRvOrders.addOnScrollListener(onEndLess);
        myOrdersPresenter.MyOrders(token, HelperMethod.changeLang(),1);


    }



    @OnClick(R.id.activity_my_orders_iv_bake)
    public void onViewClicked() {
    }

    @Override
    public void getMyOrders(List<MyOrderModelDatum> ordersList) {

        myOrderModelDatumList.addAll(ordersList);
        myOrdersAdapter=new MyOrdersAdapter(this,this,myOrderModelDatumList);
        activityMyOrdersRvOrders.setAdapter(myOrdersAdapter);
        myOrdersAdapter.notifyDataSetChanged();
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

}