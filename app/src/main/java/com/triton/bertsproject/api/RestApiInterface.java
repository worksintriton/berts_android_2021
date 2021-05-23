package com.triton.bertsproject.api;


import com.triton.bertsproject.model.DeleteCartListRequest;
import com.triton.bertsproject.requestpojo.AddWishistRequest;
import com.triton.bertsproject.requestpojo.FetchChildCateglistRequest;
import com.triton.bertsproject.requestpojo.FetchChildMakeslistRequest;
import com.triton.bertsproject.requestpojo.FetchProductBasedOnBrandRequest;
import com.triton.bertsproject.requestpojo.FetchProductBasedOnCatRequest;
import com.triton.bertsproject.requestpojo.FetchProductBasedOnMakeRequest;
import com.triton.bertsproject.requestpojo.ForgotPasswordRequest;
import com.triton.bertsproject.requestpojo.GetStateRequest;
import com.triton.bertsproject.requestpojo.LoginRequest;
import com.triton.bertsproject.requestpojo.RemoveWishistRequest;
import com.triton.bertsproject.requestpojo.ShowCartListRequest;
import com.triton.bertsproject.requestpojo.ShowWishistRequest;
import com.triton.bertsproject.requestpojo.SignupRequest;
import com.triton.bertsproject.requestpojo.SignupWholesalerRequest;
import com.triton.bertsproject.requestpojo.UpdateProfileRequest;
import com.triton.bertsproject.responsepojo.FetchAllBrandsResponse;
import com.triton.bertsproject.responsepojo.FetchAllParentCategoriesResponse;
import com.triton.bertsproject.responsepojo.FetchAllParentMakesResponse;
import com.triton.bertsproject.responsepojo.FetchChildCateglistResponse;
import com.triton.bertsproject.responsepojo.FetchChildMakeslistRequestResponse;
import com.triton.bertsproject.responsepojo.ForgotPasswordResponse;
import com.triton.bertsproject.responsepojo.GetCountryResponse;
import com.triton.bertsproject.responsepojo.GetStateResponse;
import com.triton.bertsproject.responsepojo.LoginResponse;
import com.triton.bertsproject.responsepojo.ProductListResponse;
import com.triton.bertsproject.responsepojo.ShowCartListResponse;
import com.triton.bertsproject.responsepojo.SignupResponse;
import com.triton.bertsproject.responsepojo.UpdateProfileResponse;
import com.triton.bertsproject.responsepojo.WishlistSuccessResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RestApiInterface {

    /*User Login*/
    @POST("login-customer")
    Call<LoginResponse> loginResponseCall(@Header("Content-Type") String type, @Body LoginRequest loginRequest);

    /*Signup create*/
    @POST("register-customer")
    Call<SignupResponse> signupResponseCall(@Header("Content-Type") String type, @Body SignupRequest signupRequest);

    /*Signup create*/
    @POST("register-customer")
    Call<SignupResponse> signupWholesalerResponseCall(@Header("Content-Type") String type, @Body SignupWholesalerRequest signupWholesalerRequest);

    /*forgot-password*/
    @POST("forgot-password")
    Call<ForgotPasswordResponse> forgotpasswordResponseCall(@Header("Content-Type") String type, @Body ForgotPasswordRequest forgotPasswordRequest);

    /*Get All Countries*/
    @GET("country")
    Call<GetCountryResponse> fetchallcountryListResponseCall(@Header("Content-Type") String type);

    /*Get All States*/
    @POST("country")
    Call<GetStateResponse> fetchallstateListResponseCall(@Header("Content-Type") String type, @Body GetStateRequest getStateRequest);

    /*Profile Update*/
    @POST("register-customer")
    Call<UpdateProfileResponse> updateResponseCall(@Header("Content-Type") String type, @Body UpdateProfileRequest updateProfileRequest);

    /*Get All Parent Categories*/
    @GET("category")
    Call<FetchAllParentCategoriesResponse> fetchallcategoriesListResponseCall(@Header("Content-Type") String type);

    /*Get All Brands*/
    @GET("brand")
    Call<FetchAllBrandsResponse> fetchallbrandsListResponseCall(@Header("Content-Type") String type);

    /*Get All Parent Makes*/
    @GET("make")
    Call<FetchAllParentMakesResponse> fetchallmakesListResponseCall(@Header("Content-Type") String type);

    /*Fetch Child Category List*/
    @POST("category")
    Call<FetchChildCateglistResponse> fetchallchildcateglistResponseCall(@Header("Content-Type") String type, @Body FetchChildCateglistRequest fetchChildCateglistRequest);

    /*Fetch Child Makes List*/
    @POST("make")
    Call<FetchChildMakeslistRequestResponse> fetchallchildmakelistResponseCall(@Header("Content-Type") String type, @Body FetchChildMakeslistRequest fetchChildMakeslistRequest);

    /*Fetch Product based on Brand*/
    @POST("product")
    Call<ProductListResponse> fetchallprodbasedonbrandlistResponseCall(@Header("Content-Type") String type, @Body FetchProductBasedOnBrandRequest fetchProductBasedOnBrandRequest);

    /*Fetch Product based on category*/
    @POST("product")
    Call<ProductListResponse> fetchallprodbasedonCatlistResponseCall(@Header("Content-Type") String type, @Body FetchProductBasedOnCatRequest fetchProductBasedOnCatRequest);

    /*Fetch Product based on Make*/
    @POST("product")
    Call<ProductListResponse> fetchallprodbasedonmakelistResponseCall(@Header("Content-Type") String type, @Body FetchProductBasedOnMakeRequest fetchProductBasedOnMakeRequest);

    /*add wishlist*/
    @POST("wishlist")
    Call<WishlistSuccessResponse> wishlistaddResponseCall(@Header("Content-Type") String type, @Body AddWishistRequest addWishistRequest);

    /*show wishlist*/
    @POST("wishlist")
    Call<WishlistSuccessResponse> showwishlistResponseCall(@Header("Content-Type") String type, @Body ShowWishistRequest showWishistRequest);

    /*delete wishlist*/
    @POST("wishlist")
    Call<WishlistSuccessResponse> deletewishlistResponseCall(@Header("Content-Type") String type, @Body RemoveWishistRequest removeWishistRequest);

    /*show cart*/
    @POST("cart")
    Call<ShowCartListResponse> showcartlistResponseCall(@Header("Content-Type") String type, @Body ShowCartListRequest showCartListRequest);

    /*delete cart*/
    @POST("cart")
    Call<ShowCartListResponse> deletecartlistResponseCall(@Header("Content-Type") String type, @Body DeleteCartListRequest deleteCartListRequest);

}
