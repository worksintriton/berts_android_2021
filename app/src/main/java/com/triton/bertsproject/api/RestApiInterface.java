package com.triton.bertsproject.api;


import com.triton.bertsproject.requestpojo.FetchChildCateglistRequest;
import com.triton.bertsproject.requestpojo.FetchChildMakeslistRequest;
import com.triton.bertsproject.requestpojo.LoginRequest;
import com.triton.bertsproject.requestpojo.SignupRequest;
import com.triton.bertsproject.requestpojo.UpdateProfileRequest;
import com.triton.bertsproject.responsepojo.FetchAllBrandsResponse;
import com.triton.bertsproject.responsepojo.FetchAllParentCategoriesResponse;
import com.triton.bertsproject.responsepojo.FetchAllParentMakesResponse;
import com.triton.bertsproject.responsepojo.FetchChildCateglistResponse;
import com.triton.bertsproject.responsepojo.FetchChildMakeslistRequestResponse;
import com.triton.bertsproject.responsepojo.LoginResponse;
import com.triton.bertsproject.responsepojo.SignupResponse;
import com.triton.bertsproject.responsepojo.UpdateProfileResponse;

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


}
