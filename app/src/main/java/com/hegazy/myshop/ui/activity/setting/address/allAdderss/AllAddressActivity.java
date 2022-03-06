package com.hegazy.myshop.ui.activity.setting.address.allAdderss;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hegazy.myshop.R;
import com.hegazy.myshop.adapter.AllAddressAdapter;
import com.hegazy.myshop.data.model.getAddressModel.GetAddressModelDatum;
import com.hegazy.myshop.ui.activity.setting.address.addAdderss.AddAddressActivity;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hegazy.myshop.data.locale.Const.USER_TOKEN;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.LoadData;
import static com.hegazy.myshop.helpar.HelperMethod.changeLang;
import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;

public class AllAddressActivity extends AppCompatActivity implements AllAddressInterface {

    @BindView(R.id.activity_all_address_iv_bake)
    ImageView activityAllAddressIvBake;
    @BindView(R.id.activity_all_address_rv_address)
    RecyclerView activityAllAddressRvAddress;
    @BindView(R.id.activity_all_address_iv_add)
    ImageView activityAllAddressIvAdd;
    private ProgressDialog dialog;

    AllAddressPresenter allAddressPresenter;

    private List<GetAddressModelDatum> getAddressModelDatumList = new ArrayList<>();
    AllAddressAdapter allAddressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_address);
        ButterKnife.bind(this);
        allAddressPresenter = new AllAddressPresenter(this);
        inti();
    }

    private void inti() {
        String token = LoadData(this, USER_TOKEN);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        activityAllAddressRvAddress.setLayoutManager(linearLayoutManager);
        allAddressPresenter.getAddress(token,changeLang());
    }


    @Override
    public void GetAddress(List<GetAddressModelDatum> getAddressModelList) {

        getAddressModelDatumList.addAll(getAddressModelList);
        allAddressAdapter = new AllAddressAdapter(AllAddressActivity.this,this, getAddressModelDatumList);
        activityAllAddressRvAddress.setAdapter(allAddressAdapter);
        allAddressAdapter.notifyDataSetChanged();
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



    @OnClick({R.id.activity_all_address_iv_bake, R.id.activity_all_address_iv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_all_address_iv_bake:
                onBackPressed();
                finish();
                break;
            case R.id.activity_all_address_iv_add:
                startActivity(new Intent(AllAddressActivity.this, AddAddressActivity.class));
                break;
        }
    }
}