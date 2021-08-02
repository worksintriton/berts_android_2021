package com.dci.berts.responsepojo;

public class CreateAddressListResponse {


    /**
     * status : Success
     * code : 200
     * message : Address Insert Successful
     * data : {"address":{}}
     */

    private String status;
    private int code;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
