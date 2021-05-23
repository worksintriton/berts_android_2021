package com.triton.bertsproject.requestpojo;

public class FetchProductBasedOnCatRequest {


    /**
     * CATEGORY_ID : 1
     * SUBCATEGORY_ID : 30
     * MODE : LIST
     */

    private String CATEGORY_ID;
    private String SUBCATEGORY_ID;
    private String MODE;

    public String getCATEGORY_ID() {
        return CATEGORY_ID;
    }

    public void setCATEGORY_ID(String CATEGORY_ID) {
        this.CATEGORY_ID = CATEGORY_ID;
    }

    public String getSUBCATEGORY_ID() {
        return SUBCATEGORY_ID;
    }

    public void setSUBCATEGORY_ID(String SUBCATEGORY_ID) {
        this.SUBCATEGORY_ID = SUBCATEGORY_ID;
    }

    public String getMODE() {
        return MODE;
    }

    public void setMODE(String MODE) {
        this.MODE = MODE;
    }
}
