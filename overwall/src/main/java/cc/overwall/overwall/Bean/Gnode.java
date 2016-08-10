package cc.overwall.overwall.Bean;

import java.util.List;

/**
 * Created by xps on 2016/7/19.
 */

public class Gnode {

    /**
     * ret : 1
     * msg : ok
    */

    private int ret;
    private String msg;
    /**
     * id : 1
     * name : 日本01号 - ShadowSocks
     * type : 1
     * server : 153.126.183.41
     * method : rc4-md5
     * info : 日本樱花石狩
     * status : 服务器等级50
     * sort : 0
     * custom_method : 1
     * traffic_rate : 1
     * node_class : 50
     * node_speedlimit : 0.00
     * node_connector : 0
     * node_bandwidth : 1090310902945
     * node_bandwidth_limit : 10995116277760
     * bandwidthlimit_resetday : 1
     * node_heartbeat : 1468912024
     * node_ip : 153.126.183.41
     * node_group : 0
     * custom_rss : 1
     */

    private List<DataBean> data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private String name;
        private int type;
        private String server;
        private String method;
        private String info;
        private String status;
        private int sort;
        private int custom_method;
        private int traffic_rate;
        private int node_class;
        private String node_speedlimit;
        private int node_connector;
        private long node_bandwidth;
        private long node_bandwidth_limit;
        private int bandwidthlimit_resetday;
        private int node_heartbeat;
        private String node_ip;
        private int node_group;
        private int custom_rss;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getServer() {
            return server;
        }

        public void setServer(String server) {
            this.server = server;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getCustom_method() {
            return custom_method;
        }

        public void setCustom_method(int custom_method) {
            this.custom_method = custom_method;
        }

        public int getTraffic_rate() {
            return traffic_rate;
        }

        public void setTraffic_rate(int traffic_rate) {
            this.traffic_rate = traffic_rate;
        }

        public int getNode_class() {
            return node_class;
        }

        public void setNode_class(int node_class) {
            this.node_class = node_class;
        }

        public String getNode_speedlimit() {
            return node_speedlimit;
        }

        public void setNode_speedlimit(String node_speedlimit) {
            this.node_speedlimit = node_speedlimit;
        }

        public int getNode_connector() {
            return node_connector;
        }

        public void setNode_connector(int node_connector) {
            this.node_connector = node_connector;
        }

        public long getNode_bandwidth() {
            return node_bandwidth;
        }

        public void setNode_bandwidth(long node_bandwidth) {
            this.node_bandwidth = node_bandwidth;
        }

        public long getNode_bandwidth_limit() {
            return node_bandwidth_limit;
        }

        public void setNode_bandwidth_limit(long node_bandwidth_limit) {
            this.node_bandwidth_limit = node_bandwidth_limit;
        }

        public int getBandwidthlimit_resetday() {
            return bandwidthlimit_resetday;
        }

        public void setBandwidthlimit_resetday(int bandwidthlimit_resetday) {
            this.bandwidthlimit_resetday = bandwidthlimit_resetday;
        }

        public int getNode_heartbeat() {
            return node_heartbeat;
        }

        public void setNode_heartbeat(int node_heartbeat) {
            this.node_heartbeat = node_heartbeat;
        }

        public String getNode_ip() {
            return node_ip;
        }

        public void setNode_ip(String node_ip) {
            this.node_ip = node_ip;
        }

        public int getNode_group() {
            return node_group;
        }

        public void setNode_group(int node_group) {
            this.node_group = node_group;
        }

        public int getCustom_rss() {
            return custom_rss;
        }

        public void setCustom_rss(int custom_rss) {
            this.custom_rss = custom_rss;
        }
    }
}

