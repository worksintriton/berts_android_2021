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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.triton.bertsproject.R;
import com.triton.bertsproject.adapter.CartProductListAdapter;
import com.triton.bertsproject.model.RetailerProductlistModel;
import com.triton.bertsproject.utils.SwipeToDeleteCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RetailerCartActivity extends AppCompatActivity {

    private static final String TAG = "RetailerCartActivity";

    public static String active_tag = "1";

    CartProductListAdapter cartProductListAdapter;

    Context context = RetailerCartActivity.this;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinatorLayout;

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

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_proceed)
    LinearLayout ll_proceed;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_deliveryaddrchange)
    TextView txt_deliveryaddrchange;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_shipaddrchange)
    TextView txt_shipaddrchange;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_cart);
        ButterKnife.bind( this);
        Log.w("Oncreate", TAG);
        txt_toolbar_title.setText(R.string.cart);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fromactivity = extras.getString("fromactivity");
        }
        spin_kit_loadingView.setVisibility(View.GONE);
        tag = getIntent().getStringExtra("tag");
        Log.w(TAG, " tag : " + this.tag);

        setView();

        img_back.setOnClickListener(v -> {

            onBackPressed();

        });


        ll_proceed.setOnClickListener(v -> startActivity(new Intent(RetailerCartActivity.this,CheckoutScreenActivity.class)));


        txt_deliveryaddrchange.setOnClickListener(v -> {

            startActivity(new Intent(RetailerCartActivity.this, ShippingAddressActivity.class));

            Animatoo.animateSwipeRight(context);
        });

        txt_shipaddrchange.setOnClickListener(v -> {

            startActivity(new Intent(RetailerCartActivity.this, ShippingMethodActivity.class));

            Animatoo.animateSwipeRight(context);
        });

        //btn_proceed.setOnClickListener(v -> startActivity(new Intent(RetailerCartActivity.this,OrderListActivity.class)));

    }
    public void onBackPressed() {

        if (fromactivity!=null&&!fromactivity.isEmpty()) {

            if(fromactivity.equals("HomeFragment")){

                callDirections("1");

            }

            else {

                Intent intent = new Intent(RetailerCartActivity.this,RetailerDashboardActivity.class);
                startActivity(intent);
            }

        }

    }


    public void callDirections(String tag){
        Intent intent = new Intent(RetailerCartActivity.this,RetailerDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();

    }


    private void setView() {

        List<RetailerProductlistModel> retailerProductlistModels = new ArrayList<>();

        retailerProductlistModels.add(new RetailerProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...", "Part No: K5975", "5", "120", "139.20", R.drawable.splist1, false, true));

        rv_productlist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        rv_productlist.setMotionEventSplittingEnabled(false);

        rv_productlist.setItemAnimator(new DefaultItemAnimator());

        cartProductListAdapter = new CartProductListAdapter(this, retailerProductlistModels);

        rv_productlist.setAdapter(cartProductListAdapter);
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

}
