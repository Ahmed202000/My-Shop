package com.hegazy.myshop.ui.fragment.auth.forgetPassword.code.email;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.chaos.view.PinView;
import com.hegazy.myshop.R;
import com.hegazy.myshop.helpar.HelperMethod;
import com.hegazy.myshop.ui.fragment.BaseFragment;
import com.hegazy.myshop.ui.fragment.auth.forgetPassword.resete.ResetPasswordFragment;
import com.valdesekamdem.library.mdtoast.MDToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hegazy.myshop.helpar.HelperMethod.replaceFragment;
import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class SendCodeFragment extends BaseFragment implements SendCodeInterface {


    public String email;
    @BindView(R.id.fragment_send_otp_otp)
    PinView fragmentSendOtpOtp;
    @BindView(R.id.fragment_send_otp_btn_verification)
    Button fragmentSendOtpBtnVerification;
    private SendCodePresenter sendCodePresenter;
    private ProgressDialog dialog;

    public SendCodeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_send_otp, container, false);

        ButterKnife.bind(this, view);


        sendCodePresenter = new SendCodePresenter(this);
        return view;


    }

    @Override
    public void onSuccess(String massge) {
        dialog.cancel();
        MDToast.makeText(getActivity(),massge, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
        ResetPasswordFragment resetPasswordFragment=new ResetPasswordFragment();
        resetPasswordFragment.email=email;
        resetPasswordFragment.code=fragmentSendOtpOtp.getText().toString();
        replaceFragment(getActivity().getSupportFragmentManager(),R.id.activity_auth_fr_login,resetPasswordFragment);

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


    @OnClick(R.id.fragment_send_otp_btn_verification)
    public void onViewClicked() {

        String code=fragmentSendOtpOtp.getText().toString();
        if (code.isEmpty())
        {
            Toast.makeText(baseActivity, getActivity().getString(R.string.Code), Toast.LENGTH_SHORT).show();
        }
        else {
            sendCodePresenter.forgotPasswordSendCode(email, HelperMethod.changeLang(),code);

        }
    }
}
