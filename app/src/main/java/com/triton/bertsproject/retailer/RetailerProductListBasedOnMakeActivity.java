package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.activities.LoginActivity;
import com.triton.bertsproject.adapter.RetailerProductListAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.interfaces.WishlistAddProductListener;
import com.triton.bertsproject.model.RetailerProductlistModel;
import com.triton.bertsproject.requestpojo.AddWishistRequest;
import com.triton.bertsproject.requestpojo.FetchProductBasedOnBrandRequest;
import com.triton.bertsproject.requestpojo.FetchProductBasedOnMakeRequest;
import com.triton.bertsproject.requestpojo.RemoveWishistRequest;
import com.triton.bertsproject.responsepojo.ProductListResponse;
import com.triton.bertsproject.responsepojo.WishlistSuccessResponse;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.GridSpacingItemDecoration;
import com.triton.bertsproject.utils.RestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("LongLogTag")
public class RetailerProductListBasedOnMakeActivity extends AppCompatActivity implements WishlistAddProductListener {

    Context context = RetailerProductListBasedOnMakeActivity.this;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rlList)
    LinearLayout rlList;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rlGrid)
    LinearLayout rlGrid;

//    @SuppressLint({"NonConstantResourceId", "UseSwitchCompatOrMaterialCode"})
//    @BindView(R.id.switch1)
//    Switch Switch;
//
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.edt_search)
//    EditText edt_search;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_productlist)
    RecyclerView rv_prodlist;

    private final static String TAG = "RetailerProductListActivity";

    String fromactivity;

    List<RetailerProductlistModel> retailerProductlistModels;

    List<ProductListResponse.DataBean.PrdouctsBean> prdouctsBeanList ;

    String brand_id,make_id,model_id,model_name;

//    private DD4YouNetReceiver dd4YouNetReceiver;

    private DD4YouConfig dd4YouConfig;

    AlertDialog alertDialog;

    String user_id;

//    private DD4YouNetReceiver dd4YouNetReceiver;

    SessionManager sessionManager;

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_product_list_makebased);

        ButterKnife.bind(this);

        Log.w("Oncreate", TAG);

        retailerProductlistModels = new ArrayList<>();

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            fromactivity = extras.getString("fromactivity");

            brand_id = extras.getString("brand_id");

            make_id = extras.getString("make_id");

            model_id = extras.getString("model_id");

            model_name = extras.getString("model_name");

            Log.w(TAG,"make_id : "+make_id + "model_id :" +model_id

                    + "model_name : "+model_name+

                    "fromactivity :" +TAG);




        }

        dd4YouConfig = new DD4YouConfig(this);

        //registerBroadcastReceiver();

        sessionManager=new SessionManager(this);

        if(sessionManager.isLoggedIn()){

            HashMap<String, String> user = sessionManager.getProfileDetails();

            user_id = user.get(SessionManager.KEY_ID);
        }

        else {

            user_id  = "";

        }

        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallproductsListResponseCall();

        }

        else
        {
            callnointernet();

        }

        spin_kit_loadingView.setVisibility(View.GONE);

        if(model_name!=null&&!model_name.isEmpty()){

            txt_toolbar_title.setText(model_name);
        }



        img_back.setOnClickListener(v -> {

            startActivity(new Intent(RetailerProductListBasedOnMakeActivity.this, RetailerDashboardActivity.class));

            Animatoo.animateSwipeLeft(context);
        });

        rlList.setOnClickListener(v -> {

            rlList.setBackgroundResource(R.drawable.bg_cycler_blue);

            rlGrid.setBackgroundResource(R.color.transparent);

            setlistView(prdouctsBeanList);
        });


        rlGrid.setOnClickListener(v -> {

            rlGrid.setBackgroundResource(R.drawable.bg_cycler_blue);

            rlList.setBackgroundResource(R.color.transparent);

            setGridView(prdouctsBeanList);
        });


    }

    @SuppressLint("LongLogTag")
    private void fetchallproductsListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ProductListResponse> call = apiInterface.fetchallprodbasedonmakelistResponseCall(RestUtils.getContentType(),fetchProductBasedOnMakeRequest());
        Log.w(TAG,"ProductListResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<ProductListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<ProductListResponse> call, @NonNull Response<ProductListResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"ProductListResponse" + new Gson().toJson(response.body()));

                        prdouctsBeanList = response.body().getData().getPrdoucts();

                        if(prdouctsBeanList != null && prdouctsBeanList.size()>0){


                            rv_prodlist.setVisibility(View.VISIBLE);

                            txt_no_records.setVisibility(View.GONE);

                            setGridView(prdouctsBeanList);
                        }

                        else {


                            rv_prodlist.setVisibility(View.GONE);

                            txt_no_records.setVisibility(View.VISIBLE);

                            txt_no_records.setText(R.string.no_prod_found);
                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }



                }

            }


            @Override
            public void onFailure(@NonNull Call<ProductListResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"ProductListResponse flr"+t.getMessage());
            }
        });



    }

    @SuppressLint("LongLogTag")
    private FetchProductBasedOnMakeRequest fetchProductBasedOnMakeRequest() {


        /*
         * MAKE_ID : 1
         * MODEL_ID : 1
         * MODE : LIST
         * USER_ID :
         */


        FetchProductBasedOnMakeRequest fetchProductBasedOnMakeRequest = new FetchProductBasedOnMakeRequest();
        fetchProductBasedOnMakeRequest.setMAKE_ID(make_id);
        fetchProductBasedOnMakeRequest.setMODEL_ID(model_id);
        fetchProductBasedOnMakeRequest.setMODE("LIST");
        fetchProductBasedOnMakeRequest.setUSER_ID(user_id);

        Log.w(TAG,"FetchProductBasedOnMakeRequest "+ new Gson().toJson(fetchProductBasedOnMakeRequest));
        return fetchProductBasedOnMakeRequest;
    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(RetailerProductListBasedOnMakeActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(RetailerProductListBasedOnMakeActivity.this, RetailerProductListBasedOnMakeActivity.class));
            finish();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setGridView(List<ProductListResponse.DataBean.PrdouctsBean> prdouctsBeanList) {


        rv_prodlist.setLayoutManager(new GridLayoutManager(RetailerProductListBasedOnMakeActivity.this, 2));

        rv_prodlist.setMotionEventSplittingEnabled(false);

        rv_prodlist.setNestedScrollingEnabled(true);

        int size =prdouctsBeanList.size();

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        rv_prodlist.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, true));

        rv_prodlist.setItemAnimator(new DefaultItemAnimator());

        RetailerProductListAdapter retailerProductListAdapter = new RetailerProductListAdapter(RetailerProductListBasedOnMakeActivity.this, prdouctsBeanList, false,this);

        rv_prodlist.setAdapter(retailerProductListAdapter);



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
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RetailerProductListBasedOnMakeActivity.this);
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

    private void setlistView(List<ProductListResponse.DataBean.PrdouctsBean> prdouctsBeanList) {

        rv_prodlist.setLayoutManager(new LinearLayoutManager(RetailerProductListBasedOnMakeActivity.this,LinearLayoutManager.VERTICAL,false));

        rv_prodlist.setMotionEventSplittingEnabled(false);

        //int size =3;

        rv_prodlist.setItemAnimator(new DefaultItemAnimator());

        RetailerProductListAdapter retailerProductListAdapter = new RetailerProductListAdapter(RetailerProductListBasedOnMakeActivity.this, prdouctsBeanList,true,this);

        rv_prodlist.setAdapter(retailerProductListAdapter);


    }


    @SuppressLint("LongLogTag")
    private void wishlistaddResponseCall(String productId) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<WishlistSuccessResponse> call = apiInterface.wishlistaddResponseCall(RestUtils.getContentType(),addWishistRequest(productId));
        Log.w(TAG,"WishlistSuccessResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<WishlistSuccessResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<WishlistSuccessResponse> call, @NonNull Response<WishlistSuccessResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(response.body().getStatus().equals("Success")) {

                        Log.w(TAG, "WishlistSuccessResponse" + new Gson().toJson(response.body()));

                        Toast.makeText(getApplicationContext(),""+response.body().getMessage(),Toast.LENGTH_LONG).show();

                        fetchallproductsListResponseCall();

                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }

                }



            }


            @Override
            public void onFailure(@NonNull Call<WishlistSuccessResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"WishlistSuccessResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private AddWishistRequest addWishistRequest(String productID) {

        /*
         * USER_ID : 541
         * PRODUCT_ID : 4
         * MODE : ADD_DELETE
         */

        AddWishistRequest addWishistRequest = new AddWishistRequest();
        addWishistRequest.setPRODUCT_ID(productID);
        addWishistRequest.setUSER_ID(user_id);
        addWishistRequest.setMODE("ADD_DELETE");

        Log.w(TAG,"AddWishistRequest "+ new Gson().toJson(addWishistRequest));
        return addWishistRequest;
    }


    @Override
    public void addproductListener(String id) {


        if(sessionManager.isLoggedIn()){

            if (dd4YouConfig.isInternetConnectivity()) {

                wishlistaddResponseCall(id);

            }

            else
            {
                callnointernet();

            }

        }

        else {

            showAlert();
        }


    }

    private void showAlert() {

        AlertDialog.Builder builder=new AlertDialog.Builder(RetailerProductListBasedOnMakeActivity.this);
        builder.setTitle("Alert");
        builder.setMessage("Please Login add Products in wishlist");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            startActivity(new Intent(RetailerProductListBasedOnMakeActivity.this, LoginActivity.class));
            finish();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}