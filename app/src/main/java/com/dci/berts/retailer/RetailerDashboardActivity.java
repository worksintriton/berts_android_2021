package com.dci.berts.retailer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.dci.berts.R;
import com.dci.berts.retailerfragment.HomeFragment;
import com.dci.berts.retailerfragment.LiveChatFragment;
import com.dci.berts.retailerfragment.MyGarageFragment;
import com.dci.berts.retailerfragment.ProfileFragment;
import com.dci.berts.retailerfragment.ShopFragment;
import com.dci.berts.sessionmanager.Connectivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RetailerDashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;

    private final static String TAG = "DashboardActivity";

    final Fragment HomeFragment = new HomeFragment();
    final Fragment MyGarageFragment = new MyGarageFragment();
    final Fragment ShopFragment = new ShopFragment();
    final Fragment LiveChatFragment = new LiveChatFragment();
    final Fragment ProfileFragment = new ProfileFragment();

    public static String active_tag = "1";


    Fragment active = HomeFragment;
    String tag;

    String fromactivity;

    Connectivity connectivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_retailer_dashboard);

        ButterKnife.bind(this);

        Log.w("Oncreate", TAG);

        connectivity = new Connectivity();

        connectivity.clearData(RetailerDashboardActivity.this,"ParentCategories");

        connectivity.clearData(RetailerDashboardActivity.this,"ChildCategories");

        connectivity.clearData(RetailerDashboardActivity.this,"ProductListCategories");

        connectivity.clearData(RetailerDashboardActivity.this,"ProductDetailList");

        connectivity.clearData(RetailerDashboardActivity.this,"RetailerCart");

        connectivity.clearData(RetailerDashboardActivity.this,"CheckoutScreen");

        connectivity.clearData(RetailerDashboardActivity.this,"Brand");

        connectivity.clearData(RetailerDashboardActivity.this,"BrandProduct");

        connectivity.clearData(RetailerDashboardActivity.this,"ParentMakes");

        connectivity.clearData(RetailerDashboardActivity.this,"ChildMakes");

        connectivity.clearData(RetailerDashboardActivity.this,"MakesProductList");

        connectivity.clearData(RetailerDashboardActivity.this,"SearchProductList");

        floatingActionButton.setImageResource(R.drawable.berts_logo_fb);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                active = ShopFragment;
                bottomNavigation.setSelectedItemId(R.id.shop);
                loadFragment(new ShopFragment());
            }
        });

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            fromactivity = extras.getString("fromactivity");

            Log.w(TAG," fromactivity : "+fromactivity);
        }

        tag = getIntent().getStringExtra("tag");

        Log.w(TAG," tag : "+tag);

        if(tag != null){
            if(tag.equalsIgnoreCase("1")){
                active = HomeFragment;
                bottomNavigation.setSelectedItemId(R.id.home);

                loadFragment(new HomeFragment());
            }else if(tag.equalsIgnoreCase("2")){
                active = MyGarageFragment;
                bottomNavigation.setSelectedItemId(R.id.garage);
                loadFragment(new MyGarageFragment());
            }else if(tag.equalsIgnoreCase("3")){
                active = ShopFragment;
                bottomNavigation.setSelectedItemId(R.id.shop);
                loadFragment(new ShopFragment());
            }else if(tag.equalsIgnoreCase("4")){
                active = LiveChatFragment;
                bottomNavigation.setSelectedItemId(R.id.chat);
                loadFragment(new LiveChatFragment());
            } else if(tag.equalsIgnoreCase("5")){
                active = ProfileFragment;
                bottomNavigation.setSelectedItemId(R.id.profile);
                loadFragment(new ProfileFragment());

            }
        }else{
            bottomNavigation.setSelectedItemId(R.id.home);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, active, active_tag);
            transaction.commitNowAllowingStateLoss();
        }
        bottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    private void loadFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        // set Fragmentclass Arguments
            fragment.setArguments(bundle);

            // load fragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();

    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commitNowAllowingStateLoss();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                active_tag = "1";
                replaceFragment(new HomeFragment());
                break;
            case R.id.garage:
                active_tag = "2";
                replaceFragment(new MyGarageFragment());
                break;
            case R.id.shop:
                active_tag = "3";
                replaceFragment(new ShopFragment());
                break;
            case R.id.chat:
                active_tag = "4";
               // replaceFragment(new LiveChatFragment());
                break;
            case R.id.profile:
                active_tag = "5";
                replaceFragment(new ProfileFragment());
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

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_logout_layout, null);
        dialogBuilder.setView(dialogView);

        RelativeLayout rl_yes = dialogView.findViewById(R.id.rl_yes);

        RelativeLayout rl_no = dialogView.findViewById(R.id.rl_no);


        TextView txt_alert_title = dialogView.findViewById(R.id.txt_alert_title);

        txt_alert_title.setText("Are you sure want to exit");

        TextView alert_header_txtview = dialogView.findViewById(R.id.alert_header_txtview);

        alert_header_txtview.setText("Exit App");


        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        rl_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });

        rl_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RetailerDashboardActivity.this.finish();

            }
        });
    }

}