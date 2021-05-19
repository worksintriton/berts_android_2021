package com.triton.bertsproject.responsepojo;

import java.util.List;

public class FetchChildMakeslistRequestResponse {


    /**
     * status : Success
     * code : 200
     * message : Models list successful
     * data : {"make":[{"id":"63","make_id":"2","name":"Fargo Filly","status":"1","created_at":"2019-10-11 09:34:50","make_name":"Isuzu"},{"id":"62","make_id":"2","name":"Gemini","status":"1","created_at":"2019-10-10 06:38:21","make_name":"Isuzu"},{"id":"61","make_id":"2","name":"Aska","status":"1","created_at":"2019-10-10 06:38:13","make_name":"Isuzu"},{"id":"60","make_id":"2","name":"Qkr","status":"1","created_at":"2019-10-11 09:35:51","make_name":"Isuzu"},{"id":"59","make_id":"2","name":"Pick-Up","status":"1","created_at":"2019-10-11 09:35:44","make_name":"Isuzu"},{"id":"58","make_id":"2","name":"Nqr","status":"1","created_at":"2019-10-11 09:35:35","make_name":"Isuzu"},{"id":"57","make_id":"2","name":"Npr","status":"1","created_at":"2019-10-11 09:35:29","make_name":"Isuzu"},{"id":"56","make_id":"2","name":"Nmr","status":"1","created_at":"2019-10-11 09:35:21","make_name":"Isuzu"},{"id":"55","make_id":"2","name":"Nkr","status":"1","created_at":"2019-10-11 09:35:14","make_name":"Isuzu"},{"id":"54","make_id":"2","name":"Juston","status":"1","created_at":"2019-10-11 09:35:08","make_name":"Isuzu"},{"id":"53","make_id":"2","name":"Forward","status":"1","created_at":"2019-10-11 09:35:01","make_name":"Isuzu"},{"id":"52","make_id":"2","name":"Elf","status":"1","created_at":"2019-10-11 09:34:50","make_name":"Isuzu"},{"id":"51","make_id":"2","name":"D-MAX","status":"1","created_at":"2019-10-10 06:38:21","make_name":"Isuzu"},{"id":"50","make_id":"2","name":"Como","status":"1","created_at":"2019-10-10 06:38:13","make_name":"Isuzu"}]}
     */

    private String status;
    private int code;
    private String message;
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
        /**
         * id : 63
         * make_id : 2
         * name : Fargo Filly
         * status : 1
         * created_at : 2019-10-11 09:34:50
         * make_name : Isuzu
         */

        private List<MakeBean> make;

        public List<MakeBean> getMake() {
            return make;
        }

        public void setMake(List<MakeBean> make) {
            this.make = make;
        }

        public static class MakeBean {
            private String id;
            private String make_id;
            private String name;
            private String status;
            private String created_at;
            private String make_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMake_id() {
                return make_id;
            }

            public void setMake_id(String make_id) {
                this.make_id = make_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getMake_name() {
                return make_name;
            }

            public void setMake_name(String make_name) {
                this.make_name = make_name;
            }
        }
    }
}
