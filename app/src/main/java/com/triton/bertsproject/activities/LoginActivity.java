package com.triton.bertsproject.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import com.triton.bertsproject.retailer.ProductDetailDescriptionActivity;
import com.triton.bertsproject.retailer.RetailerCartActivity;
import com.triton.bertsproject.retailer.RetailerDashboardActivity;
import com.triton.bertsproject.retailer.RetailerProductListActivity;
import com.triton.bertsproject.retailer.RetailerProductListBasedOnCategActivity;
import com.triton.bertsproject.retailer.RetailerProductListBasedOnMakeActivity;
import com.triton.bertsproject.retailer.RetailerProfileAccountActivity;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.RestUtils;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
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

    String fromActivty;

    String brand_id,brand_name,parent_id,subcategid,subcategname,make_id,model_id,model_name, prod_id,prod_name;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        txt_toolbar_title.setText(R.string.login);

        spin_kit_loadingView.setVisibility(View.GONE);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            fromActivty = extras.getString("fromActivity");

            prod_id = extras.getString("prod_id");

            //prod_id = "2";

            prod_name = extras.getString("prod_name");

            brand_id = extras.getString("brand_id");

            brand_name = extras.getString("brand_name");

            parent_id = extras.getString("parent_id");

            subcategid = extras.getString("subcategid");

            subcategname = extras.getString("subcategname");

            make_id = extras.getString("make_id");

            model_id = extras.getString("model_id");

            model_name = extras.getString("model_name");

            Log.w(TAG,"brand_id "+brand_id+"brand_name "+ brand_name+"parent_id : "+parent_id+ "subcategid :" +subcategid

                    + "subcategname : "+subcategname +

                   "make_id : "+make_id + "model_id :" +model_id

                    + "model_name : "+model_name+

                    "fromactivity :" +fromActivty);


        }

        Log.w("Oncreate ", TAG + "fromActivity " +fromActivty);

        dd4YouConfig = new DD4YouConfig(this);

        btn_sigin.setOnClickListener(v -> {

            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

            intent.putExtra("fromActivity",TAG);

            startActivity(intent);
        });

        img_back.setOnClickListener(v -> {

            onBackPressed();

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

                        SessionManager sessionManager = new SessionManager(LoginActivity.this);
                        sessionManager.setIsLogin(true);
                        sessionManager.createLoginSession(
                                response.body().getData().getProfile().getId(),
                                response.body().getData().getProfile().getFirst_name(),
                                response.body().getData().getProfile().getLast_name(),
                                response.body().getData().getProfile().getEmail(),
                                response.body().getData().getProfile().getUser_type(),
                                response.body().getData().getProfile().getAvatar(),
                                response.body().getData().getProfile().getCountry_id(),
                                response.body().getData().getProfile().getState_id(),
                                response.body().getData().getProfile().getZip_code(),
                                response.body().getData().getProfile().getRevenue()

                        );


                        Toasty.success(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                        onBackPressed();

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

    public void callDirections(String tag){
        Intent intent = new Intent(LoginActivity.this,RetailerDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed() {

        // super.onBackPressed(); commented this line in order to disable back press
        //Write your code here
       if(fromActivty!=null){
           if(fromActivty.equals("HomeFragment")){

               callDirections("1");

           }
           else if(fromActivty.equals("MyGarageFragment")){

               callDirections("2");

           }
           else if(fromActivty.equals("ShopFragment")){

               callDirections("3");

           }

           else if(fromActivty.equals("ProfileFragment")){

               callDirections("5");
           }

           else if(fromActivty.equals("RetailerProductListActivity")){

               Intent intent = new Intent(LoginActivity.this, RetailerProductListActivity.class);

               intent.putExtra("brand_id",brand_id);

               intent.putExtra("brand_name",brand_name);

               intent.putExtra("fromActivity",TAG);

               startActivity(intent);

           }

           else if(fromActivty.equals("RetailerProductListBasedOnCategActivity")){

               Intent intent = new Intent(LoginActivity.this, RetailerProductListBasedOnCategActivity.class);

               intent.putExtra("parent_id",parent_id);

               intent.putExtra("subcategid",subcategid);

               intent.putExtra("subcategname",subcategname);

               intent.putExtra("fromActivity",TAG);

               startActivity(intent);
           }

           else if(fromActivty.equals("RetailerProductListBasedOnMakeActivity")){

               Intent intent = new Intent(LoginActivity.this, RetailerProductListBasedOnMakeActivity.class);

               intent.putExtra("make_id",make_id);

               intent.putExtra("model_id", model_id);

               intent.putExtra("model_id",model_name);

               intent.putExtra("fromActivity",TAG);

               startActivity(intent);
           }

           else if(fromActivty.equals("ProductDetailDescriptionActivity")){

               Intent intent = new Intent(LoginActivity.this, ProductDetailDescriptionActivity.class);

               intent.putExtra("prod_id",prod_id);

               intent.putExtra("prod_name",prod_name);

               intent.putExtra("brand_id",brand_id);

               intent.putExtra("brand_name",brand_name);

               intent.putExtra("parent_id",parent_id);

               intent.putExtra("subcategid",subcategid);

               intent.putExtra("subcategname",subcategname);

               intent.putExtra("make_id",make_id);

               intent.putExtra("model_id", model_id);

               intent.putExtra("model_id",model_name);

               intent.putExtra("fromActivity",TAG);


               startActivity(intent);
           }

           else if(fromActivty.equals("RetailerCartActivity")){

               Intent intent = new Intent(LoginActivity.this, RetailerCartActivity.class);

               startActivity(intent);
           }

           else {

               Intent intent = new Intent(LoginActivity.this, RetailerDashboardActivity.class);

               startActivity(intent);
           }
       }

       else {

           Intent intent = new Intent(LoginActivity.this, RetailerDashboardActivity.class);

           startActivity(intent);
       }
    }
}