package com.dci.berts.requestpojo;

public class ProductDetailRequest {


    /**
     * MODE : DETAIL
     * PRODUCT_ID : 106267
     * USER_ID : 688
     */

    private String MODE;
    private String PRODUCT_ID;
    private String USER_ID;

    public String getMODE() {
        return MODE;
    }

    public void setMODE(String MODE) {
        this.MODE = MODE;
    }

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
}
