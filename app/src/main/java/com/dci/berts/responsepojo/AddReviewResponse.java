package com.dci.berts.responsepojo;

public class AddReviewResponse {


    /**
     * status : Success
     * code : 200
     * message : Product Review Added Successful
     * data : {"review":{}}
     */

    private String status;
    private int code;
    private String message;
    /**
     * review : {}
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private ReviewBean review;

        public ReviewBean getReview() {
            return review;
        }

        public void setReview(ReviewBean review) {
            this.review = review;
        }

        public static class ReviewBean {
        }
    }
}
