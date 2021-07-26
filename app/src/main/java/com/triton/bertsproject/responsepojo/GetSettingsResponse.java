package com.triton.bertsproject.responsepojo;

import java.util.List;

public class GetSettingsResponse {


    /**
     * status : Success
     * code : 200
     * message : settings list successful
     * data : {"sliders":[{"id":"6","lang_id":"1","storage":"local","image":"http://dciinternal.in/dev01/uploads/slider/slider_5ff568b47f038.jpg","link":"https://dci705.tqms.net/uploads/slider/slider_5e3bf90931414.jpg","item_order":"1"},{"id":"11","lang_id":"1","storage":"local","image":"http://dciinternal.in/dev01/uploads/slider/slider_5ff568757cbbe.jpg","link":"https://dci705.tqms.net/uploads/slider/slider_5f4c6bd9ca4a7.jpg","item_order":"14"}],"max_product_price":"1500","min_product_price":"700"}
     */

    private String status;
    private int code;
    private String message;
    /**
     * sliders : [{"id":"6","lang_id":"1","storage":"local","image":"http://dciinternal.in/dev01/uploads/slider/slider_5ff568b47f038.jpg","link":"https://dci705.tqms.net/uploads/slider/slider_5e3bf90931414.jpg","item_order":"1"},{"id":"11","lang_id":"1","storage":"local","image":"http://dciinternal.in/dev01/uploads/slider/slider_5ff568757cbbe.jpg","link":"https://dci705.tqms.net/uploads/slider/slider_5f4c6bd9ca4a7.jpg","item_order":"14"}]
     * max_product_price : 1500
     * min_product_price : 700
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
        private String max_product_price;
        private String min_product_price;
        /**
         * id : 6
         * lang_id : 1
         * storage : local
         * image : http://dciinternal.in/dev01/uploads/slider/slider_5ff568b47f038.jpg
         * link : https://dci705.tqms.net/uploads/slider/slider_5e3bf90931414.jpg
         * item_order : 1
         */

        private List<SlidersBean> sliders;

        public String getMax_product_price() {
            return max_product_price;
        }

        public void setMax_product_price(String max_product_price) {
            this.max_product_price = max_product_price;
        }

        public String getMin_product_price() {
            return min_product_price;
        }

        public void setMin_product_price(String min_product_price) {
            this.min_product_price = min_product_price;
        }

        public List<SlidersBean> getSliders() {
            return sliders;
        }

        public void setSliders(List<SlidersBean> sliders) {
            this.sliders = sliders;
        }

        public static class SlidersBean {
            private String id;
            private String lang_id;
            private String storage;
            private String image;
            private String link;
            private String item_order;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLang_id() {
                return lang_id;
            }

            public void setLang_id(String lang_id) {
                this.lang_id = lang_id;
            }

            public String getStorage() {
                return storage;
            }

            public void setStorage(String storage) {
                this.storage = storage;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getItem_order() {
                return item_order;
            }

            public void setItem_order(String item_order) {
                this.item_order = item_order;
            }
        }
    }
}
