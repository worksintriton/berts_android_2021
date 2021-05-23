package com.triton.bertsproject.requestpojo;

public class FetchProductBasedOnMakeRequest {


    /**
     * MAKE_ID : 1
     * MODEL_ID : 1
     * MODE : LIST
     */

    private String MAKE_ID;
    private String MODEL_ID;
    private String MODE;

    public String getMAKE_ID() {
        return MAKE_ID;
    }

    public void setMAKE_ID(String MAKE_ID) {
        this.MAKE_ID = MAKE_ID;
    }

    public String getMODEL_ID() {
        return MODEL_ID;
    }

    public void setMODEL_ID(String MODEL_ID) {
        this.MODEL_ID = MODEL_ID;
    }

    public String getMODE() {
        return MODE;
    }

    public void setMODE(String MODE) {
        this.MODE = MODE;
    }
}
