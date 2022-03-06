package com.hegazy.myshop.ui.activity.setting.contacts;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hegazy.myshop.R;
import com.hegazy.myshop.adapter.SocialMediaAdapter;
import com.hegazy.myshop.data.model.socialMedia.SocialMediaDatum;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;

public class ContactsActivity extends AppCompatActivity implements ContactsInterface {

    @BindView(R.id.activity_request_help_iv_bake)
    ImageView activityRequestHelpIvBake;
    @BindView(R.id.activity_contacts_rv_social_media)
    RecyclerView activityContactsRvSocialMedia;
    private ProgressDialog dialog;
    private ContactsPresenter contactsPresenter;

    private List<SocialMediaDatum> socialMediaDatumList = new ArrayList<>();
    SocialMediaAdapter socialMediaAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ButterKnife.bind(this);

        contactsPresenter=new ContactsPresenter(this);


        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
        activityContactsRvSocialMedia.setLayoutManager(gridLayoutManager);
        contactsPresenter.getContacts();

    }



    @Override
    public void getSocialMedia(List<SocialMediaDatum> productList) {

        socialMediaDatumList.addAll(productList);
        socialMediaAdapter=new SocialMediaAdapter(this,socialMediaDatumList);
        activityContactsRvSocialMedia.setAdapter(socialMediaAdapter);
        socialMediaAdapter.notifyDataSetChanged();
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


    @OnClick(R.id.activity_request_help_iv_bake)
    public void onViewClicked() {
    }
}