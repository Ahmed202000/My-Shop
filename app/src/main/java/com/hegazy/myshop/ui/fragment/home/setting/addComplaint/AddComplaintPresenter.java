package com.hegazy.myshop.ui.fragment.home.setting.addComplaint;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.addComplaintModel.AddComplaintModel;
import com.hegazy.myshop.data.model.categoriesModel.CategoriesModel;
import com.hegazy.myshop.ui.fragment.home.setting.categories.CategoryInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;
import static com.hegazy.myshop.helpar.HelperMethod.changeLang;

public class AddComplaintPresenter {



    AddComplaintInterface addComplaintInterface;

    public AddComplaintPresenter(AddComplaintInterface addComplaintInterface) {
        this.addComplaintInterface = addComplaintInterface;
    }


    public void Complaint(String name,String  phone,String email ,String message){

        addComplaintInterface.showProgress(R.string.please_wiht);

        getClient().addComplaint(changeLang(),name,phone,email,message).enqueue(new Callback<AddComplaintModel>() {
            @Override
            public void onResponse(Call<AddComplaintModel> call, Response<AddComplaintModel> response) {

                try {

                    if (response.body().getStatus() == true)
                    {
                        addComplaintInterface.onSuccess(response.body().getMessage());
                    }

                    else if (response.body().getStatus() == false)
                    {

                        addComplaintInterface.onError(response.body().getMessage());

                    }
                }
                catch(Exception e)
                {
                }

            }

            @Override
            public void onFailure(Call<AddComplaintModel> call, Throwable t) {
                addComplaintInterface.onFailure(t.getMessage());

            }
        });

    }



}
