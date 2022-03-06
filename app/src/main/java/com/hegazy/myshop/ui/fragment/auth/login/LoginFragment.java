package com.hegazy.myshop.ui.fragment.auth.login;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.loginModel.LoginModel;
import com.hegazy.myshop.data.model.loginModel.LoginModelData;
import com.hegazy.myshop.ui.activity.HomeActivity;
import com.hegazy.myshop.ui.activity.SplashActivity;
import com.hegazy.myshop.ui.fragment.BaseFragment;
import com.hegazy.myshop.ui.fragment.auth.forgetPassword.email.SendEmailFragment;
import com.hegazy.myshop.ui.fragment.auth.register.RegisterFragment;
import com.valdesekamdem.library.mdtoast.MDToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.hegazy.myshop.data.api.RetrofitClient.getClient;
import static com.hegazy.myshop.data.locale.Const.FIREBASE_TOKEN;
import static com.hegazy.myshop.data.locale.Const.USER_DATA;
import static com.hegazy.myshop.data.locale.Const.USER_TOKEN;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.LoadData;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.SaveData;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.saveUserData;
import static com.hegazy.myshop.helpar.HelperMethod.changeLang;
import static com.hegazy.myshop.helpar.HelperMethod.replaceFragment;
import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment implements ChickStatusInterface {


    @BindView(R.id.fragment_login_tv_email)
    EditText fragmentLoginTvEmail;
    @BindView(R.id.fragment_login_tv_password)
    EditText fragmentLoginTvPassword;
    @BindView(R.id.fragment_login_tv_forget_password)
    TextView fragmentLoginTvForgetPassword;
    @BindView(R.id.fragment_login_btn_login)
    Button fragmentLoginBtnLogin;
    @BindView(R.id.fragment_login_btn_register)
    Button fragmentLoginBtnRegister;


    private ProgressDialog dialog;
    private String email;
    private String password;

    private LoginPresenter loginPresenter;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);


        loginPresenter=new LoginPresenter(this);

        getFirebaseToken();


        return view;




    }







    @OnClick({R.id.fragment_login_tv_forget_password, R.id.fragment_login_btn_login, R.id.fragment_login_btn_register})
    public void onViewClicked(View view)
    {
        switch (view.getId()) {
            case R.id.fragment_login_tv_forget_password:
                replaceFragment(getActivity().getSupportFragmentManager(),R.id.activity_auth_fr_login,new SendEmailFragment());

                break;
            case R.id.fragment_login_btn_login:

                email=fragmentLoginTvEmail.getText().toString();
                password=fragmentLoginTvPassword.getText().toString();

                if (email.isEmpty())
                {
                    fragmentLoginTvEmail.setError("Please Enter Email");
                }
                else  if (password.isEmpty())
                {
                    fragmentLoginTvPassword.setError("Please Enter Password");
                }
                else
                {
                    loginPresenter.loginUser(email,password);

                }


                break;
            case R.id.fragment_login_btn_register:
                  replaceFragment(getActivity().getSupportFragmentManager(),R.id.activity_auth_fr_login,new RegisterFragment());

                break;
        }
    }


    @Override
    public void onSuccess(String massge) {
        dialog.cancel();
        MDToast.makeText(getActivity(),massge, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
        startActivity(new Intent(getActivity(), HomeActivity.class));
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
    public void saveDataUser(LoginModelData loginModelData) {
        saveUserData(getActivity(), USER_DATA, loginModelData);
    }

    @Override
    public void saveTokenUser(String token) {
        SaveData(getActivity(), USER_TOKEN, token);

    }


    @Override
    public void showProgress(int title) {
        dialog = showProgressDialog(getActivity(), getString(title));
        dialog.show();
    }




    @Override
    public void onBack() {
        getActivity().finish();
    }


    public void getFirebaseToken() {

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        SaveData(getActivity(), FIREBASE_TOKEN, token);

                    }
                });



    }

}
