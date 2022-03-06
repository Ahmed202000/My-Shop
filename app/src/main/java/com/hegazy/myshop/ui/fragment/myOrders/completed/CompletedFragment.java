package com.hegazy.myshop.ui.fragment.myOrders.completed;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.hegazy.myshop.R;
import com.hegazy.myshop.ui.fragment.BaseFragment;

import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompletedFragment extends BaseFragment {


    public CompletedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_completed, container, false);

        ButterKnife.bind(this,view);

        return view;


    }

    @Override
    public void onback() {

    }

}
