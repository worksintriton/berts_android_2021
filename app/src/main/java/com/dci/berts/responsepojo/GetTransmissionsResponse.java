package com.dci.berts.responsepojo;

import java.util.List;

public class GetTransmissionsResponse {


    /**
     * status : Success
     * code : 200
     * message : Transmissions list successful
     * data : {"transmissions":[{"id":"1","name":"Automatic","status":"1","created_at":"2019-10-16 08:42:21"},{"id":"2","name":"Manual","status":"1","created_at":"2019-10-16 08:42:29"},{"id":"3","name":"Automatic/Manual","status":"1","created_at":"2019-10-16 08:42:37"},{"id":"4","name":"Test bike Transmission","status":"1","created_at":"2020-08-25 04:46:08"},{"id":"5","name":"SMA power steering","status":"1","created_at":"2020-08-26 03:29:15"},{"id":"6","name":"Electric power","status":"1","created_at":"2020-09-02 00:11:27"},{"id":"7","name":"Solar Power","status":"1","created_at":"2020-09-02 00:11:40"}]}
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
         * id : 1
         * name : Automatic
         * status : 1
         * created_at : 2019-10-16 08:42:21
         */

        private List<TransmissionsBean> transmissions;

        public List<TransmissionsBean> getTransmissions() {
            return transmissions;
        }

        public void setTransmissions(List<TransmissionsBean> transmissions) {
            this.transmissions = transmissions;
        }

        public static class TransmissionsBean {
            private String id;
            private String name;
            private String status;
            private String created_at;

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
        }
    }
}
