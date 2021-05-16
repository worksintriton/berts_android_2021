package com.triton.bertsproject.requestpojo;

public class UpdateProfileRequest {


    /**
     * first_name : testl
     * last_name : testc
     * about_me : testc_abt
     * id : 541
     */

    private String first_name;
    private String last_name;
    private String about_me;
    private String id;

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

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
