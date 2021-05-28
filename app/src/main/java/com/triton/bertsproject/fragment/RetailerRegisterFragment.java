package com.triton.bertsproject.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.activities.RegisterActivity;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.customView.CustomEditText;
import com.triton.bertsproject.requestpojo.SignupRequest;
import com.triton.bertsproject.responsepojo.FetchAllBrandsResponse;
import com.triton.bertsproject.responsepojo.SignupResponse;
import com.triton.bertsproject.retailer.RetailerDashboardActivity;
import com.triton.bertsproject.retailer.ShowAllBrandsActivity;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.RestUtils;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetailerRegisterFragment extends Fragment {

    private static final String TAG = "RetailerRegisterFragment";

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
    @BindView(R.id.btn_sigin)
    Button btn_sigin;

    View view;

    Dialog alertDialog;

    DD4YouConfig dd4YouConfig;

    String cid ="0",sid="0",zipcode="0",revenue="0";

    public RetailerRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_retailer_register, container, false);

        ButterKnife.bind(this, view);

        btn_sigin.setOnClickListener(v -> checkValidation());

        spin_kit_loadingView.setVisibility(View.GONE);

        dd4YouConfig = new DD4YouConfig(getContext());

        return view;
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

        else if(password.equals("")){

            isvalid =false;

            edt_password.setError("Please Fill Password");

            edt_password.requestFocus();
        }

        else if(cnfm_password.equals("")){

            isvalid =false;

            edt_cnfmpassword.setError("Please Fill Confirm Password");

            edt_cnfmpassword.requestFocus();
        }

        else if(!password.equals(cnfm_password)){

            isvalid =false;

            Toast.makeText(getContext(),"Password and Confirm Password Doesn't Match",Toast.LENGTH_SHORT).show();
        }

        else if(!email.matches(emailPattern)){

            isvalid =false;

            edt_password.setError("Password and Confirm Password Doesn't Match");

            edt_password.requestFocus();

            edt_cnfmpassword.setError("Password and Confirm Password Doesn't Match");

            edt_cnfmpassword.requestFocus();
        }

        if(isvalid){

            if (dd4YouConfig.isInternetConnectivity()) {

                registerResponseCall(firstname,lastname,email,password,cnfm_password);

            }

            else
            {
                callnointernet();

            }


        }
    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(getContext(), RegisterActivity.class));
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @SuppressLint("LongLogTag")
    private void registerResponseCall(String firstname, String lastname, String email, String password, String cnfm_password) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SignupResponse> call = apiInterface.signupResponseCall(RestUtils.getContentType(),signupRequest(firstname,lastname,email,password,cnfm_password));
        Log.w(TAG,"SignupResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SignupResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<SignupResponse> call, @NonNull Response<SignupResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "SignupResponse" + new Gson().toJson(response.body()));

                        SessionManager sessionManager = new SessionManager(getActivity());
                        sessionManager.setIsLogin(true);
                        sessionManager.createLoginSession(
                                response.body().getData().getProfile().getId(),
                                response.body().getData().getProfile().getFirst_name(),
                                response.body().getData().getProfile().getLast_name(),
                                response.body().getData().getProfile().getEmail(),
                                response.body().getData().getProfile().getUser_type(),
                                response.body().getData().getProfile().getAvatar(),
                                response.body().getData().getProfile().getCountry_id(),
                                response.body().getData().getProfile().getState_id(),
                                response.body().getData().getProfile().getZip_code(),
                                response.body().getData().getProfile().getRevenue()


                        );


                        Toasty.success(Objects.requireNonNull(getContext()),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                        startActivity(new Intent(getContext(), RetailerDashboardActivity.class));

                    }

                    else {

                        Toast.makeText(getContext(),""+response.body().getMessage(),Toast.LENGTH_LONG).show();

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
    private SignupRequest signupRequest(String firstname, String lastname, String email, String password, String cnfm_password) {

        /*
         * first_name : testab
         * last_name : testab
         * email : prabhu.ims2c@gmail.com
         * password : test1234
         * role : retail
         */

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setFirst_name(firstname);
        signupRequest.setLast_name(lastname);
        signupRequest.setEmail(email);
        signupRequest.setPassword(password);
        signupRequest.setRole("retail");

        Log.w(TAG,"SignupRequest "+ new Gson().toJson(signupRequest));
        return signupRequest;
    }

    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
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


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


}