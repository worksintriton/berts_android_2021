package com.triton.bertsproject.requestpojo;

public class FetchProductBasedOnBrandRequest {


    /**
     * BRAND_ID : 2
     * MODE : LIST
     * USER_ID :
     */

    private String BRAND_ID;
    private String MODE;
    private String USER_ID;

    public String getBRAND_ID() {
        return BRAND_ID;
    }

    public void setBRAND_ID(String BRAND_ID) {
        this.BRAND_ID = BRAND_ID;
    }

    public String getMODE() {
        return MODE;
    }

    public void setMODE(String MODE) {
        this.MODE = MODE;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }
}