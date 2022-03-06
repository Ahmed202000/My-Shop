package com.hegazy.myshop.ui.activity;

import android.os.Bundle;

import com.hegazy.myshop.R;
import com.hegazy.myshop.ui.fragment.auth.login.LoginFragment;

import static com.hegazy.myshop.helpar.HelperMethod.replaceFragment;

public class AuthActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_auth);

      replaceFragment(this.getSupportFragmentManager(),R.id.activity_auth_fr_login,new LoginFragment());
    }



    @Override
    public void superBackPressed() {
        super.superBackPressed();
    }
}