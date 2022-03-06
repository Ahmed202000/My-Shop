package com.hegazy.myshop.ui.fragment.home.setting.addComplaint;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.load.data.AssetPathFetcher;
import com.hegazy.myshop.R;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.Gravity.LEFT;
import static android.view.Gravity.RIGHT;
import static com.hegazy.myshop.data.locale.Const.USER_DATA;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.loadUserData;
import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;

public class AddComplaintActivity extends AppCompatActivity implements AddComplaintInterface {


    AddComplaintPresenter addComplaintPresenter;
    @BindView(R.id.activity_add_complaint_iv_bake)
    ImageView activityAddComplaintIvBake;
    @BindView(R.id.activity_add_complaint_tv_full_name)
    EditText activityAddComplaintTvFullName;
    @BindView(R.id.activity_add_complaint_tv_email)
    EditText activityAddComplaintTvEmail;
    @BindView(R.id.activity_add_complaint_tv_phone)
    EditText activityAddComplaintTvPhone;
    @BindView(R.id.activity_add_complaint_tv_message)
    EditText activityAddComplaintTvMessage;
    @BindView(R.id.activity_add_complaint_btn_send)
    Button activityAddComplaintBtnSend;



    private String name, phone, email, message;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_complaint);
        ButterKnife.bind(this);
        addComplaintPresenter = new AddComplaintPresenter(this);


        if (Locale.getDefault().getDisplayLanguage().equals("English"))
        {
            activityAddComplaintTvEmail.setGravity(LEFT);
            activityAddComplaintTvPhone.setGravity(LEFT);
            activityAddComplaintTvFullName.setGravity(LEFT);

        }
        else
        {
            activityAddComplaintTvEmail.setGravity(RIGHT);
            activityAddComplaintTvPhone.setGravity(RIGHT);
            activityAddComplaintTvFullName.setGravity(RIGHT);

        }



        activityAddComplaintTvFullName.setText(loadUserData(AddComplaintActivity.this, USER_DATA).getName());
        activityAddComplaintTvEmail.setText(loadUserData(AddComplaintActivity.this, USER_DATA).getEmail());
        activityAddComplaintTvPhone.setText(loadUserData(AddComplaintActivity.this, USER_DATA).getPhone());


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

    @OnClick({R.id.activity_add_complaint_iv_bake, R.id.activity_add_complaint_btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_add_complaint_iv_bake:
                onBackPressed();
                break;
            case R.id.activity_add_complaint_btn_send:

                name = activityAddComplaintTvFullName.getText().toString();
                phone = activityAddComplaintTvPhone.getText().toString();
                email = activityAddComplaintTvEmail.getText().toString();
                message = activityAddComplaintTvMessage.getText().toString();

                if (name.isEmpty())
                {
                    activityAddComplaintTvFullName.setError("Please Enter Name");
                }
                else     if (phone.isEmpty())
                {
                    activityAddComplaintTvPhone.setError("Please Enter Phone");
                }
                else     if (email.isEmpty())
                {
                    activityAddComplaintTvEmail.setError("Please Enter Email");
                }
                else     if (message.isEmpty())
                {
                    activityAddComplaintTvMessage.setError("Please Enter Message");
                }
                else
                {
                    addComplaintPresenter.Complaint(name, phone, email, message);

                }

                break;
        }
    }
}