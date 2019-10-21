package com.mredrock.cyxbs.freshman.model.bean;

import java.util.List;

/**
 * Created by yyfbe on 2019-08-04
 */
public class DormitoryCanteenBean {

    /**
     * code : 200
     * info : ok
     * text : [{"title":"宿舍","message":[{"name":"明理苑","detail":".....","photos":["...","..."]},{"name":"宁静苑","detail":".....","photos":["...","..."]}]},{"title":"食堂","message":[{"name":"红高粱","detail":"....","photos":["...","..."]},{"name":"中心食堂","detail":"....","photos":["...","..."]}]}]
     */

    private List<TextBean> text;

    public List<TextBean> getText() {
        return text;
    }

    public void setText(List<TextBean> text) {
        this.text = text;
    }

    public static class TextBean {
        /**
         * title : 宿舍
         * message : [{"name":"明理苑","detail":".....","photos":["...","..."]},{"name":"宁静苑","detail":".....","photos":["...","..."]}]
         */

        private String title;
        private List<MessageBean> message;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<MessageBean> getMessage() {
            return message;
        }

        public void setMessage(List<MessageBean> message) {
            this.message = message;
        }

        public static class MessageBean {
            /**
             * name : 明理苑
             * detail : .....
             * photos : ["...","..."]
             */

            private String name;
            private String detail;
            private List<String> photos;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public List<String> getPhotos() {
                return photos;
            }

            public void setPhotos(List<String> photos) {
                this.photos = photos;
            }
        }
    }
}
