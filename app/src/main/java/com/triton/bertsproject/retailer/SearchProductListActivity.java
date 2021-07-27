package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.activities.LoginActivity;
import com.triton.bertsproject.activities.RegisterActivity;
import com.triton.bertsproject.adapter.SearchBrandFilterlistAdapter;
import com.triton.bertsproject.adapter.SearchProductListAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.interfaces.AddProductListener;
import com.triton.bertsproject.model.SearchFilterListModel;
import com.triton.bertsproject.model.SearchProductlistModel;
import com.triton.bertsproject.requestpojo.AddToCartRequest;
import com.triton.bertsproject.requestpojo.FilterRequest;
import com.triton.bertsproject.requestpojo.SearchProductsRequest;
import com.triton.bertsproject.responsepojo.AddToCartResponse;
import com.triton.bertsproject.responsepojo.FilterResponse;
import com.triton.bertsproject.responsepojo.SearchProductsResponse;
import com.triton.bertsproject.sessionmanager.Connectivity;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.RestUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
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

import static android.view.View.VISIBLE;

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

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;
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

    String makesid,modelsid,categid,brandid,color, radioValue,final_min_value,final_max_value,min_pri="0",max_pri = "0",rating;;

    String years;

    JSONObject data ;

    String jsondata,value;

    Connectivity connectivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product_list);

        ButterKnife.bind(this);

        Log.w("Oncreate", TAG);

        floatingActionButton.setImageResource(R.drawable.berts_logo_fb);

        connectivity = new Connectivity();

        value = connectivity.getData(SearchProductListActivity.this,"SearchProductList");

        if(value!=null&&!value.isEmpty()) {

            fromactivity = value;

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                search_text = extras.getString("search_text");

                jsondata = extras.getString("data");

                try {

                    if(getIntent().getStringExtra("data")!=null){

                        data = new JSONObject(getIntent().getStringExtra("data"));
                    }

                    else {

                        data = new JSONObject();

                        Log.w(TAG,"Cond --> false");

                        data.put("sample","0");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.w(TAG,"Connectivity fromactivity : "+fromactivity + "search_text : "+search_text

                        + "jsondata : "+jsondata + "data " +data);

                Log.w(TAG,"data "+ new Gson().toJson(data));

            }

        }

        else {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                fromactivity = extras.getString("fromactivity");

                search_text = extras.getString("search_text");

                jsondata = extras.getString("data");

                try {

                    if(getIntent().getStringExtra("data")!=null){

                        data = new JSONObject(getIntent().getStringExtra("data"));
                    }

                    else {

                        data = new JSONObject();

                        Log.w(TAG,"Cond --> false");

                        data.put("sample","0");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.w(TAG,"Connectivity fromactivity : "+fromactivity + "search_text : "+search_text

                        + "jsondata : "+jsondata + "data " +data);

                Log.w(TAG,"data "+ new Gson().toJson(data));

            }
        }





        if(data!=null){

            try {

                if(!data.getString("years").equals("")){

                    years = data.getString("years");
                }
                else {

                    years = "";
                }


                //prod_id = "2";

                if(!data.getString("makesid").equals("")){

                    makesid = data.getString("makesid");
                }
                else {

                    makesid = "";
                }

                if(!data.getString("modelid").equals("")){

                    modelsid = data.getString("modelid");
                }
                else {

                    modelsid = "";
                }

                if(!data.getString("brandid").equals("")){

                    brandid = data.getString("brandid");
                }
                else {

                    brandid = "";
                }

                if(!data.getString("categid").equals("")){

                    categid = data.getString("categid");
                }
                else {

                    categid = "";
                }
                if(!data.getString("min_value").equals("")){

                    final_min_value = data.getString("min_value");
                }
                else {

                    final_min_value = "";
                }
                if(!data.getString("max_value").equals("")){

                    final_max_value = data.getString("max_value");
                }
                else {

                    final_max_value = "";
                }
                if(!data.getString("color").equals("")){

                    color = data.getString("color");
                }
                else {

                    color = "";
                }
                if(!data.getString("rate").equals("")){

                    radioValue = data.getString("rate");
                }
                else {

                    radioValue = "";
                }

                Log.w(TAG, "years : " + years + "makesid : " + makesid + "modelid : "+ modelsid + "brandid : "+ brandid +

                        "categid :" + categid + "final_min_value :" + final_min_value + "final_max_value :" +final_max_value

                        + "color : " + color + "radioValue : " + radioValue );

            } catch (JSONException e) {
                e.printStackTrace();
            }


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

        ll_sort.setVisibility(View.GONE);

        if (fromactivity!=null&&fromactivity.equals("FilterlistActivity")){


            if(dd4YouConfig.isInternetConnectivity()){

                fetchallfiltersproductsListResponseCall();
            }

            else {

                callnointernet();
            }
        }
        else {

            if(dd4YouConfig.isInternetConnectivity()){

                fetchallproductsListResponseCall();
            }

            else {

                callnointernet();
            }

        }

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
    }


    @SuppressLint("NonConstantResourceId")
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.chat:
                active_tag = "4";
                callDirections(active_tag);
                return true;
            case R.id.garage:
                active_tag = "2";
                callDirections(active_tag);
                return true;
            case R.id.home:
                active_tag = "1";
                callDirections(active_tag);
                return true;
            case R.id.profile:
                active_tag = "5";
                callDirections(active_tag);
                return true;
            case R.id.shop:
                active_tag = "3";
                callDirections(active_tag);
                return true;
            default:
                return false;
        }
    }

    public void callDirections(String tag){
        Intent intent = new Intent(SearchProductListActivity.this,RetailerDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();

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

        callDirections("1");
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

                            rl_sort_filter.setOnClickListener(v -> {

                                Intent intent = new Intent(SearchProductListActivity.this, FilterlistActivity.class);

                                intent.putExtra("fromactivity",TAG);

                                intent.putExtra("search_text",search_text);

                                connectivity.storeData(SearchProductListActivity.this,"SearchProductList",fromactivity);

                                startActivity(intent);

                            });

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

        SearchProductListAdapter searchProductListAdapter = new SearchProductListAdapter(SearchProductListActivity.this, prdouctsBeanList,this,search_text,data,fromactivity);

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
    public void addproductListener(String prod_id, String quantity, String unit_price, Button btn_addcart) {

        if(sessionManager.isLoggedIn()){

            if(dd4YouConfig.isInternetConnectivity()){

                addcartlistResponseCall(prod_id,quantity,unit_price);
            }

            else {

                callnointernet();
            }
        }

        else {

            showAlert(prod_id,quantity,unit_price);
        }

    }

    private void showAlert(String prod_id, String quantity, String unit_price) {


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SearchProductListActivity.this);
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_vehicle_layout, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setCancelable(false);

        RelativeLayout rl_yes = dialogView.findViewById(R.id.rl_yes);

        RelativeLayout rl_no = dialogView.findViewById(R.id.rl_no);

        ImageView img_close = dialogView.findViewById(R.id.img_close);

        img_close.setVisibility(View.VISIBLE);

        TextView alert_title_txtview = dialogView.findViewById(R.id.alert_title_txtview);

        alert_title_txtview.setText("Please Login to Add Products in Cart");

        TextView alert_title_login = dialogView.findViewById(R.id.textView6);

        alert_title_login.setText("Login");

        TextView alert_title_signup = dialogView.findViewById(R.id.textView7);

        alert_title_signup.setText("Signup");

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        rl_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(SearchProductListActivity.this, RegisterActivity.class);

                intent.putExtra("search_text",search_text);

                intent.putExtra("prod_id",prod_id);

                intent.putExtra("quantity",quantity);

                intent.putExtra("unit_price",unit_price);

                intent.putExtra("fromactivity",TAG);

                intent.putExtra("data",data.toString());

                connectivity.storeData(SearchProductListActivity.this,"SearchProductList",fromactivity);

                startActivity(intent);

                alertDialog.dismiss();

            }
        });

        rl_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SearchProductListActivity.this, LoginActivity.class);

                intent.putExtra("search_text",search_text);

                intent.putExtra("prod_id",prod_id);

                intent.putExtra("quantity",quantity);

                intent.putExtra("unit_price",unit_price);

                intent.putExtra("fromactivity",TAG);

                intent.putExtra("data",data.toString());

                connectivity.storeData(SearchProductListActivity.this,"SearchProductList",fromactivity);

                startActivity(intent);

                alertDialog.dismiss();

            }
        });

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                alertDialog.dismiss();
            }
        });
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

                        intent.putExtra("fromactivity",TAG);

                        intent.putExtra("search_text",search_text);

                        intent.putExtra("data",data.toString());

                        connectivity.storeData(SearchProductListActivity.this,"SearchProductList",fromactivity);

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


    @SuppressLint("LongLogTag")
    private void fetchallfiltersproductsListResponseCall() {

        spin_kit_loadingView.setVisibility(VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SearchProductsResponse> call = apiInterface.fetchallfilterprodResponseCall(RestUtils.getContentType(), FilterRequest());
        Log.w(TAG,"SearchProductsResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SearchProductsResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<SearchProductsResponse> call, @NonNull Response<SearchProductsResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"FilterResponse" + new Gson().toJson(response.body()));

                        prdouctsBeanList = response.body().getData().getProducts();

                        if(response.body().getData().getTotal_count()!=0){

                            txt_total_results.setText(" "+response.body().getData().getTotal_count()+ " Results");
                        }

                        if(prdouctsBeanList != null && prdouctsBeanList.size()>0){

                            rv_searchprodlist.setVisibility(View.VISIBLE);

                            txt_no_records.setVisibility(View.GONE);

                            ll_sort.setVisibility(View.VISIBLE);

                            rl_sort_filter.setOnClickListener(v -> {

                                Intent intent = new Intent(SearchProductListActivity.this, FilterlistActivity.class);

                                intent.putExtra("fromactivity",TAG);

                                intent.putExtra("search_text",search_text);

                                startActivity(intent);

                            });

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

            }
        });



    }

    @SuppressLint("LongLogTag")
    private FilterRequest FilterRequest() {


        /*
         * CATEGORY_ID : 1
         * BRAND_ID : 1
         * MAKE_ID : 5,1,2
         * MODEL_ID : 174,226
         * YEAR : 2015,2017,2013
         * MODE : LIST
         * RATING : 3
         * COLOR : red,white
         * MIN_PRICE : 5000
         * MAX_PRICE : 15000
         * USER_ID : 541
         */

//
//               /* toString method returns the output as [Data
//           Structure,Algorithms,...] In order to replace
//           '[', ']' and spaces with empty strings to get
//           comma separated values.*/
//
//        String commaseparatedlist = year.toString();
//
//        String years = commaseparatedlist.replace("[", "")
//                .replace("]", "")
//                .replace(" ", "");


        FilterRequest FilterRequest = new FilterRequest();
        FilterRequest.setCATEGORY_ID("");
        FilterRequest.setBRAND_ID("");
        FilterRequest.setMAKE_ID("5");
        FilterRequest.setMODEL_ID("205");
        FilterRequest.setYEAR("");
        FilterRequest.setMODE("LIST");
        FilterRequest.setRATING("");
        FilterRequest.setCOLOR("");
        FilterRequest.setMIN_PRICE("100");
        FilterRequest.setMAX_PRICE("15000");
        FilterRequest.setUSER_ID(user_id);

//        FilterRequest FilterRequest = new FilterRequest();
//        FilterRequest.setCATEGORY_ID(categid);
//        FilterRequest.setBRAND_ID(brandid);
//        FilterRequest.setMAKE_ID(makesid);
//        FilterRequest.setMODEL_ID(modelsid);
//        FilterRequest.setCATEGORY_ID(categid);
//        FilterRequest.setYEAR(years);
//        FilterRequest.setMODE("LIST");
//        FilterRequest.setRATING(radioValue);
//        FilterRequest.setCOLOR(color);
//        FilterRequest.setMIN_PRICE(final_min_value);
//        FilterRequest.setMAX_PRICE(final_max_value);
//        FilterRequest.setUSER_ID(user_id);
//


        Log.w(TAG,"FilterRequest "+ new Gson().toJson(FilterRequest));
        return FilterRequest;
    }


}