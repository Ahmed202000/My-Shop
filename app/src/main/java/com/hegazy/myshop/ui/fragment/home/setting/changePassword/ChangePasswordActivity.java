package com.hegazy.myshop.ui.fragment.home.setting.changePassword;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.hegazy.myshop.R;
import com.hegazy.myshop.ui.fragment.auth.login.LoginFragment;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hegazy.myshop.data.locale.Const.USER_TOKEN;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.LoadData;
import static com.hegazy.myshop.helpar.HelperMethod.changeLang;
import static com.hegazy.myshop.helpar.HelperMethod.replaceFragment;
import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;

public class ChangePasswordActivity extends AppCompatActivity implements ChangePasswordInterface {

    @BindView(R.id.activity_change_password_iv_bake)
    ImageView activityChangePasswordIvBake;
    @BindView(R.id.activity_change_password_tv_old_password)
    EditText activityChangePasswordTvOldPassword;
    @BindView(R.id.activity_change_password_tv_new_password)
    EditText activityChangePasswordTvNewPassword;
    @BindView(R.id.activity_change_password_tv_confirm_password)
    EditText activityChangePasswordTvConfirmPassword;
    @BindView(R.id.activity_change_password_btn_verification)
    Button activityChangePasswordBtnVerification;
    private ProgressDialog dialog;
    private ChangePasswordPresenter changePasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);

        changePasswordPresenter=new ChangePasswordPresenter(this);
    }

    @Override
    public void onSuccess(String massge) {
        dialog.cancel();
        MDToast.makeText(this,massge, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
        onBackPressed();

    }

    @Override
    public void onError(String mass) {
        // hide dialog
        dialog.cancel();
        // error
        MDToast.makeText(this,mass, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();


    }

    @Override
    public void onFailure(String mass) {
        MDToast.makeText(this,mass, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();
        dialog.cancel();

    }

    @Override
    public void showProgress(int title) {

        dialog = showProgressDialog(ChangePasswordActivity.this,getString(title));
        dialog.show();
    }

    @OnClick({R.id.activity_change_password_iv_bake, R.id.activity_change_password_btn_verification})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_change_password_iv_bake:
                onBackPressed();
                finish();
                break;
            case R.id.activity_change_password_btn_verification:

                String oldPassword=activityChangePasswordTvOldPassword.getText().toString();
                String newPassword=activityChangePasswordTvNewPassword.getText().toString();
                String confirmPassword=activityChangePasswordTvConfirmPassword.getText().toString();

                if (oldPassword.isEmpty())
                {
                    activityChangePasswordTvOldPassword.setError(ChangePasswordActivity.this.getString(R.string.old_password));
                }
                else  if (newPassword.isEmpty())
                {
                    activityChangePasswordTvNewPassword.setError(ChangePasswordActivity.this.getString(R.string.new_password));
                }
                else if (confirmPassword.isEmpty())
                {
                    activityChangePasswordTvConfirmPassword.setError(ChangePasswordActivity.this.getString(R.string.confirm_password));
                }
                else if (!confirmPassword .equals(newPassword) )
                {
                    activityChangePasswordTvConfirmPassword.setError(ChangePasswordActivity.this.getString(R.string.passwords_do_not_match));
                }
                else
                {
                    String token = LoadData(this, USER_TOKEN);
                    changePasswordPresenter.changPassword(token,changeLang(),oldPassword,newPassword);
                }

                break;
        }
    }
}