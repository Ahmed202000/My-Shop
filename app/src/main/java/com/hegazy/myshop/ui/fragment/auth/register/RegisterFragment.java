package com.hegazy.myshop.ui.fragment.auth.register;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.loginModel.LoginModelData;
import com.hegazy.myshop.ui.fragment.BaseFragment;
import com.hegazy.myshop.ui.fragment.auth.login.ChickStatusInterface;
import com.hegazy.myshop.ui.fragment.auth.login.LoginFragment;
import com.valdesekamdem.library.mdtoast.MDToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hegazy.myshop.helpar.HelperMethod.replaceFragment;
import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment implements ChickStatusInterface {


    @BindView(R.id.fragment_register_tv_full_name)
    EditText fragmentRegisterTvFullName;
    @BindView(R.id.fragment_register_tv_email)
    EditText fragmentRegisterTvEmail;
    @BindView(R.id.fragment_register_tv_password)
    EditText fragmentRegisterTvPassword;
    @BindView(R.id.fragment_register_tv_phone)
    EditText fragmentRegisterTvPhone;
    @BindView(R.id.fragment_register_btn_register)
    Button fragmentRegisterBtnRegister;


    RegisterPresenter registerPresenter;

    ProgressDialog dialog;
    @BindView(R.id.fragment_register_btn_login)
    Button fragmentRegisterBtnLogin;
    private String password;
    private String phone;
    private String name;
    private String email;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        ButterKnife.bind(this, view);

        registerPresenter = new RegisterPresenter(this);

        return view;


    }

    @Override
    public void onSuccess(String massge) {
        dialog.cancel();
        MDToast.makeText(getActivity(), massge, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.activity_auth_fr_login, new LoginFragment());

    }

    @Override
    public void onError(String mass) {
        // hide dialog
        dialog.cancel();
        // error
        MDToast.makeText(getActivity(), mass, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();

    }

    @Override
    public void onFailure(String mass) {
        MDToast.makeText(getActivity(), mass, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();
        dialog.cancel();

    }

    @Override
    public void saveDataUser(LoginModelData loginModelData) {

    }

    @Override
    public void saveTokenUser(String token) {

    }

    @Override
    public void showProgress(int title) {
        dialog = showProgressDialog(getActivity(), getString(title));
        dialog.show();
    }


    @Override
    public void onbacks() {
    }


    @OnClick({R.id.fragment_register_btn_register, R.id.fragment_register_btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_register_btn_register:

                name = fragmentRegisterTvFullName.getText().toString().trim();
                phone = fragmentRegisterTvPhone.getText().toString().trim();
                email = fragmentRegisterTvEmail.getText().toString().trim();
                password = fragmentRegisterTvPassword.getText().toString().trim();

                if (name.isEmpty()) {
                    fragmentRegisterTvFullName.setError("Pleas Enter FullName");
                } else if (phone.isEmpty()) {
                    fragmentRegisterTvPhone.setError("Pleas Enter Phone");
                } else if (email.isEmpty()) {
                    fragmentRegisterTvEmail.setError("Pleas Enter Email");
                } else if (password.isEmpty()) {
                    fragmentRegisterTvPassword.setError("Pleas Enter Password");
                } else {

                    registerPresenter.registerUser(name, phone, email, password);
                }

                break;
            case R.id.fragment_register_btn_login:

                replaceFragment(getActivity().getSupportFragmentManager(),R.id.activity_auth_fr_login,new LoginFragment());

                break;
        }
    }
}
