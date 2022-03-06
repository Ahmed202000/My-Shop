package com.hegazy.myshop.ui.fragment.auth.forgetPassword.resete;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;


import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.hegazy.myshop.R;
import com.hegazy.myshop.ui.fragment.BaseFragment;
import com.hegazy.myshop.ui.fragment.auth.forgetPassword.code.email.SendCodeFragment;
import com.hegazy.myshop.ui.fragment.auth.login.LoginFragment;
import com.valdesekamdem.library.mdtoast.MDToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hegazy.myshop.helpar.HelperMethod.changeLang;
import static com.hegazy.myshop.helpar.HelperMethod.replaceFragment;
import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPasswordFragment extends BaseFragment implements ResetPasswordInterface{


    public String email;
    public String code;

    @BindView(R.id.fragment_reset_password_ed_password)
    EditText fragmentResetPasswordEdPassword;
    @BindView(R.id.fragment_reset_password_tv_confirm_password)
    EditText fragmentResetPasswordTvConfirmPassword;
    @BindView(R.id.fragment_reset_password_btn_verification)
    Button fragmentResetPasswordBtnVerification;
    private ProgressDialog dialog;
    private ResetPasswordPresenter resetPasswordPresenter;

    public ResetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);

        ButterKnife.bind(this, view);

        resetPasswordPresenter=new ResetPasswordPresenter(this);

        return view;


    }

    @OnClick(R.id.fragment_reset_password_btn_verification)
    public void onViewClicked() {

        String password=fragmentResetPasswordEdPassword.getText().toString();
        String confirmPassword=fragmentResetPasswordTvConfirmPassword.getText().toString();

        if (password.isEmpty())
        {
            fragmentResetPasswordEdPassword.setError(getActivity().getString(R.string.password));
        }
        else if (confirmPassword.isEmpty())
        {
            fragmentResetPasswordTvConfirmPassword.setError(getActivity().getString(R.string.confirm_password));
        }
        else if (!confirmPassword .equals(password) )
        {
            fragmentResetPasswordTvConfirmPassword.setError(getActivity().getString(R.string.passwords_do_not_match));
        }
        else
        {
            resetPasswordPresenter.resetPassword(email,changeLang(),code,password);
        }


    }




    @Override
    public void onSuccess(String massge) {
        dialog.cancel();
        MDToast.makeText(getActivity(),massge, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
        replaceFragment(getActivity().getSupportFragmentManager(),R.id.activity_auth_fr_login,new LoginFragment());

    }

    @Override
    public void onError(String mass) {
        // hide dialog
        dialog.cancel();
        // error
        MDToast.makeText(getActivity(),mass, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();


    }

    @Override
    public void onFailure(String mass) {
        MDToast.makeText(getActivity(),mass, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();
        dialog.cancel();

    }

    @Override
    public void showProgress(int title) {

        dialog = showProgressDialog(getActivity(),getActivity().getString(title));
        dialog.show();
    }
}
