package com.dci.berts.retailer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dci.berts.R;
import com.dci.berts.adapter.ChekOutProductListAdapter;
import com.dci.berts.api.APIClient;
import com.dci.berts.api.RestApiInterface;
import com.dci.berts.requestpojo.CouponApplyDetailRequest;
import com.dci.berts.responsepojo.CouponApplyDetailResponse;
import com.dci.berts.sessionmanager.Connectivity;
import com.dci.berts.sessionmanager.SessionManager;
import com.dci.berts.utils.RestUtils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CouponApplyDetailActivity extends AppCompatActivity {

    private static final String TAG = "CouponApplyDetailActivity";

    Context context = CouponApplyDetailActivity.this;

    ChekOutProductListAdapter chekOutProductListAdapter;

    String fromactivity;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_couponlist)
    RecyclerView rv_couponlist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    String tag;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    private DD4YouConfig dd4YouConfig;

    SessionManager sessionManager;

    String user_id;

    String brand_id,brand_name,parent_id,subcategid,subcategname,make_id,model_id,model_name;

    String prod_id,prod_name,shipid;

    String addr_name,address1,state_name,country_name;

    Connectivity connectivity;

    String value,categ_name,make_name,search_text,radioValue="";

    JSONObject data ;

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_apply_detail);

        ButterKnife.bind(this);

        txt_toolbar_title.setText(R.string.coupon_apply);

        Log.w("Oncreate", TAG);

        dd4YouConfig = new DD4YouConfig(this);

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getProfileDetails();

        user_id = user.get(SessionManager.KEY_ID);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            radioValue=extras.getString("radioValue");

            fromactivity = extras.getString("fromactivity");

            prod_id = extras.getString("prod_id");

            //prod_id = "2";

            prod_name = extras.getString("prod_name");

            brand_id = extras.getString("brand_id");

            brand_name = extras.getString("brand_name");

            parent_id = extras.getString("parent_id");

            categ_name = extras.getString("categ_name");

            subcategid = extras.getString("subcategid");

            subcategname = extras.getString("subcategname");

            make_id = extras.getString("make_id");

            make_name = extras.getString("make_name");

            model_id = extras.getString("model_id");

            model_name = extras.getString("model_name");

            search_text = extras.getString("search_text");

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


            Log.w(TAG,"Connectivity fromactivity "+ fromactivity + "brand_id : "+brand_id + "brand_name : "+brand_name+"parent_id : "+parent_id+ "subcategid :" +subcategid

                    + "subcategname : "+subcategname + "make_id : "+make_id + "model_id :" +model_id

                    + "model_name : "+model_name + "search_text : "+search_text);
        }


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        if (dd4YouConfig.isInternetConnectivity()) {

            couponListResponseCall();

        }

        else
        {
            callnointernet();

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gotoCheckout();
    }

    private void gotoCheckout() {

        Intent intent=new Intent(CouponApplyDetailActivity.this,CheckoutScreenActivity.class);

        intent.putExtra("fromactivity",TAG);

        intent.putExtra("prod_id",prod_id);

        intent.putExtra("prod_name",prod_name);

        intent.putExtra("brand_id",brand_id);

        intent.putExtra("brand_name",brand_name);

        intent.putExtra("parent_id",parent_id);

        intent.putExtra("categ_name",categ_name);

        intent.putExtra("subcategid",subcategid);

        intent.putExtra("subcategname",subcategname);

        intent.putExtra("make_id",make_id);

        intent.putExtra("make_name",make_name);

        intent.putExtra("model_id", model_id);

        intent.putExtra("model_name",model_name);

        intent.putExtra("search_text",search_text);

        intent.putExtra("data",data.toString());

        startActivity(intent);

        finish();

    }

    @SuppressLint("LongLogTag")
    private void couponListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<CouponApplyDetailResponse> call = apiInterface.couponListResponseCall(RestUtils.getContentType(),CouponApplyDetailRequest());
        Log.w(TAG,"CouponApplyDetailResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<CouponApplyDetailResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<CouponApplyDetailResponse> call, @NonNull Response<CouponApplyDetailResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                 /*   if(200==response.body().getCode()) {

                        Log.w(TAG, "CouponApplyDetailResponse" + new Gson().toJson(response.body()));

                        showErrorLoading(response.body().getMessage());

                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }*/

                }



            }


            @Override
            public void onFailure(@NonNull Call<CouponApplyDetailResponse> call, @NonNull Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"CouponApplyDetailResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private CouponApplyDetailRequest CouponApplyDetailRequest() {



        CouponApplyDetailRequest CouponApplyDetailRequest = new CouponApplyDetailRequest();
        //CouponApplyDetailRequest.setEmail(email);

        Log.w(TAG,"CouponApplyDetailRequest "+ new Gson().toJson(CouponApplyDetailRequest));
        return CouponApplyDetailRequest;
    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(CouponApplyDetailActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(CouponApplyDetailActivity.this, CouponApplyDetailActivity.class));
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}