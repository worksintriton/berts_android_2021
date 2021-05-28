package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.activities.LoginActivity;
import com.triton.bertsproject.adapter.CartProductListAdapter;
import com.triton.bertsproject.adapter.MywishListAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.interfaces.CartRemoveProductListener;
import com.triton.bertsproject.model.DeleteCartListRequest;
import com.triton.bertsproject.model.RetailerProductlistModel;
import com.triton.bertsproject.requestpojo.RemoveWishistRequest;
import com.triton.bertsproject.requestpojo.ShowCartListRequest;
import com.triton.bertsproject.requestpojo.ShowWishistRequest;
import com.triton.bertsproject.responsepojo.ShowCartListResponse;
import com.triton.bertsproject.responsepojo.ShowCartListResponse;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.RestUtils;
import com.triton.bertsproject.utils.SwipeToDeleteCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetailerCartActivity extends AppCompatActivity implements CartRemoveProductListener {

    private static final String TAG = "RetailerCartActivity";

    public static String active_tag = "1";

    CartProductListAdapter cartProductListAdapter;

    Context context = RetailerCartActivity.this;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinatorLayout;


    String fromactivity;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_productlist)
    RecyclerView rv_productlist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    String tag;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.btn_proceed)
//    Button btn_proceed;
//
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_proceed)
     LinearLayout ll_proceed;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_shippingaddr)
    CardView cv_shippingaddr;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_shippingcharg)
    CardView cv_shippingcharg;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_deliveryaddrchange)
    TextView txt_deliveryaddrchange;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_shipaddrchange)
    TextView txt_shipaddrchange;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    List<ShowCartListResponse.DataBean.CartBean> cartBeanList ;

    String user_id;

//    private DD4YouNetReceiver dd4YouNetReceiver;

    private DD4YouConfig dd4YouConfig;

    AlertDialog alertDialog;

    SessionManager sessionManager;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_cart);
        ButterKnife.bind( this);
        Log.w("Oncreate", TAG);
        txt_toolbar_title.setText(R.string.cart);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           fromactivity = extras.getString("fromactivity");
        }
        spin_kit_loadingView.setVisibility(View.GONE);
        tag = getIntent().getStringExtra("tag");
        Log.w(TAG, " tag : " + this.tag);

        img_back.setOnClickListener(v -> {

            onBackPressed();

        });


        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getProfileDetails();

//        user_id = user.get(SessionManager.KEY_ID);

        user_id  = "1";


        dd4YouConfig = new DD4YouConfig(this);

        //enableSwipeToDeleteAndUndo();

        if(sessionManager.isLoggedIn()){

            if (dd4YouConfig.isInternetConnectivity()) {

                showcartlistResponseCall();

            }

            else
            {
                callnointernet();

            }
        }

        else {

            showAlert();
        }

        ll_proceed.setVisibility(View.GONE);

        cv_shippingaddr.setVisibility(View.GONE);

        cv_shippingcharg.setVisibility(View.GONE);


    }





    private void setView(List<ShowCartListResponse.DataBean.CartBean> cartBeanList) {

        rv_productlist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        rv_productlist.setMotionEventSplittingEnabled(false);

        rv_productlist.setItemAnimator(new DefaultItemAnimator());

        cartProductListAdapter = new CartProductListAdapter(this, cartBeanList,this);

        rv_productlist.setAdapter(cartProductListAdapter);

    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onBackPressed() {

        if (fromactivity!=null&&!fromactivity.isEmpty()) {

            if(fromactivity.equals("HomeFragment")){

                callDirections("1");

            }

            else {

                Intent intent = new Intent(RetailerCartActivity.this,RetailerDashboardActivity.class);
                startActivity(intent);
            }

        }

    }



    public void callDirections(String tag){
        Intent intent = new Intent(RetailerCartActivity.this,RetailerDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();

    }


    @SuppressLint("LongLogTag")
    private void showcartlistResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ShowCartListResponse> call = apiInterface.showcartlistResponseCall(RestUtils.getContentType(),showCartListRequest());
        Log.w(TAG,"ShowCartListResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<ShowCartListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<ShowCartListResponse> call, @NonNull Response<ShowCartListResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "ShowCartListResponse" + new Gson().toJson(response.body()));

                        cartBeanList=response.body().getData().getCart();

                        if(cartBeanList!=null&&cartBeanList.size()>0){

                            rv_productlist.setVisibility(View.VISIBLE);

                            ll_proceed.setVisibility(View.VISIBLE);

                            cv_shippingaddr.setVisibility(View.VISIBLE);

                            cv_shippingcharg.setVisibility(View.VISIBLE);

                            txt_no_records.setVisibility(View.GONE);

                            txt_deliveryaddrchange.setOnClickListener(v -> {

                                startActivity(new Intent(RetailerCartActivity.this, ShippingAddressActivity.class));

                                Animatoo.animateSwipeRight(context);
                            });

                            txt_shipaddrchange.setOnClickListener(v -> {

                                startActivity(new Intent(RetailerCartActivity.this, ShippingMethodActivity.class));

                                Animatoo.animateSwipeRight(context);
                            });

                            ll_proceed.setOnClickListener(v -> startActivity(new Intent(RetailerCartActivity.this,CheckoutScreenActivity.class)));


                            setView(cartBeanList);

                        }

                        else {

                            rv_productlist.setVisibility(View.GONE);

                            txt_no_records.setVisibility(View.VISIBLE);

                            txt_no_records.setText("No Products Found");
                        }
                    }

                    else {

//                        showErrorLoading(response.body().getMessage());

                        txt_no_records.setVisibility(View.VISIBLE);

                        txt_no_records.setText("No Products Found");
                    }

                }



            }


            @Override
            public void onFailure(@NonNull Call<ShowCartListResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"ShowCartListResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private ShowCartListRequest showCartListRequest() {


        /**
         * USER_ID : 1
         * MODE : LIST
         */

        ShowCartListRequest showCartListRequest = new ShowCartListRequest();
        showCartListRequest.setUSER_ID(user_id);
        showCartListRequest.setMODE("LIST");

        Log.w(TAG,"ShowCartListRequest "+ new Gson().toJson(showCartListRequest));
        return showCartListRequest;
    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(RetailerCartActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(RetailerCartActivity.this,RetailerCartActivity.class));
            finish();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RetailerCartActivity.this);
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

    @SuppressLint("LongLogTag")
    private void deletecartlistResponseCall(String id) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ShowCartListResponse> call = apiInterface.deletecartlistResponseCall(RestUtils.getContentType(), deleteCartListRequest(id));
        Log.w(TAG,"ShowCartListResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<ShowCartListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<ShowCartListResponse> call, @NonNull Response<ShowCartListResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "ShowCartListResponse" + new Gson().toJson(response.body()));

                        Toast.makeText(getApplicationContext(),""+response.body().getMessage(),Toast.LENGTH_LONG).show();

                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }

                }



            }


            @Override
            public void onFailure(@NonNull Call<ShowCartListResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"ShowCartListResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private DeleteCartListRequest deleteCartListRequest(String id) {

        /*
         * USER_ID : 1
         * BASKET_ID : 8
         * MODE : DELETE
         */

        DeleteCartListRequest deleteCartListRequest = new DeleteCartListRequest();
        deleteCartListRequest.setUSER_ID(user_id);
        deleteCartListRequest.setBASKET_ID(id);
        deleteCartListRequest.setMODE("DELETE");

        Log.w(TAG,"RemoveWishistRequest "+ new Gson().toJson(deleteCartListRequest));
        return deleteCartListRequest;
    }

    @Override
    public void removeproductListener(String id) {

        if (dd4YouConfig.isInternetConnectivity()) {

            deletecartlistResponseCall(id);

        }

        else
        {
            callnointernet();

        }


    }
    private void showAlert() {

        new SweetAlertDialog(RetailerCartActivity.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Alert!!")
                .setContentText("Please Login")
                .setCancelText("No")
                .setConfirmText("Yes")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {

                        Intent intent = new Intent(RetailerCartActivity.this,LoginActivity.class);
                        startActivity(intent);
                        sDialog.dismiss();

                    }
                })
                .show();

    }
}
