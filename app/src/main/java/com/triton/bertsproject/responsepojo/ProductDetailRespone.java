package com.triton.bertsproject.responsepojo;

import java.util.List;

public class ProductDetailRespone {


    /**
     * status : Success
     * code : 200
     * message : Product Detail successful
     * data : {"products":{"id":"1","title":"RADIATOR  (ATM) NIS/XTRAIL T30 QR20DE 2000-03","slug":"radiator-atm-nisxtrail-t30-qr20de-2000-03-1","category_id":"6","subcategory_id":"21","third_category_id":"40","price":"1000","currency":"USD","description":"RADIATOR  (ATM) NIS/XTRAIL T30 QR20DE 2000-03","product_condition":null,"country_id":null,"state_id":null,"address":null,"zip_code":null,"user_id":"1","status":"1","is_promoted":"0","promote_start_date":null,"promote_end_date":null,"promote_plan":null,"promote_day":null,"visibility":"1","rating":"0","hit":"0","external_link":null,"quantity":"4","shipping_time":"2_3_business_days","shipping_cost_type":null,"shipping_cost":"0","is_sold":"0","is_deleted":"0","created_at":"2021-06-05 00:32:56","part_number":"21460-8H303","brand_id":"24","colors":"red","weight":"0","height":"0","length":"0","tag_hot":"0","tag_new":"0","unit":null,"discount":"0","video":null,"vin":null,"subcategory_name":"Engine Cooling","category_name":"Performance & Tuning","brand_name":"Nissan","make_names":"Nissan","model_names":"Nissan-X-Trail","images":[{"id":"334","product_id":"1","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_6087ec7369432.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_6087ec7393c7f.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_6087ec73bdcac.jpg","image_order":"1","storage":"local"},{"id":"335","product_id":"1","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_6087ec7b87ad4.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_6087ec7bba7db.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_6087ec7becfb7.jpg","image_order":"2","storage":"local"},{"id":"336","product_id":"1","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_6087ec8703eb9.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_6087ec8733c9d.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_6087ec876a4ac.jpg","image_order":"3","storage":"local"},{"id":"337","product_id":"1","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_6087ec906eeb6.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_6087ec90988c7.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_6087ec90c4744.jpg","image_order":"4","storage":"local"},{"id":"338","product_id":"1","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_6087ec990a0ed.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_6087ec99378cb.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_6087ec996181f.jpg","image_order":"5","storage":"local"}],"default_quantity":1,"reviews_comments":1,"reviews_ratings":3,"reviews_details":[{"rating":"3","review":"test","date":"06/05/2021","added_by":"testc testc"}],"reviews_ratings_total_count":1,"reviews_ratings_percentage":{"rating_1":0,"rating_2":0,"rating_3":100,"rating_4":0,"rating_5":0},"wholesaler_price":[{"quantity":"2","price":"250"},{"quantity":"3","price":"375"},{"quantity":"4","price":"500"}]}}
     */

    private String status;
    private int code;
    private String message;
    /**
     * products : {"id":"1","title":"RADIATOR  (ATM) NIS/XTRAIL T30 QR20DE 2000-03","slug":"radiator-atm-nisxtrail-t30-qr20de-2000-03-1","category_id":"6","subcategory_id":"21","third_category_id":"40","price":"1000","currency":"USD","description":"RADIATOR  (ATM) NIS/XTRAIL T30 QR20DE 2000-03","product_condition":null,"country_id":null,"state_id":null,"address":null,"zip_code":null,"user_id":"1","status":"1","is_promoted":"0","promote_start_date":null,"promote_end_date":null,"promote_plan":null,"promote_day":null,"visibility":"1","rating":"0","hit":"0","external_link":null,"quantity":"4","shipping_time":"2_3_business_days","shipping_cost_type":null,"shipping_cost":"0","is_sold":"0","is_deleted":"0","created_at":"2021-06-05 00:32:56","part_number":"21460-8H303","brand_id":"24","colors":"red","weight":"0","height":"0","length":"0","tag_hot":"0","tag_new":"0","unit":null,"discount":"0","video":null,"vin":null,"subcategory_name":"Engine Cooling","category_name":"Performance & Tuning","brand_name":"Nissan","make_names":"Nissan","model_names":"Nissan-X-Trail","images":[{"id":"334","product_id":"1","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_6087ec7369432.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_6087ec7393c7f.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_6087ec73bdcac.jpg","image_order":"1","storage":"local"},{"id":"335","product_id":"1","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_6087ec7b87ad4.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_6087ec7bba7db.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_6087ec7becfb7.jpg","image_order":"2","storage":"local"},{"id":"336","product_id":"1","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_6087ec8703eb9.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_6087ec8733c9d.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_6087ec876a4ac.jpg","image_order":"3","storage":"local"},{"id":"337","product_id":"1","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_6087ec906eeb6.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_6087ec90988c7.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_6087ec90c4744.jpg","image_order":"4","storage":"local"},{"id":"338","product_id":"1","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_6087ec990a0ed.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_6087ec99378cb.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_6087ec996181f.jpg","image_order":"5","storage":"local"}],"default_quantity":1,"reviews_comments":1,"reviews_ratings":3,"reviews_details":[{"rating":"3","review":"test","date":"06/05/2021","added_by":"testc testc"}],"reviews_ratings_total_count":1,"reviews_ratings_percentage":{"rating_1":0,"rating_2":0,"rating_3":100,"rating_4":0,"rating_5":0},"wholesaler_price":[{"quantity":"2","price":"250"},{"quantity":"3","price":"375"},{"quantity":"4","price":"500"}]}
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
        /**
         * id : 1
         * title : RADIATOR  (ATM) NIS/XTRAIL T30 QR20DE 2000-03
         * slug : radiator-atm-nisxtrail-t30-qr20de-2000-03-1
         * category_id : 6
         * subcategory_id : 21
         * third_category_id : 40
         * price : 1000
         * currency : USD
         * description : RADIATOR  (ATM) NIS/XTRAIL T30 QR20DE 2000-03
         * product_condition : null
         * country_id : null
         * state_id : null
         * address : null
         * zip_code : null
         * user_id : 1
         * status : 1
         * is_promoted : 0
         * promote_start_date : null
         * promote_end_date : null
         * promote_plan : null
         * promote_day : null
         * visibility : 1
         * rating : 0
         * hit : 0
         * external_link : null
         * quantity : 4
         * shipping_time : 2_3_business_days
         * shipping_cost_type : null
         * shipping_cost : 0
         * is_sold : 0
         * is_deleted : 0
         * created_at : 2021-06-05 00:32:56
         * part_number : 21460-8H303
         * brand_id : 24
         * colors : red
         * weight : 0
         * height : 0
         * length : 0
         * tag_hot : 0
         * tag_new : 0
         * unit : null
         * discount : 0
         * video : null
         * vin : null
         * subcategory_name : Engine Cooling
         * category_name : Performance & Tuning
         * brand_name : Nissan
         * make_names : Nissan
         * model_names : Nissan-X-Trail
         * images : [{"id":"334","product_id":"1","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_6087ec7369432.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_6087ec7393c7f.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_6087ec73bdcac.jpg","image_order":"1","storage":"local"},{"id":"335","product_id":"1","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_6087ec7b87ad4.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_6087ec7bba7db.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_6087ec7becfb7.jpg","image_order":"2","storage":"local"},{"id":"336","product_id":"1","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_6087ec8703eb9.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_6087ec8733c9d.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_6087ec876a4ac.jpg","image_order":"3","storage":"local"},{"id":"337","product_id":"1","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_6087ec906eeb6.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_6087ec90988c7.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_6087ec90c4744.jpg","image_order":"4","storage":"local"},{"id":"338","product_id":"1","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_6087ec990a0ed.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_6087ec99378cb.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_6087ec996181f.jpg","image_order":"5","storage":"local"}]
         * default_quantity : 1
         * reviews_comments : 1
         * reviews_ratings : 3
         * reviews_details : [{"rating":"3","review":"test","date":"06/05/2021","added_by":"testc testc"}]
         * reviews_ratings_total_count : 1
         * reviews_ratings_percentage : {"rating_1":0,"rating_2":0,"rating_3":100,"rating_4":0,"rating_5":0}
         * wholesaler_price : [{"quantity":"2","price":"250"},{"quantity":"3","price":"375"},{"quantity":"4","price":"500"}]
         */

        private ProductsBean products;

        public ProductsBean getProducts() {
            return products;
        }

        public void setProducts(ProductsBean products) {
            this.products = products;
        }

        public static class ProductsBean {
            private String id;
            private String title;
            private String slug;
            private String category_id;
            private String subcategory_id;
            private String third_category_id;
            private String price;
            private String currency;
            private String description;
            private Object product_condition;
            private Object country_id;
            private Object state_id;
            private Object address;
            private Object zip_code;
            private String user_id;
            private String status;
            private String is_promoted;
            private Object promote_start_date;
            private Object promote_end_date;
            private Object promote_plan;
            private Object promote_day;
            private String visibility;
            private String rating;
            private String hit;
            private Object external_link;
            private String quantity;
            private String shipping_time;
            private Object shipping_cost_type;
            private String shipping_cost;
            private String is_sold;
            private String is_deleted;
            private String created_at;
            private String part_number;
            private String brand_id;
            private String colors;
            private String weight;
            private String height;
            private String length;
            private String tag_hot;
            private String tag_new;
            private Object unit;
            private String discount;
            private Object video;
            private Object vin;
            private String subcategory_name;
            private String category_name;
            private String brand_name;
            private String make_names;
            private String model_names;
            private int default_quantity;
            private int reviews_comments;
            private int reviews_ratings;
            private int reviews_ratings_total_count;
            /**
             * rating_1 : 0
             * rating_2 : 0
             * rating_3 : 100
             * rating_4 : 0
             * rating_5 : 0
             */

            private ReviewsRatingsPercentageBean reviews_ratings_percentage;
            /**
             * id : 334
             * product_id : 1
             * image_default : http://dciinternal.in/dev01/uploads/images/img_x500_6087ec7369432.jpg
             * image_big : http://dciinternal.in/dev01/uploads/images/img_1920x_6087ec7393c7f.jpg
             * image_small : http://dciinternal.in/dev01/uploads/images/img_x300_6087ec73bdcac.jpg
             * image_order : 1
             * storage : local
             */

            private List<ImagesBean> images;
            /**
             * rating : 3
             * review : test
             * date : 06/05/2021
             * added_by : testc testc
             */

            private List<ReviewsDetailsBean> reviews_details;
            /**
             * quantity : 2
             * price : 250
             */

            private List<WholesalerPriceBean> wholesaler_price;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getSubcategory_id() {
                return subcategory_id;
            }

            public void setSubcategory_id(String subcategory_id) {
                this.subcategory_id = subcategory_id;
            }

            public String getThird_category_id() {
                return third_category_id;
            }

            public void setThird_category_id(String third_category_id) {
                this.third_category_id = third_category_id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getCurrency() {
                return currency;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Object getProduct_condition() {
                return product_condition;
            }

            public void setProduct_condition(Object product_condition) {
                this.product_condition = product_condition;
            }

            public Object getCountry_id() {
                return country_id;
            }

            public void setCountry_id(Object country_id) {
                this.country_id = country_id;
            }

            public Object getState_id() {
                return state_id;
            }

            public void setState_id(Object state_id) {
                this.state_id = state_id;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getZip_code() {
                return zip_code;
            }

            public void setZip_code(Object zip_code) {
                this.zip_code = zip_code;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getIs_promoted() {
                return is_promoted;
            }

            public void setIs_promoted(String is_promoted) {
                this.is_promoted = is_promoted;
            }

            public Object getPromote_start_date() {
                return promote_start_date;
            }

            public void setPromote_start_date(Object promote_start_date) {
                this.promote_start_date = promote_start_date;
            }

            public Object getPromote_end_date() {
                return promote_end_date;
            }

            public void setPromote_end_date(Object promote_end_date) {
                this.promote_end_date = promote_end_date;
            }

            public Object getPromote_plan() {
                return promote_plan;
            }

            public void setPromote_plan(Object promote_plan) {
                this.promote_plan = promote_plan;
            }

            public Object getPromote_day() {
                return promote_day;
            }

            public void setPromote_day(Object promote_day) {
                this.promote_day = promote_day;
            }

            public String getVisibility() {
                return visibility;
            }

            public void setVisibility(String visibility) {
                this.visibility = visibility;
            }

            public String getRating() {
                return rating;
            }

            public void setRating(String rating) {
                this.rating = rating;
            }

            public String getHit() {
                return hit;
            }

            public void setHit(String hit) {
                this.hit = hit;
            }

            public Object getExternal_link() {
                return external_link;
            }

            public void setExternal_link(Object external_link) {
                this.external_link = external_link;
            }

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }

            public String getShipping_time() {
                return shipping_time;
            }

            public void setShipping_time(String shipping_time) {
                this.shipping_time = shipping_time;
            }

            public Object getShipping_cost_type() {
                return shipping_cost_type;
            }

            public void setShipping_cost_type(Object shipping_cost_type) {
                this.shipping_cost_type = shipping_cost_type;
            }

            public String getShipping_cost() {
                return shipping_cost;
            }

            public void setShipping_cost(String shipping_cost) {
                this.shipping_cost = shipping_cost;
            }

            public String getIs_sold() {
                return is_sold;
            }

            public void setIs_sold(String is_sold) {
                this.is_sold = is_sold;
            }

            public String getIs_deleted() {
                return is_deleted;
            }

            public void setIs_deleted(String is_deleted) {
                this.is_deleted = is_deleted;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getPart_number() {
                return part_number;
            }

            public void setPart_number(String part_number) {
                this.part_number = part_number;
            }

            public String getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(String brand_id) {
                this.brand_id = brand_id;
            }

            public String getColors() {
                return colors;
            }

            public void setColors(String colors) {
                this.colors = colors;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public String getLength() {
                return length;
            }

            public void setLength(String length) {
                this.length = length;
            }

            public String getTag_hot() {
                return tag_hot;
            }

            public void setTag_hot(String tag_hot) {
                this.tag_hot = tag_hot;
            }

            public String getTag_new() {
                return tag_new;
            }

            public void setTag_new(String tag_new) {
                this.tag_new = tag_new;
            }

            public Object getUnit() {
                return unit;
            }

            public void setUnit(Object unit) {
                this.unit = unit;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public Object getVideo() {
                return video;
            }

            public void setVideo(Object video) {
                this.video = video;
            }

            public Object getVin() {
                return vin;
            }

            public void setVin(Object vin) {
                this.vin = vin;
            }

            public String getSubcategory_name() {
                return subcategory_name;
            }

            public void setSubcategory_name(String subcategory_name) {
                this.subcategory_name = subcategory_name;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getBrand_name() {
                return brand_name;
            }

            public void setBrand_name(String brand_name) {
                this.brand_name = brand_name;
            }

            public String getMake_names() {
                return make_names;
            }

            public void setMake_names(String make_names) {
                this.make_names = make_names;
            }

            public String getModel_names() {
                return model_names;
            }

            public void setModel_names(String model_names) {
                this.model_names = model_names;
            }

            public int getDefault_quantity() {
                return default_quantity;
            }

            public void setDefault_quantity(int default_quantity) {
                this.default_quantity = default_quantity;
            }

            public int getReviews_comments() {
                return reviews_comments;
            }

            public void setReviews_comments(int reviews_comments) {
                this.reviews_comments = reviews_comments;
            }

            public int getReviews_ratings() {
                return reviews_ratings;
            }

            public void setReviews_ratings(int reviews_ratings) {
                this.reviews_ratings = reviews_ratings;
            }

            public int getReviews_ratings_total_count() {
                return reviews_ratings_total_count;
            }

            public void setReviews_ratings_total_count(int reviews_ratings_total_count) {
                this.reviews_ratings_total_count = reviews_ratings_total_count;
            }

            public ReviewsRatingsPercentageBean getReviews_ratings_percentage() {
                return reviews_ratings_percentage;
            }

            public void setReviews_ratings_percentage(ReviewsRatingsPercentageBean reviews_ratings_percentage) {
                this.reviews_ratings_percentage = reviews_ratings_percentage;
            }

            public List<ImagesBean> getImages() {
                return images;
            }

            public void setImages(List<ImagesBean> images) {
                this.images = images;
            }

            public List<ReviewsDetailsBean> getReviews_details() {
                return reviews_details;
            }

            public void setReviews_details(List<ReviewsDetailsBean> reviews_details) {
                this.reviews_details = reviews_details;
            }

            public List<WholesalerPriceBean> getWholesaler_price() {
                return wholesaler_price;
            }

            public void setWholesaler_price(List<WholesalerPriceBean> wholesaler_price) {
                this.wholesaler_price = wholesaler_price;
            }

            public static class ReviewsRatingsPercentageBean {
                private int rating_1;
                private int rating_2;
                private int rating_3;
                private int rating_4;
                private int rating_5;

                public int getRating_1() {
                    return rating_1;
                }

                public void setRating_1(int rating_1) {
                    this.rating_1 = rating_1;
                }

                public int getRating_2() {
                    return rating_2;
                }

                public void setRating_2(int rating_2) {
                    this.rating_2 = rating_2;
                }

                public int getRating_3() {
                    return rating_3;
                }

                public void setRating_3(int rating_3) {
                    this.rating_3 = rating_3;
                }

                public int getRating_4() {
                    return rating_4;
                }

                public void setRating_4(int rating_4) {
                    this.rating_4 = rating_4;
                }

                public int getRating_5() {
                    return rating_5;
                }

                public void setRating_5(int rating_5) {
                    this.rating_5 = rating_5;
                }
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

            public static class ReviewsDetailsBean {
                private String rating;
                private String review;
                private String date;
                private String added_by;

                public String getRating() {
                    return rating;
                }

                public void setRating(String rating) {
                    this.rating = rating;
                }

                public String getReview() {
                    return review;
                }

                public void setReview(String review) {
                    this.review = review;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getAdded_by() {
                    return added_by;
                }

                public void setAdded_by(String added_by) {
                    this.added_by = added_by;
                }
            }

            public static class WholesalerPriceBean {
                private String quantity;
                private String price;

                public String getQuantity() {
                    return quantity;
                }

                public void setQuantity(String quantity) {
                    this.quantity = quantity;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }
            }
        }
    }
}