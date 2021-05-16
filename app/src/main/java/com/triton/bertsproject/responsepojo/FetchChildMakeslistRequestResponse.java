package com.triton.bertsproject.responsepojo;

import java.util.List;

public class FetchChildMakeslistRequestResponse {


    /**
     * status : true
     * error_code : 0
     * error_message :
     * data : {"makes":[{"id":"312","make_id":"6","name":"Sambar","status":"1","created_at":"2019-10-11 09:47:31","make_name":"Subaru"},{"id":"311","make_id":"6","name":"Liberty","status":"1","created_at":"2019-10-11 09:47:31","make_name":"Subaru"},{"id":"310","make_id":"6","name":"Baja","status":"1","created_at":"2019-10-11 09:47:22","make_name":"Subaru"},{"id":"309","make_id":"6","name":"Trezia","status":"1","created_at":"2019-10-11 09:47:15","make_name":"Subaru"},{"id":"308","make_id":"6","name":"Legacy Outback","status":"1","created_at":"2019-10-11 09:47:08","make_name":"Subaru"},{"id":"307","make_id":"6","name":"Impeza WRX","status":"1","created_at":"2019-10-11 09:47:01","make_name":"Subaru"},{"id":"306","make_id":"6","name":"Alcyone","status":"1","created_at":"2019-10-11 09:46:54","make_name":"Subaru"},{"id":"305","make_id":"6","name":"Leone","status":"1","created_at":"2019-10-11 09:46:42","make_name":"Subaru"},{"id":"304","make_id":"6","name":"XV","status":"1","created_at":"2019-10-11 09:47:31","make_name":"Subaru"},{"id":"303","make_id":"6","name":"Wrx","status":"1","created_at":"2019-10-11 09:47:22","make_name":"Subaru"},{"id":"302","make_id":"6","name":"Outback","status":"1","created_at":"2019-10-11 09:47:15","make_name":"Subaru"},{"id":"301","make_id":"6","name":"Legacy","status":"1","created_at":"2019-10-11 09:47:08","make_name":"Subaru"},{"id":"300","make_id":"6","name":"Impreza","status":"1","created_at":"2019-10-11 09:47:01","make_name":"Subaru"},{"id":"299","make_id":"6","name":"Forester","status":"1","created_at":"2019-10-11 09:46:54","make_name":"Subaru"},{"id":"298","make_id":"6","name":"Exiga","status":"1","created_at":"2019-10-11 09:46:42","make_name":"Subaru"}],"display_message":""}
     */

    private boolean status;
    private int error_code;
    private String error_message;
    /**
     * makes : [{"id":"312","make_id":"6","name":"Sambar","status":"1","created_at":"2019-10-11 09:47:31","make_name":"Subaru"},{"id":"311","make_id":"6","name":"Liberty","status":"1","created_at":"2019-10-11 09:47:31","make_name":"Subaru"},{"id":"310","make_id":"6","name":"Baja","status":"1","created_at":"2019-10-11 09:47:22","make_name":"Subaru"},{"id":"309","make_id":"6","name":"Trezia","status":"1","created_at":"2019-10-11 09:47:15","make_name":"Subaru"},{"id":"308","make_id":"6","name":"Legacy Outback","status":"1","created_at":"2019-10-11 09:47:08","make_name":"Subaru"},{"id":"307","make_id":"6","name":"Impeza WRX","status":"1","created_at":"2019-10-11 09:47:01","make_name":"Subaru"},{"id":"306","make_id":"6","name":"Alcyone","status":"1","created_at":"2019-10-11 09:46:54","make_name":"Subaru"},{"id":"305","make_id":"6","name":"Leone","status":"1","created_at":"2019-10-11 09:46:42","make_name":"Subaru"},{"id":"304","make_id":"6","name":"XV","status":"1","created_at":"2019-10-11 09:47:31","make_name":"Subaru"},{"id":"303","make_id":"6","name":"Wrx","status":"1","created_at":"2019-10-11 09:47:22","make_name":"Subaru"},{"id":"302","make_id":"6","name":"Outback","status":"1","created_at":"2019-10-11 09:47:15","make_name":"Subaru"},{"id":"301","make_id":"6","name":"Legacy","status":"1","created_at":"2019-10-11 09:47:08","make_name":"Subaru"},{"id":"300","make_id":"6","name":"Impreza","status":"1","created_at":"2019-10-11 09:47:01","make_name":"Subaru"},{"id":"299","make_id":"6","name":"Forester","status":"1","created_at":"2019-10-11 09:46:54","make_name":"Subaru"},{"id":"298","make_id":"6","name":"Exiga","status":"1","created_at":"2019-10-11 09:46:42","make_name":"Subaru"}]
     * display_message :
     */

    private DataBean data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String display_message;
        /**
         * id : 312
         * make_id : 6
         * name : Sambar
         * status : 1
         * created_at : 2019-10-11 09:47:31
         * make_name : Subaru
         */

        private List<MakesBean> makes;

        public String getDisplay_message() {
            return display_message;
        }

        public void setDisplay_message(String display_message) {
            this.display_message = display_message;
        }

        public List<MakesBean> getMakes() {
            return makes;
        }

        public void setMakes(List<MakesBean> makes) {
            this.makes = makes;
        }

        public static class MakesBean {
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
