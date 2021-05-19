package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

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
import com.triton.bertsproject.adapter.ParentCategoriesListAdapter;
import com.triton.bertsproject.adapter.RetailerProductListAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.model.RetailerProductlistModel;
import com.triton.bertsproject.responsepojo.FetchAllParentCategoriesResponse;
import com.triton.bertsproject.utils.GridSpacingItemDecoration;
import com.triton.bertsproject.utils.RestUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetailerProductListActivity extends AppCompatActivity {

    Context context = RetailerProductListActivity.this;

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

    List<FetchAllParentCategoriesResponse.DataBean.CategoriesBean> categoriesBeanList ;

    String brand_id;

//    private DD4YouNetReceiver dd4YouNetReceiver;

    private DD4YouConfig dd4YouConfig;

    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_product_list);

        ButterKnife.bind(this);

        Log.w("Oncreate", TAG);

        retailerProductlistModels = new ArrayList<>();

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            fromactivity = extras.getString("fromactivity");

            brand_id = extras.getString("brand_id");

        }

        dd4YouConfig = new DD4YouConfig(this);

        //registerBroadcastReceiver();

        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallcategoriesListResponseCall();

        }

        else
        {
            callnointernet();

        }


        spin_kit_loadingView.setVisibility(View.GONE);

        txt_toolbar_title.setText(R.string.side_view_mirrors);

        img_back.setOnClickListener(v -> {

            startActivity(new Intent(RetailerProductListActivity.this, RetailerDashboardActivity.class));

            Animatoo.animateSwipeLeft(context);
        });

        rlList.setOnClickListener(v -> {

            rlList.setBackgroundResource(R.drawable.bg_cycler_blue);

            rlGrid.setBackgroundResource(R.color.transparent);

            setlistView();
        });


        rlGrid.setOnClickListener(v -> {

            rlGrid.setBackgroundResource(R.drawable.bg_cycler_blue);

            rlList.setBackgroundResource(R.color.transparent);

            setView();
        });


        setView();

    }

    @SuppressLint("LongLogTag")
    private void fetchallcategoriesListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchAllParentCategoriesResponse> call = apiInterface.fetchallcategoriesListResponseCall(RestUtils.getContentType());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<FetchAllParentCategoriesResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FetchAllParentCategoriesResponse> call, @NonNull Response<FetchAllParentCategoriesResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"FetchAllParentCategoriesResponse" + new Gson().toJson(response.body()));

                        categoriesBeanList = response.body().getData().getCategories();

                        if(categoriesBeanList != null && categoriesBeanList.size()>0){


                            rv_prodlist.setVisibility(View.VISIBLE);

                            txt_no_records.setVisibility(View.GONE);

                            setView(categoriesBeanList);
                        }

                        else {


                            rv_prodlist.setVisibility(View.GONE);

                            txt_no_records.setVisibility(View.VISIBLE);

                            txt_no_records.setText(R.string.cat_dis_msg);
                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }



                }

            }


            @Override
            public void onFailure(@NonNull Call<FetchAllParentCategoriesResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"FetchAllParentCategoriesResponse flr"+t.getMessage());
            }
        });



    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(RetailerProductListActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(RetailerProductListActivity.this,RetailerProductListActivity.class));
            finish();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setView(List<FetchAllParentCategoriesResponse.DataBean.CategoriesBean> categoriesBeanList) {


        rv_prodlist.setLayoutManager(new GridLayoutManager(RetailerProductListActivity.this, 2));

        rv_prodlist.setMotionEventSplittingEnabled(false);

        rv_prodlist.setNestedScrollingEnabled(true);

        int size =categoriesBeanList.size();

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        rv_prodlist.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, true));

        rv_prodlist.setItemAnimator(new DefaultItemAnimator());

        ParentCategoriesListAdapter parentCategoriesListAdapter = new ParentCategoriesListAdapter(RetailerProductListActivity.this, categoriesBeanList,size);

        rv_prodlist.setAdapter(parentCategoriesListAdapter);



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

            fetchallcategoriesListResponseCall();

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
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RetailerProductListActivity.this);
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

    private void setlistView() {

        retailerProductlistModels.clear();

        retailerProductlistModels.add(new RetailerProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...","Jeep CJ-Style Replacement Mirrors","5","120","$4.94 - $1,054.00",R.drawable.splist1,false,false));

        retailerProductlistModels.add(new RetailerProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...","Jeep CJ-Style Replacement Mirrors","5","120","$4.94 - $1,054.00",R.drawable.splist1,false,true));

        retailerProductlistModels.add(new RetailerProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...","Jeep CJ-Style Replacement Mirrors","5","120","$4.94 - $1,054.00",R.drawable.splist1,true,false));

        retailerProductlistModels.add(new RetailerProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...","Jeep CJ-Style Replacement Mirrors","5","120","$4.94 - $1,054.00",R.drawable.splist1,true,true));

        rv_prodlist.setLayoutManager(new LinearLayoutManager(RetailerProductListActivity.this,LinearLayoutManager.VERTICAL,false));

        rv_prodlist.setMotionEventSplittingEnabled(false);

        //int size =3;

        rv_prodlist.setItemAnimator(new DefaultItemAnimator());

        RetailerProductListAdapter retailerProductListAdapter = new RetailerProductListAdapter(RetailerProductListActivity.this, retailerProductlistModels,true);

        rv_prodlist.setAdapter(retailerProductListAdapter);


    }

    private void setView() {

        retailerProductlistModels.clear();

        retailerProductlistModels.add(new RetailerProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...","Jeep CJ-Style Replacement Mirrors","5","120","$4.94 - $1,054.00",R.drawable.splist1,false,false));

        retailerProductlistModels.add(new RetailerProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...","Jeep CJ-Style Replacement Mirrors","5","120","$4.94 - $1,054.00",R.drawable.splist1,false,true));

        retailerProductlistModels.add(new RetailerProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...","Jeep CJ-Style Replacement Mirrors","5","120","$4.94 - $1,054.00",R.drawable.splist1,true,false));

        retailerProductlistModels.add(new RetailerProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...","Jeep CJ-Style Replacement Mirrors","5","120","$4.94 - $1,054.00",R.drawable.splist1,true,true));

        rv_prodlist.setLayoutManager(new GridLayoutManager(RetailerProductListActivity.this, 2));

        rv_prodlist.setMotionEventSplittingEnabled(false);

        //int size =3;

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_prodlist.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_prodlist.setItemAnimator(new DefaultItemAnimator());

        RetailerProductListAdapter retailerProductListAdapter = new RetailerProductListAdapter(RetailerProductListActivity.this, retailerProductlistModels, false);

        rv_prodlist.setAdapter(retailerProductListAdapter);

      }

  }