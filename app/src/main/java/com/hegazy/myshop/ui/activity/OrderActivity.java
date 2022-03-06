package com.hegazy.myshop.ui.activity;

import android.os.Bundle;

import com.hegazy.myshop.R;
import com.hegazy.myshop.ui.fragment.order.cart.CartFragment;

import static com.hegazy.myshop.helpar.HelperMethod.replaceFragment;

public class OrderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        replaceFragment(this.getSupportFragmentManager(),R.id.activity_order_fr_order,new CartFragment());

    }

    @Override
    public void superBackPressed() {
        super.superBackPressed();
    }
}