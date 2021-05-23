package com.triton.bertsproject.retailer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.activities.LoginActivity;
import com.triton.bertsproject.activities.RegisterActivity;
import com.triton.bertsproject.adapter.ParentCategoriesListAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.customView.CustomEditText;
import com.triton.bertsproject.requestpojo.GetStateRequest;
import com.triton.bertsproject.requestpojo.SignupRequest;
import com.triton.bertsproject.requestpojo.UpdateProfileRequest;
import com.triton.bertsproject.responsepojo.FetchAllParentCategoriesResponse;
import com.triton.bertsproject.responsepojo.GetCountryResponse;
import com.triton.bertsproject.responsepojo.GetStateResponse;
import com.triton.bertsproject.responsepojo.SignupResponse;
import com.triton.bertsproject.responsepojo.UpdateProfileResponse;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.GridSpacingItemDecoration;
import com.triton.bertsproject.utils.RestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetailerProfileAccountActivity extends AppCompatActivity {

    private static final String TAG = "RetailerProfileAccountActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_firstname)
    CustomEditText edt_firstname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_lastname)
    CustomEditText edt_lastname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_email)
    CustomEditText edt_email;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_password)
    CustomEditText edt_password;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_cnfmpassword)
    CustomEditText edt_cnfmpassword;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_zipcode)
    CustomEditText edt_zipcode;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sp_country)
    Spinner sp_country;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sp_state)
    Spinner sp_state;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_revenue)
    CustomEditText edt_revenue;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_update)
    Button btn_update;

    Dialog alertDialog;

    DD4YouConfig dd4YouConfig;

    SessionManager sessionManager;

    String userid, firstname, lastname, zipcode, revenue, countryid, stateid,email;

    List<GetCountryResponse.DataBean.CountriesBean> countriesBeanList ;

    List<GetStateResponse.DataBean.StatesBean> statesBeanList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_profile_account);

        ButterKnife.bind(this);

        Log.w("oncreate",TAG);

        dd4YouConfig = new DD4YouConfig(this);

        txt_toolbar_title.setText(R.string.edit_profile);

        edt_firstname.setTitle(getString(R.string.firstname));

        edt_lastname.setTitle(getString(R.string.lastname));

        edt_email.setTitle(getString(R.string.email));

        edt_password.setTitle(getString(R.string.password));

        edt_cnfmpassword.setTitle(getString(R.string.confirm_password));

        edt_zipcode.setTitle(getString(R.string.zipcode));

        edt_revenue.setTitle(getString(R.string.revenue));

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getProfileDetails();

        if(sessionManager.isLoggedIn())
        {
            userid = user.get(SessionManager.KEY_ID);

            firstname = user.get(SessionManager.KEY_FIRST_NAME);

            lastname = user.get(SessionManager.KEY_LAST_NAME);

            email = user.get(SessionManager.KEY_EMAIL_ID);

//            zipcode = user.get(SessionManager.KEY_ZIPCODE);

        }

        if(firstname!=null&&!firstname.isEmpty()){

            edt_firstname.setTitle(firstname);
        }

        if(lastname!=null&&!lastname.isEmpty()){

            edt_lastname.setTitle(lastname);
        }

        if(email!=null&&!email.isEmpty()){

            edt_email.setTitle(email);
        }

//        if(zipcode!=null&&!zipcode.isEmpty()){
//
//            edt_zipcode.setTitle(zipcode);
//        }

        edt_revenue.setVisibility(View.GONE);

        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallcountryListResponseCall();

        }

        else
        {
            callnointernet();

        }


        btn_update.setOnClickListener(v -> {

            if (dd4YouConfig.isInternetConnectivity()) {

                checkValidation();

            }

            else
            {
                callnointernet();

            }


        });



        spin_kit_loadingView.setVisibility(View.GONE);
    }

    private void checkValidation() {

        boolean isvalid = true;

        String emailPattern = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$";

        String firstname = edt_firstname.edtContent.getText().toString();

        String lastname = edt_lastname.edtContent.getText().toString();

        String email = edt_email.edtContent.getText().toString();

        String password = edt_password.edtContent.getText().toString();

        String cnfm_password = edt_cnfmpassword.edtContent.getText().toString();

        if(firstname.equals("")){

            isvalid =false;

            edt_firstname.setError("Please Fill First Name");

            edt_firstname.requestFocus();
        }

        else if(lastname.equals("")){

            isvalid =false;

            edt_lastname.setError("Please Fill Last Name");

            edt_lastname.requestFocus();
        }

        else if(email.equals("")){

            isvalid =false;

            edt_email.setError("Please Fill Mail ID");

            edt_email.requestFocus();
        }

        else if(!email.matches(emailPattern)){

            edt_email.setError("Please enter correct email address");

            edt_email.requestFocus();

            isvalid = false;
        }

        if(isvalid){

            if (dd4YouConfig.isInternetConnectivity()) {

                registerResponseCall(firstname,lastname,email);

            }

            else
            {
                callnointernet();

            }


        }
    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(RetailerProfileAccountActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(RetailerProfileAccountActivity.this, RetailerProfileAccountActivity.class));
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    @SuppressLint("LongLogTag")
    private void registerResponseCall(String firstname, String lastname, String email) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<UpdateProfileResponse> call = apiInterface.updateResponseCall(RestUtils.getContentType(),updateProfileRequest(firstname,lastname,email));
        Log.w(TAG,"UpdateProfileResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<UpdateProfileResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<UpdateProfileResponse> call, @NonNull Response<UpdateProfileResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(response.body().isStatus()) {

                        Log.w(TAG, "UpdateProfileResponse" + new Gson().toJson(response.body()));

                        SessionManager sessionManager = new SessionManager(RetailerProfileAccountActivity.this);
                        sessionManager.setIsLogin(true);
                        sessionManager.createLoginSession(
                                response.body().getData().getId(),
                                response.body().getData().getFirst_name(),
                                response.body().getData().getLast_name(),
                                response.body().getData().getEmail(),
                                response.body().getData().getUser_type(),
                                response.body().getData().getAvatar()

                        );

                        startActivity(new Intent(RetailerProfileAccountActivity.this, RetailerDashboardActivity.class));

                    }

                    else {

                      Toast.makeText(RetailerProfileAccountActivity.this,""+response.body().getError_message(),Toast.LENGTH_LONG).show();

                    }

                }

            }


            @Override
            public void onFailure(@NonNull Call<UpdateProfileResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"UpdateProfileResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private UpdateProfileRequest updateProfileRequest(String firstname, String lastname, String email) {

        /*
         * first_name : testl
         * last_name : testc
         * about_me : testc_abt
         * id : 541
         */

        UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest();
        updateProfileRequest.setFirst_name(firstname);
        updateProfileRequest.setLast_name(lastname);
        updateProfileRequest.setId(userid);
        updateProfileRequest.setAbout_me("testc_abt");

        Log.w(TAG,"UpdateProfileRequest "+ new Gson().toJson(updateProfileRequest));
        return updateProfileRequest;
    }

    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RetailerProfileAccountActivity.this);
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

    /* Get Country */

    @SuppressLint("LongLogTag")
    private void fetchallcountryListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<GetCountryResponse> call = apiInterface.fetchallcountryListResponseCall(RestUtils.getContentType());
        Log.w(TAG,"GetCountryResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<GetCountryResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<GetCountryResponse> call, @NonNull Response<GetCountryResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"GetCountryResponse" + new Gson().toJson(response.body()));

                        countriesBeanList = response.body().getData().getCountries();

                        if(countriesBeanList != null && countriesBeanList.size()>0){


                            setCountry(countriesBeanList);


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
            public void onFailure(@NonNull Call<GetCountryResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"GetCountryResponse flr"+t.getMessage());
            }
        });



    }


    private void setCountry(List<GetCountryResponse.DataBean.CountriesBean> countriesBeanList) {

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Select Country");

        for(int i=0;i<countriesBeanList.size();i++){

            arrayList.add(countriesBeanList.get(i).getName());
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(RetailerProfileAccountActivity.this, R.layout.spinner_item, arrayList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item); // The drop down view

        sp_country.setAdapter(spinnerArrayAdapter);

        sp_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.hint_color));

                countryid =  countriesBeanList.get(position).getId() ;

                Log.w(TAG,"country_id "+countryid);

                if (dd4YouConfig.isInternetConnectivity()) {

                    fetchallstateListResponseCall(countryid);

                }

                else
                {
                    callnointernet();

                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });



    }

    /* Get Country */

    @SuppressLint("LongLogTag")
    private void fetchallstateListResponseCall(String countryid) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<GetStateResponse> call = apiInterface.fetchallstateListResponseCall(RestUtils.getContentType(),getStateRequest(countryid));
        Log.w(TAG,"GetStateResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<GetStateResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<GetStateResponse> call, @NonNull Response<GetStateResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"GetCountryResponse" + new Gson().toJson(response.body()));

                        statesBeanList = response.body().getData().getStates();

                        if(countriesBeanList != null && countriesBeanList.size()>0){


                            setState(statesBeanList);


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
            public void onFailure(@NonNull Call<GetStateResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"GetStateResponse flr"+t.getMessage());
            }
        });



    }


    private void setState(List<GetStateResponse.DataBean.StatesBean> statesBeanList) {

        ArrayList<String> arrayList1 = new ArrayList<>();

        arrayList1.add("Select State");

        for(int i=0;i<statesBeanList.size();i++){

            arrayList1.add(statesBeanList.get(i).getName());
        }


        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<>(RetailerProfileAccountActivity.this, R.layout.spinner_item, arrayList1);

        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinner_item); // The drop down view

        sp_state.setAdapter(spinnerArrayAdapter1);

        sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.hint_color));

                stateid = statesBeanList.get(arg2).getId();

                Log.w(TAG,"stateid "+stateid);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });



    }

    @SuppressLint("LongLogTag")
    private GetStateRequest getStateRequest(String countryid) {

        /*
         * COUNTRY_ID : 101
         */

        GetStateRequest getStateRequest = new GetStateRequest();
        getStateRequest.setCOUNTRY_ID(countryid);

        Log.w(TAG,"GetStateRequest "+ new Gson().toJson(getStateRequest));
        return getStateRequest;
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



}