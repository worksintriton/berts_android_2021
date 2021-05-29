package com.triton.bertsproject.responsepojo;

import java.util.List;

public class SearchProductsResponse {


    /**
     * status : Success
     * code : 200
     * message : Product list successful
     * data : {"page":1,"per_page":15,"total_count":1,"total_pages":1,"prdoucts":[{"id":"3","title":"Alloy Wheel ","slug":"alloy-wheel-3","category_id":"32","subcategory_id":"91","third_category_id":null,"price":"10000","currency":"USD","description":"<p>Rim / Alloy Wheels for HONDA, MARUTI, TATA, HYUNDAI, MAHINDRA, CHEVROLET, RENAULT - W1D051-000M00 - UNO MINDA<\/p>\n\n<ul>\n\t<li>100% Leak, Heat, and X-Ray Tested Designs.<\/li>\n\t<li>IS-9436/38 safety standards followed during manufacturing. Tested and Approved by ICAT.<\/li>\n\t<li>Excellent compatibility with radial, regular and tubeless tires.<\/li>\n\t<li>Safe and Elegant Design with a stunning look. Lighter than regular steel wheels.<\/li>\n\t<li>Improves Braking cooling and reduces brake fading.<\/li>\n\t<li>Improves fuel efficiency.<\/li>\n\t<li>Best functionality and optimum fit, Durable, and Reliable.<\/li>\n<\/ul>\n","product_condition":null,"country_id":null,"state_id":null,"address":null,"zip_code":null,"user_id":"1","status":"1","is_promoted":"0","promote_start_date":"2021-04-27 13:02:16","promote_end_date":"2021-04-27 13:02:16","promote_plan":"none","promote_day":"0","visibility":"1","rating":"0","hit":"8","external_link":null,"quantity":"0","shipping_time":"2_3_business_days","shipping_cost_type":null,"shipping_cost":"0","is_sold":"0","is_deleted":"0","created_at":"2021-04-27 13:02:16","part_number":"123456789","brand_id":"2","colors":"black","weight":"10","height":"5","length":"5","tag_hot":"0","tag_new":"0","unit":"100","discount":"0","video":null,"vin":"1M8GDM9AXKP042788","subcategory_name":"Exteriors","category_name":"Transmission & Drivetrain","images":[{"id":"341","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608809f3ad051.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608809f3cdaa7.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608809f3ed906.jpg","image_order":"1","storage":"local"},{"id":"342","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608809ff4a6ec.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608809ff73764.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608809ff96854.jpg","image_order":"2","storage":"local"},{"id":"343","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_60880a0ae41dd.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_60880a0b2104a.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_60880a0b4c541.jpg","image_order":"3","storage":"local"}],"reviews_comments":0,"reviews_ratings":0,"best_fit":0,"wishlist":0}],"page_count":1}
     */

    private String status;
    private int code;
    private String message;
    /**
     * page : 1
     * per_page : 15
     * total_count : 1
     * total_pages : 1
     * prdoucts : [{"id":"3","title":"Alloy Wheel ","slug":"alloy-wheel-3","category_id":"32","subcategory_id":"91","third_category_id":null,"price":"10000","currency":"USD","description":"<p>Rim / Alloy Wheels for HONDA, MARUTI, TATA, HYUNDAI, MAHINDRA, CHEVROLET, RENAULT - W1D051-000M00 - UNO MINDA<\/p>\n\n<ul>\n\t<li>100% Leak, Heat, and X-Ray Tested Designs.<\/li>\n\t<li>IS-9436/38 safety standards followed during manufacturing. Tested and Approved by ICAT.<\/li>\n\t<li>Excellent compatibility with radial, regular and tubeless tires.<\/li>\n\t<li>Safe and Elegant Design with a stunning look. Lighter than regular steel wheels.<\/li>\n\t<li>Improves Braking cooling and reduces brake fading.<\/li>\n\t<li>Improves fuel efficiency.<\/li>\n\t<li>Best functionality and optimum fit, Durable, and Reliable.<\/li>\n<\/ul>\n","product_condition":null,"country_id":null,"state_id":null,"address":null,"zip_code":null,"user_id":"1","status":"1","is_promoted":"0","promote_start_date":"2021-04-27 13:02:16","promote_end_date":"2021-04-27 13:02:16","promote_plan":"none","promote_day":"0","visibility":"1","rating":"0","hit":"8","external_link":null,"quantity":"0","shipping_time":"2_3_business_days","shipping_cost_type":null,"shipping_cost":"0","is_sold":"0","is_deleted":"0","created_at":"2021-04-27 13:02:16","part_number":"123456789","brand_id":"2","colors":"black","weight":"10","height":"5","length":"5","tag_hot":"0","tag_new":"0","unit":"100","discount":"0","video":null,"vin":"1M8GDM9AXKP042788","subcategory_name":"Exteriors","category_name":"Transmission & Drivetrain","images":[{"id":"341","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608809f3ad051.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608809f3cdaa7.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608809f3ed906.jpg","image_order":"1","storage":"local"},{"id":"342","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608809ff4a6ec.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608809ff73764.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608809ff96854.jpg","image_order":"2","storage":"local"},{"id":"343","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_60880a0ae41dd.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_60880a0b2104a.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_60880a0b4c541.jpg","image_order":"3","storage":"local"}],"reviews_comments":0,"reviews_ratings":0,"best_fit":0,"wishlist":0}]
     * page_count : 1
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
        private int page;
        private int per_page;
        private int total_count;
        private int total_pages;
        private int page_count;
        /**
         * id : 3
         * title : Alloy Wheel
         * slug : alloy-wheel-3
         * category_id : 32
         * subcategory_id : 91
         * third_category_id : null
         * price : 10000
         * currency : USD
         * description : <p>Rim / Alloy Wheels for HONDA, MARUTI, TATA, HYUNDAI, MAHINDRA, CHEVROLET, RENAULT - W1D051-000M00 - UNO MINDA</p>

         <ul>
         <li>100% Leak, Heat, and X-Ray Tested Designs.</li>
         <li>IS-9436/38 safety standards followed during manufacturing. Tested and Approved by ICAT.</li>
         <li>Excellent compatibility with radial, regular and tubeless tires.</li>
         <li>Safe and Elegant Design with a stunning look. Lighter than regular steel wheels.</li>
         <li>Improves Braking cooling and reduces brake fading.</li>
         <li>Improves fuel efficiency.</li>
         <li>Best functionality and optimum fit, Durable, and Reliable.</li>
         </ul>
         * product_condition : null
         * country_id : null
         * state_id : null
         * address : null
         * zip_code : null
         * user_id : 1
         * status : 1
         * is_promoted : 0
         * promote_start_date : 2021-04-27 13:02:16
         * promote_end_date : 2021-04-27 13:02:16
         * promote_plan : none
         * promote_day : 0
         * visibility : 1
         * rating : 0
         * hit : 8
         * external_link : null
         * quantity : 0
         * shipping_time : 2_3_business_days
         * shipping_cost_type : null
         * shipping_cost : 0
         * is_sold : 0
         * is_deleted : 0
         * created_at : 2021-04-27 13:02:16
         * part_number : 123456789
         * brand_id : 2
         * colors : black
         * weight : 10
         * height : 5
         * length : 5
         * tag_hot : 0
         * tag_new : 0
         * unit : 100
         * discount : 0
         * video : null
         * vin : 1M8GDM9AXKP042788
         * subcategory_name : Exteriors
         * category_name : Transmission & Drivetrain
         * images : [{"id":"341","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608809f3ad051.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608809f3cdaa7.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608809f3ed906.jpg","image_order":"1","storage":"local"},{"id":"342","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_608809ff4a6ec.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_608809ff73764.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_608809ff96854.jpg","image_order":"2","storage":"local"},{"id":"343","product_id":"3","image_default":"http://dciinternal.in/dev01/uploads/images/img_x500_60880a0ae41dd.jpg","image_big":"http://dciinternal.in/dev01/uploads/images/img_1920x_60880a0b2104a.jpg","image_small":"http://dciinternal.in/dev01/uploads/images/img_x300_60880a0b4c541.jpg","image_order":"3","storage":"local"}]
         * reviews_comments : 0
         * reviews_ratings : 0
         * best_fit : 0
         * wishlist : 0
         */

        private List<PrdouctsBean> prdoucts;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getTotal_count() {
            return total_count;
        }

        public void setTotal_count(int total_count) {
            this.total_count = total_count;
        }

        public int getTotal_pages() {
            return total_pages;
        }

        public void setTotal_pages(int total_pages) {
            this.total_pages = total_pages;
        }

        public int getPage_count() {
            return page_count;
        }

        public void setPage_count(int page_count) {
            this.page_count = page_count;
        }

        public List<PrdouctsBean> getPrdoucts() {
            return prdoucts;
        }

        public void setPrdoucts(List<PrdouctsBean> prdoucts) {
            this.prdoucts = prdoucts;
        }

        public static class PrdouctsBean {
            private String id;
            private String title;
            private String slug;
            private String category_id;
            private String subcategory_id;
            private Object third_category_id;
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
            private String promote_start_date;
            private String promote_end_date;
            private String promote_plan;
            private String promote_day;
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
            private String unit;
            private String discount;
            private Object video;
            private String vin;
            private String subcategory_name;
            private String category_name;
            private int reviews_comments;
            private int reviews_ratings;
            private int best_fit;
            private int wishlist;
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

            public Object getThird_category_id() {
                return third_category_id;
            }

            public void setThird_category_id(Object third_category_id) {
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

            public String getPromote_start_date() {
                return promote_start_date;
            }

            public void setPromote_start_date(String promote_start_date) {
                this.promote_start_date = promote_start_date;
            }

            public String getPromote_end_date() {
                return promote_end_date;
            }

            public void setPromote_end_date(String promote_end_date) {
                this.promote_end_date = promote_end_date;
            }

            public String getPromote_plan() {
                return promote_plan;
            }

            public void setPromote_plan(String promote_plan) {
                this.promote_plan = promote_plan;
            }

            public String getPromote_day() {
                return promote_day;
            }

            public void setPromote_day(String promote_day) {
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

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
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

            public String getVin() {
                return vin;
            }

            public void setVin(String vin) {
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

            public int getBest_fit() {
                return best_fit;
            }

            public void setBest_fit(int best_fit) {
                this.best_fit = best_fit;
            }

            public int getWishlist() {
                return wishlist;
            }

            public void setWishlist(int wishlist) {
                this.wishlist = wishlist;
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
