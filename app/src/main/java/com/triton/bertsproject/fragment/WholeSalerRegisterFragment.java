package com.triton.bertsproject.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.activities.RegisterActivity;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.customView.CustomEditText;
import com.triton.bertsproject.requestpojo.GetStateRequest;
import com.triton.bertsproject.requestpojo.SignupRequest;
import com.triton.bertsproject.requestpojo.SignupWholesalerRequest;
import com.triton.bertsproject.responsepojo.GetCountryResponse;
import com.triton.bertsproject.responsepojo.GetStateResponse;
import com.triton.bertsproject.responsepojo.SignupResponse;
import com.triton.bertsproject.retailer.RetailerDashboardActivity;
import com.triton.bertsproject.retailer.RetailerProfileAccountActivity;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.RestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WholeSalerRegisterFragment extends Fragment {

    private static final String TAG = "WholeSalerRegisterFragment";

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
    @BindView(R.id.btn_sigin)
    Button btn_sigin;


    View view;

    Dialog alertDialog;

    DD4YouConfig dd4YouConfig;

    String countryid,stateid;


    List<GetCountryResponse.DataBean.CountriesBean> countriesBeanList ;

    List<GetStateResponse.DataBean.StatesBean> statesBeanList ;

    public WholeSalerRegisterFragment() {
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
        view = inflater.inflate(R.layout.fragment_whole_saler_register, container, false);

        ButterKnife.bind(this, view);

        edt_firstname.setTitle(getString(R.string.firstname));

        edt_lastname.setTitle(getString(R.string.lastname));

        edt_email.setTitle(getString(R.string.email));

        edt_password.setTitle(getString(R.string.password));

        edt_cnfmpassword.setTitle(getString(R.string.confirm_password));

        edt_zipcode.setTitle(getString(R.string.zipcode));

        edt_revenue.setTitle(getString(R.string.revenue));

        btn_sigin.setOnClickListener(v -> checkValidation());

        spin_kit_loadingView.setVisibility(View.GONE);

        return view;
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

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, arrayList);

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


        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<>(getContext(), R.layout.spinner_item, arrayList1);

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


    private void checkValidation() {

        boolean isvalid = true;

        String emailPattern = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$";

        String firstname = edt_firstname.edtContent.getText().toString();

        String lastname = edt_lastname.edtContent.getText().toString();

        String email = edt_email.edtContent.getText().toString();

        String password = edt_password.edtContent.getText().toString();

        String cnfm_password = edt_cnfmpassword.edtContent.getText().toString();

        String zipcode = edt_zipcode.edtContent.getText().toString();

        String revenue = edt_revenue.edtContent.getText().toString();


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

        else if(zipcode.equals("")){

            isvalid =false;

            edt_zipcode.setError("Please Fill Zipcode");

            edt_zipcode.requestFocus();
        }

        else if(revenue.equals("")){

            isvalid =false;

            edt_revenue.setError("Please Fill Zipcode");

            edt_revenue.requestFocus();
        }

        else if(countryid.equals("")){

            isvalid =false;

            Toast.makeText(getContext(),"Please Select Country",Toast.LENGTH_SHORT).show();
        }

        else if(stateid.equals("")){

            isvalid =false;

            Toast.makeText(getContext(),"Please Select State",Toast.LENGTH_SHORT).show();
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

                registerResponseCall(firstname,lastname,email,password,cnfm_password,countryid,stateid,revenue,zipcode);

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
    private void registerResponseCall(String firstname, String lastname, String email, String password, String cnfm_password, String country_id, String state_id, String revenue, String zipcode) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SignupResponse> call = apiInterface.signupWholesalerResponseCall(RestUtils.getContentType(),signupWholesalerRequest(firstname,lastname,email,password,cnfm_password,country_id,state_id,revenue,zipcode));
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
                                response.body().getData().getProfile().getAvatar()
                        );

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
    private SignupWholesalerRequest signupWholesalerRequest(String firstname, String lastname, String email, String password, String cnfm_password, String country_id, String state_id, String revenue, String zipcode) {

        /**
         * first_name : testab
         * last_name : testab
         * email : prabhu.imsc@gmail.com
         * password : test1234
         * country_id : 101
         * state_id : 5
         * zip_code : 123456
         * revenue : 10000
         * role : wholesale
         */

        SignupWholesalerRequest signupWholesalerRequest = new SignupWholesalerRequest();
        signupWholesalerRequest.setFirst_name(firstname);
        signupWholesalerRequest.setLast_name(lastname);
        signupWholesalerRequest.setEmail(email);
        signupWholesalerRequest.setPassword(password);
        signupWholesalerRequest.setCountry_id(country_id);
        signupWholesalerRequest.setState_id(state_id);
        signupWholesalerRequest.setRevenue(revenue);
        signupWholesalerRequest.setZip_code(zipcode);
        signupWholesalerRequest.setRole("wholesale");

        Log.w(TAG,"SignupWholesalerRequest "+ new Gson().toJson(signupWholesalerRequest));
        return signupWholesalerRequest;
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