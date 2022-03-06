package com.hegazy.myshop.ui.fragment.home.profile;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.hegazy.myshop.R;
import com.hegazy.myshop.ui.activity.editProfile.EditProfileActivity;
import com.hegazy.myshop.ui.fragment.BaseFragment;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.view.Gravity.LEFT;
import static android.view.Gravity.RIGHT;
import static com.hegazy.myshop.data.locale.Const.USER_DATA;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.loadUserData;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment {


    @BindView(R.id.fragment_profile_iv_user)
    CircleImageView fragmentProfileIvUser;
    @BindView(R.id.fragment_profile_tv_name)
    TextView fragmentProfileTvName;
    @BindView(R.id.fragment_profile_tv_email)
    TextView fragmentProfileTvEmail;
    @BindView(R.id.fragment_profile_tv_phone)
    TextView fragmentProfileTvPhone;
    @BindView(R.id.fragment_profile_btn_Edit)
    Button fragmentProfileBtnEdit;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

         ButterKnife.bind(this,view);


        if (Locale.getDefault().getDisplayLanguage().equals("English"))
        {
            fragmentProfileTvEmail.setGravity(LEFT);
            fragmentProfileTvPhone.setGravity(LEFT);

        }
        else
        {
            fragmentProfileTvEmail.setGravity(RIGHT);
            fragmentProfileTvPhone.setGravity(RIGHT);

        }



         Glide.with(getActivity()).load(loadUserData(getActivity(), USER_DATA).getImage()).into(fragmentProfileIvUser);
        fragmentProfileTvName.setText(loadUserData(getActivity(), USER_DATA).getName());
        fragmentProfileTvEmail.setText(loadUserData(getActivity(), USER_DATA).getEmail());
        fragmentProfileTvPhone.setText(loadUserData(getActivity(), USER_DATA).getPhone());



        return view;


    }

    @Override
    public void onback() {

    }

    @OnClick(R.id.fragment_profile_btn_Edit)
    public void onViewClicked() {

        startActivity(new Intent(getActivity(), EditProfileActivity.class));
    }
}
