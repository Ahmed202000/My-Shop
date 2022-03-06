package com.hegazy.myshop.ui.activity.setting.setting;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hegazy.myshop.R;
import com.valdesekamdem.library.mdtoast.MDToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;

public class AboutAppActivity extends AppCompatActivity implements SettingInterface {

    @BindView(R.id.activity_about_app_iv_bake)
    ImageView activityAboutAppIvBake;
    @BindView(R.id.text_view)
    TextView textView;
    private ProgressDialog dialog;
    SettingPresenter settingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        ButterKnife.bind(this);

        settingPresenter=new SettingPresenter(this);
        settingPresenter.setting();
    }


    @Override
    public void showProgress(int title) {
        dialog = showProgressDialog(this, getString(title));
        dialog.show();
    }

    @Override
    public void onSuccess(String massge) {
        MDToast.makeText(this, massge, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
        dialog.dismiss();
    }

    @Override
    public void onError(String mass) {
        dialog.dismiss();
        MDToast.makeText(this, mass, MDToast.LENGTH_SHORT, MDToast.TYPE_INFO).show();

    }

    @Override
    public void onFailure(String mass) {
        MDToast.makeText(this, mass, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();

        dialog.dismiss();

    }

    @Override
    public void data(String text) {
        textView.setText(text);
    }

    @OnClick(R.id.activity_about_app_iv_bake)
    public void onViewClicked() {
        onBackPressed();
    }
}