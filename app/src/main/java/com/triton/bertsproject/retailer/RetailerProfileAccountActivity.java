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
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.activities.LoginActivity;
import com.triton.bertsproject.activities.RegisterActivity;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.customView.CustomEditText;
import com.triton.bertsproject.requestpojo.SignupRequest;
import com.triton.bertsproject.requestpojo.UpdateProfileRequest;
import com.triton.bertsproject.responsepojo.SignupResponse;
import com.triton.bertsproject.responsepojo.UpdateProfileResponse;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.RestUtils;

import java.util.ArrayList;
import java.util.HashMap;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_profile_account);

        ButterKnife.bind(this);

        Log.w("oncreate",TAG);

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

}