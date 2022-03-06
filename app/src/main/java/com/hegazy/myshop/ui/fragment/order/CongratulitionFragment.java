package com.hegazy.myshop.ui.fragment.order;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.hegazy.myshop.R;
import com.hegazy.myshop.ui.activity.HomeActivity;
import com.hegazy.myshop.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class CongratulitionFragment extends BaseFragment {


    @BindView(R.id.activity_congratulation_btn_shape_bottom)
    Button activityCongratulationBtnShapeBottom;

    public CongratulitionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.congratulation_empty, container, false);

        ButterKnife.bind(this, view);

        return view;


    }

    @Override
    public void onback() {
        startActivity(new Intent(getActivity(), HomeActivity.class));

    }

    @OnClick(R.id.activity_congratulation_btn_shape_bottom)
    public void onViewClicked() {

        startActivity(new Intent(getActivity(), HomeActivity.class));

    }
}
