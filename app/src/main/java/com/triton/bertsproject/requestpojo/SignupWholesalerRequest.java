package com.triton.bertsproject.requestpojo;

public class SignupWholesalerRequest {


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

    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String country_id;
    private String state_id;
    private String zip_code;
    private String revenue;
    private String role;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
