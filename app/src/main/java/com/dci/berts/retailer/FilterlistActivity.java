package com.dci.berts.retailer;

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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.dci.berts.adapter.SearchChildCategoriesListAdapter;
import com.dci.berts.adapter.SearchEnginelistAdapter;
import com.dci.berts.adapter.SearchFuelTypelistAdapter;
import com.dci.berts.adapter.SearchThirdChildCategoriesListAdapter;
import com.dci.berts.adapter.SearchTransmisionlistAdapter;
import com.dci.berts.interfaces.GetChildCategIDListener;
import com.dci.berts.interfaces.GetEngineIDListener;
import com.dci.berts.interfaces.GetFuelTypeIDListener;
import com.dci.berts.interfaces.GetThirdCategIDListener;
import com.dci.berts.interfaces.GetTransmissonIDListener;
import com.dci.berts.requestpojo.FetchChildCateglistRequest;
import com.dci.berts.requestpojo.GetEngineSizeRequest;
import com.dci.berts.requestpojo.GetThirdCategoryRequest;
import com.dci.berts.responsepojo.FetchChildCateglistResponse;
import com.dci.berts.responsepojo.GetEngineSizeResponse;
import com.dci.berts.responsepojo.GetFuelTypesResponse;
import com.dci.berts.responsepojo.GetThirdCategoryResponse;
import com.dci.berts.responsepojo.GetTransmissionsResponse;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.dci.berts.R;
import com.dci.berts.adapter.SearchBrandFilterlistAdapter;
import com.dci.berts.adapter.SearchCategFilterlistAdapter;
import com.dci.berts.adapter.SearchColorFilterlistAdapter;
import com.dci.berts.adapter.SearchMakeFilterlistAdapter;
import com.dci.berts.adapter.SearchModelFilterlistAdapter;
import com.dci.berts.adapter.SearchYearFilterlistAdapter;
import com.dci.berts.api.APIClient;
import com.dci.berts.api.RestApiInterface;
import com.dci.berts.interfaces.GetBrandIDListener;
import com.dci.berts.interfaces.GetCategIDListener;
import com.dci.berts.interfaces.GetColorIDListener;
import com.dci.berts.interfaces.GetMakeIDListener;
import com.dci.berts.interfaces.GetModelIDListener;
import com.dci.berts.interfaces.GetYearNameListener;
import com.dci.berts.requestpojo.FetchAllColorsRequest;
import com.dci.berts.requestpojo.FetchAllYearRequest;
import com.dci.berts.requestpojo.FetchChildMakeslistRequest;
import com.dci.berts.responsepojo.FetchAllBrandsResponse;
import com.dci.berts.responsepojo.FetchAllColorsResponse;
import com.dci.berts.responsepojo.FetchAllParentCategoriesResponse;
import com.dci.berts.responsepojo.FetchAllParentMakesResponse;
import com.dci.berts.responsepojo.FetchAllYearResponse;
import com.dci.berts.responsepojo.FetchChildMakeslistRequestResponse;
import com.dci.berts.responsepojo.GetSettingsResponse;
import com.dci.berts.sessionmanager.SessionManager;
import com.dci.berts.utils.GridSpacingItemDecoration;
import com.dci.berts.utils.RestUtils;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterlistActivity extends AppCompatActivity implements GetYearNameListener, GetMakeIDListener, GetBrandIDListener, GetCategIDListener, GetColorIDListener, GetModelIDListener, View.OnClickListener, GetEngineIDListener, GetFuelTypeIDListener, GetTransmissonIDListener, GetChildCategIDListener, GetThirdCategIDListener {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_yr)
    ExpandableLayout expandableLayout_yr;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_make)
    ExpandableLayout expandable_layout_make;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_model)
    ExpandableLayout expandable_layout_model;

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
    @BindView(R.id.expandable_layout_engine_size)
    ExpandableLayout expandable_layout_engine_size;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_fuel_type)
    ExpandableLayout expandable_layout_fuel_type;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_transmission)
    ExpandableLayout expandable_layout_transmission;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_subcateg)
    ExpandableLayout expandable_layout_subcateg;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_thirdcateg)
    ExpandableLayout expandable_layout_thirdcateg;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_year)
    LinearLayout ll_year;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_makes)
    LinearLayout ll_makes;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_models)
    LinearLayout ll_models;

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
    @BindView(R.id.ll_engine_size)
    LinearLayout ll_engine_size;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_fuel_type)
    LinearLayout ll_fuel_type;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_transmission)
    LinearLayout ll_transmission;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_subcateg)
    LinearLayout ll_subcateg;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_thirdcateg)
    LinearLayout ll_thirdcateg;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_year)
    ImageView img_arrow_year;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_make)
    ImageView img_arrow_make;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_model)
    ImageView img_arrow_model;

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
    @BindView(R.id.img_arrow_engine_size)
    ImageView img_arrow_engine_size;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_subcateg)
    ImageView img_arrow_subcateg;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_thirdcateg)
    ImageView img_arrow_thirdcateg;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_fuel_type)
    ImageView img_arrow_fuel_type;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_transmission)
    ImageView img_arrow_transmission;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_year)
    RecyclerView rv_year;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_makes)
    RecyclerView rv_makes;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_models)
    RecyclerView rv_models;

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
    @BindView(R.id.rv_engine_size)
    RecyclerView rv_engine_size;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_fuel_type)
    RecyclerView rv_fuel_type;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_transmission)
    RecyclerView rv_transmission;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_subcateg)
    RecyclerView rv_subcateg;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_thirdcateg)
    RecyclerView rv_thirdcateg;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_model_selected_value)
    TextView txt_model_selected_value;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_reset)
    Button btn_reset;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_apply)
    Button btn_apply;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view4)
    View view4;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view6)
    View view6;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view16)
    View view16;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view7)
    View view7;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view8)
    View view8;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view9)
    View view9;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view10)
    View view10;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view11)
    View view11;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view12)
    View view12;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view13)
    View view13;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view14)
    View view14;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view15)
    View view15;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rb_5star)
    RadioButton rb_5star;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rb_4star)
    RadioButton rb_4star;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rb_3star)
    RadioButton rb_3star;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rb_2star)
    RadioButton rb_2star;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rb_1star)
    RadioButton rb_1star;

    List<FetchAllBrandsResponse.DataBean.BrandBean> brandsBeanList;

    private static final String TAG = "FilterlistActivity";

    AlertDialog alertDialog;

    DD4YouConfig dd4YouConfig;

    SessionManager sessionManager;

    String userid, ywar = "", maked = "", modelid = "", make_name, model_name;

    List<FetchAllParentMakesResponse.DataBean.MakeBean> makesBeanList;

    List<FetchChildMakeslistRequestResponse.DataBean.ModelBean> modelBeanList;

    List<FetchAllYearResponse.DataBean.YearBean> yearBeanList;

    List<FetchAllColorsResponse.DataBean.ColorsBean> colorsBeanList;

    List<FetchAllParentCategoriesResponse.DataBean.CategoriesBean> categoriesBeanList;

    List<GetEngineSizeResponse.DataBean.EngineSizeBean> engineSizeBeanList;

    List<GetFuelTypesResponse.DataBean.FuelTypesBean> fuelTypesBeanList;

    List<GetTransmissionsResponse.DataBean.TransmissionsBean> transmissionsBeanList;

    ArrayList<String> year = new ArrayList();

    ArrayList<String> make = new ArrayList();

    ArrayList<String> model = new ArrayList();

    ArrayList<String> brand = new ArrayList();

    ArrayList<String> category = new ArrayList();

    ArrayList<String> colors = new ArrayList();

    SearchBrandFilterlistAdapter searchBrandFilterlistAdapter;

    List<FetchChildCateglistResponse.DataBean.CategoriesBean> subcategoriesBeanList ;

    List<GetThirdCategoryResponse.DataBean.CategoriesBean> thirdcategoriesBeanList ;

    boolean isFilter = false;

    String search_text,fromactivity,makesid,modelsid,categid,subcategid,thirdlevelcategid,brandid,engineid,fueltypeid,transmissionid,color, final_min_value,final_max_value,min_pri="0",max_pri = "0",rating;;

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

        ll_year.setOnClickListener(this);

        ll_makes.setOnClickListener(this);

        ll_models.setOnClickListener(this);

        ll_brand.setOnClickListener(this);

        ll_categ.setOnClickListener(this);

        ll_price_range.setOnClickListener(this);

        ll_rating.setOnClickListener(this);

        ll_color.setOnClickListener(this);

        ll_engine_size.setOnClickListener(this);

        ll_fuel_type.setOnClickListener(this);

        ll_transmission.setOnClickListener(this);

        ll_subcateg.setOnClickListener(this);

        ll_thirdcateg.setOnClickListener(this);




        ll_year.setVisibility(View.GONE);

        ll_makes.setVisibility(View.GONE);

        ll_models.setVisibility(View.GONE);

        ll_brand.setVisibility(View.GONE);

        ll_categ.setVisibility(View.GONE);

        ll_price_range.setVisibility(View.GONE);

        ll_rating.setVisibility(View.GONE);

        ll_color.setVisibility(View.GONE);

        ll_engine_size.setVisibility(View.GONE);

        ll_fuel_type.setVisibility(View.GONE);

        ll_transmission.setVisibility(View.GONE);

        ll_subcateg.setVisibility(View.GONE);

        ll_thirdcateg.setVisibility(View.GONE);

        btn_apply.setOnClickListener(this);



        view4.setVisibility(View.GONE);

        view6.setVisibility(View.GONE);

        view16.setVisibility(View.GONE);

        view7.setVisibility(View.GONE);

        view8.setVisibility(View.GONE);

        view9.setVisibility(View.GONE);

        view10.setVisibility(View.GONE);

        view11.setVisibility(View.GONE);

        view12.setVisibility(View.GONE);

        view13.setVisibility(View.GONE);

        view14.setVisibility(View.GONE);

        view15.setVisibility(View.GONE);



        btn_reset.setVisibility(View.GONE);

        btn_apply.setVisibility(View.GONE);

        spin_kit_loadingView.setVisibility(View.VISIBLE);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });

    }

    /* Get Year */

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


    /* Get Makes */

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


    /* Get Model */

    private void modellist() {

        if (expandable_layout_model.isExpanded()) {
            expandable_layout_model.collapse();
            rv_models.setVisibility(View.GONE);
            img_arrow_model.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_model.expand();
            rv_models.setVisibility(View.VISIBLE);
            img_arrow_model.setImageResource(R.drawable.ic_up_arrow);
        }

    }


    private void setModelView(List<FetchChildMakeslistRequestResponse.DataBean.ModelBean> makesBeanList) {

        rv_models.setLayoutManager(new GridLayoutManager(FilterlistActivity.this,2));

        rv_models.setMotionEventSplittingEnabled(false);

        rv_models.setNestedScrollingEnabled(true);

        int size =makesBeanList.size();

        rv_models.setItemAnimator(new DefaultItemAnimator());

        SearchModelFilterlistAdapter searchModelFilterlistAdapter = new SearchModelFilterlistAdapter(FilterlistActivity.this, makesBeanList,this);

        rv_models.setAdapter(searchModelFilterlistAdapter);



    }


    /* Get Brand */

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

        searchBrandFilterlistAdapter = new SearchBrandFilterlistAdapter(FilterlistActivity.this, brandsBeanList, this);

        rv_brand.setAdapter(searchBrandFilterlistAdapter);


    }


    /* Get Category */

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

    /* Get Subcategory */

    private void subcategorylist() {

        if (expandable_layout_subcateg.isExpanded()) {
            expandable_layout_subcateg.collapse();
            rv_subcateg.setVisibility(View.GONE);
            img_arrow_subcateg.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_subcateg.expand();
            rv_subcateg.setVisibility(View.VISIBLE);
            img_arrow_subcateg.setImageResource(R.drawable.ic_up_arrow);
        }

    }

    @SuppressLint("LongLogTag")
    private void fetchallchildcategoriesListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchChildCateglistResponse> call = apiInterface.fetchallchildcateglistResponseCall(RestUtils.getContentType(),fetchChildCateglistRequest());
        Log.w(TAG,"FetchChildCateglistResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<FetchChildCateglistResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FetchChildCateglistResponse> call, @NonNull Response<FetchChildCateglistResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"FetchChildCateglistResponse" + new Gson().toJson(response.body()));

                        subcategoriesBeanList = response.body().getData().getCategories();

                        if(subcategoriesBeanList != null && subcategoriesBeanList.size()>0){


                            setSubcategory(subcategoriesBeanList);
                        }

                        else {


                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }



                }

            }


            @Override
            public void onFailure(@NonNull Call<FetchChildCateglistResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"FetchChildCateglistResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private FetchChildCateglistRequest fetchChildCateglistRequest() {

        /*
         * PARENT_ID : 1
         */

        FetchChildCateglistRequest fetchChildCateglistRequest = new FetchChildCateglistRequest();
        fetchChildCateglistRequest.setPARENT_ID("");

        Log.w(TAG,"FetchChildCateglistRequest "+ new Gson().toJson(fetchChildCateglistRequest));
        return fetchChildCateglistRequest;
    }


    private void setSubcategory(List<FetchChildCateglistResponse.DataBean.CategoriesBean> categoriesBeanList) {

        rv_subcateg.setLayoutManager(new GridLayoutManager(FilterlistActivity.this, 2));

        rv_subcateg.setMotionEventSplittingEnabled(false);

        rv_subcateg.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_subcateg.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_subcateg.setItemAnimator(new DefaultItemAnimator());

        SearchChildCategoriesListAdapter searchChildCategoriesListAdapter = new SearchChildCategoriesListAdapter(FilterlistActivity.this, categoriesBeanList, this);

        rv_subcateg.setAdapter(searchChildCategoriesListAdapter);

    }

    /* Get Thirdcategory */

    private void thirdcategorylist() {

        if (expandable_layout_thirdcateg.isExpanded()) {
            expandable_layout_thirdcateg.collapse();
            rv_thirdcateg.setVisibility(View.GONE);
            img_arrow_thirdcateg.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_thirdcateg.expand();
            rv_thirdcateg.setVisibility(View.VISIBLE);
            img_arrow_thirdcateg.setImageResource(R.drawable.ic_up_arrow);
        }

    }

    @SuppressLint("LongLogTag")
    private void getthirdcategoryListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<GetThirdCategoryResponse> call = apiInterface.getthirdcategoryListResponseCall(RestUtils.getContentType(),fetchthirdcateglistRequest());
        Log.w(TAG,"GetThirdCategoryResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<GetThirdCategoryResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<GetThirdCategoryResponse> call, @NonNull Response<GetThirdCategoryResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"GetThirdCategoryResponse" + new Gson().toJson(response.body()));

                        thirdcategoriesBeanList = response.body().getData().getCategories();

                        if(thirdcategoriesBeanList != null && thirdcategoriesBeanList.size()>0){


                            setThirdcategory(thirdcategoriesBeanList);
                        }

                        else {


                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }



                }

            }


            @Override
            public void onFailure(@NonNull Call<GetThirdCategoryResponse> call, @NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"GetThirdCategoryResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private GetThirdCategoryRequest fetchthirdcateglistRequest() {

        /*
         * PARENT_ID : 1
         */

        GetThirdCategoryRequest fetchChildCateglistRequest = new GetThirdCategoryRequest();
        fetchChildCateglistRequest.setPARENT_ID("");

        Log.w(TAG,"GetThirdCategoryRequest "+ new Gson().toJson(fetchChildCateglistRequest));
        return fetchChildCateglistRequest;
    }

    private void setThirdcategory(List<GetThirdCategoryResponse.DataBean.CategoriesBean> thirdcategoriesBeanList) {

        rv_thirdcateg.setLayoutManager(new GridLayoutManager(FilterlistActivity.this, 2));

        rv_thirdcateg.setMotionEventSplittingEnabled(false);

        rv_thirdcateg.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_thirdcateg.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_thirdcateg.setItemAnimator(new DefaultItemAnimator());

        SearchThirdChildCategoriesListAdapter searchThirdChildCategoriesListAdapter = new SearchThirdChildCategoriesListAdapter(FilterlistActivity.this, thirdcategoriesBeanList, this);

        rv_thirdcateg.setAdapter(searchThirdChildCategoriesListAdapter);

    }


    /* Price Range */

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

    /* Rating list */

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

    /* Color list */

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

    private void setPrice(){

//        if(response.body().getData().getMin_product_price()!=null&&!response.body().getData().getMin_product_price().isEmpty()){
//
//            Log.w(TAG,"MinPrice" + response.body().getData().getMin_product_price());
//
//            min_pri = response.body().getData().getMin_product_price();
//        }
//
//        else {
//
//            min_pri = "0";
//        }
//
//        if(response.body().getData().getMax_product_price()!=null&&!response.body().getData().getMax_product_price().isEmpty()){
//
//            Log.w(TAG,"MaxPrice" + response.body().getData().getMax_product_price());
//
//            max_pri = response.body().getData().getMax_product_price();
//        }
//
//        else {
//
//            max_pri = "0";
//        }

        // get seekbar from view
        final CrystalRangeSeekbar rangeSeekbar = findViewById(R.id.rangeSeekbar);

        final TextView tvMin = findViewById(R.id.txt_min_value);
        final TextView tvMax = findViewById(R.id.txt_max_value);

        rangeSeekbar.setMinValue(Float.valueOf(min_pri));

        rangeSeekbar.setMaxValue(Float.valueOf(max_pri));


        tvMin.setText("$ "+min_pri);

        tvMax.setText("$ "+max_pri);

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText("$ "+String.valueOf(minValue));
                tvMax.setText("$ " +String.valueOf(maxValue));
            }
        });

        // set final value listener
        rangeSeekbar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {

                final_min_value = String.valueOf(minValue);

                final_max_value = String.valueOf(maxValue);

                Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
            }
        });

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

    /* Get engine size */

    private void enginesizelist() {

        if (expandable_layout_engine_size.isExpanded()) {
            expandable_layout_engine_size.collapse();
            rv_engine_size.setVisibility(View.GONE);
            img_arrow_engine_size.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_engine_size.expand();
            rv_engine_size.setVisibility(View.VISIBLE);
            img_arrow_engine_size.setImageResource(R.drawable.ic_up_arrow);
        }

    }

    private void setEngineSizeList(List<GetEngineSizeResponse.DataBean.EngineSizeBean> engineSizeBeanList) {

        rv_engine_size.setLayoutManager(new GridLayoutManager(FilterlistActivity.this, 2));

        rv_engine_size.setMotionEventSplittingEnabled(false);

        rv_engine_size.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_engine_size.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_engine_size.setItemAnimator(new DefaultItemAnimator());

        SearchEnginelistAdapter searchEnginelistAdapter = new SearchEnginelistAdapter(FilterlistActivity.this, engineSizeBeanList, this);

        rv_engine_size.setAdapter(searchEnginelistAdapter);


    }

   /* Get fuel type */

    private void fueltypelist() {

        if (expandable_layout_fuel_type.isExpanded()) {
            expandable_layout_fuel_type.collapse();
            rv_fuel_type.setVisibility(View.GONE);
            img_arrow_fuel_type.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_fuel_type.expand();
            rv_fuel_type.setVisibility(View.VISIBLE);
            img_arrow_fuel_type.setImageResource(R.drawable.ic_up_arrow);
        }

    }


    private void setFuelTypeList(List<GetFuelTypesResponse.DataBean.FuelTypesBean> fuelTypesBeanList) {

        rv_fuel_type.setLayoutManager(new GridLayoutManager(FilterlistActivity.this, 2));

        rv_fuel_type.setMotionEventSplittingEnabled(false);

        rv_fuel_type.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_fuel_type.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_fuel_type.setItemAnimator(new DefaultItemAnimator());

        SearchFuelTypelistAdapter searchFuelTypelistAdapter = new SearchFuelTypelistAdapter(FilterlistActivity.this, fuelTypesBeanList, this);

        rv_fuel_type.setAdapter(searchFuelTypelistAdapter);


    }


    /* Get Transmission */

    private void transmissionlist() {

        if (expandable_layout_transmission.isExpanded()) {
            expandable_layout_transmission.collapse();
            rv_transmission.setVisibility(View.GONE);
            img_arrow_transmission.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_transmission.expand();
            rv_transmission.setVisibility(View.VISIBLE);
            img_arrow_transmission.setImageResource(R.drawable.ic_up_arrow);
        }

    }

    private void setTransmission(List<GetTransmissionsResponse.DataBean.TransmissionsBean> transmissionsBeanList) {

        rv_transmission.setLayoutManager(new GridLayoutManager(FilterlistActivity.this, 2));

        rv_transmission.setMotionEventSplittingEnabled(false);

        rv_transmission.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_transmission.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_transmission.setItemAnimator(new DefaultItemAnimator());

        SearchTransmisionlistAdapter searchTransmisionlistAdapter = new SearchTransmisionlistAdapter(FilterlistActivity.this, transmissionsBeanList, this);

        rv_transmission.setAdapter(searchTransmisionlistAdapter);


    }

    @Override
    public void getYearNameListener(String id, String year_name, CheckBox cb_flist, boolean isChecked) {

        Log.w(TAG, "Selected  Year ID" + id);

        Log.w(TAG, "Selected  Year Name" + year_name);

        if(isChecked){

            cb_flist.setChecked(true);

            year.add(year_name);

            isFilter = true;

            Log.w(TAG, "Year_Values" + new Gson().toJson(year));
        }

        else {

            cb_flist.setChecked(false);

            year.remove(year_name);

            isFilter = false;

            Log.w(TAG, "Year_Values" + new Gson().toJson(year));

        }




    }

    @Override
    public void getMakeIDListener(String id, String make_name, CheckBox cb_flist, boolean isChecked) {

        Log.w(TAG, "Selected  Make ID" + id);

        Log.w(TAG, "Selected  Make Name" + make_name);

        if(isChecked){

            cb_flist.setChecked(true);

            make.add(id);

            isFilter = true;

            Log.w(TAG, "Make_ID" + new Gson().toJson(make));

               /* toString method returns the output as [Data
           Structure,Algorithms,...] In order to replace
           '[', ']' and spaces with empty strings to get
           comma separated values.*/

            String commaseparatedlist = make.toString();

            makesid = commaseparatedlist.replace("[", "")
                    .replace("]", "")
                    .replace(" ", "");

            Log.w(TAG, "commaseparatedlist_Make_ID" + makesid);

        }

        else {

            cb_flist.setChecked(false);

            make.remove(id);

            isFilter = false;

            Log.w(TAG, "Make_ID" + new Gson().toJson(make));

                           /* toString method returns the output as [Data
           Structure,Algorithms,...] In order to replace
           '[', ']' and spaces with empty strings to get
           comma separated values.*/

            String commaseparatedlist = make.toString();

            makesid = commaseparatedlist.replace("[", "")
                    .replace("]", "")
                    .replace(" ", "");


        }
    }


    @Override
    public void getModelIDListener(String id, String model_name, CheckBox cb_flist, boolean isChecked) {

        Log.w(TAG, "Selected  Model ID" + id);

        Log.w(TAG, "Selected  Model Name" + model_name);

        if(isChecked){

            cb_flist.setChecked(true);

            model.add(id);

            isFilter = true;

            Log.w(TAG, "Model_ID" + new Gson().toJson(model));

               /* toString method returns the output as [Data
           Structure,Algorithms,...] In order to replace
           '[', ']' and spaces with empty strings to get
           comma separated values.*/

            String commaseparatedlist = model.toString();

            modelid = commaseparatedlist.replace("[", "")
                    .replace("]", "")
                    .replace(" ", "");

            Log.w(TAG, "commaseparatedlist_Model_ID" + modelid);


        }

        else {

            cb_flist.setChecked(false);

            model.remove(id);

            isFilter = false;

            Log.w(TAG, "Model_ID" + new Gson().toJson(model));

               /* toString method returns the output as [Data
           Structure,Algorithms,...] In order to replace
           '[', ']' and spaces with empty strings to get
           comma separated values.*/

            String commaseparatedlist = model.toString();

            modelid = commaseparatedlist.replace("[", "")
                    .replace("]", "")
                    .replace(" ", "");

            Log.w(TAG, "commaseparatedlist_Model_ID" + modelid);
        }

    }


    @Override
    public void getBrandIDListener(String idd, String brand_name, boolean isChecked) {

        Log.w(TAG, "Selected  Brand ID" + idd);

        Log.w(TAG, "Selected  Brand Name" + brand_name);

        if(isChecked){

            brandid = idd;

            isFilter = true;

        }

        else {

           brandid="";

            isFilter = false;
        }
    }

    @Override
    public void getCategIDListener(String id, String categ_name) {

        Log.w(TAG,"Selected  Categ ID"+id);

        categid = id;

        Log.w(TAG,"Selected  Categ Name"+categ_name);
    }

    @Override
    public void getchildcategIDListener(String id, String childcateg_name) {

        Log.w(TAG,"Selected Child Categ ID"+id);

        subcategid = id;

        Log.w(TAG,"Selected  Child Categ Name"+childcateg_name);
    }

    @Override
    public void getthirdcategIDListener(String id, String thirdcateg_name) {

        Log.w(TAG,"Selected Third Categ ID"+id);

        thirdlevelcategid = id;

        Log.w(TAG,"Selected  third Categ Name"+thirdcateg_name);
    }

    @Override
    public void getColorIDListener(String id, String color_name) {

        Log.w(TAG,"Selected  Color ID"+id);

        color = id;

        Log.w(TAG,"Selected Color Name"+color_name);
    }

    @Override
    public void getengineIDListener(String id, String engine_name) {


        Log.w(TAG,"Selected  engine ID "+id);

        engineid = id;

        Log.w(TAG,"Selected engine Name "+engine_name);
    }

    @Override
    public void getfueltypeIDListener(String id, String fuel_name) {

        Log.w(TAG,"Selected  fuel ID "+id);

        fueltypeid = id;

        Log.w(TAG,"Selected Fuel Name "+fuel_name);
    }

    @Override
    public void gettransmissionIDListener(String id, String transmission_name) {

        Log.w(TAG,"Selected transmission ID "+id);

        transmissionid = id;

        Log.w(TAG,"Selected transmission Name "+transmission_name);
    }

    private void gotoSearchProd() {

        String radioValue = "0";

        if(rb_1star.isChecked()){

            radioValue = "1";
        }

        else if(rb_2star.isChecked()){

            radioValue = "2";
        }

        else if(rb_3star.isChecked()){

            radioValue = "3";
        }

        else if(rb_4star.isChecked()){

            radioValue = "4";
        }

        else if(rb_5star.isChecked()){

            radioValue = "5";
        }

        Log.w(TAG, "year : " + year + "makesid : " + makesid + "modelid : "+ modelid + "brandid : "+ brandid +

                "categid :" + categid + "final_min_value :" + final_min_value + "final_max_value :" +final_max_value

                + "color : " + color + "radioValue : " + radioValue );

                                        /* toString method returns the output as [Data
                                        Structure,Algorithms,...] In order to replace
                                        '[', ']' and spaces with empty strings to get
                                        comma separated values.*/

        String commaseparatedlist = year.toString();

        String years = commaseparatedlist.replace("[", "")
                .replace("]", "")
                .replace(" ", "");

        JSONObject data = new JSONObject();
        try {

            if(!years.isEmpty()){

                data.put("years",years);
            }
            else {

                data.put("years","");
            }
            if(makesid!=null){

                data.put("makesid",makesid);
            }
            else {

                data.put("makesid","");
            }
            if(modelid!=null){

                data.put("modelid",modelid);
            }
            else {

                data.put("modelid","");
            }
            if(brandid!=null){

                data.put("brandid",brandid);
            }
            else {

                data.put("brandid","");
            }

            if(categid!=null){

                data.put("categid",categid);
            }
            else {

                data.put("categid","");
            }
            if(final_min_value!=null){

                data.put("min_value",final_min_value);
            }
            else {

                data.put("min_value","");
            }

            if(final_max_value!=null){

                data.put("max_value",final_max_value);
            }
            else {

                data.put("max_value",final_max_value);
            }


            if(color!=null){

                data.put("color",color);
            }
            else {

                data.put("color","");
            }


            data.put("rate",radioValue);

            Log.w(TAG, "Data" + new Gson().toJson(data));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Intent intent = new Intent(FilterlistActivity.this,SearchProductListActivity.class);

        intent.putExtra("fromactivity",TAG);

        intent.putExtra("search_text",search_text);

        intent.putExtra("data", data.toString());

        startActivity(intent);

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(FilterlistActivity.this,SearchProductListActivity.class);
        intent.putExtra("search_text",search_text);
        intent.putExtra("fromactivity",fromactivity);
        startActivity(intent);
        finish();

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


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.ll_year:

                yearlist();

                break;

            case R.id.ll_makes:

                makelist();

                break;

            case R.id.ll_models:

                modellist();

                break;

            case R.id.ll_brand:

                brandlist();

                break;

            case R.id.ll_categ:

                categlist();

                break;

            case R.id.ll_price_range:

                pricelist();

                break;

            case R.id.ll_rating:

                ratinglist();

                break;

            case R.id.ll_color:

                colorlist();

                break;

            case R.id.ll_engine_size:

                enginesizelist();

                break;

            case R.id.ll_fuel_type:

                fueltypelist();

                break;

            case R.id.ll_transmission:

                transmissionlist();

                break;

            case R.id.ll_subcateg:

                subcategorylist();

                break;

            case R.id.ll_thirdcateg:

                thirdcategorylist();

                break;

            case R.id.btn_apply:

                gotoSearchProd();

                break;


        }

    }



}