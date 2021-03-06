package com.dci.berts.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.dci.berts.R;
import com.dci.berts.activities.RegisterActivity;
import com.dci.berts.api.APIClient;
import com.dci.berts.api.RestApiInterface;
import com.dci.berts.customView.CustomEditText;
import com.dci.berts.requestpojo.SignupRequest;
import com.dci.berts.responsepojo.SignupResponse;
import com.dci.berts.retailer.ProductDetailDescriptionActivity;
import com.dci.berts.retailer.RetailerCartActivity;
import com.dci.berts.retailer.RetailerDashboardActivity;
import com.dci.berts.retailer.RetailerProductListActivity;
import com.dci.berts.retailer.RetailerProductListBasedOnCategActivity;
import com.dci.berts.retailer.RetailerProductListBasedOnMakeActivity;
import com.dci.berts.retailer.SearchProductListActivity;
import com.dci.berts.retailer.TermsAndConditionsActivity;
import com.dci.berts.sessionmanager.SessionManager;
import com.dci.berts.utils.RestUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
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

//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.login_button)
//    LoginButton btnLogin;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_sigin_with_facebook)
    Button btn_sigin_with_facebook;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_or)
    TextView txt_or;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_terms_conditions)
    TextView txt_terms_conditions;

    View view;

    Dialog alertDialog;

    DD4YouConfig dd4YouConfig;

    String cid ="0",sid="0",zipcode="0",revenue="0";

    String fromActivity;

    CallbackManager callbackManager;

    private String SMedia;

    String strName,strCurrentemail,strSMedia;

    String register_mode = "Manual";

    String firstname,lastname,email,password,cnfm_password,facebook_id;

    JSONObject data,json_data;

    String brand_id,brand_name,parent_id,subcategid,categ_name,subcategname,make_id,make_name,model_id,model_name, prod_id,prod_name;;

    String search_text , quantity, unit_price;

    public RetailerRegisterFragment(String fromActivity, JSONObject data, JSONObject json_data) {
        // Required empty public constructor
        this.fromActivity=fromActivity;
        this.data=data;
        this.json_data = json_data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("LongLogTag")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FacebookSdk.sdkInitialize(getActivity());
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_retailer_register, container, false);

        ButterKnife.bind(this, view);

        dd4YouConfig = new DD4YouConfig(getContext());

        Log.w("Oncreate ", TAG + "fromActivity " +fromActivity);

        Log.w(TAG, "Data" + new Gson().toJson(data));

        if(data!=null){

            try {
                prod_id = data.getString("prod_id");

                //prod_id = "2";

                prod_name = data.getString("prod_name");

                brand_id = data.getString("brand_id");

                brand_name = data.getString("brand_name");

                parent_id = data.getString("parent_id");

                categ_name = data.getString("categ_name");

                subcategid = data.getString("subcategid");

                subcategname = data.getString("subcategname");

                make_id = data.getString("make_id");

                make_name = data.getString("make_name");

                model_id = data.getString("model_id");

                model_name = data.getString("model_name");

                search_text = data.getString("search_text");

                quantity = data.getString("quantity");

                unit_price = data.getString("unit_price");


                Log.w(TAG,"brand_id "+brand_id+"brand_name "+ brand_name+"parent_id : "+parent_id+ "categ_name : "+categ_name+"subcategid :" +subcategid

                        + "subcategname : "+subcategname +

                        "make_id : "+make_id +"make_name :" +make_name +"model_id :" +model_id

                        + "model_name : "+model_name+ "quantity "+quantity+ "unit_price : "+unit_price+

                        "search_text : "+search_text+

                        "fromactivity :" +fromActivity);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

//        btnLogin=view.findViewById(R.id.login_button);
//
//        btnLogin.setFragment(this);

        getKeyHash();

        callbackManager = CallbackManager.Factory.create();

     //   btnLogin.setPermissions(Arrays.asList("user_photos", "email", "public_profile", "user_posts"));

        String text = "<font color=#FF000000> with Bert's Auto</font> <font color=#014492>Terms and Condition</font>";

        txt_terms_conditions.setText(Html.fromHtml(text));

        txt_terms_conditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), TermsAndConditionsActivity.class);
                intent.putExtra("fromactivity",TAG);
                startActivity(intent);

            }
        });


//        // Callback registration
//        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                GraphRequest request = GraphRequest.newMeRequest(
//                        loginResult.getAccessToken(),
//                        new GraphRequest.GraphJSONObjectCallback() {
//
//                            @SuppressLint("LongLogTag")
//                            @Override
//                            public void onCompleted(JSONObject object, GraphResponse response) {
//
//                                Log.w(TAG, "Facebook" + object);
//
//                                Log.v("Main", response.toString());
//                                setProfileToView(object);
//                            }
//                        });
//                Bundle parameters = new Bundle();
//                parameters.putString("fields", "id,name,link,email,gender,last_name,first_name,locale,timezone,updated_time,verified");
//                request.setParameters(parameters);
//                request.executeAsync();
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                Toast.makeText(getContext(), "error to Login Facebook", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        if (!App.appUtils.isNetAvailable()) {
//            alertUserP(getContext(), "Connection Error", "No Internet connection available", "OK");
//        } else {
//            disconnectFromFacebook();
//        }

        btn_sigin_with_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAlert();
            }
        });

        btn_sigin.setOnClickListener(v -> checkValidation());

        spin_kit_loadingView.setVisibility(View.GONE);

        return view;
    }

    private void showAlert() {


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_fb_layout, null);
        dialogBuilder.setView(dialogView);

        RelativeLayout rl_yes = dialogView.findViewById(R.id.rl_yes);

        RelativeLayout rl_no = dialogView.findViewById(R.id.rl_no);

        RelativeLayout rl_cancel = dialogView.findViewById(R.id.rl_cancel);

        ImageView img_close = dialogView.findViewById(R.id.img_close);

        rl_cancel.setVisibility(View.GONE);

        img_close.setVisibility(View.GONE);

        TextView alert_title_txtview = dialogView.findViewById(R.id.alert_title_txtview);

        alert_title_txtview.setText(R.string.fb_alert);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        rl_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();

            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressLint("LongLogTag")
    private void setProfileToView(JSONObject jsonObject) {

        register_mode="Facebook";

        password = cnfm_password = getAlphaNumericString(10);

        edt_password.setVisibility(View.GONE);

        edt_cnfmpassword.setVisibility(View.GONE);

        txt_or.setVisibility(View.GONE);

     //   btnLogin.setVisibility(View.GONE);

        try {
//
            if(jsonObject.has("email")){

                email = jsonObject.getString("email");

                edt_email.edtContent.setText(email);

                Log.w(TAG,"email "+jsonObject.getString("email"));
            }

            else {

                email="";

                edt_email.edtContent.setText("");
            }

            if(jsonObject.has("first_name")){

                firstname = jsonObject.getString("first_name");

                edt_firstname.edtContent.setText(firstname);

                Log.w(TAG,"first_name "+jsonObject.getString("first_name"));

            }

            else {

                firstname="";

                edt_firstname.edtContent.setText("");
            }

            if(jsonObject.has("last_name")){

                lastname = jsonObject.getString("last_name");

                edt_lastname.edtContent.setText(lastname);

                Log.w(TAG,"last_name "+jsonObject.getString("last_name"));

            }

            else {

                lastname="";

                edt_lastname.edtContent.setText("");
            }

            if(jsonObject.has("id")){

                facebook_id = jsonObject.getString("id");

                Log.w(TAG,"fb_id "+jsonObject.getString("id"));

            }

            else {

                facebook_id="";

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void facebookClickAction() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("user_photos", "email", "public_profile", "user_posts"));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        String f_name = "";
                        String m_name = "";
                        String l_name = "";
                        String full_name = "";
                        String profile_image = "";
                        String facebook_id = "";
                        String f_email = "";
                        if (AccessToken.getCurrentAccessToken() != null) {
                            RequestData();
                            Profile profile = Profile.getCurrentProfile();
                            if (profile != null) {
                                facebook_id = profile.getId();

                                Log.e("facebook_id", facebook_id);
                                Log.e("f_email", f_email);
                                f_name = profile.getFirstName();
                                Log.e("f_name", f_name);
                                m_name = profile.getMiddleName();
                                Log.e("m_name", m_name);
                                l_name = profile.getLastName();
                                Log.e("l_name", l_name);
                                full_name = profile.getName();
                                Log.e("full_name", full_name);
                                profile_image = profile.getProfilePictureUri(400, 400).toString();
                                Log.e("profile_image", profile_image);
                            }

                        } else {
                            showDialogMethod("Warning", "Facebook is not available at the moment, Please try other method to signup");
                        }
                    }

                    @Override
                    public void onCancel() {
                        showDialogMethod("Warning", "Facebook is not available at the moment, Please try other method to signup");
                        // App code
                        disconnectFromFacebook();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }


    private void RequestData() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                JSONObject json = response.getJSONObject();
                System.out.println("Json data :" + json);

                try {
                    if (json != null) {
                        String currentemail = "";
                        String name = "";
                        SMedia = "Facebook";
                        currentemail = json.getString("email");
                        name = json.getString("name");
                        Log.d("email", currentemail);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }



    private void KeyboardHide() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        // imm.hideSoftInputFromWindow(etRenterPassword.getWindowToken(), 0);
    }

    private void showDialogMethod(String title, String message) {
        KeyboardHide();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();

    }



    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();

            }
        }).executeAsync();
    }
    public void alertUserP(Context context, String title, String msg, String btn) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setMessage(msg).setTitle(title).setCancelable(false)
                .setPositiveButton(btn, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        android.app.AlertDialog alert = builder.create();
        alert.show();
    }


    @SuppressLint("PackageManagerGetSignatures")
    private void getKeyHash() {

        PackageInfo info;
        try {
            info = Objects.requireNonNull(getContext()).getPackageManager().getPackageInfo("com.triton.bertsproject", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.w("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.w("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.w("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.w("exception", e.toString());
        }
    }


    @SuppressLint("LongLogTag")
    private void checkValidation() {

        boolean isvalid = true;

        int passwd_length =0 , cfmpaswd_lenth = 0;

        String emailPattern = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$";

        String passwdPattern = "^(.{0,7}|[^0-9]*|[^a-z]*)$";

        if(register_mode.equals("Manual")){

            password = edt_password.edtContent.getText().toString();

            cnfm_password = edt_cnfmpassword.edtContent.getText().toString();

            passwd_length = edt_password.edtContent.getText().toString().length();

            cfmpaswd_lenth = edt_cnfmpassword.edtContent.getText().toString().length();

            Log.w(TAG,"passwd_length "+passwd_length);

            Log.w(TAG,"cfmpaswd_lenth "+cfmpaswd_lenth);
        }

            firstname = edt_firstname.edtContent.getText().toString();

            lastname = edt_lastname.edtContent.getText().toString();

            email = edt_email.edtContent.getText().toString();

        if(firstname.equals("")){

            isvalid =false;

            edt_firstname.setError("Please Fill First Name");

            edt_firstname.requestFocus();
        }

//        else if(lastname.equals("")){
//
//            isvalid =false;
//
//            edt_lastname.setError("Please Fill Last Name");
//
//            edt_lastname.requestFocus();
//        }

        else if(email.equals("")){

            isvalid =false;

            edt_email.setError("Please Fill Mail ID");

            edt_email.requestFocus();
        }

        else if(password.equals("")||passwd_length<8||password.matches(passwdPattern)){

            isvalid =false;

            edt_password.setError("Please enter min 8 characters, at least 1 letter & 1 number");

            edt_password.requestFocus();
        }

    else if(cnfm_password.equals("")||cfmpaswd_lenth<8&&cnfm_password.matches(passwdPattern)){

            isvalid =false;

            if(cnfm_password.matches(passwdPattern)){

                Log.w(TAG,"cnfm_password --> true");

            }

            else {

                Log.w(TAG,"cnfm_password --> false");
            }


            edt_cnfmpassword.setError("Please enter min 8 characters, at least 1 letter & 1 number");

            edt_cnfmpassword.requestFocus();
        }

        else if(!password.equals(cnfm_password)){

            isvalid =false;

            Toast.makeText(getContext(),"Password and Confirm Password Doesn't Match",Toast.LENGTH_SHORT).show();
        }

        else if(!email.matches(emailPattern)){

            isvalid =false;

            edt_email.setError("Please Enter Valid Email ID");

            edt_email.requestFocus();

        }

        if(isvalid){

            if (dd4YouConfig.isInternetConnectivity()) {

                registerResponseCall(firstname,lastname,email,password,cnfm_password,facebook_id);

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
    private void registerResponseCall(String firstname, String lastname, String email, String password, String cnfm_password, String facebook_id) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SignupResponse> call = apiInterface.signupResponseCall(RestUtils.getContentType(),signupRequest(firstname,lastname,email,password,cnfm_password,facebook_id));
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
                                response.body().getData().getProfile().getRole(),
                                response.body().getData().getProfile().getAvatar(),
                                response.body().getData().getProfile().getCountry_id(),
                                response.body().getData().getProfile().getState_id(),
                                response.body().getData().getProfile().getZip_code(),
                                response.body().getData().getProfile().getRevenue()


                        );


                        Toasty.success(Objects.requireNonNull(getContext()),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                        onBackPressed();

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


    public void onBackPressed() {

        if(fromActivity!=null) {

            if (fromActivity.equals("HomeFragment")) {

                callDirections("1");


            }
            else if(fromActivity.equals("MyGarageFragment")){

                callDirections("2");

            }

            else if(fromActivity.equals("ProfileFragment")){

                callDirections("5");
            }

            else if(fromActivity.equals("RetailerCartActivity")){

                gotoCartActivity();
            }

            else if(fromActivity.equals("SearchProdListActivity")){

                gotoSearchProductListActivity();
            }

            else if(fromActivity.equals("ProductDetailDescriptionActivity")){

                gotoProductDetailDescriptionActivity();
            }

            else if(fromActivity.equals("RetailerProductListActivity")){

                gotoBrandProductListActivity();

            }

            else if(fromActivity.equals("RetailerProductListBasedOnCategActivity")){

                gotoCategoryProductListActivity();
            }

            else if(fromActivity.equals("RetailerProductListBasedOnMakeActivity")){

                gotoMakeProductListActivity();
            }


            else  {

                Intent intent = new Intent(getContext(),RetailerDashboardActivity.class);
                intent.putExtra("fromactivity",fromActivity);
                startActivity(intent);

            }

        }
        else  {

            Intent intent = new Intent(getContext(),RetailerDashboardActivity.class);
            intent.putExtra("fromactivity",fromActivity);
            startActivity(intent);

        }
    }

    public void callDirections(String tag){
        Intent intent = new Intent(getContext(),RetailerDashboardActivity.class);
        intent.putExtra("tag",tag);
        intent.putExtra("fromactivity",fromActivity);
        startActivity(intent);

    }

    @SuppressLint("LongLogTag")
    private SignupRequest signupRequest(String firstname, String lastname, String email, String password, String cnfm_password, String facebook_id) {

        /**
         * first_name : testab
         * last_name : testab
         * email : prabhu.imsfc@gmail.com
         * password : test1234
         * country_id : 0
         * state_id : 0
         * zip_code : 0
         * revenue : 0
         * special_offer_email : 1
         * role : retail
         * fb_id : FBID_123
         */
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setFirst_name(firstname);
        signupRequest.setLast_name(lastname);
        signupRequest.setEmail(email);
        signupRequest.setPassword(password);
        signupRequest.setCountry_id("0");
        signupRequest.setState_id("0");
        signupRequest.setZip_code("0");
        signupRequest.setRevenue("0");
        signupRequest.setSpecial_offer_email("1");
        signupRequest.setFb_id(facebook_id);
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

    // function to generate a random string of length n
    static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    private void gotoMakeProductListActivity() {

        Intent intent = new Intent(getContext(), RetailerProductListBasedOnMakeActivity.class);

        intent.putExtra("make_id",make_id);

        intent.putExtra("make_name",make_name);

        intent.putExtra("model_id", model_id);

        intent.putExtra("model_name",model_name);

        intent.putExtra("fromactivity",TAG);

        intent.putExtra("data",json_data.toString());

        startActivity(intent);

        Objects.requireNonNull(getActivity()).finish();

    }
    private void gotoCategoryProductListActivity() {

        Intent intent = new Intent(getContext(), RetailerProductListBasedOnCategActivity.class);

        intent.putExtra("parent_id",parent_id);

        intent.putExtra("categ_name",categ_name);

        intent.putExtra("subcategid",subcategid);

        intent.putExtra("subcategname",subcategname);

        intent.putExtra("fromactivity",TAG);

        intent.putExtra("data",json_data.toString());

        startActivity(intent);

        Objects.requireNonNull(getActivity()).finish();

    }
    private void gotoBrandProductListActivity() {

        Intent intent = new Intent(getContext(), RetailerProductListActivity.class);

        intent.putExtra("brand_id",brand_id);

        intent.putExtra("brand_name",brand_name);

        intent.putExtra("fromactivity",TAG);

        intent.putExtra("data",json_data.toString());

        startActivity(intent);

        Objects.requireNonNull(getActivity()).finish();

    }

    private void gotoCartActivity() {

        Intent intent = new Intent(getContext(), RetailerCartActivity.class);

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

        intent.putExtra("data",json_data.toString());

        startActivity(intent);

        Objects.requireNonNull(getActivity()).finish();

    }

    private void gotoSearchProductListActivity() {

        Intent intent = new Intent(getContext(), SearchProductListActivity.class);

        intent.putExtra("fromactivity",TAG);

        intent.putExtra("search_text",search_text);

        intent.putExtra("prod_id",prod_id);

        intent.putExtra("quantity",quantity);

        intent.putExtra("unit_price",unit_price);

        intent.putExtra("data",json_data.toString());

        startActivity(intent);

        Objects.requireNonNull(getActivity()).finish();

    }

    private void gotoProductDetailDescriptionActivity() {

        Intent intent = new Intent(getContext(), ProductDetailDescriptionActivity.class);

        intent.putExtra("prod_id",prod_id);

        intent.putExtra("prod_name",prod_name);

        intent.putExtra("brand_id",brand_id);

        intent.putExtra("brand_name",brand_name);

        intent.putExtra("parent_id",parent_id);

        intent.putExtra("categ_name",categ_name);

        intent.putExtra("subcategid",subcategid);

        intent.putExtra("subcategname",subcategname);

        intent.putExtra("make_id",make_id);

        intent.putExtra("model_id", model_id);

        intent.putExtra("model_name",model_name);

        intent.putExtra("search_text",search_text);

        intent.putExtra("fromactivity",TAG);

        intent.putExtra("data",json_data.toString());

        startActivity(intent);

        Objects.requireNonNull(getActivity()).finish();
    }

}