package com.hegazy.myshop.ui.activity.setting.address.addAdderss;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.hegazy.myshop.R;
import com.hegazy.myshop.ui.activity.setting.address.allAdderss.AllAddressActivity;
import com.valdesekamdem.library.mdtoast.MDToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hegazy.myshop.data.locale.Const.USER_TOKEN;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.LoadData;
import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;

public class AddAddressActivity extends AppCompatActivity implements AddAddressInterface {



    @BindView(R.id.activity_add_address_iv_bake)
    ImageView activityAddAddressIvBake;
    @BindView(R.id.activity_add_address_tv_name_address)
    EditText activityAddAddressTvNameAddress;
    @BindView(R.id.activity_add_address_tv_city)
    EditText activityAddAddressTvCity;
    @BindView(R.id.activity_add_complaint_tv_region)
    EditText activityAddComplaintTvRegion;
    @BindView(R.id.activity_add_complaint_tv_details_address)
    EditText activityAddComplaintTvDetailsAddress;
    @BindView(R.id.activity_add_complaint_tv_note_address)
    EditText activityAddComplaintTvNoteAddress;
    @BindView(R.id.activity_add_complaint_btn_send)
    Button activityAddComplaintBtnSend;



    AddAddressPresenter addAddressPresenter;

    String name;
    String city;
    String region;
    String details;
    double latitude;
    double longitude;
    String notes;

    private ProgressDialog dialog;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);
        addAddressPresenter = new AddAddressPresenter(this);


    }


    @Override
    public void showProgress(int title) {
        dialog = showProgressDialog(this,getString(title));
        dialog.show();
    }

    @Override
    public void onSuccess(String massge) {
        // hide dialog
        dialog.cancel();
        MDToast.makeText(this, massge, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
        startActivity(new Intent(AddAddressActivity.this, AllAddressActivity.class));

    }

    @Override
    public void onError(String mass) {
        // hide dialog
        dialog.cancel();
        MDToast.makeText(this, mass, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();

    }

    @Override
    public void onFailure(String mass) {
        MDToast.makeText(this, mass, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();
        dialog.cancel();
    }

    @OnClick({R.id.activity_add_address_iv_bake, R.id.activity_add_complaint_btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_add_address_iv_bake:
                onBackPressed();
                break;
            case R.id.activity_add_complaint_btn_send:

                token = LoadData(this, USER_TOKEN);
                name = activityAddAddressTvNameAddress.getText().toString();
                city = activityAddAddressTvCity.getText().toString();
                region = activityAddComplaintTvRegion.getText().toString();
                details = activityAddComplaintTvDetailsAddress.getText().toString();
                notes = activityAddComplaintTvNoteAddress.getText().toString();

                if (name.isEmpty()) {
                    activityAddAddressTvNameAddress.setError("Please Enter Name");
                } else if (city.isEmpty()) {
                    activityAddAddressTvCity.setError("Please Enter Phone");
                } else if (region.isEmpty()) {
                    activityAddComplaintTvRegion.setError("Please Enter Email");
                } else if (details.isEmpty()) {
                    activityAddComplaintTvDetailsAddress.setError("Please Enter Message");
                } else if (notes.isEmpty()) {
                    activityAddComplaintTvNoteAddress.setError("Please Enter Message");
                } else {
                    addAddressPresenter.AddAddress(token,name,city,region,details,0.0,0.0,notes);

                }

                break;
        }

    }
}