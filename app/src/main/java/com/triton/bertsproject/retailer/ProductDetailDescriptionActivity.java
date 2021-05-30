package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.activities.LoginActivity;
import com.triton.bertsproject.adapter.BrandListAdapter;
import com.triton.bertsproject.adapter.ReviewCommentsistAdapter;
import com.triton.bertsproject.adapter.ViewPagerProductDetailsAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.model.RetailerProductlistModel;
import com.triton.bertsproject.requestpojo.ProductDetailRequest;
import com.triton.bertsproject.responsepojo.ProductDetailRespone;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.GridSpacingItemDecoration;
import com.triton.bertsproject.utils.RestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.VISIBLE;

public class ProductDetailDescriptionActivity extends AppCompatActivity {

    Context context = ProductDetailDescriptionActivity.this;

    private final static String TAG = "ProductDetailDescriptionActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_addcart)
    Button btn_addcart;

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
    @BindView(R.id.txt_product_name)
    TextView txt_product_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_parts_no)
    TextView txt_parts_no;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_total_reviews)
    TextView txt_total_reviews;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_price)
    TextView txt_price;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_add_minus)
    LinearLayout ll_add_minus;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_minus)
    ImageView img_minus;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_count)
    TextView txt_count;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_plus)
    ImageView img_plus;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_text_fit)
    LinearLayout ll_text_fit;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_prod_desc)
    CardView cv_prod_desc;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_prod_desc)
    TextView txt_prod_desc;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_manufacturer)
    TextView txt_manufacturer;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_brand)
    TextView txt_brand;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_model)
    TextView txt_model;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_weight)
    TextView txt_weight;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_dimen)
    TextView txt_dimen;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_vehicle_fit)
    CardView cv_vehicle_fit;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_quest_ans)
    CardView cv_quest_ans;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_star_review)
    CardView cv_star_review;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_star_review)
    LinearLayout ll_star_review;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_total_rating_calc)
    TextView txt_total_rating_calc;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_review_countings)
    TextView txt_review_countings;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_review_ratings)
    RecyclerView rv_review_ratings;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.pager)
    ViewPager viewPager;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tabDots)
    TabLayout tabLayout;

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;


    String fromactivity;

    ProductDetailRespone.DataBean dataBeanList;

    List<ProductDetailRespone.DataBean.PrdouctsBean> prdouctsBeanList ;

    List<ProductDetailRespone.DataBean.PrdouctsBean.ReviewsDetailsBean> reviewsDetailsBeanList ;

    List<String> imageList;

    String prod_id,prod_name;

//    private DD4YouNetReceiver dd4YouNetReceiver;

    private DD4YouConfig dd4YouConfig;

    AlertDialog alertDialog;

    String user_id;

//    private DD4YouNetReceiver dd4YouNetReceiver;

    SessionManager sessionManager;

    String brand_id,brand_name,parent_id,subcategid,subcategname,make_id,model_id,model_name;




    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_description);

        ButterKnife.bind(this);

        Log.w("Oncreate", TAG);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            fromactivity = extras.getString("fromActivity");

            prod_id = extras.getString("prod_id");

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


        if(prod_name!=null&&!prod_name.isEmpty()){

            txt_toolbar_title.setText(prod_name);

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

        img_back.setOnClickListener(v -> {

            onBackPressed();
        });

        txt_product_name.setVisibility(View.GONE);

        txt_parts_no.setVisibility(View.GONE);

        txt_total_reviews.setVisibility(View.GONE);

        txt_price.setVisibility(View.GONE);

        ll_add_minus.setVisibility(View.GONE);

        img_minus.setVisibility(View.GONE);

        txt_count.setVisibility(View.GONE);

        img_plus.setVisibility(View.GONE);

        ll_text_fit.setVisibility(View.GONE);

        cv_prod_desc.setVisibility(View.GONE);

        txt_prod_desc.setVisibility(View.GONE);

        txt_manufacturer.setVisibility(View.GONE);

        txt_brand.setVisibility(View.GONE);

        txt_model.setVisibility(View.GONE);

        txt_weight.setVisibility(View.GONE);

        txt_dimen.setVisibility(View.GONE);

        cv_vehicle_fit.setVisibility(View.GONE);

        cv_quest_ans.setVisibility(View.GONE);

        cv_star_review.setVisibility(View.GONE);

        ll_star_review.setVisibility(View.GONE);

        txt_total_rating_calc.setVisibility(View.GONE);

        txt_review_countings.setVisibility(View.GONE);

        rv_review_ratings.setVisibility(View.GONE);

        viewPager.setVisibility(View.GONE);

        tabLayout.setVisibility(View.GONE);


        btn_addcart.setOnClickListener(v -> startActivity(new Intent(ProductDetailDescriptionActivity.this,RetailerCartActivity.class)));

    }

    @Override
    public void onBackPressed() {

        if(fromactivity.equals("RetailerProductListActivity")){

            Intent intent = new Intent(ProductDetailDescriptionActivity.this,RetailerProductListActivity.class);

            intent.putExtra("brand_id",brand_id);

            intent.putExtra("brand_name",brand_name);

            intent.putExtra("prod_id",prod_id);

            intent.putExtra("prod_name",prod_name);

            intent.putExtra("fromActivity",TAG);

            startActivity(intent);


        }

        else if(fromactivity.equals("RetailerProductListBasedOnCategActivity")){

            Intent intent = new Intent(ProductDetailDescriptionActivity.this,RetailerProductListBasedOnCategActivity.class);

            intent.putExtra("parent_id",parent_id);

            intent.putExtra("subcategid",subcategid);

            intent.putExtra("subcategname",subcategname);

            intent.putExtra("prod_id",prod_id);

            intent.putExtra("prod_name",prod_name);

            intent.putExtra("fromActivity",TAG);

            startActivity(intent);


        }

        else if(fromactivity.equals("RetailerProductListBasedOnMakeActivity")){

            Intent intent = new Intent(ProductDetailDescriptionActivity.this,RetailerProductListBasedOnMakeActivity.class);

            intent.putExtra("make_id",make_id);

            intent.putExtra("model_id", model_id);

            intent.putExtra("model_id",model_name);

            intent.putExtra("prod_id",prod_id);

            intent.putExtra("prod_name",prod_name);

            intent.putExtra("fromActivity",TAG);

            startActivity(intent);


        }


        Intent intent = new Intent(ProductDetailDescriptionActivity.this, ShowAllBrandsActivity.class);

        intent.putExtra("fromactivity",TAG);

        context.startActivity(intent);

        Animatoo.animateSwipeLeft(context);

        finish();

    }


    @SuppressLint("LongLogTag")
    private void fetchallproductsListResponseCall() {

        spin_kit_loadingView.setVisibility(VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ProductDetailRespone> call = apiInterface.fetchproductdetailsResponseCall(RestUtils.getContentType(), ProductDetailRequest());
        Log.w(TAG,"ProductDetailRespone url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<ProductDetailRespone>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<ProductDetailRespone> call, @NonNull Response<ProductDetailRespone> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"ProductDetailRespone" + new Gson().toJson(response.body()));

                        prdouctsBeanList = response.body().getData().getPrdoucts();

                        if(prdouctsBeanList != null && prdouctsBeanList.size()>0){

                            setView(prdouctsBeanList);
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
            public void onFailure(@NonNull Call<ProductDetailRespone> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"ProductDetailRespone flr"+t.getMessage());

            }
        });



    }

    @SuppressLint("LongLogTag")
    private ProductDetailRequest ProductDetailRequest() {

        /*
         * MODE : DETAIL
         * PRODUCT_ID : 2
         */

        ProductDetailRequest ProductDetailRequest = new ProductDetailRequest();
        ProductDetailRequest.setPRODUCT_ID(prod_id);
        ProductDetailRequest.setMODE("DETAIL");

        Log.w(TAG,"ProductDetailRequest "+ new Gson().toJson(ProductDetailRequest));
        return ProductDetailRequest;
    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(ProductDetailDescriptionActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(ProductDetailDescriptionActivity.this,ProductDetailDescriptionActivity.class));
            finish();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setView(List<ProductDetailRespone.DataBean.PrdouctsBean> prdouctsBeanList) {

        txt_product_name.setVisibility(VISIBLE);

        txt_parts_no.setVisibility(VISIBLE);

        txt_total_reviews.setVisibility(VISIBLE);

        txt_price.setVisibility(VISIBLE);

        ll_add_minus.setVisibility(VISIBLE);

        img_minus.setVisibility(VISIBLE);

        txt_count.setVisibility(VISIBLE);

        img_plus.setVisibility(VISIBLE);

        ll_text_fit.setVisibility(VISIBLE);

        cv_prod_desc.setVisibility(VISIBLE);

        txt_prod_desc.setVisibility(VISIBLE);

//        txt_manufacturer.setVisibility(VISIBLE);
//
//        txt_brand.setVisibility(VISIBLE);
//
//        txt_model.setVisibility(VISIBLE);
//
//        txt_weight.setVisibility(VISIBLE);
//
//        txt_dimen.setVisibility(VISIBLE);
//
//        cv_vehicle_fit.setVisibility(View.GONE);
//
//        cv_quest_ans.setVisibility(View.GONE);

        cv_star_review.setVisibility(VISIBLE);

        ll_star_review.setVisibility(VISIBLE);

        txt_total_rating_calc.setVisibility(VISIBLE);

        txt_review_countings.setVisibility(VISIBLE);

        rv_review_ratings.setVisibility(VISIBLE);

        viewPager.setVisibility(VISIBLE);

        tabLayout.setVisibility(VISIBLE);

        if(dataBeanList.getPrdoucts().get(0).getTitle()!=null&&!dataBeanList.getPrdoucts().get(0).getTitle().isEmpty()) {

            txt_product_name.setText(dataBeanList.getPrdoucts().get(0).getTitle());
        }

        if(dataBeanList.getPrdoucts().get(0).getPart_number()!=null&&!dataBeanList.getPrdoucts().get(0).getPart_number().isEmpty()){

            txt_parts_no.setText(dataBeanList.getPrdoucts().get(0).getPart_number());
        }

        if(dataBeanList.getPrdoucts().get(0).getReviews_ratings()!=0){

            txt_total_reviews.setText("( "+dataBeanList.getPrdoucts().get(0).getReviews_ratings()+ " Reviews)");
        }

        if(dataBeanList.getPrdoucts().get(0).getImages()!=null&&!dataBeanList.getPrdoucts().get(0).getImages().isEmpty()){

            if(dataBeanList.getPrdoucts().get(0).getImages().size()>0){

                for(int i = 0; i<= dataBeanList.getPrdoucts().get(0).getImages().size();i++){

                    imageList.add(dataBeanList.getPrdoucts().get(0).getImages().get(i).getImage_default());
                }
            }
        }

        if(imageList.size()>0){

            viewpageData(imageList);
        }

        if(dataBeanList.getPrdoucts().get(0).getPrice()!=null&&!dataBeanList.getPrdoucts().get(0).getPrice().isEmpty()){

            txt_price.setText(dataBeanList.getPrdoucts().get(0).getPart_number());
        }

        if(dataBeanList.getPrdoucts().get(0).getDescription()!=null&&!dataBeanList.getPrdoucts().get(0).getDescription().isEmpty()){

            txt_prod_desc.setText(dataBeanList.getPrdoucts().get(0).getDescription());
        }

//        if(dataBeanList.getPrdoucts().get(0).getBrand_id()!=null&&!dataBeanList.getPrdoucts().get(0).getDescription().isEmpty()){
//
//            txt_brand.setText(dataBeanList.getPrdoucts().get(0).getDescription());
//        }

        if(dataBeanList.getPrdoucts().get(0).getWeight()!=null&&!dataBeanList.getPrdoucts().get(0).getWeight().isEmpty()){

            txt_weight.setText("( "+dataBeanList.getPrdoucts().get(0).getWeight() + " ) pounds");
        }

        if(dataBeanList.getPrdoucts().get(0).getRating()!=null&&!dataBeanList.getPrdoucts().get(0).getRating().isEmpty()){

            txt_total_rating_calc.setText(" "+dataBeanList.getPrdoucts().get(0).getRating() + " Out of 5");
        }

        if(dataBeanList.getPrdoucts().get(0).getReviews_comments()!=0){

            txt_review_countings.setText("( "+dataBeanList.getPrdoucts().get(0).getReviews_comments() + " ) Reviews");
        }

        reviewsDetailsBeanList = dataBeanList.getPrdoucts().get(0).getReviews_details();

        if(reviewsDetailsBeanList!=null&&reviewsDetailsBeanList.size()>0){

            setReviewComments(reviewsDetailsBeanList);
        }


    }

    private void setReviewComments(List<ProductDetailRespone.DataBean.PrdouctsBean.ReviewsDetailsBean> reviewsDetailsBeanList) {

        rv_review_ratings.setLayoutManager(new LinearLayoutManager(ProductDetailDescriptionActivity.this));

        rv_review_ratings.setMotionEventSplittingEnabled(false);

        rv_review_ratings.setNestedScrollingEnabled(true);

        int size =reviewsDetailsBeanList.size();

        rv_review_ratings.setItemAnimator(new DefaultItemAnimator());

        ReviewCommentsistAdapter reviewCommentsistAdapter = new ReviewCommentsistAdapter(ProductDetailDescriptionActivity.this, reviewsDetailsBeanList,size);

        rv_review_ratings.setAdapter(reviewCommentsistAdapter);

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
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ProductDetailDescriptionActivity.this);
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

    private void viewpageData(List<String> product_img) {
        tabLayout.setupWithViewPager(viewPager, true);

        ViewPagerProductDetailsAdapter viewPagerProductDetailsAdapter = new ViewPagerProductDetailsAdapter(getApplicationContext(), product_img);
        viewPager.setAdapter(viewPagerProductDetailsAdapter);
        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = () -> {
            if (currentPage == product_img.size()) {
                currentPage = 0;
            }
            viewPager.setCurrentItem(currentPage++, false);
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    }

    private void showAlert() {

        AlertDialog.Builder builder=new AlertDialog.Builder(ProductDetailDescriptionActivity.this);
        builder.setTitle("Alert");
        builder.setMessage("Please Login to add Products in wishlist");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            startActivity(new Intent(ProductDetailDescriptionActivity.this, LoginActivity.class));
            finish();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}