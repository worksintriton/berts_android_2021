package com.dci.berts.responsepojo;

import java.util.List;

public class GetFuelTypesResponse {


    /**
     * status : Success
     * code : 200
     * message : Fuel Types list successful
     * data : {"fuel_types":[{"id":"1","name":"Diesel","status":"1","created_at":"2019-10-16 08:42:21"},{"id":"2","name":"Gas","status":"1","created_at":"2019-10-16 08:42:29"},{"id":"3","name":"Gasoline","status":"1","created_at":"2019-10-16 08:42:37"},{"id":"4","name":"Gasoline/Electric","status":"1","created_at":"2019-10-16 08:42:49"},{"id":"5","name":"LPG autogas","status":"1","created_at":"2019-10-16 08:42:59"},{"id":"6","name":"Gas/Electric","status":"1","created_at":"2019-10-16 08:43:07"},{"id":"7","name":"Test Fuel Type","status":"1","created_at":"2020-08-25 04:45:38"},{"id":"8","name":"SMA Petrol, Desel & Gas ","status":"1","created_at":"2020-08-26 03:30:47"},{"id":"9","name":"Battery - Universal Power Group 12V 35AH.","status":"1","created_at":"2020-09-02 00:10:31"},{"id":"10","name":"Battery - Mighty Max Battery YTX4L-BS.","status":"1","created_at":"2020-09-02 00:10:03"}]}
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
         * name : Diesel
         * status : 1
         * created_at : 2019-10-16 08:42:21
         */

        private List<FuelTypesBean> fuel_types;

        public List<FuelTypesBean> getFuel_types() {
            return fuel_types;
        }

        public void setFuel_types(List<FuelTypesBean> fuel_types) {
            this.fuel_types = fuel_types;
        }

        public static class FuelTypesBean {
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
