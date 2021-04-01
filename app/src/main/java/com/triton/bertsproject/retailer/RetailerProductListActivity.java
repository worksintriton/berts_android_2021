package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import com.triton.bertsproject.adapter.RetailerProductListAdapter;
import com.triton.bertsproject.model.RetailerProductlistModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @SuppressLint({"NonConstantResourceId", "UseSwitchCompatOrMaterialCode"})
    @BindView(R.id.switch1)
    Switch Switch;
//
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.edt_search)
//    EditText edt_search;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_productlist)
    RecyclerView rv_prodlist;

    private final static String TAG = "RetailerProductListActivity";

    String fromactivity;

    List<RetailerProductlistModel> retailerProductlistModels;

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

        }

        spin_kit_loadingView.setVisibility(View.GONE);

        txt_toolbar_title.setText(R.string.side_view_mirrors);

        img_back.setOnClickListener(v -> {

            startActivity(new Intent(RetailerProductListActivity.this, RetailerDashboardActivity.class));

            Animatoo.animateSwipeLeft(context);
        });

        Switch.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if(isChecked){

                setlistView();
            }

            else {

                setView();
            }

        });

        setView();

    }

    private void setlistView() {

        retailerProductlistModels.clear();

        retailerProductlistModels.add(new RetailerProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...","Part No: K5975","5","120","139.20",R.drawable.splist1,false,false));

        retailerProductlistModels.add(new RetailerProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...","Part No: K5975","5","120","139.20",R.drawable.splist1,false,true));

        retailerProductlistModels.add(new RetailerProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...","Part No: K5975","5","120","139.20",R.drawable.splist1,true,false));

        retailerProductlistModels.add(new RetailerProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...","Part No: K5975","5","120","139.20",R.drawable.splist1,true,true));

        rv_prodlist.setLayoutManager(new LinearLayoutManager(RetailerProductListActivity.this,LinearLayoutManager.VERTICAL,false));

        rv_prodlist.setMotionEventSplittingEnabled(false);

        //int size =3;

        rv_prodlist.setItemAnimator(new DefaultItemAnimator());

        RetailerProductListAdapter retailerProductListAdapter = new RetailerProductListAdapter(RetailerProductListActivity.this, retailerProductlistModels,true);

        rv_prodlist.setAdapter(retailerProductListAdapter);


    }

    private void setView() {

        retailerProductlistModels.clear();

        retailerProductlistModels.add(new RetailerProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...","Part No: K5975","5","120","139.20",R.drawable.splist1,false,false));

        retailerProductlistModels.add(new RetailerProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...","Part No: K5975","5","120","139.20",R.drawable.splist1,false,true));

        retailerProductlistModels.add(new RetailerProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...","Part No: K5975","5","120","139.20",R.drawable.splist1,true,false));

        retailerProductlistModels.add(new RetailerProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...","Part No: K5975","5","120","139.20",R.drawable.splist1,true,true));

        rv_prodlist.setLayoutManager(new GridLayoutManager(RetailerProductListActivity.this, 2));

        rv_prodlist.setMotionEventSplittingEnabled(false);

        //int size =3;

        rv_prodlist.setItemAnimator(new DefaultItemAnimator());

        RetailerProductListAdapter retailerProductListAdapter = new RetailerProductListAdapter(RetailerProductListActivity.this, retailerProductlistModels, false);

        rv_prodlist.setAdapter(retailerProductListAdapter);

      }

  }