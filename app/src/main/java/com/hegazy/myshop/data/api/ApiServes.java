package com.hegazy.myshop.data.api;


import com.hegazy.myshop.data.model.addAddressModel.AddAddressModel;
import com.hegazy.myshop.data.model.addComplaintModel.AddComplaintModel;
import com.hegazy.myshop.data.model.addFavoritesModel.AddFavoritesModel;
import com.hegazy.myshop.data.model.addOrRemoveCartModel.AddOrRemoveCartModel;
import com.hegazy.myshop.data.model.categoriesModel.CategoriesModel;
import com.hegazy.myshop.data.model.categoryProductsModel.CategoryProductsModel;
import com.hegazy.myshop.data.model.commonQuestionsModel.CommonQuestionsModel;
import com.hegazy.myshop.data.model.deleteFavoritesModel.DeleteFavoritesModel;
import com.hegazy.myshop.data.model.favoritesModel.FavoritesModel;
import com.hegazy.myshop.data.model.getAddressModel.GetAddressModel;
import com.hegazy.myshop.data.model.getCartModel.GetCartModel;
import com.hegazy.myshop.data.model.homeModel.HomeModel;
import com.hegazy.myshop.data.model.loginModel.LoginModel;
import com.hegazy.myshop.data.model.myOrderModel.MyOrderModel;
import com.hegazy.myshop.data.model.notificationsModel.NotificationsModel;
import com.hegazy.myshop.data.model.orderDetailsModel.OrderDetailsModel;
import com.hegazy.myshop.data.model.productDetailsModel.ProductDetailsModel;
import com.hegazy.myshop.data.model.promoCodeModel.PromoCodeModel;
import com.hegazy.myshop.data.model.registerModel.RegisterModel;
import com.hegazy.myshop.data.model.searchProductModel.SearchProductModel;
import com.hegazy.myshop.data.model.sendCodeModel.SendCodeModel;
import com.hegazy.myshop.data.model.sendEmailModel.SendEmailModel;
import com.hegazy.myshop.data.model.sendEmailModel.SendEmailModelData;
import com.hegazy.myshop.data.model.settingModel.SettingModel;
import com.hegazy.myshop.data.model.socialMedia.SocialMedia;
import com.hegazy.myshop.data.model.updateCartMedia.UpdateCartMedia;
import com.hegazy.myshop.data.model.updateProfileModel.UpdateProfileModel;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServes {


    @POST("login")
    @FormUrlEncoded
    Call<LoginModel> loginUser(@Field("email") String email,
                               @Field("password") String password);



    @POST("fcm-token")
    @FormUrlEncoded
    Call<LoginModel> fcmToken(@Header("Authorization") String Authorization,
                              @Header("lang")String lang,
                               @Field("token") String fcmToken);


    @POST("register")
    @FormUrlEncoded
    Call<RegisterModel> registerUser(@Header("lang")String lang,
                                     @Field("name") String name,
                                     @Field("phone") String phone,
                                     @Field("email") String email,
                                     @Field("password") String password);

    @GET("home")
    Call<HomeModel> getHome(@Header("Authorization") String Authorization,
                            @Header("lang")String lang);


    @GET("categories")
    Call<CategoriesModel> getCategories(@Header("lang")String lang);


    @POST("products/search")
    @FormUrlEncoded
    Call<SearchProductModel> getProductSearch(@Header("Authorization") String Authorization,
                                              @Header("lang")String lang,
                                              @Field("text") String text);


    @GET("notifications")
    Call<NotificationsModel> getNotifications(@Header("Authorization") String Authorization,
                                              @Header("lang")String lang);


    @GET("favorites")
    Call<FavoritesModel> getFavorites(@Header("Authorization") String Authorization,
                                      @Header("lang")String lang);

    @GET("faqs")
    Call<CommonQuestionsModel> getQuestionsModel(@Header("lang")String lang);


    @POST("favorites")
    @FormUrlEncoded
    Call<AddFavoritesModel> addFavorite(@Header("Authorization") String Authorization,
                                        @Header("lang")String lang,
                                        @Field("product_id") int product_id);


    @DELETE("favorites/{product_id}")
    Call<DeleteFavoritesModel> deleteFavorite(@Header("Authorization") String Authorization,
                                              @Header("lang")String lang,
                                              @Path("product_id") int product_id);


    @GET("products/{product_id}")
    Call<ProductDetailsModel> getProductDetails(@Header("Authorization") String Authorization,
                                                @Header("lang")String lang,
                                                @Path("product_id") int product_id);

    @GET("products")
    Call<CategoryProductsModel> getCategoryProducts(@Header("Authorization") String Authorization,
                                                    @Header("lang")String lang,
                                                    @Query("category_id") int category_id);


    @POST("carts")
    @FormUrlEncoded
    Call<AddOrRemoveCartModel> addOrRemoveCart(@Header("Authorization") String Authorization,
                                               @Header("lang")String lang,
                                               @Field("product_id") int product_id);


    @GET("carts")
    Call<GetCartModel> getCart(@Header("Authorization") String Authorization,
                               @Header("lang")String lang);


    @POST("complaints")
    @FormUrlEncoded
    Call<AddComplaintModel> addComplaint(@Header("lang")String lang,
                                         @Field("name") String name,
                                         @Field("phone") String phone,
                                         @Field("email") String email,
                                         @Field("message") String message);


    @POST("addresses")
    @FormUrlEncoded
    Call<AddAddressModel> addAddress(@Header("Authorization") String Authorization,
                                     @Header("lang")String lang,
                                     @Field("name") String name,
                                     @Field("city") String city,
                                     @Field("region") String region,
                                     @Field("details") String details,
                                     @Field("latitude") double latitude,
                                     @Field("longitude") double longitude,
                                     @Field("notes") String notes);



    @PUT("addresses/{id}")
    @FormUrlEncoded
    Call<AddAddressModel> updateAddress(@Header("Authorization") String Authorization,
                                        @Header("lang")String lang,
                                     @Path("id") int id,
                                     @Field("name") String name,
                                     @Field("city") String city,
                                     @Field("region") String region,
                                     @Field("details") String details,
                                     @Field("latitude") double latitude,
                                     @Field("longitude") double longitude,
                                     @Field("notes") String notes);



    @Headers({"Accept: application/json"})
    @PUT("update-profile")
    @Multipart
    Call<UpdateProfileModel> updateProfile(@Header("Authorization") String Authorization,
                                           @Header("lang")String lang,
                                           @Part("name")String name,
                                           @Part("phone") String phone,
                                           @Part("email") String email,
                                           @Part MultipartBody.Part image);




    @GET("settings")
    Call<SettingModel> setting(@Header("lang")String lang);


    @GET("contacts")
    Call<SocialMedia> socialMedia(@Header("lang")String lang);




    @Headers({"Accept: application/json"})
    @PUT("carts/{id}")
    @FormUrlEncoded
    Call<UpdateCartMedia> updateCart(@Header("Authorization") String Authorization,
                                     @Header("lang")String lang,
                                     @Path("id")int id,
                                     @Field("quantity") int quantity);



    @Headers({"Accept: application/json"})
    @GET("addresses")
    Call<GetAddressModel> getAddress(@Header("Authorization") String Authorization,
                                     @Header("lang")String lang);



    @Headers({"Accept: application/json"})
    @DELETE("addresses/{id}")
    Call<GetAddressModel> deleteAddress(@Header("Authorization") String Authorization,
                                        @Header("lang")String lang,
                                        @Path("id")int id);



    @Headers({"Accept: application/json"})
    @POST("promo-codes/validate")
    @FormUrlEncoded
    Call<PromoCodeModel> promoCode(@Header("Authorization") String Authorization,
                                   @Header("lang")String lang,
                                       @Field("code") String code);




    @Headers({"Accept: application/json"})
    @POST("orders")
    @FormUrlEncoded
    Call<PromoCodeModel> addOrder(@Header("Authorization") String Authorization,
                                  @Header("lang")String lang,
                                   @Field("address_id") int address_id,
                                   @Field("payment_method") int payment_method,
                                   @Field("use_points") boolean use_points,
                                   @Field("promo_code_id") int promo_code_id);



    @Headers({"Accept: application/json"})
    @GET("orders")
    Call<MyOrderModel> myOrderModel(@Header("Authorization") String Authorization,
                                  @Header("lang")String lang,
                                    @Query("page")int page);




    @Headers({"Accept: application/json"})
    @GET("orders/{id}")
    Call<OrderDetailsModel> orderDetails(@Header("Authorization") String Authorization,
                                         @Header("lang")String lang,
                                         @Path("id")int id);


    @Headers({"Accept: application/json"})
    @GET("orders/{id}/cancel")
    Call<PromoCodeModel> cancelOrder(@Header("Authorization") String Authorization,
                                  @Header("lang") String lang,
                                  @Path("id") int id);


    @Headers({"Accept: application/json"})
    @POST("verify-email")
    @FormUrlEncoded
    Call<SendEmailModel> sendEmailModel( @Header("lang") String lang,
                                         @Field("email")String email);


    @Headers({"Accept: application/json"})
    @POST("verify-code")
    @FormUrlEncoded
    Call<SendCodeModel> sendCodeModel(@Header("lang") String lang,
                                      @Field("email")String email,
                                      @Field("code")String code);



    @Headers({"Accept: application/json"})
    @POST("reset-password")
    @FormUrlEncoded
    Call<SendEmailModel> resetPasswordModel(@Header("lang") String lang,
                                      @Field("email")String email,
                                      @Field("code")String code,
                                      @Field("password")String password);

    @Headers({"Accept: application/json"})
    @POST("change-password")
    @FormUrlEncoded
    Call<SendEmailModel> changePasswordModel(@Header("Authorization") String Authorization,
                                             @Header("lang") String lang,
                                      @Field("current_password")String current_password,
                                      @Field("new_password")String new_password);

}



