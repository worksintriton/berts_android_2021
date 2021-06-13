package com.triton.bertsproject.retailer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.adapter.BrandListAdapter;
import com.triton.bertsproject.adapter.FilterColorlistAdapter;
import com.triton.bertsproject.adapter.FilterlistAdapter;
import com.triton.bertsproject.adapter.ParentCategoriesListAdapter;
import com.triton.bertsproject.adapter.SearchBrandFilterlistAdapter;
import com.triton.bertsproject.adapter.SearchCategFilterlistAdapter;
import com.triton.bertsproject.adapter.SearchColorFilterlistAdapter;
import com.triton.bertsproject.adapter.SearchMakeFilterlistAdapter;
import com.triton.bertsproject.adapter.SearchYearFilterlistAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.interfaces.GetBrandIDListener;
import com.triton.bertsproject.interfaces.GetCategIDListener;
import com.triton.bertsproject.interfaces.GetColorIDListener;
import com.triton.bertsproject.interfaces.GetMakeIDListener;
import com.triton.bertsproject.interfaces.GetYearNameListener;
import com.triton.bertsproject.model.FilterColorlistModel;
import com.triton.bertsproject.model.FilterlistModel;
import com.triton.bertsproject.requestpojo.FetchAllColorsRequest;
import com.triton.bertsproject.requestpojo.FetchAllYearRequest;
import com.triton.bertsproject.responsepojo.FetchAllBrandsResponse;
import com.triton.bertsproject.responsepojo.FetchAllColorsResponse;
import com.triton.bertsproject.responsepojo.FetchAllParentCategoriesResponse;
import com.triton.bertsproject.responsepojo.FetchAllParentMakesResponse;
import com.triton.bertsproject.responsepojo.FetchAllYearResponse;
import com.triton.bertsproject.responsepojo.FetchChildMakeslistRequestResponse;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.GridSpacingItemDecoration;
import com.triton.bertsproject.utils.RestUtils;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterlistActivity extends AppCompatActivity implements GetYearNameListener, GetMakeIDListener, GetBrandIDListener, GetCategIDListener, GetColorIDListener {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_yr)
    ExpandableLayout expandableLayout_yr;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_make)
    ExpandableLayout expandable_layout_make;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_brand)
    ExpandableLayout expandable_layout_brand;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_categ)
    ExpandableLayout expandable_layout_categ;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_price_range)
    ExpandableLayout expandable_layout_price_range;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_rating)
    ExpandableLayout expandable_layout_rating;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_color)
    ExpandableLayout expandable_layout_color;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_year)
    LinearLayout ll_year;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_makes)
    LinearLayout ll_makes;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_brand)
    LinearLayout ll_brand;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_categ)
    LinearLayout ll_categ;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_price_range)
    LinearLayout ll_price_range;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_rating)
    LinearLayout ll_rating;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_color)
    LinearLayout ll_color;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_year)
    ImageView img_arrow_year;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_make)
    ImageView img_arrow_make;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_brand)
    ImageView img_arrow_brand;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_categ)
    ImageView img_arrow_categ;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_pr_range)
    ImageView img_arrow_pr_range;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_rate)
    ImageView img_arrow_rate;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_color)
    ImageView img_arrow_color;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_year)
    RecyclerView rv_year;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_makes)
    RecyclerView rv_makes;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_brand)
    RecyclerView rv_brand;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_categ)
    RecyclerView rv_categ;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_color)
    RecyclerView rv_color;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    List<FetchAllBrandsResponse.DataBean.BrandBean> brandsBeanList;

    List<FilterlistModel> filterlistModel;

    List<FilterColorlistModel> filterColorlistModels;

    private static final String TAG = "FilterlistActivity";

    AlertDialog alertDialog;

    DD4YouConfig dd4YouConfig;

    SessionManager sessionManager;

    String userid, ywar = "", maked = "", modelid = "", make_name, model_name;

    List<FetchAllParentMakesResponse.DataBean.MakeBean> makesBeanList;

    List<FetchChildMakeslistRequestResponse.DataBean.MakeBean> modelBeanList;

    List<FetchAllYearResponse.DataBean.YearBean> yearBeanList;

    List<FetchAllColorsResponse.DataBean.ColorsBean> colorsBeanList;

    List<FetchAllParentCategoriesResponse.DataBean.CategoriesBean> categoriesBeanList;

    String search_text,fromactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filterlist);

        ButterKnife.bind(this);

        dd4YouConfig = new DD4YouConfig(this);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            fromactivity = extras.getString("fromactivity");

            search_text = extras.getString("search_text");

        }

        ll_year.setOnClickListener(v -> yearlist());

        ll_makes.setOnClickListener(v -> makelist());

        ll_brand.setOnClickListener(v -> brandlist());

        ll_categ.setOnClickListener(v -> categlist());

        ll_price_range.setOnClickListener(v -> pricelist());

        ll_rating.setOnClickListener(v -> ratinglist());

        ll_color.setOnClickListener(v -> colorlist());

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });

        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallyearListResponseCall();
        } else {

            callnointernet();
        }

        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallbrandsListResponseCall();
        } else {

            callnointernet();
        }

        setCateg();

        setColor();


    }

    /* Get Year */

    @SuppressLint("LongLogTag")
    private void fetchallyearListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchAllYearResponse> call = apiInterface.fetchallyearListResponseCall(RestUtils.getContentType(), fetchAllYearRequest());
        Log.w(TAG, "FetchAllYearResponse url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<FetchAllYearResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FetchAllYearResponse> call, @NonNull Response<FetchAllYearResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if (200 == response.body().getCode()) {

                        Log.w(TAG, "FetchAllYearResponse" + new Gson().toJson(response.body()));

                        yearBeanList = response.body().getData().getYear();

                        if (yearBeanList != null && yearBeanList.size() > 0) {

                            if (dd4YouConfig.isInternetConnectivity()) {

                                fetchallmakesListResponseCall();
                            } else {

                                callnointernet();
                            }

                            setViewYearList(yearBeanList);
                        } else {


                        }
                    } else {

                        showErrorLoading(response.body().getMessage());
                    }


                }

            }


            @Override
            public void onFailure(@NonNull Call<FetchAllYearResponse> call, @NonNull Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG, "FetchAllYearResponse flr" + t.getMessage());
            }
        });


    }

    @SuppressLint("LongLogTag")
    private FetchAllYearRequest fetchAllYearRequest() {


        /*
         * ATTRIBUTE : YEAR
         */

        FetchAllYearRequest fetchAllYearRequest = new FetchAllYearRequest();
        fetchAllYearRequest.setATTRIBUTE("YEAR");


        Log.w(TAG, "FetchAllYearRequest " + new Gson().toJson(fetchAllYearRequest));
        return fetchAllYearRequest;
    }

    private void setViewYearList(List<FetchAllYearResponse.DataBean.YearBean> yearBeanList) {

        rv_year.setLayoutManager(new GridLayoutManager(FilterlistActivity.this, 2));

        rv_year.setMotionEventSplittingEnabled(false);

        rv_year.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_year.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_year.setItemAnimator(new DefaultItemAnimator());

        SearchYearFilterlistAdapter searchYearFilterlistAdapter = new SearchYearFilterlistAdapter(FilterlistActivity.this, yearBeanList, this);

        rv_year.setAdapter(searchYearFilterlistAdapter);


    }

    private void yearlist() {

        if (expandableLayout_yr.isExpanded()) {
            expandableLayout_yr.collapse();
            rv_year.setVisibility(View.GONE);
            img_arrow_year.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandableLayout_yr.expand();
            rv_year.setVisibility(View.VISIBLE);
            img_arrow_year.setImageResource(R.drawable.ic_up_arrow);
        }

    }

    /* Get Makes */

    @SuppressLint("LongLogTag")
    private void fetchallmakesListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchAllParentMakesResponse> call = apiInterface.fetchallmakesListResponseCall(RestUtils.getContentType());
        Log.w(TAG, "FetchAllParentMakesResponse url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<FetchAllParentMakesResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FetchAllParentMakesResponse> call, @NonNull Response<FetchAllParentMakesResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if (200 == response.body().getCode()) {

                        Log.w(TAG, "FetchAllParentMakesResponse" + new Gson().toJson(response.body()));

                        makesBeanList = response.body().getData().getMake();

                        if (makesBeanList != null && makesBeanList.size() > 0) {


                            setViewMakesList(makesBeanList);
                        } else {


                        }
                    } else {

                        showErrorLoading(response.body().getMessage());
                    }


                }

            }


            @Override
            public void onFailure(@NonNull Call<FetchAllParentMakesResponse> call, @NonNull Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG, "FetchAllParentMakesResponse flr" + t.getMessage());
            }
        });


    }

    private void setViewMakesList(List<FetchAllParentMakesResponse.DataBean.MakeBean> makesBeanList) {

        rv_makes.setLayoutManager(new GridLayoutManager(FilterlistActivity.this, 2));

        rv_makes.setMotionEventSplittingEnabled(false);

        rv_makes.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_makes.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_makes.setItemAnimator(new DefaultItemAnimator());

        SearchMakeFilterlistAdapter searchMakeFilterlistAdapter = new SearchMakeFilterlistAdapter(FilterlistActivity.this, makesBeanList, this);

        rv_makes.setAdapter(searchMakeFilterlistAdapter);

    }

    private void makelist() {

        if (expandable_layout_make.isExpanded()) {
            expandable_layout_make.collapse();
            rv_makes.setVisibility(View.GONE);
            img_arrow_make.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_make.expand();
            rv_makes.setVisibility(View.VISIBLE);
            img_arrow_make.setImageResource(R.drawable.ic_up_arrow);
        }

    }


    private void brandlist() {

        if (expandable_layout_brand.isExpanded()) {
            expandable_layout_brand.collapse();
            rv_brand.setVisibility(View.GONE);
            img_arrow_brand.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_brand.expand();
            rv_brand.setVisibility(View.VISIBLE);
            img_arrow_brand.setImageResource(R.drawable.ic_up_arrow);
        }

    }


    private void setCateg() {

        filterlistModel = new ArrayList<>();

        filterlistModel.add(new FilterlistModel("Interior", false));

        filterlistModel.add(new FilterlistModel("Honda", false));

        filterlistModel.add(new FilterlistModel("Extorior", false));

        filterlistModel.add(new FilterlistModel("Jeep", false));

        rv_categ.setLayoutManager(new GridLayoutManager(FilterlistActivity.this, 2));

        rv_categ.setMotionEventSplittingEnabled(false);

        rv_categ.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_categ.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_categ.setItemAnimator(new DefaultItemAnimator());

        FilterlistAdapter filterlistAdapter = new FilterlistAdapter(FilterlistActivity.this, filterlistModel);

        rv_categ.setAdapter(filterlistAdapter);

    }

    private void categlist() {

        if (expandable_layout_categ.isExpanded()) {
            expandable_layout_categ.collapse();
            rv_categ.setVisibility(View.GONE);
            img_arrow_categ.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_categ.expand();
            rv_categ.setVisibility(View.VISIBLE);
            img_arrow_categ.setImageResource(R.drawable.ic_up_arrow);
        }

    }


    private void pricelist() {

        if (expandable_layout_price_range.isExpanded()) {
            expandable_layout_price_range.collapse();
//            rv.setVisibility(View.GONE);
            img_arrow_pr_range.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_price_range.expand();
//            rv_categ.setVisibility(View.VISIBLE);
            img_arrow_pr_range.setImageResource(R.drawable.ic_up_arrow);
        }

    }


    private void ratinglist() {

        if (expandable_layout_rating.isExpanded()) {
            expandable_layout_rating.collapse();
//            rv.setVisibility(View.GONE);
            img_arrow_rate.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_rating.expand();
//            rv_categ.setVisibility(View.VISIBLE);
            img_arrow_rate.setImageResource(R.drawable.ic_up_arrow);
        }

    }


    private void setColor() {

        filterColorlistModels = new ArrayList<>();

        filterColorlistModels.add(new FilterColorlistModel("Red", false, 1));

        filterColorlistModels.add(new FilterColorlistModel("Black", false, 2));

        filterColorlistModels.add(new FilterColorlistModel("Green", false, 3));

        filterColorlistModels.add(new FilterColorlistModel("Blue", false, 4));

        rv_color.setLayoutManager(new GridLayoutManager(FilterlistActivity.this, 2));

        rv_color.setMotionEventSplittingEnabled(false);

        rv_color.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_color.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_color.setItemAnimator(new DefaultItemAnimator());

        FilterColorlistAdapter filterColorlistAdapter = new FilterColorlistAdapter(FilterlistActivity.this, filterColorlistModels);

        rv_color.setAdapter(filterColorlistAdapter);

    }

    private void colorlist() {

        if (expandable_layout_color.isExpanded()) {
            expandable_layout_color.collapse();
            rv_color.setVisibility(View.GONE);
            img_arrow_color.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_color.expand();
            rv_color.setVisibility(View.VISIBLE);
            img_arrow_color.setImageResource(R.drawable.ic_up_arrow);
        }

    }

    public void showErrorLoading(String errormesage) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(FilterlistActivity.this);
        alertDialogBuilder.setMessage(errormesage);
        alertDialogBuilder.setPositiveButton("ok",
                (arg0, arg1) -> hideLoading());


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void hideLoading() {
        try {
            alertDialog.dismiss();
        } catch (Exception ignored) {

        }
    }


    private void callnointernet() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FilterlistActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(FilterlistActivity.this, FilterlistActivity.class));
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public void getYearNameListener(String id, String year_name) {

        Log.w(TAG, "Selected  Year ID" + id);

        Log.w(TAG, "Selected  Year Name" + year_name);

    }

    @Override
    public void getMakeIDListener(String id, String make_name) {

        Log.w(TAG, "Selected  Make ID" + id);

        Log.w(TAG, "Selected  Make Name" + make_name);
    }


    @SuppressLint("LongLogTag")
    private void fetchallbrandsListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchAllBrandsResponse> call = apiInterface.fetchallbrandsListResponseCall(RestUtils.getContentType());
        Log.w(TAG, "FetchAllBrandsResponse url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<FetchAllBrandsResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FetchAllBrandsResponse> call, @NonNull Response<FetchAllBrandsResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if (200 == response.body().getCode()) {

                        Log.w(TAG, "FetchAllBrandsResponse" + new Gson().toJson(response.body()));

                        brandsBeanList = response.body().getData().getBrand();

                        if (brandsBeanList != null && brandsBeanList.size() > 0) {

                            setBrandView(brandsBeanList);
                        } else {

                        }
                    } else {

                        showErrorLoading(response.body().getMessage());
                    }


                }

            }


            @Override
            public void onFailure(@NonNull Call<FetchAllBrandsResponse> call, @NonNull Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG, "FetchAllParentCategoriesResponse flr" + t.getMessage());
            }
        });


    }


    private void setBrandView(List<FetchAllBrandsResponse.DataBean.BrandBean> brandsBeanList) {

        rv_brand.setLayoutManager(new GridLayoutManager(FilterlistActivity.this, 2));

        rv_brand.setMotionEventSplittingEnabled(false);

        rv_brand.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_brand.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_brand.setItemAnimator(new DefaultItemAnimator());

        SearchBrandFilterlistAdapter searchBrandFilterlistAdapter = new SearchBrandFilterlistAdapter(FilterlistActivity.this, brandsBeanList, this);

        rv_brand.setAdapter(searchBrandFilterlistAdapter);


    }

    @Override
    public void getBrandIDListener(String id, String brand_name) {

        Log.w(TAG, "Selected  Brand ID" + id);

        Log.w(TAG, "Selected  Brand Name" + brand_name);
    }

    @SuppressLint("LongLogTag")
    private void fetchallcategoriesListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchAllParentCategoriesResponse> call = apiInterface.fetchallcategoriesListResponseCall(RestUtils.getContentType());
        Log.w(TAG, "url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<FetchAllParentCategoriesResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FetchAllParentCategoriesResponse> call, @NonNull Response<FetchAllParentCategoriesResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if (200 == response.body().getCode()) {

                        Log.w(TAG, "FetchAllParentCategoriesResponse" + new Gson().toJson(response.body()));

                        categoriesBeanList = response.body().getData().getCategories();

                        if (categoriesBeanList != null && categoriesBeanList.size() > 0) {

                            setView(categoriesBeanList);
                        } else {

                        }
                    } else {

                        showErrorLoading(response.body().getMessage());

                    }


                }

            }


            @Override
            public void onFailure(@NonNull Call<FetchAllParentCategoriesResponse> call, @NonNull Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG, "FetchAllParentCategoriesResponse flr" + t.getMessage());
            }
        });


    }

    private void setView(List<FetchAllParentCategoriesResponse.DataBean.CategoriesBean> categoriesBeanList) {


        rv_categ.setLayoutManager(new GridLayoutManager(FilterlistActivity.this, 2));

        rv_categ.setMotionEventSplittingEnabled(false);

        rv_categ.setNestedScrollingEnabled(true);

        int size = categoriesBeanList.size();

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        rv_categ.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, true));

        rv_categ.setItemAnimator(new DefaultItemAnimator());

        SearchCategFilterlistAdapter searchCategFilterlistAdapter = new SearchCategFilterlistAdapter(FilterlistActivity.this, categoriesBeanList, this);

        rv_categ.setAdapter(searchCategFilterlistAdapter);


    }

    @Override
    public void getCategIDListener(String id, String categ_name) {

        Log.w(TAG,"Selected  Categ ID"+id);

        Log.w(TAG,"Selected  Categ Name"+categ_name);
    }

    /* Get Colors */

    @SuppressLint("LongLogTag")
    private void fetchallcolorListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchAllColorsResponse> call = apiInterface.fetchallcolorListResponseCall(RestUtils.getContentType(), fetchAllColorsRequest());
        Log.w(TAG, "FetchAllColorsResponse url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<FetchAllColorsResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FetchAllColorsResponse> call, @NonNull Response<FetchAllColorsResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if (200 == response.body().getCode()) {

                        Log.w(TAG, "FetchAllColorsResponse" + new Gson().toJson(response.body()));

                        colorsBeanList = response.body().getData().getColors();

                        if (colorsBeanList != null && colorsBeanList.size() > 0) {


                            setViewColorList(colorsBeanList);
                        } else {


                        }
                    } else {

                        showErrorLoading(response.body().getMessage());
                    }


                }

            }


            @Override
            public void onFailure(@NonNull Call<FetchAllColorsResponse> call, @NonNull Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG, "FetchAllColorsResponse flr" + t.getMessage());
            }
        });


    }

    @SuppressLint("LongLogTag")
    private FetchAllColorsRequest fetchAllColorsRequest() {


        /**
         * ATTRIBUTE : COLORS
         */


        FetchAllColorsRequest fetchAllColorsRequest = new FetchAllColorsRequest();
        fetchAllColorsRequest.setATTRIBUTE("COLORS");


        Log.w(TAG, "FetchAllYearRequest " + new Gson().toJson(fetchAllColorsRequest));
        return fetchAllColorsRequest;
    }

    private void setViewColorList(List<FetchAllColorsResponse.DataBean.ColorsBean> colorsBeanList) {

        rv_color.setLayoutManager(new GridLayoutManager(FilterlistActivity.this, 2));

        rv_color.setMotionEventSplittingEnabled(false);

        rv_color.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_color.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_color.setItemAnimator(new DefaultItemAnimator());

        SearchColorFilterlistAdapter searchColorFilterlistAdapter = new SearchColorFilterlistAdapter( FilterlistActivity.this, colorsBeanList, this);

        rv_color.setAdapter(searchColorFilterlistAdapter);


    }

    @Override
    public void getColorIDListener(String id, String color_name) {

        Log.w(TAG,"Selected  Color ID"+id);

        Log.w(TAG,"Selected Color Name"+color_name);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(FilterlistActivity.this,SearchProductListActivity.class);
        intent.putExtra("search_text",search_text);
        intent.putExtra("fromactivity",fromactivity);
        startActivity(intent);
        finish();

    }
}