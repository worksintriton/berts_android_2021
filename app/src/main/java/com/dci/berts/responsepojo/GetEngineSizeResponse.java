package com.dci.berts.responsepojo;

import java.util.List;

public class GetEngineSizeResponse {


    /**
     * status : Success
     * code : 200
     * message : Engine Size list successful
     * data : {"engine_size":[{"id":"0.66L","name":"0.66L"},{"id":"1.0L","name":"1.0L"},{"id":"1.1L","name":"1.1L"},{"id":"1.2L","name":"1.2L"},{"id":"1.3L","name":"1.3L"},{"id":"1.4L","name":"1.4L"},{"id":"1.5L","name":"1.5L"},{"id":"1.6L","name":"1.6L"},{"id":"1.7L","name":"1.7L"},{"id":"1.8L","name":"1.8L"},{"id":"2.0L","name":"2.0L"},{"id":"2.2L","name":"2.2L"},{"id":"2.3L","name":"2.3L"},{"id":"2.4L","name":"2.4L"},{"id":"2.5L","name":"2.5L"},{"id":"2.6L","name":"2.6L"},{"id":"2.7L","name":"2.7L"},{"id":"2.8L","name":"2.8L"},{"id":"3.0L","name":"3.0L"},{"id":"3.1L","name":"3.1L"},{"id":"3.2L","name":"3.2L"},{"id":"3.3L","name":"3.3L"},{"id":"3.5L","name":"3.5L"},{"id":"3.7L","name":"3.7L"},{"id":"3.8L","name":"3.8L"},{"id":"4.0L","name":"4.0L"},{"id":"4.1L","name":"4.1L"},{"id":"4.2L","name":"4.2L"},{"id":"4.3L","name":"4.3L"},{"id":"4.4L","name":"4.4L"},{"id":"4.5L","name":"4.5L"},{"id":"4.6L","name":"4.6L"},{"id":"4.8L","name":"4.8L"},{"id":"4.9L","name":"4.9L"},{"id":"5.6L","name":"5.6L"},{"id":"658cc","name":"658cc"},{"id":"659cc","name":"659cc"},{"id":"660cc","name":"660cc"}]}
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
         * id : 0.66L
         * name : 0.66L
         */

        private List<EngineSizeBean> engine_size;

        public List<EngineSizeBean> getEngine_size() {
            return engine_size;
        }

        public void setEngine_size(List<EngineSizeBean> engine_size) {
            this.engine_size = engine_size;
        }

        public static class EngineSizeBean {
            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
