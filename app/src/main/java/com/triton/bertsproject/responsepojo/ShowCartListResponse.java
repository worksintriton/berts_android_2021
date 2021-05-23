package com.triton.bertsproject.responsepojo;

import java.util.List;

public class ShowCartListResponse {


    /**
     * status : Success
     * code : 200
     * message : Cart list successful
     * data : {"cart":[{"basket_id":"7","userid":"1","basket_product_id":"3","basket_quantity":"2","basket_unit_price":"10000","basket_total_price":"20000","price":"10000","title":"Alloy Wheel ","images":[{"id":"341","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608809f3ad051.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608809f3cdaa7.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608809f3ed906.jpg","image_order":"1","storage":"local"},{"id":"342","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608809ff4a6ec.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608809ff73764.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608809ff96854.jpg","image_order":"2","storage":"local"},{"id":"343","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_60880a0ae41dd.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_60880a0b2104a.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_60880a0b4c541.jpg","image_order":"3","storage":"local"}]}]}
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
         * basket_id : 7
         * userid : 1
         * basket_product_id : 3
         * basket_quantity : 2
         * basket_unit_price : 10000
         * basket_total_price : 20000
         * price : 10000
         * title : Alloy Wheel
         * images : [{"id":"341","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608809f3ad051.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608809f3cdaa7.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608809f3ed906.jpg","image_order":"1","storage":"local"},{"id":"342","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608809ff4a6ec.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608809ff73764.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608809ff96854.jpg","image_order":"2","storage":"local"},{"id":"343","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_60880a0ae41dd.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_60880a0b2104a.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_60880a0b4c541.jpg","image_order":"3","storage":"local"}]
         */

        private List<CartBean> cart;

        public List<CartBean> getCart() {
            return cart;
        }

        public void setCart(List<CartBean> cart) {
            this.cart = cart;
        }

        public static class CartBean {
            private String basket_id;
            private String userid;
            private String basket_product_id;
            private String basket_quantity;
            private String basket_unit_price;
            private String basket_total_price;
            private String price;
            private String title;
            /**
             * id : 341
             * product_id : 3
             * image_default : http://dciinternal.in/dev01/uploads/images/img_x500_608809f3ad051.jpg
             * image_big : http://dciinternal.in/dev01/uploads/images/img_1920x_608809f3cdaa7.jpg
             * image_small : http://dciinternal.in/dev01/uploads/images/img_x300_608809f3ed906.jpg
             * image_order : 1
             * storage : local
             */

            private List<ImagesBean> images;

            public String getBasket_id() {
                return basket_id;
            }

            public void setBasket_id(String basket_id) {
                this.basket_id = basket_id;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getBasket_product_id() {
                return basket_product_id;
            }

            public void setBasket_product_id(String basket_product_id) {
                this.basket_product_id = basket_product_id;
            }

            public String getBasket_quantity() {
                return basket_quantity;
            }

            public void setBasket_quantity(String basket_quantity) {
                this.basket_quantity = basket_quantity;
            }

            public String getBasket_unit_price() {
                return basket_unit_price;
            }

            public void setBasket_unit_price(String basket_unit_price) {
                this.basket_unit_price = basket_unit_price;
            }

            public String getBasket_total_price() {
                return basket_total_price;
            }

            public void setBasket_total_price(String basket_total_price) {
                this.basket_total_price = basket_total_price;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ImagesBean> getImages() {
                return images;
            }

            public void setImages(List<ImagesBean> images) {
                this.images = images;
            }

            public static class ImagesBean {
                private String id;
                private String product_id;
                private String image_default;
                private String image_big;
                private String image_small;
                private String image_order;
                private String storage;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(String product_id) {
                    this.product_id = product_id;
                }

                public String getImage_default() {
                    return image_default;
                }

                public void setImage_default(String image_default) {
                    this.image_default = image_default;
                }

                public String getImage_big() {
                    return image_big;
                }

                public void setImage_big(String image_big) {
                    this.image_big = image_big;
                }

                public String getImage_small() {
                    return image_small;
                }

                public void setImage_small(String image_small) {
                    this.image_small = image_small;
                }

                public String getImage_order() {
                    return image_order;
                }

                public void setImage_order(String image_order) {
                    this.image_order = image_order;
                }

                public String getStorage() {
                    return storage;
                }

                public void setStorage(String storage) {
                    this.storage = storage;
                }
            }
        }
    }
}
