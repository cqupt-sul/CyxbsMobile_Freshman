package com.mredrock.cyxbs.freshman.model.bean;

import java.util.List;

/**
 * Created by yyfbe on 2019-08-04
 */
public class ExpressBean {

    /**
     * code : 200
     * info : ok
     * text : [{"name":"顺丰","message":[{"title":"樱花园","detail":".....................","Photo":"...."}]},{"name":"圆通","message":[{"title":"樱花园","detail":"..............","Photo":"...."},{"title":"樱花园","detail":"........","Photo":"...."}]}]
     */

    private String info;
    private List<TextBean> text;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<TextBean> getText() {
        return text;
    }

    public void setText(List<TextBean> text) {
        this.text = text;
    }

    public static class TextBean {
        /**
         * name : 顺丰
         * message : [{"title":"樱花园","detail":".....................","Photo":"...."}]
         */

        private String name;
        private List<MessageBean> message;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<MessageBean> getMessage() {
            return message;
        }

        public void setMessage(List<MessageBean> message) {
            this.message = message;
        }

        public static class MessageBean {
            /**
             * title : 樱花园
             * detail : .....................
             * Photo : ....
             */

            private String title;
            private String detail;
            private String photo;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }
        }
    }
}
