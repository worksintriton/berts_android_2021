package com.dci.berts.requestpojo;

public class AddReviewRequest {


    /**
     * PRODUCT_ID : 3
     * USER_ID : 688
     * RATING : 4
     * REVIEW : Good Product
     * MODE : ADD
     */

    private String PRODUCT_ID;
    private String USER_ID;
    private String RATING;
    private String REVIEW;
    private String MODE;

    public String getPRODUCT_ID() {
        return PRODUCT_ID;
    }

    public void setPRODUCT_ID(String PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getRATING() {
        return RATING;
    }

    public void setRATING(String RATING) {
        this.RATING = RATING;
    }

    public String getREVIEW() {
        return REVIEW;
    }

    public void setREVIEW(String REVIEW) {
        this.REVIEW = REVIEW;
    }

    public String getMODE() {
        return MODE;
    }

    public void setMODE(String MODE) {
        this.MODE = MODE;
    }
}
