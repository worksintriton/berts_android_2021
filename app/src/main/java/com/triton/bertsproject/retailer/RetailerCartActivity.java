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
import com.triton.bertsproject.activities.RegisterActivity;
import com.triton.bertsproject.adapter.CartProductListAdapter;
import com.triton.bertsproject.adapter.MywishListAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.interfaces.AddProductListener;
import com.triton.bertsproject.interfaces.CartRemoveProductListener;
import com.triton.bertsproject.interfaces.RemoveProductListener;
import com.triton.bertsproject.model.DeleteCartListRequest;
import com.triton.bertsproject.model.RetailerProductlistModel;
import com.triton.bertsproject.requestpojo.AddToCartRequest;
import com.triton.bertsproject.requestpojo.RemoveWishistRequest;
import com.triton.bertsproject.requestpojo.RemovefromCartRequest;
import com.triton.bertsproject.requestpojo.ShowCartListRequest;
import com.triton.bertsproject.requestpojo.ShowWishistRequest;
import com.triton.bertsproject.responsepojo.AddToCartResponse;
import com.triton.bertsproject.responsepojo.RemovefromCartResponse;
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
import es.dmoral.toasty.Toasty;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetailerCartActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, CartRemoveProductListener, AddProductListener, RemoveProductListener {

    private static final String TAG = "RetailerCartActivity";

    public static String active_tag = "1";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;

    CartProductListAdapter cartProductListAdapter;

    Context context = RetailerCartActivity.this;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinatorLayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

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
    @BindView(R.id.txt_deliveryaddrchange)
    TextView txt_deliveryaddrchange;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_shipaddrchange)
    TextView txt_shipaddrchange;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_shipping)
    CardView cv_shipping;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_shipcharge)
    CardView cv_shipcharge;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_price)
    CardView cv_price;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_order_total)
    TextView txt_order_total;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_deliveryaddrtype)
    TextView txt_deliveryaddrtype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_deliveryaddr)
    TextView txt_deliveryaddr;

    List<ShowCartListResponse.DataBean.CartBean> cartBeanList ;

    ShowCartListResponse.DataBean.DefaultAddressBean defaultAddressBean;

    String user_id;

//    private DD4YouNetReceiver dd4YouNetReceiver;

    private DD4YouConfig dd4YouConfig;

    AlertDialog alertDialog;

    SessionManager sessionManager;

    String brand_id,brand_name,parent_id,subcategid,subcategname,make_id,model_id,model_name;

    String prod_id,prod_name;

    String addr_name,address1,state_name,country_name;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_cart);
        ButterKnife.bind( this);
        Log.w("Oncreate", TAG);
        txt_toolbar_title.setText(R.string.cart);
        floatingActionButton.setImageResource(R.drawable.berts_logo_fb);

        Log.w("Oncreate", TAG);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            fromactivity = extras.getString("fromActivity");

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

            Log.w(TAG,"brand_id : "+brand_id + "brand_name : "+brand_name+"parent_id : "+parent_id+ "subcategid :" +subcategid

                    + "subcategname : "+subcategname + "make_id : "+make_id + "model_id :" +model_id

                    + "model_name : "+model_name);

        }

        spin_kit_loadingView.setVisibility(View.GONE);
        tag = getIntent().getStringExtra("tag");
        Log.w(TAG, " tag : " + this.tag);
        bottomNavigation.setSelectedItemId(R.id.shop);
        bottomNavigation.setOnNavigationItemSelectedListener(this);

        img_back.setOnClickListener(v -> {

            onBackPressed();

        });

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getProfileDetails();

        user_id = user.get(SessionManager.KEY_ID);

        dd4YouConfig = new DD4YouConfig(this);

        //enableSwipeToDeleteAndUndo();

        cv_shipping.setVisibility(View.GONE);

        cv_shipcharge.setVisibility(View.GONE);

        ll_proceed.setVisibility(View.GONE);

        cv_price.setVisibility(View.GONE);

        if(sessionManager.isLoggedIn()){

            if (dd4YouConfig.isInternetConnectivity()) {

                showcartlistResponseCall();

            }

            else
            {
                callnointernet();

            }
        }


        else
        {
            showAlert();

        }


    }

    private void setView(List<ShowCartListResponse.DataBean.CartBean> cartBeanList) {

        rv_productlist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        rv_productlist.setMotionEventSplittingEnabled(false);

        rv_productlist.setItemAnimator(new DefaultItemAnimator());

        cartProductListAdapter = new CartProductListAdapter(this, cartBeanList,this,this,this);

        rv_productlist.setAdapter(cartProductListAdapter);

    }

    @SuppressLint("NonConstantResourceId")
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.chat:
                active_tag = "4";
                return true;
            case R.id.garage:
                active_tag = "2";
                return true;
            case R.id.home:
                active_tag = "1";
                return true;
            case R.id.profile:
                active_tag = "5";
                return true;
            case R.id.shop:
                active_tag = "3";
                return true;
            default:
                return false;
        }
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

        if(fromactivity!=null){

            if(fromactivity.equals("HomeFragment")){

                Intent intent = new Intent(RetailerCartActivity.this, RetailerDashboardActivity.class);
                intent.putExtra("fromActivity",TAG);
                startActivity(intent);
                finish();
            }

            else if(fromactivity.equals("ProductDetailDescriptionActivity")){

                Intent intent = new Intent(RetailerCartActivity.this, ProductDetailDescriptionActivity.class);
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

                startActivity(intent);

                finish();
            }
            else {
                Intent intent = new Intent(RetailerCartActivity.this, RetailerDashboardActivity.class);
                intent.putExtra("fromActivity",TAG);
                startActivity(intent);
                finish();

            }
        }
        else {
            Intent intent = new Intent(RetailerCartActivity.this, RetailerDashboardActivity.class);
            intent.putExtra("fromActivity",TAG);
            startActivity(intent);
            finish();

        }
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

                            cv_shipping.setVisibility(View.VISIBLE);

                            cv_shipcharge.setVisibility(View.VISIBLE);

                            ll_proceed.setVisibility(View.VISIBLE);

                            rv_productlist.setVisibility(View.VISIBLE);

                            cv_price.setVisibility(View.VISIBLE);

                            if(response.body().getData().getCart_total()!=0){

                                txt_order_total.setText(""+response.body().getData().getCart_total());
                            }

                            txt_no_records.setVisibility(View.GONE);

                            defaultAddressBean = response.body().getData().getDefault_address();

                            if(defaultAddressBean!=null&&defaultAddressBean.getId()!=null){

                                if(defaultAddressBean.getAddress1()!=null&&!defaultAddressBean.getAddress1().isEmpty()){

                                    address1 = defaultAddressBean.getAddress1();
                                }
                                else {

                                    address1="";
                                }

                                if(defaultAddressBean.getAddress1()!=null&&!defaultAddressBean.getAddress1().isEmpty()){

                                    state_name = defaultAddressBean.getState_name();
                                }
                                else {

                                    state_name="";
                                }

                                if(defaultAddressBean.getAddress1()!=null&&!defaultAddressBean.getAddress1().isEmpty()){

                                    country_name = defaultAddressBean.getCountry_name();
                                }

                                else {

                                    country_name="";
                                }

                                txt_deliveryaddr.setText(address1 + " "+state_name+ " "+country_name);

                                if(defaultAddressBean.getName()!=null&&!defaultAddressBean.getName().isEmpty()){

                                    addr_name = defaultAddressBean.getName();
                                }

                                else {

                                    addr_name="";
                                }


                                txt_deliveryaddrtype.setText(addr_name);

                            }

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

                            cv_shipping.setVisibility(View.GONE);

                            cv_shipcharge.setVisibility(View.GONE);

                            ll_proceed.setVisibility(View.GONE);

                            rv_productlist.setVisibility(View.GONE);

                            txt_no_records.setVisibility(View.VISIBLE);

                            cv_price.setVisibility(View.GONE);

                            txt_no_records.setText("No Products Found");
                        }
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
    private ShowCartListRequest showCartListRequest() {


        /*
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

                        if (dd4YouConfig.isInternetConnectivity()) {

                            showcartlistResponseCall();

                        }

                        else
                        {
                            callnointernet();

                        }

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

    @Override
    public void addproductListener(String prod_id, String quantity, String unit_price) {

        addcartlistResponseCall(prod_id,quantity,unit_price);

    }

    @Override
    public void removeproductListener(String prod_id, String quantity, String unit_price) {

        removecartlistResponseCall(prod_id,quantity,unit_price);

    }

    @SuppressLint("LongLogTag")
    private void addcartlistResponseCall(String prod_id, String quantity, String unit_price) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AddToCartResponse> call = apiInterface.addcartlistResponseCall(RestUtils.getContentType(),AddToCartRequest(prod_id,quantity,unit_price));
        Log.w(TAG,"AddToCartResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<AddToCartResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<AddToCartResponse> call, @NonNull Response<AddToCartResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "AddToCartResponse" + new Gson().toJson(response.body()));

                        Toasty.success(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                        if (dd4YouConfig.isInternetConnectivity()) {

                            showcartlistResponseCall();

                        }

                        else
                        {
                            callnointernet();

                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }

                }

            }


            @Override
            public void onFailure(@NonNull Call<AddToCartResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"AddToCartResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private AddToCartRequest AddToCartRequest(String prod_id, String quantity, String unit_price) {


        /*
         * USER_ID : 541
         * PRODUCT_ID : 2
         * QUANTITY : 1
         * UNIT_PRICE : 50000
         * MODE : ADDTOCART
         */

        AddToCartRequest AddToCartRequest = new AddToCartRequest();
        AddToCartRequest.setUNIT_PRICE(unit_price);
        AddToCartRequest.setQUANTITY(quantity);
        AddToCartRequest.setPRODUCT_ID(prod_id);
        AddToCartRequest.setUSER_ID(user_id);
        AddToCartRequest.setMODE("ADDTOCART");

        Log.w(TAG,"AddToCartRequest "+ new Gson().toJson(AddToCartRequest));
        return AddToCartRequest;
    }

    @SuppressLint("LongLogTag")
    private void removecartlistResponseCall(String prod_id, String quantity, String unit_price) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<RemovefromCartResponse> call = apiInterface.removefromcartlistResponseCall(RestUtils.getContentType(),RemovefromCartRequest(prod_id,quantity,unit_price));
        Log.w(TAG,"RemovefromCartResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<RemovefromCartResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<RemovefromCartResponse> call, @NonNull Response<RemovefromCartResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "RemovefromCartResponse" + new Gson().toJson(response.body()));

                        Toasty.success(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                        if (dd4YouConfig.isInternetConnectivity()) {

                            showcartlistResponseCall();

                        }

                        else
                        {
                            callnointernet();

                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }

                }

            }


            @Override
            public void onFailure(@NonNull Call<RemovefromCartResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"RemovefromCartResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private RemovefromCartRequest RemovefromCartRequest(String prod_id, String quantity, String unit_price) {


        /*
         * USER_ID : 541
         * PRODUCT_ID : 2
         * QUANTITY : 1
         * MODE : REMOVE
         */


        RemovefromCartRequest RemovefromCartRequest = new RemovefromCartRequest();
        RemovefromCartRequest.setQUANTITY(quantity);
        RemovefromCartRequest.setPRODUCT_ID(prod_id);
        RemovefromCartRequest.setUSER_ID(user_id);
        RemovefromCartRequest.setMODE("REMOVE");

        Log.w(TAG,"RemovefromCartRequest "+ new Gson().toJson(RemovefromCartRequest));
        return RemovefromCartRequest;
    }

    private void showAlert() {

        AlertDialog.Builder builder=new AlertDialog.Builder(RetailerCartActivity.this);
        builder.setTitle("Alert");
        builder.setMessage("Please Login to View Products in Cart");
        builder.setCancelable(false);
        builder.setPositiveButton("Login", (dialogInterface, i) -> {
            Intent intent = new Intent(RetailerCartActivity.this,LoginActivity.class);
            intent.putExtra("fromActivity",TAG);
            startActivity(intent);
            finish();
        });
        builder.setNegativeButton("SignUp", (dialogInterface, i) -> {
            Intent intent = new Intent(RetailerCartActivity.this, RegisterActivity.class);
            intent.putExtra("fromActivity",TAG);
            startActivity(intent);
            finish();
        });
        builder.setNeutralButton("Cancel", (dialogInterface, i) -> {
            AlertDialog alertDialog = builder.create();
            alertDialog.dismiss();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
