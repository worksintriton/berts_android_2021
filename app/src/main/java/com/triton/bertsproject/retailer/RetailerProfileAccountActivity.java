package com.triton.bertsproject.retailer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.customView.CustomEditText;
import com.triton.bertsproject.requestpojo.SignupRequest;
import com.triton.bertsproject.responsepojo.SignupResponse;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.RestUtils;

import java.util.ArrayList;
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

    Dialog alertDialog;

    DD4YouConfig dd4YouConfig;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_profile_account);

        ButterKnife.bind(this);

        txt_toolbar_title.setText(R.string.edit_profile);

        edt_firstname.setTitle(getString(R.string.firstname));

        edt_lastname.setTitle(getString(R.string.lastname));

        edt_email.setTitle(getString(R.string.email));

        edt_password.setTitle(getString(R.string.password));

        edt_cnfmpassword.setTitle(getString(R.string.confirm_password));

        edt_zipcode.setTitle(getString(R.string.zipcode));

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Country");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(RetailerProfileAccountActivity.this, R.layout.spinner_item, arrayList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item); // The drop down view

        sp_country.setAdapter(spinnerArrayAdapter);

        sp_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.hint_color));

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        ArrayList<String> arrayList1 = new ArrayList<>();

        arrayList1.add("State");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<>(RetailerProfileAccountActivity.this, R.layout.spinner_item, arrayList1);

        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinner_item); // The drop down view

        sp_state.setAdapter(spinnerArrayAdapter1);

        sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.hint_color));

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        edt_revenue.setTitle(getString(R.string.revenue));

        spin_kit_loadingView.setVisibility(View.GONE);
    }

    @SuppressLint("LongLogTag")
    private void registerResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SignupResponse> call = apiInterface.signupResponseCall(RestUtils.getContentType(),signupRequest());
        Log.w(TAG,"SignupResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SignupResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<SignupResponse> call, @NonNull Response<SignupResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(response.body().isStatus()) {

                        Log.w(TAG, "SignupResponse" + new Gson().toJson(response.body()));

                        SessionManager sessionManager = new SessionManager(RetailerProfileAccountActivity.this);
                        sessionManager.setIsLogin(true);
                        sessionManager.createLoginSession(
                                response.body().getData().getProfile().getId(),
                                response.body().getData().getProfile().getFirst_name(),
                                response.body().getData().getProfile().getLast_name(),
                                response.body().getData().getProfile().getEmail(),
                                response.body().getData().getProfile().getUser_type(),
                                response.body().getData().getProfile().getAvatar()

                        );

                        startActivity(new Intent(RetailerProfileAccountActivity.this, RetailerDashboardActivity.class));

                    }

                    else {

                      Toast.makeText(RetailerProfileAccountActivity.this,""+response.body().getError_message(),Toast.LENGTH_LONG).show();

                    }

                }

            }


            @Override
            public void onFailure(@NonNull Call<SignupResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"SignupResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private SignupRequest signupRequest() {

        /*
         * first_name : testab
         * last_name : testab
         * email : prabhu.ims2c@gmail.com
         * password : test1234
         * role : retail
         */

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setFirst_name("");
        signupRequest.setLast_name("");
        signupRequest.setEmail("");
        signupRequest.setPassword("");
        signupRequest.setRole("retail");

        Log.w(TAG,"SignupRequest "+ new Gson().toJson(signupRequest));
        return signupRequest;
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

}