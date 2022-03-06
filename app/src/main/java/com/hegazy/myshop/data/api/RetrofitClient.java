package com.hegazy.myshop.data.api;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

  private static final String TAG = "ahmed";

  private static Retrofit retrofit=null;
  private static String BASE_URL="https://student.valuxapps.com/api/";
  private static OkHttpClient okHttpClient;

  public static ApiServes getClient() {


    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


    retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();



    return retrofit.create(ApiServes.class);
  }




}





