package com.triton.bertsproject.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.customView.CustomEditText;
import com.triton.bertsproject.requestpojo.LoginRequest;
import com.triton.bertsproject.requestpojo.SignupRequest;
import com.triton.bertsproject.responsepojo.LoginResponse;
import com.triton.bertsproject.responsepojo.SignupResponse;
import com.triton.bertsproject.retailer.RetailerDashboardActivity;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.RestUtils;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    Context context = LoginActivity.this;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_email)
    CustomEditText edt_email;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_password)
    CustomEditText edt_password;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_sigin)
    Button btn_sigin;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_sigin_with_facebook)
    Button btn_sigin_with_facebook;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_forget_password)
    TextView txt_forget_password;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    DD4YouConfig dd4YouConfig;

    Dialog alertDialog;

    String cid,sid,zipcode,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        txt_toolbar_title.setText(R.string.login);

        spin_kit_loadingView.setVisibility(View.GONE);

        Log.w("Oncreate", TAG);

        dd4YouConfig = new DD4YouConfig(this);

        btn_sigin.setOnClickListener(v -> {

            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);
        });

        img_back.setOnClickListener(v -> {

            startActivity(new Intent(LoginActivity.this, RetailerDashboardActivity.class));

            Animatoo.animateSwipeRight(context);

        });

        txt_forget_password.setOnClickListener(v -> {

            startActivity(new Intent(LoginActivity.this,ForgetPasswordActivity.class));

            Animatoo.animateSwipeRight(context);

        });

        edt_email.setTitle(getString(R.string.email));

        edt_password.setTitle(getString(R.string.password));

        btn_sigin.setOnClickListener(v -> checkValidation());

    }

    private void checkValidation() {

        boolean isvalid = true;

        String email = edt_email.edtContent.getText().toString();

        String password = edt_password.edtContent.getText().toString();

        if(edt_email.edtContent.getText().toString().equals("") || !isValidEmail(edt_email.edtContent.getText().toString() )){

            isvalid = false;

            edt_email.setError("Please Enter Valid Mail ID");

        }

        else if(edt_password.edtContent.getText().toString().equals("")){

            isvalid = false;

            edt_password.setError("Please Enter Valid Password");

        }

        if(isvalid){

            if (dd4YouConfig.isInternetConnectivity()) {

                loginResponseCall(email,password);

            }

            else
            {
                callnointernet();

            }



        }

    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(LoginActivity.this, LoginActivity.class));
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @SuppressLint("LongLogTag")
    private void loginResponseCall(String email, String password) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<LoginResponse> call = apiInterface.loginResponseCall(RestUtils.getContentType(),loginRequest(email,password));
        Log.w(TAG,"LoginResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<LoginResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "LoginResponse" + new Gson().toJson(response.body()));

//                        if(response.body().getData().getProfile().getCountry_id()!=null){
//
//                            cid = response.body().getData().getProfile().getCountry_id().toString();
//
//                        }
//
//                        else {
//
//                            cid="";
//                        }
//
//                        if(response.body().getData().getProfile().getState_id()!=null){
//
//                            sid = response.body().getData().getProfile().getState_id().toString();
//
//                        }
//
//                        else {
//
//                            sid="";
//                        }
//
//                        if(response.body().getData().getProfile().getState_id()!=null){
//
//                            String cid = response.body().getData().getProfile().getState_id().toString();
//
//                        }
//
//                        if(response.body().getData().getProfile().getCountry_id()!=null){
//
//                            String cid = response.body().getData().getProfile().getCountry_id().toString();
//
//                        }

                        SessionManager sessionManager = new SessionManager(LoginActivity.this);
                        sessionManager.setIsLogin(true);
                        sessionManager.createLoginSession(
                                response.body().getData().getProfile().getId(),
                                response.body().getData().getProfile().getFirst_name(),
                                response.body().getData().getProfile().getLast_name(),
                                response.body().getData().getProfile().getEmail(),
                                response.body().getData().getProfile().getUser_type(),
                                response.body().getData().getProfile().getAvatar()

                        );

                        startActivity(new Intent(LoginActivity.this, RetailerDashboardActivity.class));

                    }

                    else {

                        Toast.makeText(LoginActivity.this,""+response.body().getMessage(),Toast.LENGTH_LONG).show();

                    }

                }



            }


            @Override
            public void onFailure(@NonNull Call<LoginResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"LoginResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private LoginRequest loginRequest(String email, String password) {

        /*
         * email : prabhu.imsc@gmail.com
         * password : testc1234
         */

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        Log.w(TAG,"LoginRequest "+ new Gson().toJson(loginRequest));
        return loginRequest;
    }

    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
        alertDialogBuilder.setMessage(errormesage);
        alertDialogBuilder.setPositiveButton("ok",
                (arg0, arg1) -> hideLoading());


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void hideLoading(){
        try {
            alertDialog.dismiss();
        }catch (Exception ignored){

        }
    }


    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {

        // super.onBackPressed(); commented this line in order to disable back press
        //Write your code here
        startActivity(new Intent(LoginActivity.this, RetailerDashboardActivity.class));

        Animatoo.animateSwipeRight(context);
    }
}