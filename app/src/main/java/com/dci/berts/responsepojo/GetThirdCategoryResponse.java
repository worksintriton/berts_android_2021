package com.dci.berts.responsepojo;

import java.util.List;

public class GetThirdCategoryResponse {


    /**
     * status : Success
     * code : 200
     * message : Categories list successful
     * data : {"categories":[{"id":"77","slug":"fender-liners","parent_id":"24","top_parent_id":"1","category_level":"3","parent_slug":"fender-parts","top_parent_slug":"body-parts-trim","description":"Fender Liners","keywords":"Fender Liners","category_order":"1","homepage_order":"1","visibility":"1","show_on_homepage":"0","storage":"local","image_1":"http://dciinternal.in/dev01/uploads/category/category_640-360_60cc6d79aa078.jpg","image_2":"http://dciinternal.in/dev01/uploads/category/category_440-541_60cc6d79e78ff.jpg","created_at":"2021-06-18 09:55:06","lang_id":"1","name":"Fender Liners"},{"id":"78","slug":"fenders","parent_id":"24","top_parent_id":"1","category_level":"3","parent_slug":"fender-parts","top_parent_slug":"body-parts-trim","description":"Fenders","keywords":"Fenders","category_order":"1","homepage_order":"1","visibility":"1","show_on_homepage":"0","storage":"local","image_1":"http://dciinternal.in/dev01/uploads/category/category_400-225_60cc6dd07561d.jpg","image_2":"http://dciinternal.in/dev01/uploads/category/category_440-541_60cc6dd086545.jpg","created_at":"2021-06-18 09:56:32","lang_id":"1","name":"Fenders"}]}
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
         * id : 77
         * slug : fender-liners
         * parent_id : 24
         * top_parent_id : 1
         * category_level : 3
         * parent_slug : fender-parts
         * top_parent_slug : body-parts-trim
         * description : Fender Liners
         * keywords : Fender Liners
         * category_order : 1
         * homepage_order : 1
         * visibility : 1
         * show_on_homepage : 0
         * storage : local
         * image_1 : http://dciinternal.in/dev01/uploads/category/category_640-360_60cc6d79aa078.jpg
         * image_2 : http://dciinternal.in/dev01/uploads/category/category_440-541_60cc6d79e78ff.jpg
         * created_at : 2021-06-18 09:55:06
         * lang_id : 1
         * name : Fender Liners
         */

        private List<CategoriesBean> categories;

        public List<CategoriesBean> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoriesBean> categories) {
            this.categories = categories;
        }

        public static class CategoriesBean {
            private String id;
            private String slug;
            private String parent_id;
            private String top_parent_id;
            private String category_level;
            private String parent_slug;
            private String top_parent_slug;
            private String description;
            private String keywords;
            private String category_order;
            private String homepage_order;
            private String visibility;
            private String show_on_homepage;
            private String storage;
            private String image_1;
            private String image_2;
            private String created_at;
            private String lang_id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getTop_parent_id() {
                return top_parent_id;
            }

            public void setTop_parent_id(String top_parent_id) {
                this.top_parent_id = top_parent_id;
            }

            public String getCategory_level() {
                return category_level;
            }

            public void setCategory_level(String category_level) {
                this.category_level = category_level;
            }

            public String getParent_slug() {
                return parent_slug;
            }

            public void setParent_slug(String parent_slug) {
                this.parent_slug = parent_slug;
            }

            public String getTop_parent_slug() {
                return top_parent_slug;
            }

            public void setTop_parent_slug(String top_parent_slug) {
                this.top_parent_slug = top_parent_slug;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public String getCategory_order() {
                return category_order;
            }

            public void setCategory_order(String category_order) {
                this.category_order = category_order;
            }

            public String getHomepage_order() {
                return homepage_order;
            }

            public void setHomepage_order(String homepage_order) {
                this.homepage_order = homepage_order;
            }

            public String getVisibility() {
                return visibility;
            }

            public void setVisibility(String visibility) {
                this.visibility = visibility;
            }

            public String getShow_on_homepage() {
                return show_on_homepage;
            }

            public void setShow_on_homepage(String show_on_homepage) {
                this.show_on_homepage = show_on_homepage;
            }

            public String getStorage() {
                return storage;
            }

            public void setStorage(String storage) {
                this.storage = storage;
            }

            public String getImage_1() {
                return image_1;
            }

            public void setImage_1(String image_1) {
                this.image_1 = image_1;
            }

            public String getImage_2() {
                return image_2;
            }

            public void setImage_2(String image_2) {
                this.image_2 = image_2;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getLang_id() {
                return lang_id;
            }

            public void setLang_id(String lang_id) {
                this.lang_id = lang_id;
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
