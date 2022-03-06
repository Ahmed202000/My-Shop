package com.hegazy.myshop.ui.activity.setting.address.updateAddress;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.hegazy.myshop.R;
import com.hegazy.myshop.ui.activity.setting.address.addAdderss.AddAddressActivity;
import com.hegazy.myshop.ui.activity.setting.address.allAdderss.AllAddressActivity;
import com.valdesekamdem.library.mdtoast.MDToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hegazy.myshop.data.locale.Const.USER_TOKEN;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.LoadData;
import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;

public class UpdateAddressActivity extends AppCompatActivity implements UpdateAddressInterface{

    @BindView(R.id.activity_update_address_iv_bake)
    ImageView activityUpdateAddressIvBake;
    @BindView(R.id.activity_update_address_tv_name_address)
    EditText activityUpdateAddressTvNameAddress;
    @BindView(R.id.activity_update_address_tv_city)
    EditText activityUpdateAddressTvCity;
    @BindView(R.id.activity_add_complaint_tv_region)
    EditText activityAddComplaintTvRegion;
    @BindView(R.id.activity_add_complaint_tv_details_address)
    EditText activityAddComplaintTvDetailsAddress;
    @BindView(R.id.activity_add_complaint_tv_note_address)
    EditText activityAddComplaintTvNoteAddress;
    @BindView(R.id.activity_add_complaint_btn_send)
    Button activityAddComplaintBtnSend;
    private int idAddress;
    private ProgressDialog dialog;

    UpdateAddressPresenter updateAddressPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_address);
        ButterKnife.bind(this);

        updateAddressPresenter=new UpdateAddressPresenter(this);

        idAddress =getIntent().getExtras().getInt("id");
        activityUpdateAddressTvNameAddress.setText(getIntent().getExtras().getString("name"));
        activityUpdateAddressTvCity.setText(getIntent().getExtras().getString("city"));
        activityAddComplaintTvRegion.setText(getIntent().getExtras().getString("region"));
        activityAddComplaintTvDetailsAddress.setText(getIntent().getExtras().getString("details"));
        activityAddComplaintTvNoteAddress.setText(getIntent().getExtras().getString("notes"));
    }

    @OnClick({R.id.activity_update_address_iv_bake, R.id.activity_add_complaint_btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_update_address_iv_bake:
                break;
            case R.id.activity_add_complaint_btn_send:

                String token = LoadData(this, USER_TOKEN);
                String name = activityUpdateAddressTvNameAddress.getText().toString();
                String city = activityUpdateAddressTvCity.getText().toString();
                String region = activityAddComplaintTvRegion.getText().toString();
                String details = activityAddComplaintTvDetailsAddress.getText().toString();
                String  notes = activityAddComplaintTvNoteAddress.getText().toString();
                updateAddressPresenter.UpdateAddress(token,idAddress,name,city,region,details,0.0,0.0,notes);
                break;
        }
    }

    @Override
    public void showProgress(int title) {
        dialog = showProgressDialog(this, getString(title));
        dialog.show();
    }


    @Override
    public void onSuccess(String massge) {
        // hide dialog
        dialog.cancel();
        MDToast.makeText(this, massge, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
        startActivity(new Intent(UpdateAddressActivity.this, AllAddressActivity.class));

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
}