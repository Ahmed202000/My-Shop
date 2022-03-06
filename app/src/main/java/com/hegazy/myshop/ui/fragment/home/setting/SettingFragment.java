package com.hegazy.myshop.ui.fragment.home.setting;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.locale.SharedPreferencesManger;
import com.hegazy.myshop.ui.activity.AuthActivity;
import com.hegazy.myshop.ui.activity.setting.setting.AboutAppActivity;
import com.hegazy.myshop.ui.activity.setting.myOrders.MyOrdersActivity;
import com.hegazy.myshop.ui.activity.setting.setting.TermsAndConditionsActivity;
import com.hegazy.myshop.ui.activity.setting.address.allAdderss.AllAddressActivity;
import com.hegazy.myshop.ui.activity.setting.contacts.ContactsActivity;
import com.hegazy.myshop.ui.fragment.BaseFragment;
import com.hegazy.myshop.ui.fragment.home.setting.addComplaint.AddComplaintActivity;
import com.hegazy.myshop.ui.fragment.home.setting.categories.CategoiseActivity;
import com.hegazy.myshop.ui.fragment.home.setting.changePassword.ChangePasswordActivity;
import com.hegazy.myshop.ui.fragment.home.setting.commonQuestions.CommonQuestionsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends BaseFragment {


    @BindView(R.id.fragment_setting_tv_change_password)
    TextView fragmentSettingTvChangePassword;
    @BindView(R.id.fragment_setting_tv_categories)
    TextView fragmentSettingTvCategories;
    @BindView(R.id.fragment_setting_tv_add_address)
    TextView fragmentSettingTvAddAddress;
    @BindView(R.id.fragment_setting_tv_contacts)
    TextView fragmentSettingTvContacts;
    @BindView(R.id.fragment_setting_tv_common_questions)
    TextView fragmentSettingTvCommonQuestions;
    @BindView(R.id.fragment_setting_tv_about_app)
    TextView fragmentSettingTvAboutApp;
    @BindView(R.id.fragment_setting_tv_terms_and_conditions)
    TextView fragmentSettingTvTermsAndConditions;
    @BindView(R.id.fragment_setting_tv_rate_app)
    TextView fragmentSettingTvRateApp;
    @BindView(R.id.fragment_setting_tv_shear_app)
    TextView fragmentSettingTvShearApp;
    @BindView(R.id.fragment_setting_tv_log_out)
    TextView fragmentSettingTvLogOut;
    @BindView(R.id.fragment_setting_tv_my_order)
    TextView fragmentSettingTvMyOrder;
    private Dialog dialog1;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        ButterKnife.bind(this, view);

        return view;


    }

    @Override
    public void onback() {

    }


    @OnClick({R.id.fragment_setting_tv_my_order,R.id.fragment_setting_tv_about_app, R.id.fragment_setting_tv_terms_and_conditions, R.id.fragment_setting_tv_rate_app, R.id.fragment_setting_tv_shear_app, R.id.fragment_setting_tv_log_out,
            R.id.fragment_setting_tv_add_complaint, R.id.fragment_setting_tv_change_password, R.id.fragment_setting_tv_categories, R.id.fragment_setting_tv_add_address, R.id.fragment_setting_tv_contacts, R.id.fragment_setting_tv_common_questions})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_setting_tv_change_password:

                startActivity(new Intent(getActivity(), ChangePasswordActivity.class));

                break;
            case R.id.fragment_setting_tv_categories:
                startActivity(new Intent(getActivity(), CategoiseActivity.class));

                break;
            case R.id.fragment_setting_tv_add_address:
                startActivity(new Intent(getActivity(), AllAddressActivity.class));

                break;
            case R.id.fragment_setting_tv_contacts:

                startActivity(new Intent(getActivity(), ContactsActivity.class));

                break;
            case R.id.fragment_setting_tv_common_questions:
                startActivity(new Intent(getActivity(), CommonQuestionsActivity.class));

                break;
            case R.id.fragment_setting_tv_add_complaint:
                startActivity(new Intent(getActivity(), AddComplaintActivity.class));
                break;
            case R.id.fragment_setting_tv_about_app:
                startActivity(new Intent(getActivity(), AboutAppActivity.class));

                break;
            case R.id.fragment_setting_tv_terms_and_conditions:
                startActivity(new Intent(getActivity(), TermsAndConditionsActivity.class));

                break;
            case R.id.fragment_setting_tv_rate_app:
                Intent ratingIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?" + "id=com.hegazy.myshop"));
                startActivity(ratingIntent);
                break;
            case R.id.fragment_setting_tv_shear_app:

                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My Shop");
                    String shareMessage = "شارك التطبيق مع اصدقائك";
                    shareMessage = shareMessage + " https://play.google.com/store/apps/details?id=" + "com.hegazy.myshop";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, getString(R.string.app_name)));
                } catch (Exception e) {
                    //e.toString();
                }

                break;


                case R.id.fragment_setting_tv_my_order:
                startActivity(new Intent(getActivity(), MyOrdersActivity.class));
                break;


            case R.id.fragment_setting_tv_log_out:
                dialogLogUot();
                break;
        }
    }

    //TODO dialog LogUot
    private void dialogLogUot() {
        dialog1 = new Dialog(getActivity(), R.style.customDialogTheme);
        dialog1.setCancelable(false);
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dialog_loguot, null);
        dialog1.setContentView(v);


        TextView yes = dialog1.findViewById(R.id.dialog_loguot_tv_yes);
        TextView no = dialog1.findViewById(R.id.dialog_loguot_tv_no);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferencesManger.clean(getActivity());
                startActivity(new Intent(getActivity(), AuthActivity.class));
                getActivity().finishAffinity();
            }
        });
        dialog1.show();
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
        dialog1.show();
    }


}
