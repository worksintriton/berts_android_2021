package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.activities.LoginActivity;
import com.triton.bertsproject.activities.RegisterActivity;
import com.triton.bertsproject.adapter.RetailerProductListAdapter;
import com.triton.bertsproject.adapter.SearchFilterlistAdapter;
import com.triton.bertsproject.adapter.SearchProductListAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.interfaces.AddProductListener;
import com.triton.bertsproject.model.SearchFilterListModel;
import com.triton.bertsproject.model.SearchProductlistModel;
import com.triton.bertsproject.requestpojo.AddToCartRequest;
import com.triton.bertsproject.requestpojo.SearchProductsRequest;
import com.triton.bertsproject.responsepojo.AddToCartResponse;
import com.triton.bertsproject.responsepojo.SearchProductsResponse;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.GridSpacingItemDecoration;
import com.triton.bertsproject.utils.RestUtils;

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

public class SearchProductListActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, AddProductListener {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    Context context = SearchProductListActivity.this;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.img_back)
//    ImageView img_back;
//
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
      TextView txt_toolbar_title;
//
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.edt_search)
//    EditText edt_search;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_filterlist)
    RecyclerView rv_filterlist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_searchprodlist)
    RecyclerView rv_searchprodlist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_total_results)
    TextView txt_total_results;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_sort)
    LinearLayout ll_sort;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_sort_filter)
    RelativeLayout rl_sort_filter;

    private final static String TAG = "SearchProdListActivity";

    public static String active_tag = "1";

    String tag;

    String fromactivity,search_text;

    List<SearchFilterListModel> searchFilterListModels;

    List<SearchProductlistModel> searchProductlistModels;

    AlertDialog alertDialog;

    List<SearchProductsResponse.DataBean.ProductsBean> prdouctsBeanList ;

    String user_id;

//    private DD4YouNetReceiver dd4YouNetReceiver;

    private DD4YouConfig dd4YouConfig;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product_list);

        ButterKnife.bind(this);

        Log.w("Oncreate", TAG);

        floatingActionButton.setImageResource(R.drawable.berts_logo_fb);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            fromactivity = extras.getString("fromactivity");

            search_text = extras.getString("search_text");

        }

        sessionManager=new SessionManager(this);

        dd4YouConfig=new DD4YouConfig(this);

        HashMap<String, String> user = sessionManager.getProfileDetails();

        user_id = user.get(SessionManager.KEY_ID);

       // user_id  = "541";

        spin_kit_loadingView.setVisibility(View.GONE);

        txt_toolbar_title.setText(R.string.search_products);

        tag = getIntent().getStringExtra("tag");

        Log.w(TAG," tag : "+tag);

        bottomNavigation.setSelectedItemId(R.id.shop);

        bottomNavigation.setOnNavigationItemSelectedListener(this);

//        rl_sort_filter.setOnClickListener(v -> {
//
//            Intent intent = new Intent(SearchProductListActivity.this, FilterlistActivity.class);
//
//            intent.putExtra("fromactivity",TAG);
//
//            startActivity(intent);
//
//        });

        ll_sort.setVisibility(View.GONE);

        if(dd4YouConfig.isInternetConnectivity()){

            fetchallproductsListResponseCall();
        }

        else {

            callnointernet();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                active_tag = "1";
                //replaceFragment(new HomeFragment());
                break;
            case R.id.garage:
                active_tag = "2";
                // replaceFragment(new MyGarageFragment());
                break;
            case R.id.shop:
                active_tag = "3";
                //replaceFragment(new ShopFragment());
                break;
            case R.id.chat:
                active_tag = "4";
                // replaceFragment(new LiveChatFragment());
                break;
            case R.id.profile:
                active_tag = "5";
                //replaceFragment(new ProfileFragment());
                break;

            default:
                return  false;
        }
        return true;
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
        startActivity(new Intent(SearchProductListActivity.this, SearchProductsActivity.class));

        Animatoo.animateSwipeRight(context);
    }


    @SuppressLint("LongLogTag")
    private void fetchallproductsListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SearchProductsResponse> call = apiInterface.searchprodResponseCall(RestUtils.getContentType(),SearchProductsRequest());
        Log.w(TAG,"SearchProductsResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SearchProductsResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<SearchProductsResponse> call, @NonNull Response<SearchProductsResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"SearchProductsResponse" + new Gson().toJson(response.body()));

                        prdouctsBeanList = response.body().getData().getProducts();

                        if(response.body().getData().getTotal_count()!=0){

                            txt_total_results.setText(" "+response.body().getData().getTotal_count()+ " Results");
                        }

                        if(prdouctsBeanList != null && prdouctsBeanList.size()>0){

                            rv_searchprodlist.setVisibility(View.VISIBLE);

                            txt_no_records.setVisibility(View.GONE);

                            ll_sort.setVisibility(View.VISIBLE);

                            setGridView(prdouctsBeanList);
                        }

                        else {


                            rv_searchprodlist.setVisibility(View.GONE);

                            txt_no_records.setVisibility(View.VISIBLE);

                            ll_sort.setVisibility(View.GONE);

                            txt_no_records.setText(R.string.no_prod_found);
                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }



                }

            }


            @Override
            public void onFailure(@NonNull Call<SearchProductsResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"SearchProductsResponse flr"+t.getMessage());

                txt_no_records.setText(R.string.no_prod_found);
            }
        });



    }

    @SuppressLint("LongLogTag")
    private SearchProductsRequest SearchProductsRequest() {


        /*
         * SEARCH_STRING : wheel
         * USER_ID :
         */

        SearchProductsRequest SearchProductsRequest = new SearchProductsRequest();
        SearchProductsRequest.setSEARCH_STRING(search_text);
        SearchProductsRequest.setUSER_ID(user_id);

        Log.w(TAG,"SearchProductsRequest "+ new Gson().toJson(SearchProductsRequest));
        return SearchProductsRequest;
    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(SearchProductListActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(SearchProductListActivity.this,SearchProductListActivity.class));
            finish();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setGridView(List<SearchProductsResponse.DataBean.ProductsBean> prdouctsBeanList) {


        rv_searchprodlist.setLayoutManager(new LinearLayoutManager(SearchProductListActivity.this, LinearLayoutManager.VERTICAL, false));

        rv_searchprodlist.setMotionEventSplittingEnabled(false);

        rv_searchprodlist.setNestedScrollingEnabled(true);

        //int size =3;

        rv_searchprodlist.setItemAnimator(new DefaultItemAnimator());

        SearchProductListAdapter searchProductListAdapter = new SearchProductListAdapter(SearchProductListActivity.this, prdouctsBeanList,this);

        rv_searchprodlist.setAdapter(searchProductListAdapter);
    }


    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SearchProductListActivity.this);
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

    @Override
    public void addproductListener(String prod_id, String quantity, String unit_price) {

        if(sessionManager.isLoggedIn()){

            if(dd4YouConfig.isInternetConnectivity()){

                addcartlistResponseCall(prod_id,quantity,unit_price);
            }

            else {

                callnointernet();
            }
        }

        else {

            showAlert();
        }

    }

    private void showAlert() {

        AlertDialog.Builder builder=new AlertDialog.Builder(SearchProductListActivity.this);
        builder.setTitle("Alert");
        builder.setMessage("Please Login to add Products");
        builder.setCancelable(false);
        builder.setPositiveButton("Login", (dialogInterface, i) -> {
            Intent intent = new Intent(SearchProductListActivity.this, LoginActivity.class);

            intent.putExtra("search_text",search_text);


            intent.putExtra("fromActivity",TAG);

            startActivity(intent);
        });
        builder.setNegativeButton("Sign In", (dialogInterface, i) -> {
            Intent intent = new Intent(SearchProductListActivity.this, RegisterActivity.class);

            intent.putExtra("search_text",search_text);


            intent.putExtra("fromActivity",TAG);

            startActivity(intent);
        });
        builder.setNeutralButton("Cancel", (dialogInterface, i) -> {


        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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

                        Intent intent = new Intent(SearchProductListActivity.this, RetailerCartActivity.class);

                        intent.putExtra("fromActivity",TAG);

                        startActivity(intent);

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
}