package com.hegazy.myshop.ui.activity.editProfile;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.hegazy.myshop.R;
import com.hegazy.myshop.ui.activity.categoryProducts.CategoryProductsActivity;
import com.valdesekamdem.library.mdtoast.MDToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.hegazy.myshop.data.locale.Const.USER_DATA;
import static com.hegazy.myshop.data.locale.Const.USER_TOKEN;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.LoadData;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.loadUserData;
import static com.hegazy.myshop.helpar.HelperMethod.changeLang;
import static com.hegazy.myshop.helpar.HelperMethod.convertFileToMultipart;
import static com.hegazy.myshop.helpar.HelperMethod.convertToRequestBody;
import static com.hegazy.myshop.helpar.HelperMethod.image_url;
import static com.hegazy.myshop.helpar.HelperMethod.openSingleGallery;
import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;

public class EditProfileActivity extends AppCompatActivity implements EditProfileInterface{

    @BindView(R.id.activity_add_complaint_iv_bake)
    ImageView activityAddComplaintIvBake;
    @BindView(R.id.activity_edit_profile_profile_iv_user)
    CircleImageView activityEditProfileProfileIvUser;
    @BindView(R.id.activity_edit_profile_profile_tv_upload_image)
    TextView activityEditProfileProfileTvUploadImage;
    @BindView(R.id.activity_edit_profile_profile_tv_name)
    EditText activityEditProfileProfileTvName;
    @BindView(R.id.activity_edit_profile_profile_tv_email)
    EditText activityEditProfileProfileTvEmail;
    @BindView(R.id.activity_edit_profile_profile_tv_phone)
    EditText activityEditProfileProfileTvPhone;
    @BindView(R.id.activity_edit_profile_profile_btn_save)
    Button activityEditProfileProfileBtnSave;

    private ProgressDialog dialog;
    EditProfilePresenter editProfilePresenter;




    private String nameUser;
    private String phone;
    private String email;
    private MultipartBody.Part image;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        editProfilePresenter=new EditProfilePresenter(this);



        Glide.with(EditProfileActivity.this).load(loadUserData(EditProfileActivity.this, USER_DATA).getImage()).into
                (activityEditProfileProfileIvUser);
        activityEditProfileProfileTvName.setText(loadUserData(EditProfileActivity.this, USER_DATA).getName());
        activityEditProfileProfileTvEmail.setText(loadUserData(EditProfileActivity.this, USER_DATA).getEmail());
        activityEditProfileProfileTvPhone.setText(loadUserData(EditProfileActivity.this, USER_DATA).getPhone());




    }

    @OnClick({R.id.activity_add_complaint_iv_bake, R.id.activity_edit_profile_profile_tv_upload_image, R.id.activity_edit_profile_profile_btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_add_complaint_iv_bake:
                onBackPressed();

                break;
            case R.id.activity_edit_profile_profile_tv_upload_image:
                openSingleGallery(EditProfileActivity.this,activityEditProfileProfileIvUser);
                break;
            case R.id.activity_edit_profile_profile_btn_save:

                token = LoadData(EditProfileActivity.this, USER_TOKEN);
                image=convertFileToMultipart(image_url,"image");
                nameUser=activityEditProfileProfileTvName.getText().toString().trim();
                phone=activityEditProfileProfileTvPhone.getText().toString().trim();
                email=activityEditProfileProfileTvEmail.getText().toString().trim();

                    editProfilePresenter.Update(token,changeLang(),nameUser,phone,email,image);



                break;
        }
    }

    @Override
    public void showProgress(String title) {
        dialog = showProgressDialog(this, title);
        dialog.show();
    }

    @Override
    public void onSuccess(String massge) {
        MDToast.makeText(this, massge, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
        onBackPressed();
        finish();
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
}