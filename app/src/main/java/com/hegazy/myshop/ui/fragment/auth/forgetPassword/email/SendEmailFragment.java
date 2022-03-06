package com.hegazy.myshop.ui.fragment.auth.forgetPassword.email;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.hegazy.myshop.R;
import com.hegazy.myshop.ui.fragment.BaseFragment;
import com.hegazy.myshop.ui.fragment.auth.forgetPassword.code.email.SendCodeFragment;
import com.valdesekamdem.library.mdtoast.MDToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hegazy.myshop.data.locale.SharedPreferencesManger.SaveData;
import static com.hegazy.myshop.helpar.HelperMethod.changeLang;
import static com.hegazy.myshop.helpar.HelperMethod.replaceFragment;
import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class SendEmailFragment extends BaseFragment implements SendEmailInterface{


    @BindView(R.id.fragment_send_email_tv_email)
    EditText fragmentSendEmailTvEmail;
    @BindView(R.id.fragment_send_email_btn_send)
    Button fragmentSendEmailBtnSend;
    private ProgressDialog dialog;
    private SendEmailPresenter sendEmailPresenter;
    private String email;

    public SendEmailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_send_email, container, false);

        ButterKnife.bind(this, view);

        sendEmailPresenter=new SendEmailPresenter(this);

        return view;


    }






    @OnClick(R.id.fragment_send_email_btn_send)
    public void onViewClicked() {

        email=fragmentSendEmailTvEmail.getText().toString();
        if (email.isEmpty())
        {
            fragmentSendEmailTvEmail.setError(getActivity().getString(R.string.email));
        }
        else
        {
            sendEmailPresenter.forgotPasswordSendEmail(email,changeLang());

        }

    }

    @Override
    public void onSuccess(String massge) {
        dialog.cancel();
        MDToast.makeText(getActivity(),massge, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
        SendCodeFragment sendCodeFragment=new SendCodeFragment();
        sendCodeFragment.email=fragmentSendEmailTvEmail.getText().toString();
        replaceFragment(getActivity().getSupportFragmentManager(),R.id.activity_auth_fr_login,sendCodeFragment);

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
