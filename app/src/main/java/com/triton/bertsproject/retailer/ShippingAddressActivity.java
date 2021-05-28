package com.triton.bertsproject.retailer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.adapter.ChildCategoriesListAdapter;
import com.triton.bertsproject.adapter.ShippingaddrListAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.requestpojo.DeleteAddressListRequest;
import com.triton.bertsproject.requestpojo.UserAddressListRequest;
import com.triton.bertsproject.responsepojo.UserAddressListResponse;
import com.triton.bertsproject.responsepojo.UserAddressListResponse;
import com.triton.bertsproject.responsepojo.DeleteAddressListResponse;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.GridSpacingItemDecoration;
import com.triton.bertsproject.utils.RestUtils;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippingAddressActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_addrlist)
    RecyclerView rv_addrlist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_addnewaddr)
    Button btn_addnewaddr;

    AlertDialog alertDialog;

    private static final String TAG = "ShippingAddressActivity";

//    private DD4YouNetReceiver dd4YouNetReceiver;

    private DD4YouConfig dd4YouConfig;

    List<UserAddressListResponse.DataBean.AddressBean> addressBeanList ;

    SessionManager sessionManager;

    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_address);

        ButterKnife.bind(this);

        Log.w("oncreate",TAG);

        dd4YouConfig = new DD4YouConfig(this);

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getProfileDetails();

        if(sessionManager.isLoggedIn()) {

            userid = user.get(SessionManager.KEY_ID);

            Log.w(TAG,"user_id "+userid);
        }

        txt_toolbar_title.setText(R.string.ship_addr);

        btn_addnewaddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ShippingAddressActivity.this,ShippingAddressAddActivity.class));

            }
        });


        img_back.setOnClickListener(v -> {

            onBackPressed();

        });

        spin_kit_loadingView.setVisibility(View.GONE);

    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(ShippingAddressActivity.this,RetailerCartActivity.class);
        startActivity(intent);
        finish();
    }

    @SuppressLint("LongLogTag")
    private void fetchalladdressListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<UserAddressListResponse> call = apiInterface.useraddressListResponseCall(RestUtils.getContentType(),UserAddressListRequest());
        Log.w(TAG,"UserAddressListResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<UserAddressListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<UserAddressListResponse> call, @NonNull Response<UserAddressListResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"UserAddressListResponse" + new Gson().toJson(response.body()));

                        addressBeanList = response.body().getData().getAddress();

                        if(addressBeanList != null && addressBeanList.size()>0){

                            rv_addrlist.setVisibility(View.VISIBLE);

                            txt_no_records.setVisibility(View.GONE);

                            setView(addressBeanList);
                        }

                        else {

                            rv_addrlist.setVisibility(View.GONE);

                            txt_no_records.setVisibility(View.VISIBLE);

                            txt_no_records.setText(R.string.shipaddr_dzis_msg);
                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }



                }

            }


            @Override
            public void onFailure(@NonNull Call<UserAddressListResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"UserAddressListResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private UserAddressListRequest UserAddressListRequest() {


        /**
         * USER_ID : 541
         * MODE : LIST
         */

        UserAddressListRequest UserAddressListRequest = new UserAddressListRequest();
        UserAddressListRequest.setUSER_ID(userid);
        UserAddressListRequest.setMODE("LIST");

        Log.w(TAG,"UserAddressListRequest "+ new Gson().toJson(UserAddressListRequest));
        return UserAddressListRequest;
    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(ShippingAddressActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(ShippingAddressActivity.this,ShippingAddressActivity.class));
            finish();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setView(List<UserAddressListResponse.DataBean.AddressBean> addressBeanList) {


        rv_addrlist.setLayoutManager(new LinearLayoutManager(ShippingAddressActivity.this));

        rv_addrlist.setMotionEventSplittingEnabled(false);

        rv_addrlist.setNestedScrollingEnabled(true);

        int size =addressBeanList.size();

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        rv_addrlist.setItemAnimator(new DefaultItemAnimator());

        ShippingaddrListAdapter shippingaddrListAdapter = new ShippingaddrListAdapter(ShippingAddressActivity.this, addressBeanList);

        rv_addrlist.setAdapter(shippingaddrListAdapter);


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    //    private void registerBroadcastReceiver() {
//        if (dd4YouNetReceiver == null)
//            dd4YouNetReceiver = new DD4YouNetReceiver(rl_root,1000);
//        dd4YouNetReceiver.register(this.getApplicationContext());
//    }
//    private void unregisterBroadcastReceiver() {
//        if (dd4YouNetReceiver != null)        {
//            dd4YouNetReceiver.unregister(this.getApplicationContext());
//        }
//    }
    @Override
    protected void onPause() {
        super.onPause();
//        unregisterBroadcastReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dd4YouConfig.isInternetConnectivity()) {

            fetchalladdressListResponseCall();

        }else {
            callnointernet();

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // unregisterBroadcastReceiver();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ShippingAddressActivity.this);
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
    private void deleteaddrListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<DeleteAddressListResponse> call = apiInterface.deleteaddressResponseCall(RestUtils.getContentType(), DeleteAddressListRequest());
        Log.w(TAG,"DeleteAddressListResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<DeleteAddressListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<DeleteAddressListResponse> call, @NonNull Response<DeleteAddressListResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "DeleteAddressListResponse" + new Gson().toJson(response.body()));



                        }

                        else {


                        showErrorLoading(response.body().getMessage());
                        }


                }



            }


            @Override
            public void onFailure(@NonNull Call<DeleteAddressListResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"DeleteAddressListResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private DeleteAddressListRequest DeleteAddressListRequest() {

        /*
         * ADDRESS_ID : 352
         * MODE : DELETE
         */



        DeleteAddressListRequest DeleteAddressListRequest = new DeleteAddressListRequest();
        DeleteAddressListRequest.setADDRESS_ID("352");
        DeleteAddressListRequest.setMODE("DELETE");

        Log.w(TAG,"DeleteAddressListRequest "+ new Gson().toJson(DeleteAddressListRequest));
        return DeleteAddressListRequest;
    }


}