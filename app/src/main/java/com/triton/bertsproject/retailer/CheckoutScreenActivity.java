package com.triton.bertsproject.retailer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.retailer.CheckoutScreenActivity;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.customView.CustomEditText;
import com.triton.bertsproject.requestpojo.OrderCreateRequest;
import com.triton.bertsproject.responsepojo.OrderCreateResponse;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.RestUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutScreenActivity extends AppCompatActivity {

    private static final String TAG = "CheckoutScreenActivity";

    Context context = CheckoutScreenActivity.this;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_proceed)
    LinearLayout ll_proceed;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    DD4YouConfig dd4YouConfig;

    AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_screen);

        ButterKnife.bind(this);

        txt_toolbar_title.setText(R.string.login);

        spin_kit_loadingView.setVisibility(View.GONE);

        Log.w("Oncreate", TAG);

        dd4YouConfig = new DD4YouConfig(this);

        ll_proceed.setOnClickListener(v -> {

            if (dd4YouConfig.isInternetConnectivity()) {

                OrderCreateResponseCall();

            }

            else
            {
                callnointernet();

            }
        });

    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(CheckoutScreenActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(CheckoutScreenActivity.this, CheckoutScreenActivity.class));
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @SuppressLint("LongLogTag")
    private void OrderCreateResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<OrderCreateResponse> call = apiInterface.orderCreateListResponseCall(RestUtils.getContentType(),OrderCreateRequest());
        Log.w(TAG,"OrderCreateResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<OrderCreateResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<OrderCreateResponse> call, @NonNull Response<OrderCreateResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "OrderCreateResponse" + new Gson().toJson(response.body()));

                        startActivity(new Intent(CheckoutScreenActivity.this, RetailerDashboardActivity.class));

                    }

                    else {

                        Toast.makeText(CheckoutScreenActivity.this,""+response.body().getMessage(),Toast.LENGTH_LONG).show();

                    }

                }



            }


            @Override
            public void onFailure(@NonNull Call<OrderCreateResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"OrderCreateResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private OrderCreateRequest OrderCreateRequest() {


        /*
         * MODE : SAVE
         * USER_ID : 541
         * ADDRESS_ID : 351
         * PAYMENT_METHOD : Offline Payment
         */

        OrderCreateRequest OrderCreateRequest = new OrderCreateRequest();
        OrderCreateRequest.setMODE("SAVE");
        OrderCreateRequest.setUSER_ID("541");
        OrderCreateRequest.setADDRESS_ID("351");
        OrderCreateRequest.setPAYMENT_METHOD("Offline Payment");

        Log.w(TAG,"OrderCreateRequest "+ new Gson().toJson(OrderCreateRequest));
        return OrderCreateRequest;
    }

    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CheckoutScreenActivity.this);
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

}