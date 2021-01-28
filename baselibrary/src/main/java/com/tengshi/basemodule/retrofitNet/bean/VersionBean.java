package com.tengshi.basemodule.retrofitNet.bean;

/**
 * 作者 : Adam on 2018/12/7.
 * 邮箱 : itgaojian@163.com
 * 描述 : 版本
 */
public class VersionBean {

    /**
     * msg : 查询完成
     * state : 200
     * id : null
     * data : {"version":"0.0.1004","name":"集宁追溯","strategy":"2","info":"1.测试 2.还是测试 3.继续测试","url":"http://124.67.110.247:8082/syzf_app/uploadFiles/apps/225/01/1bb91c3f1552484bb9d53729ea5b106c.apk"}
     * list : null
     */

    public String msg;
    public String state;
    public Object id;
    public VersionDataBean data;
    public Object list;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public VersionDataBean getData() {
        return data;
    }

    public void setData(VersionDataBean data) {
        this.data = data;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }

    public static class VersionDataBean {
        /**
         * version : 0.0.1004
         * name : 集宁追溯
         * strategy : 2
         * info : 1.测试 2.还是测试 3.继续测试
         * url : http://124.67.110.247:8082/syzf_app/uploadFiles/apps/225/01/1bb91c3f1552484bb9d53729ea5b106c.apk
         */

        public String version;  //版本号
        public String versioncode;  //版本号
        public String name;     //名称
        public String strategy; //更新方式
        public String info;     //升级信息
        public String url;      //下载路径

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getVersioncode() {
            return versioncode;
        }

        public void setVersioncode(String versioncode) {
            this.versioncode = versioncode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStrategy() {
            return strategy;
        }

        public void setStrategy(String strategy) {
            this.strategy = strategy;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "VersionDataBean{" +
                    "version='" + version + '\'' +
                    ", versioncode='" + versioncode + '\'' +
                    ", name='" + name + '\'' +
                    ", strategy='" + strategy + '\'' +
                    ", info='" + info + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "VersionbBean{" +
                "msg='" + msg + '\'' +
                ", state='" + state + '\'' +
                ", id=" + id +
                ", data=" + data +
                ", list=" + list +
                '}';
    }
}
