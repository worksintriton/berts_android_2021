package com.dci.berts.requestpojo;

public class ShowCartListRequest {


    /**
     * USER_ID : 1
     * MODE : LIST
     */

    private String USER_ID;
    private String MODE;

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getMODE() {
        return MODE;
    }

    public void setMODE(String MODE) {
        this.MODE = MODE;
    }
}
